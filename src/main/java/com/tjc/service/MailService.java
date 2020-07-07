package com.tjc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Author: TJC
 * @Date: 2020/6/20 17:21
 * @description: TODO
 */
@Service("mailService")
public class MailService {
    @Autowired
    private JavaMailSenderImpl mailSender;

    @Async
    public void sendRegisterMail(String email, String code) throws MessagingException {
        //发送验证码
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setSubject("感谢注册Fly社区!");
        helper.setText("<div>您的注册验证码是: </div><h1 style='color: blue'>" + code + "</h1><div>请在10分钟内完成验证, 验证码只能使用一次!</div>", true);

        helper.setTo(email);
        helper.setFrom("flyforumadmin@163.com");
        mailSender.send(message);
    }
}
