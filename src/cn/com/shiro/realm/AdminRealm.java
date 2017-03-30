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

public class AdminRealm extends AuthorizingRealm {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		System.out.println("admin授权");
		
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		Set<String> roleNames = new HashSet<String>();
		roleNames.add("admin");
        // 将角色名称提供给info
        authorizationInfo.setRoles(roleNames);
        System.out.println("授权为：admin");
        // 根据用户名查询当前用户权限
//        Set<Permission> permissions = userService.findPermissions(username);
//        permissionNames.add(permission.getPermission());
//        // 将权限名称提供给info
//        authorizationInfo.setStringPermissions(permissionNames);

        return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("admin认证");
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
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password .toCharArray(),getName());
		return info; 
	}
}
