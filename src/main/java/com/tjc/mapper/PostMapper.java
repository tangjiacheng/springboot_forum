package com.tjc.mapper;

import com.tjc.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/16 21:44
 * @description: TODO
 */
@Mapper
@Repository
public interface PostMapper {
    void addPost(Post post);

    List<Post> findAllPostByTime();

    List<Post> findUpPostByTime();

    List<Post> findPostByUserId(Integer userId);

    List<Post> findAllPostByType(Integer type);

    Post findPostById(Integer id);

    List<Post> findHotPostByCommentNum();

    void updatePostReply(Post post);

    List<Post> findAllPostByReply();

    void deletePostById(Integer id);

    void setPostUp(Integer id);

    void unSetPostUp(Integer id);

    void setPostGood(Integer id);

    void unSetPostGood(Integer id);

    void adoptComment(@Param("postId") Integer postId, @Param("commentId") Integer commentId);

    void removeAdoptComment(Integer id);
}
