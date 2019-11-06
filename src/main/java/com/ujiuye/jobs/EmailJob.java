package com.ujiuye.jobs;

import com.ujiuye.infomation.bean.Email;
import org.quartz.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class EmailJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        Email email = (Email) jobDataMap.get("email");
        Scheduler sched = (Scheduler)jobDataMap.get("sched");
        JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl)jobDataMap.get("javaMailSender");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            //设置邮箱的发件人，收件人，标题，内容，
            mimeMessageHelper.setFrom("13693965604@163.com");
            mimeMessageHelper.setTo(email.getEname());
            mimeMessageHelper.setSubject(email.getTitle());
            mimeMessageHelper.setText(
                    "<html><head></head>" +
                            "<body>" +
                            "嘿嘿嘿 <img src=cid:identifie>  " +
                            "<span style='color:blue'>嘿嘿</span>" +
                            "<h1 style='color:red;'>哈哈,秀不秀？^_^</h1><br/>" + email.getContent()+
                            "</body></html>",true);
            //上传附件
            FileSystemResource file = new FileSystemResource(email.getPath());
            //1参数 别名   2参数要上传的附件
            mimeMessageHelper.addAttachment("analysis.xlsx",file);
            //图片作为内置资源
            FileSystemResource file1 = new FileSystemResource("C:\\Users\\Administrator\\Desktop\\1.jpg");
            mimeMessageHelper.addInline("identifie",file1);

            //发送邮件
            javaMailSender.send(mimeMessage);
            System.out.println("发送成功！");
            //关闭定时任务管理器
            sched.shutdown(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    *
    * */
}
