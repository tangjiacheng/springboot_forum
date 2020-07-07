package com.tjc.mapper;

import com.tjc.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/16 15:17
 * @description: TODO
 */
@Mapper
@Repository("userMapper")
public interface UserMapper {

    int addUser(User user);

    User findUserByEmail(String email);

    User findUserById(Integer id);

    void updateUser(User user);

    void updateUserReply(User user);

    List<User> findHotUserByReply();

    User findUserByUsername(String username);

    void updatePassword(@Param("password") String password, @Param("id") Integer id);

    void updateUserAvatar(@Param("id") Integer id, @Param("filePath") String filePath);

    void updateCoin(Integer id, Integer newCoin);

    List<User> findFanByUserId(Integer id);

    List<User> findFollowByUserId(Integer id);
}
