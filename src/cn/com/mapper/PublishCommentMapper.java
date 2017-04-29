package cn.com.mapper;

import java.util.ArrayList;

import cn.com.domain.PublishComment;

public interface PublishCommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(PublishComment record);

    int insertSelective(PublishComment record);

    PublishComment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PublishComment record);

    int updateByPrimaryKey(PublishComment record);
    
    ArrayList<PublishComment> selectByGoodsId(String goodsId);
}