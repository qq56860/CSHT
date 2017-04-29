package cn.com.mapper;

import java.util.ArrayList;
import java.util.Map;

import cn.com.domain.GoodsPublish;
import cn.com.domain.UserCollection;

public interface UserCollectionMapper {
    int insert(UserCollection record);

    int insertSelective(UserCollection record);
    
    UserCollection selectByGoodsidAndUserid(Map<String, String> map);
    
    int selectByGoodsid(String id);
    
    int deleteByUseridAndGoodsid(Map<String, String> map);
    
    ArrayList<UserCollection> selectByUserid(String id);
    
}