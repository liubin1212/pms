package com.ujiuye.infomation.controller;

import com.ujiuye.infomation.bean.Email;
import com.ujiuye.infomation.service.EmailService;
import com.ujiuye.jobs.EmailJob;
import com.ujiuye.sys.bean.Employee;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/email")
public class EmailController {
    @Autowired
    EmailService emailService;

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @RequestMapping(value = "/addEmail",method = RequestMethod.POST)
    public String addEmail(Email email, HttpSession session, MultipartFile pathfile) throws SchedulerException {
        Employee employee = (Employee) session.getAttribute("loginUser");
        //上传附件
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("/upload");
        String filePath = UUID.randomUUID().toString().replaceAll("-","") + pathfile.getOriginalFilename();
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        try {
            pathfile.transferTo(new File(realPath + "/" + filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        email.setEmpFk(employee.getEid());
        email.setSendtime(new Date());
        email.setPath(realPath +"/" +filePath);
        //发送邮箱
        //创建JobDetail对象，指定对象的任务名称、组名
        JobDetail job = JobBuilder.newJob(EmailJob.class).withIdentity("job1", "group1").build();
        //创建SimpleTrigger对象，指定对象名称、组名  设置任务重复执行间隔时间，重复执行次数 启动时间
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().startAt(new Date()).build();
        //创建任务管理器Scheduler对象
        Scheduler sched= StdSchedulerFactory.getDefaultScheduler();
        //往要执行的Emailjob里面存储值
        JobDataMap jobDataMap = job.getJobDataMap();
        jobDataMap.put("email",email);
        jobDataMap.put("sched",sched);
        jobDataMap.put("javaMailSender",javaMailSender);
        //使sched监视job和trigger
        sched.scheduleJob(job,trigger);
        //启动定时任务管理器
        sched.start();

        int i = emailService.insertSelective(email);
        if (i> 0){
            return "redirect:/main.jsp";
        }
        return "error";
    }
}
