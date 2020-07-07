package com.tjc.mapper;

import com.tjc.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/20 9:29
 * @description: TODO
 */
@Mapper
@Repository
public interface RoleMapper {

    List<Role> findRoleByUserId(Integer userId);
}
