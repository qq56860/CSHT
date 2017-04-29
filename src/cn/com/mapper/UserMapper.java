package cn.com.mapper;

import java.util.Map;

import cn.com.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectByAccount(String id);
    
    User selectUser(Map<String, String> map);
    
    int updateByEmailOrPhone(Map<String, String> map);
    
    
}