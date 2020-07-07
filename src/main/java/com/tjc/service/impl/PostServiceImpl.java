package com.tjc.service.impl;

import com.tjc.mapper.CommentMapper;
import com.tjc.mapper.PostMapper;
import com.tjc.pojo.Post;
import com.tjc.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/16 21:43
 * @description: TODO
 */
@Service("postService")
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void addPost(Post post) {
        post.setCreateTime(new Date());
        postMapper.addPost(post);
        redisTemplate.opsForValue().set("post:view" + post.getId(), 0);
    }

    @Override
    public List<Post> findAllPostByTime() {
        List<Post> posts = postMapper.findAllPostByTime();
        for (Post post : posts) {
            post.setView((String) redisTemplate.opsForValue().get("post:view" + post.getId()));
        }
        return posts;
    }

    @Override
    public List<Post> findUpPostByTime() {
        List<Post> posts = postMapper.findUpPostByTime();
        for (Post post : posts) {
            post.setView((String) redisTemplate.opsForValue().get("post:view" + post.getId()));
        }
        return posts;
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        List<Post> posts = postMapper.findPostByUserId(userId);
        for (Post post : posts) {
            post.setView((String) redisTemplate.opsForValue().get("post:view" + post.getId()));
        }
        return posts;
    }

    @Override
    public List<Post> findAllPostByType(Integer type) {
        List<Post> posts = postMapper.findAllPostByType(type);
        for (Post post : posts) {
            post.setView((String) redisTemplate.opsForValue().get("post:view" + post.getId()));
        }
        return posts;
    }

    @Override
    public Post findPostById(Integer id) {
        Post post = postMapper.findPostById(id);
        post.setView((String) redisTemplate.opsForValue().get("post:view" + post.getId()));
        return post;
    }

    @Override
    public List<Post> findHotPostByCommentNum() {
        List<Post> posts = postMapper.findHotPostByCommentNum();
        return posts;
    }

    @Override
    public List<Post> findAllPostByReply() {
        List<Post> posts = postMapper.findAllPostByReply();
        for (Post post : posts) {
            post.setView((String) redisTemplate.opsForValue().get("post:view" + post.getId()));
        }
        return posts;
    }

    @Override
    public void deletePost(Integer id) {
        redisTemplate.delete("post:view" + id);
        commentMapper.deleteCommentByPostId(id);
        postMapper.deletePostById(id);
    }

    @Override
    public void setPostUp(Integer id) {
        postMapper.setPostUp(id);
    }

    @Override
    public void unSetPostUp(Integer id) {
        postMapper.unSetPostUp(id);
    }

    @Override
    public void setPostGood(Integer id) {
        postMapper.setPostGood(id);
    }

    @Override
    public void unSetPostGood(Integer id) {
        postMapper.unSetPostGood(id);
    }

    @Override
    public void removeAdoptComment(Integer id) {
        postMapper.removeAdoptComment(id);
    }
}
