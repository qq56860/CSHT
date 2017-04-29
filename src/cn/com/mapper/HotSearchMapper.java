package cn.com.mapper;

import java.util.ArrayList;

import cn.com.domain.HotSearch;

public interface HotSearchMapper {
    int deleteByPrimaryKey(String id);

    int insert(HotSearch record);

    int insertSelective(HotSearch record);

    HotSearch selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HotSearch record);

    int updateByPrimaryKey(HotSearch record);
    
    HotSearch selectByText(String text);
    
    ArrayList<HotSearch> selectByKey5(String key);
    
}