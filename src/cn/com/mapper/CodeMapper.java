package cn.com.mapper;

import java.util.Map;

import cn.com.domain.Code;

public interface CodeMapper {
    int deleteByPrimaryKey(String id);

    int insert(Code record);

    int insertSelective(Code record);

    Code selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Code record);

    int updateByPrimaryKey(Code record);
    
    Code selectByPhoneAndType(Map<String, String> map);
    
}