package com.tjc.service;

import com.tjc.pojo.Comment;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/18 14:55
 * @description: TODO
 */
public interface CommentService {
    void addComment(Comment comment);

    List<Comment> findCommentByPostId(Integer postId);

    List<Comment> findCommentByUserId(Integer userId);


    void adoptComment(Integer postId, Integer commentId);

    Comment findCommentById(Integer id);

    void deleteComment(Integer id);
}
