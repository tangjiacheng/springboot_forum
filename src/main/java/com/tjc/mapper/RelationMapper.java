package com.tjc.mapper;

import com.tjc.pojo.Relation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: TJC
 * @Date: 2020/6/21 12:29
 * @description: TODO
 */
@Mapper
@Repository
public interface RelationMapper {
    Relation findRelation(@Param("fanUserId") Integer fanUserId, @Param("followUserId") Integer followUserId);

    void addRelation(@Param("fanUserId") Integer fanUserId, @Param("followUserId") Integer followUserId);

    void removeRelation(@Param("fanUserId") Integer fanUserId, @Param("followUserId") Integer followUserId);
}
