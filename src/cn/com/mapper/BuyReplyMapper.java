package cn.com.mapper;

import cn.com.domain.BuyReply;

public interface BuyReplyMapper {
    int deleteByPrimaryKey(String id);

    int insert(BuyReply record);

    int insertSelective(BuyReply record);

    BuyReply selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BuyReply record);

    int updateByPrimaryKey(BuyReply record);
}