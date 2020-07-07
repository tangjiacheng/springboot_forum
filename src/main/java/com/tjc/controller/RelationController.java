package com.tjc.controller;

import com.tjc.pojo.User;
import com.tjc.service.RelationService;
import com.tjc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author: TJC
 * @Date: 2020/6/21 13:30
 * @description: TODO
 */
@Controller
@RequestMapping("/relation")
public class RelationController {
    @Autowired
    private RelationService relationService;

    @Autowired
    private UserService userService;

    @GetMapping("/follow/{username}")
    private String follow(@PathVariable("username") String username, HttpSession session) {
        System.out.println("follow ==> " + username);
        User user = userService.findUserByUsername(username);
        User loginUser = (User) session.getAttribute("loginUser");
        relationService.addRelation(loginUser.getId(), user.getId());
        return "redirect:/user/home/" + user.getUsername();
    }

    @GetMapping("/unfollow/{username}")
    private String unfollow(@PathVariable("username") String username, HttpSession session) {
        System.out.println("unfollow ==> " + username);
        User user = userService.findUserByUsername(username);
        User loginUser = (User) session.getAttribute("loginUser");
        relationService.removeRelation(loginUser.getId(), user.getId());
        return "redirect:/user/home/" + user.getUsername();
    }
}
