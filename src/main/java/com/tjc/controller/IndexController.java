package com.tjc.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.tjc.pojo.Post;
import com.tjc.pojo.User;
import com.tjc.service.PostService;
import com.tjc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/16 12:50
 * @description: TODO
 */
@Controller
public class IndexController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @GetMapping({"/", "/index"})
    public String main(Model model, HttpSession session) {
        List<Post> upPostList = postService.findUpPostByTime();
        List<Post> postList = postService.findAllPostByTime();
        List<Post> hotPostList = postService.findHotPostByCommentNum();
        List<User> hotUserList = userService.findHotUserByReply();
        model.addAttribute("upPostList", upPostList);
        model.addAttribute("postList", postList);
        session.setAttribute("hotUserList", hotUserList);
        session.setAttribute("hotPostList", hotPostList);
        return "index";
    }

    @GetMapping("/hot")
    public String hotMain(Model model, HttpSession session) {
        List<Post> upPostList = postService.findUpPostByTime();
        List<Post> postList = postService.findAllPostByReply();
        List<Post> hotPostList = postService.findHotPostByCommentNum();
        List<User> hotUserList = userService.findHotUserByReply();
        model.addAttribute("upPostList", upPostList);
        model.addAttribute("postList", postList);
        session.setAttribute("hotUserList", hotUserList);
        session.setAttribute("hotPostList", hotPostList);
        return "index";
    }

    @RequestMapping(value = "verification")
    public void getVerification(HttpServletRequest request, HttpServletResponse response) throws IOException {
        byte[] verByte = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute("verify_session_Code", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        verByte = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(verByte);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
