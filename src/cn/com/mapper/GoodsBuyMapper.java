package cn.com.mapper;

import cn.com.domain.GoodsBuy;

public interface GoodsBuyMapper {
    int deleteByPrimaryKey(String id);

    int insert(GoodsBuy record);

    int insertSelective(GoodsBuy record);

    GoodsBuy selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsBuy record);

    int updateByPrimaryKey(GoodsBuy record);
}