package com.tjc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/16 15:15
 * @description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String email;
    private String username;
    private String password;
    private Integer coin;
    private Integer sex;
    private Date createTime;
    private String location;
    private String introduction;
    private Integer level;
    private String avatar;
    private String qq;
    private String weibo;
    private Integer replyNum;

    private List<Role> roles;
}
