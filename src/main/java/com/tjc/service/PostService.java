package com.tjc.service;

import com.tjc.pojo.Post;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/16 21:43
 * @description: TODO
 */
public interface PostService {
    void addPost(Post post);

    List<Post> findAllPostByTime();

    List<Post> findUpPostByTime();

    List<Post> findPostByUserId(Integer userId);

    List<Post> findAllPostByType(Integer type);

    Post findPostById(Integer id);

    List<Post> findHotPostByCommentNum();

    List<Post> findAllPostByReply();

    void deletePost(Integer id);

    void setPostUp(Integer id);

    void unSetPostUp(Integer id);

    void setPostGood(Integer id);

    void unSetPostGood(Integer id);

    void removeAdoptComment(Integer id);
}
