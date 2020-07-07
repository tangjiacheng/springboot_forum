package com.tjc.service.impl;

import com.tjc.mapper.UserMapper;
import com.tjc.mapper.UserRoleMapper;
import com.tjc.pojo.User;
import com.tjc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/16 15:22
 * @description: TODO
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public int addUser(User user) {
        user.setCreateTime(new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.addUser(user);
        userRoleMapper.addUserRole(user.getId(), 2); // 2 ==> 用户权限
        return 2;
    }

    @Override
    public User login(String email, String password) {
        User user = userMapper.findUserByEmail(email);
        if (user == null)
            return null;
        else if (!user.getPassword().equals(password))
            return null;
        else return user;
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public List<User> findHotUserByReply() {
        return userMapper.findHotUserByReply();
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public void changePassword(String password, Integer id) {
        userMapper.updatePassword(passwordEncoder.encode(password), id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    @Override
    public void updateUserAvatar(Integer id, String filePath) {
        userMapper.updateUserAvatar(id, filePath);
    }

    @Override
    public void decreaseCoin(Integer userId, Integer coin) {
        User user = userMapper.findUserById(userId);
        Integer newCoin = user.getCoin() - coin;
        userMapper.updateCoin(userId, newCoin);
    }

    @Override
    public void increaseCoin(Integer userId, Integer coin) {
        User user = userMapper.findUserById(userId);
        Integer newCoin = user.getCoin() + coin;
        userMapper.updateCoin(userId, newCoin);
    }

    @Override
    public List<User> findFanByUserId(Integer id) {
        return userMapper.findFanByUserId(id);
    }

    @Override
    public List<User> findFollowByUserId(Integer id) {
        return userMapper.findFollowByUserId(id);
    }

}
