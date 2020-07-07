package com.tjc.service;


import com.tjc.pojo.User;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/16 15:17
 * @description: TODO
 */
public interface UserService {

    int addUser(User user);

    User login(String email, String password);

    User findUserById(Integer id);

    void updateUser(User user);

    List<User> findHotUserByReply();

    User findUserByUsername(String username);

    void changePassword(String password, Integer id);

    User findUserByEmail(String email);

    void updateUserAvatar(Integer id, String filePath);

    void decreaseCoin(Integer userId, Integer coin);

    void increaseCoin(Integer userId, Integer coin);

    List<User> findFanByUserId(Integer id);

    List<User> findFollowByUserId(Integer id);
}
