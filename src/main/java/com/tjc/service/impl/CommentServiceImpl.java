package com.tjc.service.impl;

import com.tjc.mapper.CommentMapper;
import com.tjc.mapper.PostMapper;
import com.tjc.mapper.UserMapper;
import com.tjc.pojo.Comment;
import com.tjc.pojo.Post;
import com.tjc.pojo.User;
import com.tjc.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/18 14:55
 * @description: TODO
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addComment(Comment comment) {
        comment.setCreateTime(new Date());
        Post post = postMapper.findPostById(comment.getPostId());
        post.setReply(post.getReply() + 1);
        postMapper.updatePostReply(post);
        User user = userMapper.findUserById(comment.getUserId());
        user.setReplyNum(user.getReplyNum() + 1);
        userMapper.updateUserReply(user);
        commentMapper.addComment(comment);
    }

    @Override
    public List<Comment> findCommentByPostId(Integer postId) {
        return commentMapper.findCommentByPostId(postId);
    }

    @Override
    public List<Comment> findCommentByUserId(Integer userId) {
        return commentMapper.findCommentByUserId(userId);
    }

    @Override
    public void adoptComment(Integer postId, Integer commentId) {
        commentMapper.adoptComment(commentId);
        postMapper.adoptComment(postId, commentId);
    }

    @Override
    public Comment findCommentById(Integer id) {
        return commentMapper.findCommentById(id);
    }

    @Override
    public void deleteComment(Integer id) {
        commentMapper.deleteComment(id);
    }
}
