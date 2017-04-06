package cn.com.mapper;

import cn.com.domain.PublishComment;

public interface PublishCommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(PublishComment record);

    int insertSelective(PublishComment record);

    PublishComment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PublishComment record);

    int updateByPrimaryKey(PublishComment record);
}