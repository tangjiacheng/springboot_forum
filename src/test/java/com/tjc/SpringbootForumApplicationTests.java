package com.tjc;

import com.tjc.mapper.PostMapper;
import com.tjc.mapper.UserMapper;
import com.tjc.pojo.Post;
import com.tjc.pojo.User;
import com.tjc.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;

@SpringBootTest
class SpringbootForumApplicationTests {

    @Autowired
    PostMapper postMapper;

    @Autowired
    PostService postService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Test
    void contextLoads() {

        Post post = new Post();
        post.setUserId(100);
        post.setTitle("测试");
        post.setType(0);
        post.setContent("asdfjasd;");
        post.setCoin(0);
        post.setCreateTime(new Date());
        postService.addPost(post);
        System.out.println(post);
    }

    @Test
    void redisTest() {
        redisTemplate.opsForValue().set("post:view4", 1);
        redisTemplate.opsForValue().increment("post:view4");
        System.out.println(redisTemplate.opsForValue().get("post:view4"));
//        Integer uv = (Integer) redisTemplate.opsForValue().get("post:view4");
//        System.out.println(uv);
    }

    @Test
    void redisGetTest() {
        System.out.println((String) redisTemplate.opsForValue().get("post:view11"));
    }

    @Test
    void findUserByEmail() {
        User user = userMapper.findUserByEmail("857548582@qq.com");
        System.out.println(user);
    }

    @Test
    void findPostByUserId() {
        Post post = postMapper.findPostById(1);
        System.out.println(post);
    }

    @Test
    void mailConfig() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("欢迎注册Fly社区!");

        message.setText("您的注册验证码是: ");
        message.setTo("857548582@qq.com");
        message.setFrom("flyforumadmin@163.com");

        mailSender.send(message);
    }

    @Test
    void mailSend2() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setSubject("感谢注册Fly社区!");
        helper.setText("<div>您的注册验证码是: </div><h1 style='color: blue'>123456</h1>", true);

        helper.setTo("857548582@qq.com");
        helper.setFrom("flyforumadmin@163.com");
        mailSender.send(message);
    }
}
