package cn.com.mapper;

import java.util.ArrayList;

import cn.com.domain.PublishReply;

public interface PublishReplyMapper {
    int deleteByPrimaryKey(String id);

    int insert(PublishReply record);

    int insertSelective(PublishReply record);

    PublishReply selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PublishReply record);

    int updateByPrimaryKey(PublishReply record);
    
    ArrayList<PublishReply> selectByCommentId(String commentId);
    
}