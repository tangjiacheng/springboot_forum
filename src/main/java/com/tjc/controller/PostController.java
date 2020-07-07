package com.tjc.controller;

import com.tjc.pojo.Comment;
import com.tjc.pojo.Post;
import com.tjc.pojo.User;
import com.tjc.service.CommentService;
import com.tjc.service.PostService;
import com.tjc.service.RedisService;
import com.tjc.service.UserService;
import com.tjc.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Author: TJC
 * @Date: 2020/6/16 13:26
 * @description: TODO
 */
@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String main(Model model) {
        List<Post> posts = postService.findAllPostByTime();
        model.addAttribute("postList", posts);
        return "post/index";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "post/add";
    }

    @PostMapping("/addPost")
    public String addPost(Post post) {
        userService.decreaseCoin(post.getUserId(), post.getCoin());
        postService.addPost(post);
        return "redirect:/post/detail/" + post.getId();
    }

    @GetMapping("/type/{type}")
    public String showPostByType(@PathVariable("type") String type, Model model) {
        Integer intType = Consts.TYPE_MAP.get(type);
        List<Post> postList = postService.findAllPostByType(intType);
        model.addAttribute("postList", postList);
        return "post/index";
    }

    @GetMapping("/detail/{id}")
    public String showPost(@PathVariable("id") Integer id, Model model) {

        redisService.incrPostView(id);
        Post post = postService.findPostById(id);
        List<Comment> commentList = commentService.findCommentByPostId(id);
        model.addAttribute("post", post);
        model.addAttribute("commentList", commentList);
        return "post/detail";
    }

    @GetMapping("/del/{id}")
    public String deletePost(@PathVariable("id") Integer id) {
        postService.deletePost(id);
        return "redirect:/";
    }

    @GetMapping("/setUp/{id}")
    public String setUp(@PathVariable("id") Integer id) {
        postService.setPostUp(id);
        return "redirect:/post/detail/" + id;
    }

    @GetMapping("/unSetUp/{id}")
    public String unSetUp(@PathVariable("id") Integer id) {
        postService.unSetPostUp(id);
        return "redirect:/post/detail/" + id;
    }

    @GetMapping("/setGood/{id}")
    public String setGood(@PathVariable("id") Integer id) {
        postService.setPostGood(id);
        return "redirect:/post/detail/" + id;
    }

    @GetMapping("/unSetGood/{id}")
    public String unSetGood(@PathVariable("id") Integer id) {
        postService.unSetPostGood(id);
        return "redirect:/post/detail/" + id;
    }
}
