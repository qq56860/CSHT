package cn.com.mapper;

import cn.com.domain.GoodsSub;

public interface GoodsSubMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSub record);

    int insertSelective(GoodsSub record);

    GoodsSub selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsSub record);

    int updateByPrimaryKey(GoodsSub record);
}