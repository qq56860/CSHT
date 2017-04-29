package cn.com.mapper;

import java.util.ArrayList;
import java.util.Map;

import cn.com.domain.GoodsBuy;
import cn.com.domain.GoodsPublish;

public interface GoodsBuyMapper {
    int deleteByPrimaryKey(String id);

    int insert(GoodsBuy record);

    int insertSelective(GoodsBuy record);

    GoodsBuy selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsBuy record);

    int updateByPrimaryKey(GoodsBuy record);
    
    //-------------------------
    
    int selectAllNum(Map<String, String> map);
    
    ArrayList<GoodsBuy> selectBuyBysearch(Map<String, String> map);
    
    ArrayList<GoodsBuy> selectByUserIdNotBuy(String userId);
    
    
    
}