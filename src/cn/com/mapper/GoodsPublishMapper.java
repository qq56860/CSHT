package cn.com.mapper;

import java.util.ArrayList;
import java.util.Map;

import cn.com.domain.GoodsPublish;

public interface GoodsPublishMapper {
    int deleteByPrimaryKey(String id);

    int insert(GoodsPublish record);

    int insertSelective(GoodsPublish record);

    GoodsPublish selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsPublish record);

    int updateByPrimaryKey(GoodsPublish record);
    
    
    
    
    //各种搜索
    int selectGoodsSize(Map<String,String> map);
    
    ArrayList<GoodsPublish> selectGoods(Map<String,String> map);
    
    ArrayList<GoodsPublish> selectUserIdNotBuyed(String userId);
    ArrayList<GoodsPublish> selectUserIdIsBuyed(String userId);
    
    int selectSellNumByUser(String id);
    
}