package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.bean.AttachmentExample;
import com.ujiuye.pro.mapper.AttachmentMapper;
import com.ujiuye.pro.service.AttachmentService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/attach")
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;

    //根据条件搜索数据
    @RequestMapping(value = "/searchAttachment",method = RequestMethod.GET)
    public ModelAndView searchAttachment(Integer cid,String keyword,Integer orderby){
        ModelAndView mv = new ModelAndView("project-file");
        List<Attachment> list = attachmentService.searchAttachment(cid,keyword,orderby);
        mv.addObject("list",list);
        return mv;
    }

    //删除单条数据
    @RequestMapping(value = "/deleteAttachmentById/{id}")
    public String deleteAttachmentById(@PathVariable("id") Integer id){
        attachmentService.deleteByPrimaryKey(id);
        return "redirect:/attach/findAllAttachment";
    }

    //删除多条数据
    @RequestMapping(value = "/deleteAttachment" ,method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> deleteAttachment(@RequestParam("ids[]") Integer[] ids){
        Map<String,Object> map = new HashMap<>();
        AttachmentExample example = new AttachmentExample();
        AttachmentExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        int i = attachmentService.deleteByExample(example);
        if (i > 0){
            map.put("statusCode",200);
            map.put("message","删除成功");
        }
        return map;
    }

    //修改操作之删除上传的附件
    @RequestMapping(value = "/deletePath",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String,Object> deletePath(Attachment attachment){
        Map<String,Object> map = new HashMap<>();
        int i = attachmentService.updateByPrimaryKeySelective(attachment);
        if (i > 0){
            map.put("statusCode",200);
        }
        return map;
    }

    //修改操作之上传一个新的附件
    @RequestMapping(value = "/updatePath",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updatePath(Attachment attach, MultipartFile attachment, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("/upload");
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        String name = attachment.getOriginalFilename();
        String filename = UUID.randomUUID().toString().replaceAll("-", "") + name;
        try {
            attachment.transferTo(new File(realPath + "/" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        attach.setPath(filename);
        int i = attachmentService.updateByPrimaryKeySelective(attach);
        if (i > 0){
            map.put("statusCode",200);
            map.put("path",name);
        }
        return map;
    }

    //修改数据并存到数据库
    @RequestMapping(value = "/updateAttachment",method = RequestMethod.PUT)
    public String updateAttachment(Attachment attach ){
        attachmentService.updateByPrimaryKeySelective(attach);
        return "redirect:/attach/findAllAttachment";
    }

    //修改或者查看详情的查询单条数据
    @RequestMapping(value = "/findAttachmentById/{id}",method = RequestMethod.GET)
    public ModelAndView findAttachmentById(@PathVariable("id") Integer id,String mark){
        ModelAndView mv = new ModelAndView("project-file-edit");
        Attachment attachment = attachmentService.selectByPrimaryKey(id);
        mv.addObject("attachment",attachment);
        if("1".equals(mark)){
            mv.setViewName("project-file-look");
            return mv;
        }
        return mv;
    }

    //导出Excel代码
    @RequestMapping(value = "/exportExcel")
    @ResponseBody
    public Map<String,Object> exportExcel(){
        List<Attachment> attachments = attachmentService.selectByExample(new AttachmentExample());
        //创建Excel表格
        Workbook wb = new XSSFWorkbook();
        //创建sheet对象
        Sheet sheet = wb.createSheet();
        //创建行
        Row row = sheet.createRow(0);
        //创建单元格
        Cell[] cells = new XSSFCell[6];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = row.createCell(i);
        }
        cells[0].setCellValue("ID");
        cells[1].setCellValue("附件名称");
        cells[2].setCellValue("所属项目");
        cells[3].setCellValue("附件信息描述");
        cells[4].setCellValue("上传时间");
        cells[5].setCellValue("修改时间");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < attachments.size(); i++) {
            Attachment attachment = attachments.get(i);
            Row row1 = sheet.createRow(i+1);
            //创建单元格
            Cell[] cells1 = new XSSFCell[6];
            for (int j = 0; j < cells1.length; j++) {
                cells1[j] = row1.createCell(j);
            }
            cells1[0].setCellValue(attachment.getId());
            cells1[1].setCellValue(attachment.getAttname());
            cells1[2].setCellValue(attachment.getProject().getPname());
            cells1[3].setCellValue(attachment.getAttdis());
            cells1[4].setCellValue(sdf.format(attachment.getProject().getStarttime()));
            cells1[5].setCellValue(sdf.format(attachment.getProject().getBuildtime()));
        }

        FileOutputStream fos= null;
        try {
            fos= new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\attachment.xlsx"));
            wb.write(fos);
            fos.close();
        }catch (Exception ex){
                ex.printStackTrace();
        }finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("satatusCode",200);
        map.put("message","导出成功");
        return map;
    }
    //下载附件代码
    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> download(HttpSession session,String name){
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("/upload/" + name);
        System.out.println(realPath);
        try {
            FileInputStream fis = new FileInputStream(realPath);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            MultiValueMap<String,String> headers = new HttpHeaders();
            name = name.substring(32,name.length());
            name = new String(name.getBytes("gbk"),"iso8859-1");
            headers.add("Content-Disposition","attachment;filename=" + name);
            HttpStatus status = HttpStatus.OK;
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(b,headers,status);
            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //添加附件信息
    @RequestMapping(value = "/addAttachment",method = RequestMethod.POST)
    public String addAttachment(Attachment attach, MultipartFile attachment, HttpSession session){
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("/upload");
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        String name = attachment.getOriginalFilename();
        String filename = UUID.randomUUID().toString().replaceAll("-", "") + name;
        try {
            attachment.transferTo(new File(realPath + "/" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        attach.setPath(filename);
        attachmentService.insertSelective(attach);
        return "redirect:/attach/findAllAttachment";
    }

    //查询所有的附件信息
    @RequestMapping(value = "/findAllAttachment",method = RequestMethod.GET)
    public ModelAndView findAllAttachment(){
        ModelAndView mv = new ModelAndView("project-file");
        List<Attachment> list = attachmentService.selectByExample(new AttachmentExample());
        mv.addObject("list",list);
        return mv;
    }
}
