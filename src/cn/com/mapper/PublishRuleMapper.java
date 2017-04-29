package cn.com.mapper;

import java.util.ArrayList;

import cn.com.domain.PublishRule;

public interface PublishRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PublishRule record);

    int insertSelective(PublishRule record);

    PublishRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PublishRule record);

    int updateByPrimaryKey(PublishRule record);
    
    ArrayList<PublishRule> selectAll();
    
}