package com.tjc.controller;

import com.alibaba.fastjson.JSONObject;
import com.tjc.pojo.Comment;
import com.tjc.pojo.Post;
import com.tjc.pojo.Relation;
import com.tjc.pojo.User;
import com.tjc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @Author: TJC
 * @Date: 2020/6/16 12:58
 * @description: TODO
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private MailService mailService;

    @Autowired
    private RelationService relationService;

    @GetMapping("/toLogin")
    public String toLogin(HttpSession session) {
        System.out.println("/user/toLogin ==> 执行");
        if (session.getAttribute("loginUser") != null) {
            return "redirect:/";
        }
        else
            return "user/login";
    }

    @RequestMapping("/loginSuccess")
    public String loginSuccess(HttpSession session) {
        System.out.println("/user/loginSuccess ==> 执行");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        session.setAttribute("loginUser", user);
        return "redirect:/";
    }

    @RequestMapping("/loginFailure")
    public String loginFailure(Model model) {
        model.addAttribute("loginFailure", "用户名或密码错误!");
        return "user/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:/";
    }

    @RequestMapping("/toReg")
    public String toRegister() {
        return "user/reg";
    }

    @PostMapping("/register")
    public String register(User user, String rePassword, String code, Model model, HttpSession session) {
        User checkUser = userService.findUserByEmail(user.getEmail());
        if (checkUser != null) {
            model.addAttribute("emailError", "邮箱地址已被注册");
            return "user/reg";
        }
        checkUser = userService.findUserByUsername(user.getUsername());
        if (checkUser != null) {
            model.addAttribute("usernameError", "用户名已存在");
            return "user/reg";
        }
        if (!rePassword.equals(user.getPassword())) {
            model.addAttribute("passwordError", "两次密码不一致");
            return "user/reg";
        }
        JSONObject userCode = (JSONObject)session.getAttribute("code");
        //验证码
        String saveCode = (String) userCode.get("code");
        String saveEmail = (String) userCode.get("email");
        if (!code.equals(saveCode) || !saveEmail.equals(user.getEmail())) {
            model.addAttribute("codeError", "验证码错误");
            return "user/reg";
        }
        else {
            userService.addUser(user);
            return "user/login";
        }
    }

    @RequestMapping("/post")
    public String myPost() {
        return "user/index";
    }

    @RequestMapping("/collection")
    public String myCollection() {
        return "user/index";
    }

    @RequestMapping("/set")
    public String toSetting() {
        return "user/set";
    }

    @PostMapping("/update")
    public String updateUser(User user, Model model, HttpSession session) {
        userService.updateUser(user);
        User newUser = userService.findUserById(user.getId());
        session.setAttribute("loginUser", newUser);
        model.addAttribute("updateSuccess", "修改成功!");
        model.addAttribute("msg", 1);
        return "user/set";
    }

    @GetMapping("/home/{username}")
    public String userHome(@PathVariable(value = "username", required = false) String username, Model model, HttpSession session) {
        System.out.println("userHome ==> " + username);
        User user = userService.findUserByUsername(username);
        List<Post> postList = postService.findPostByUserId(user.getId());
        List<Comment> commentList = commentService.findCommentByUserId(user.getId());
        User loginUser = (User) session.getAttribute("loginUser");
        if (!user.getId().equals(loginUser.getId())) {
            Relation relation = relationService.findRelation(loginUser.getId(), user.getId());
            if (relation == null) {
                model.addAttribute("relation", 0);
            } else {
                model.addAttribute("relation", 1);
            }
        }
        model.addAttribute("viewUser", user);
        model.addAttribute("postList", postList);
        model.addAttribute("commentList", commentList);
        return "user/home";
    }

    @RequestMapping("/message")
    public String myMessage(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");

        return "user/message";
    }

    @GetMapping("/index")
    public String toIndex(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        List<User> fanList = userService.findFanByUserId(user.getId());
        List<User> followList = userService.findFollowByUserId(user.getId());
        List<Post> postList = postService.findPostByUserId(user.getId());
        model.addAttribute("postList", postList);
        model.addAttribute("fanList", fanList);
        model.addAttribute("followList", followList);
        return "user/index";
    }

    @PostMapping("/changePasswd")
    public String changePassword(String password, String rePassword, HttpSession session, Model model) {
        model.addAttribute("msg", 3);
        if (!rePassword.equals(password)) {
            model.addAttribute("passwordError", "两次密码输入不一致!");
            return "user/set";
        }
        userService.changePassword(password, ((User) session.getAttribute("loginUser")).getId());
        model.addAttribute("changePassSuccess", "修改成功!");
        return "user/set";
    }

    @PostMapping("/updateAvatar")
    public String updateAvatar(Integer id, MultipartFile avatar, HttpSession session, Model model) {
        if (!avatar.isEmpty()) {
            try {
                Resource resource = new ClassPathResource("static/images/avatar");
                if (resource.exists()) {
                    String absPath = resource.getURL().getPath();
                    int start = 0;
                    for (int i = avatar.getOriginalFilename().length() - 1; i > 0; i--) {
                        if (avatar.getOriginalFilename().charAt(i) == '.') {
                            start = i;
                            break;
                        }
                    }
                    String ext = avatar.getOriginalFilename().substring(start);
                    String filename = UUID.randomUUID().toString() + ext;
                    File f = new File(absPath, filename);
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
                    bos.write(avatar.getBytes());
                    bos.flush();
                    bos.close();
                    userService.updateUserAvatar(id, "/images/avatar/" + filename);
                    User user = userService.findUserById(id);
                    session.setAttribute("loginUser", user);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("msg", 2);
        return "user/set";
    }

    @ResponseBody
    @GetMapping("/emailVerify")
    public boolean emailVerify(@RequestParam("email") String email, HttpSession session) {
        try {
            //随机生成验证码
            String code = String.valueOf(new Random().nextInt(999999));

            mailService.sendRegisterMail(email, code);
            //将验证码存到session中,同时存入创建时间
            //以json存放，这里使用的是阿里的fastjson
            JSONObject json = new JSONObject();
            json.put("email",email);
            json.put("code",code);
            json.put("createTime",System.currentTimeMillis());
            // 将认证码存入SESSION
            session.setAttribute("code",json);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
