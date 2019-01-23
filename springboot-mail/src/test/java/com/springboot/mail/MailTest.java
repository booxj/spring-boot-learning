package com.springboot.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void 邮件测试() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("lvkingjade@163.com"); //发送者
        message.setTo("lvkingjade@163.com"); //接受者
        message.setSubject("主题:邮件"); //主题
        message.setText("邮件内容"); //邮件内容
        mailSender.send(message);
    }
}
