package com.tjc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: TJC
 * @Date: 2020/6/18 14:52
 * @description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private Integer id;
    private Integer userId;
    private Integer postId;
    private String content;
    private Date createTime;
    private Boolean isAdopt;
    private Integer parent;

    private User user;
    private Post post;
}
