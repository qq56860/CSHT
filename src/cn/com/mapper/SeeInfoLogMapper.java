package cn.com.mapper;

import cn.com.domain.SeeInfoLog;

public interface SeeInfoLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SeeInfoLog record);

    int insertSelective(SeeInfoLog record);

    SeeInfoLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SeeInfoLog record);

    int updateByPrimaryKey(SeeInfoLog record);
}