package com.tjc.controller;

import com.tjc.pojo.Comment;
import com.tjc.service.CommentService;
import com.tjc.service.PostService;
import com.tjc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/18 14:55
 * @description: TODO
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping("/reply")
    public String reply(Comment comment) {
        commentService.addComment(comment);
        return "redirect:/post/detail/" + comment.getPostId();
    }

    @GetMapping("/adoptComment/{id}")
    public String adoptComment(@PathVariable("id") Integer id, Model model) {
        Comment comment = commentService.findCommentById(id);
        if (comment.getPost().getAdoptId() != null) {
            return "redirect:/post/detail/" + comment.getPostId();
        }
        userService.increaseCoin(comment.getUserId(), comment.getPost().getCoin());
        commentService.adoptComment(comment.getPostId(), id);
        return "redirect:/post/detail/" + comment.getPostId();
    }

    @GetMapping("/delComment/{id}")
    public String deleteComment(@PathVariable("id") Integer id) {
        Comment comment = commentService.findCommentById(id);
        Integer postId = comment.getPostId();
        if (id.equals(comment.getPost().getAdoptId())) {
            postService.removeAdoptComment(comment.getPostId());
        }
        userService.decreaseCoin(comment.getUserId(), comment.getPost().getCoin());
        commentService.deleteComment(id);
        return "redirect:/post/detail/" + postId;
    }

}
