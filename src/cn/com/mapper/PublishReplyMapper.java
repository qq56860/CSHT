package cn.com.mapper;

import cn.com.domain.PublishReply;

public interface PublishReplyMapper {
    int deleteByPrimaryKey(String id);

    int insert(PublishReply record);

    int insertSelective(PublishReply record);

    PublishReply selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PublishReply record);

    int updateByPrimaryKey(PublishReply record);
}