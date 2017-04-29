package cn.com.mapper;

import java.util.ArrayList;
import java.util.Map;

import cn.com.domain.UserNews;

public interface UserNewsMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserNews record);

    int insertSelective(UserNews record);

    UserNews selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserNews record);

    int updateByPrimaryKey(UserNews record);
    
    
    
    int selectNumByUserid(String userid);
    
    int deleteByUseridTimeOut(Map<String, String> map);
    
    int updateAllNotReadByUser(String userId);
    
    ArrayList<UserNews> selectPublishByUserid(String userId);
    ArrayList<UserNews> selectBuyByUserid(String userId);
    
}