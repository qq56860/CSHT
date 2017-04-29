package cn.com.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.domain.User;
import cn.com.mapper.UserMapper;
import cn.com.shiro.util.UsernamePasswordUsertypeToken;
import cn.com.util.IDGenerator;

public class UserRealm extends AuthorizingRealm {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		System.out.println("user授权");
		
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		Set<String> roleNames = new HashSet<String>();
		roleNames.add("user");
        // 将角色名称提供给info
        authorizationInfo.setRoles(roleNames);
        System.out.println("授权为：user");
        // 根据用户名查询当前用户权限
//        Set<Permission> permissions = userService.findPermissions(username);
//        permissionNames.add(permission.getPermission());
//        // 将权限名称提供给info
//        authorizationInfo.setStringPermissions(permissionNames);

        return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("user认证");
		UsernamePasswordUsertypeToken upToken = (UsernamePasswordUsertypeToken)token;
		String username = upToken.getUsername();
		String password = String.valueOf(upToken.getPassword());
		
		User user = userMapper.selectByAccount(username);
		if(user == null){
			throw new UnknownAccountException();
		}
		if(!password.equals(user.getPassword())){
			throw new IncorrectCredentialsException();
		}
		
		String SaveToken = IDGenerator.getId();
		user.setToken(SaveToken);
		userMapper.updateByPrimaryKey(user);
		
		String pri = username+","+SaveToken;
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(pri, password .toCharArray(),getName());
		return info; 
	}
}
