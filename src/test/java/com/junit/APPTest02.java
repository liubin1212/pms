package com.junit;

import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.service.AttachmentServiceImp;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class APPTest02 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-quarlz.xml");

    }
    @Test
    public void test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        AttachmentServiceImp bean = context.getBean(AttachmentServiceImp.class);
        List<Attachment> list = bean.getAttachmentList();
        for (Attachment attachment : list) {
            System.out.println(attachment);
        }
    }
}
