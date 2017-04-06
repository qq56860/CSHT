package cn.com.mapper;

import cn.com.domain.Affiche;

public interface AfficheMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Affiche record);

    int insertSelective(Affiche record);

    Affiche selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Affiche record);

    int updateByPrimaryKey(Affiche record);
}