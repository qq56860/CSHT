package cn.com.mapper;

import java.util.ArrayList;

import cn.com.domain.BuyComment;

public interface BuyCommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(BuyComment record);

    int insertSelective(BuyComment record);

    BuyComment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BuyComment record);

    int updateByPrimaryKey(BuyComment record);
    
    ArrayList<BuyComment> selectByGoodsId(String goodsid);
    
}