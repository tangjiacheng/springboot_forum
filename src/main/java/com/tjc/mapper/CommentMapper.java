package com.tjc.mapper;

import com.tjc.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/18 14:55
 * @description: TODO
 */
@Mapper
@Repository
public interface CommentMapper {
    void addComment(Comment comment);

    List<Comment> findCommentByPostId(Integer postId);

    List<Comment> findCommentByUserId(Integer userId);

    Integer findCommentCountByPostId(Integer postId);

    void deleteCommentByPostId(Integer postId);

    void adoptComment(Integer id);

    Comment findCommentById(Integer id);

    void deleteComment(Integer id);
}
