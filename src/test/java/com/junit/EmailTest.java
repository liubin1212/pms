package com.junit;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

public class EmailTest {
    @Test
    public void Test01() throws  Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-Email.xml");
        /*JavaMailSenderImpl bean = context.getBean(JavaMailSenderImpl.class);
        //获取邮箱的对象
        MimeMessage mimeMessage = bean.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        //设置邮箱的发件人，收件人，标题，内容
        mimeMessageHelper.setFrom("13693965604@163.com");
        mimeMessageHelper.setTo("18710204083@163.com");
        mimeMessageHelper.setSubject("你好啊");
        mimeMessageHelper.setText("嘿，小雨");
        //发送邮箱
        bean.send(mimeMessage);*/
        //获取容器bean对象
        JavaMailSenderImpl bean = context.getBean(JavaMailSenderImpl.class);
        //创建邮件对象
        MimeMessage mimeMessage = bean.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

        //设置邮箱的发件人，收件人，标题，内容，
        mimeMessageHelper.setFrom("13693965604@163.com");
        mimeMessageHelper.setTo("18710204083@163.com");
        mimeMessageHelper.setSubject("0708JAVA2班测试");
        mimeMessageHelper.setText(
                "<html><head></head>" +
                        "<body>" +
                        "嘿嘿嘿 <img src=cid:identifie>  " +
                        "<span style='color:blue'>嘿嘿</span>" +
                        "<h1 style='color:red;'>哈哈,秀不秀？^_^</h1>" +
                        "</body></html>",true);
        //上传附件
        FileSystemResource file = new FileSystemResource("C:\\Users\\Administrator\\Desktop\\analysis.xlsx");
        //1参数 别名   2参数要上传的附件
        mimeMessageHelper.addAttachment("需求表.xlsx",file);
        //图片作为内置资源
        FileSystemResource file1 = new FileSystemResource("C:\\Users\\Administrator\\Desktop\\1.jpg");
        mimeMessageHelper.addInline("identifie",file1);

        //发送邮件
        bean.send(mimeMessage);
        System.out.println("发送成功！");

    }
}
