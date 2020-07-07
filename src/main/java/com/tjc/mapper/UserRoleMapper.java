package com.tjc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: TJC
 * @Date: 2020/6/20 9:30
 * @description: TODO
 */
@Mapper
@Repository
public interface UserRoleMapper {
    void addUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}
