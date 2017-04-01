package cn.com.mapper;

import java.util.ArrayList;

import cn.com.domain.GoodsPublish;

public interface GoodsPublishMapper {
    int deleteByPrimaryKey(String id);

    int insert(GoodsPublish record);

    int insertSelective(GoodsPublish record);

    GoodsPublish selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsPublish record);

    int updateByPrimaryKey(GoodsPublish record);
    
    ArrayList<GoodsPublish> selectByTime();
    
    
}