package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Analysis;
import com.ujiuye.pro.bean.AnalysisExample;
import com.ujiuye.pro.service.AnalysisService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/ana")
public class AnalysisController {
    @Autowired
    private AnalysisService analysisService;

    //导入Excel
    @RequestMapping(value = "/importExcel",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> importExcel(MultipartFile excel){
        System.out.println("上传Excel开始i了------");
        Map<String,Object> map = new HashMap<>();
        List<Analysis> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            //获取到上传的Excel对象
            Workbook wb = WorkbookFactory.create(excel.getInputStream());
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                //获取到其中的sheet
                Sheet sheetAt = wb.getSheetAt(i);
                if(sheetAt == null){
                    break;
                }
                //判断sheet是否为空
                if (sheetAt != null){
                    //遍历每个sheet，获取到每一行，第一行（名称）不要
                    for (int j = sheetAt.getFirstRowNum() + 1; j <= sheetAt.getLastRowNum(); j++) {
                        Row row = sheetAt.getRow(j);
                        Analysis analysis = new Analysis();
                        if(row != null){
                            //遍历每一行，获取到其中的值.从2开始0和1是序号和id不需要
                           String title = row.getCell(2).getStringCellValue();
                           analysis.setTitle(title);
                           String proname = row.getCell(3).getStringCellValue();
                           analysis.setProname(proname);
                           Date addtime = sdf.parse(row.getCell(4).getStringCellValue());
                           analysis.setAddtime(addtime);
                           Date updatetime = sdf.parse(row.getCell(5).getStringCellValue());
                           analysis.setUpdatetime(updatetime);
                           String simpledis = row.getCell(6).getStringCellValue();
                           analysis.setSimpledis(simpledis);
                           String detaileddis = row.getCell(7).getStringCellValue();
                           analysis.setDetaileddis(detaileddis);
                           String remark = row.getCell(8).getStringCellValue();
                           analysis.setRemark(remark);
                        }
                        list.add(analysis);
                    }
                }
            }
            System.out.println(list);
            map.put("statusCode",200);
            map.put("message","上传成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("出异常了");
            map.put("statusCode",500);
            map.put("message","上传失败");
        }
        return map;
    }

    //导出与数据库对应的Excel表格
    @RequestMapping(value = "/exportExcel",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> exportExcel(){
        //创建一个表格
        Workbook wb = new XSSFWorkbook();
        //创建一个sheet
        Sheet sheet = wb.createSheet();
        //创建一个标题行 行下标从0开始
        Row row1 = sheet.createRow(0);
        //创建单元格
        Cell[] cells = new XSSFCell[9];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = row1.createCell(i);
        }
        cells[0].setCellValue("序号");
        cells[1].setCellValue("ID");
        cells[2].setCellValue("标题");
        cells[3].setCellValue("项目名称");
        cells[4].setCellValue("添加时间");
        cells[5].setCellValue("修改时间");
        cells[6].setCellValue("简单描述(数据库中的值)");
        cells[7].setCellValue("详细描述(数据库中的值)");
        cells[8].setCellValue("备注(数据库中的值)");
        //获取数据库中的内容
        List<Analysis> analyses = analysisService.selectByExample(new AnalysisExample());
        //格式化时间类型
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //遍历并添加到Excel中
        for (int i = 0; i < analyses.size(); i++) {
            //创建一个标题行 行下标从0开始
            Row row = sheet.createRow(i+1);
            //创建单元格
            Cell[] cell = new XSSFCell[9];
            for (int j = 0; j < cells.length; j++) {
                cell[j] = row.createCell(j);
            }
            cell[0].setCellValue(i+1);
            cell[1].setCellValue(analyses.get(i).getId());
            cell[2].setCellValue(analyses.get(i).getTitle());
            cell[3].setCellValue(analyses.get(i).getProname());
            cell[4].setCellValue(sdf.format(analyses.get(i).getAddtime()));
            cell[5].setCellValue(sdf.format(analyses.get(i).getUpdatetime()));
            cell[6].setCellValue(analyses.get(i).getSimpledis());
            cell[7].setCellValue(analyses.get(i).getDetaileddis());
            cell[8].setCellValue(analyses.get(i).getRemark());
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\analysis.xlsx"));
            wb.write(fos);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (fos != null){
                try{
                    fos.close();
                }catch (Exception e){}
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("statusCode",200);
        map.put("message","导出成功");
        return map;
    }

    //根据id查询并返回Analysis对象
    @RequestMapping(value = "/findAnalysis",method = RequestMethod.GET)
    @ResponseBody
    public Analysis findAnalysis(Integer id){
        return analysisService.selectByPrimaryKey(id);
    }
    //查询所有的analysis并返回一个list数组
    @RequestMapping(value = "/findJsonAnalysis",method = RequestMethod.GET)
    @ResponseBody
    public List<Analysis> findJsonAnalysis(){
        return analysisService.selectByExample(new AnalysisExample());
    }
    //根据关键字搜索并排序
    @RequestMapping(value = "/searchAnalysis",method = RequestMethod.GET)
    public ModelAndView searchAnalysis(Integer id,String keyword,String orderby){
        ModelAndView mv = new ModelAndView("project-need");
        List<Analysis> list = analysisService.searchAnalysis(id,keyword,orderby);
        mv.addObject("list",list);
        return mv;
    }
    //删除或者批量删除analysis
    @RequestMapping(value = "/deleteAnalysis",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> deleteAnalysis(@RequestParam(value = "ids[]") Integer[] ids){
        Map<String,Object> map = new HashMap<>();
        List<Integer> integers = Arrays.asList(ids);
        AnalysisExample example = new AnalysisExample();
        AnalysisExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(integers);
        int i = analysisService.deleteByExample(example);
        if (i > 0){
            map.put("statusCode",200);
            map.put("message","删除成功");
        }
        return map;
    }
    //更新操作
    @RequestMapping(value = "/updateAnalysis",method = RequestMethod.PUT)
    public String updateAnalysis(Analysis analysis){
        analysis.setUpdatetime(new Date());
        analysisService.updateByPrimaryKeySelective(analysis);
        return "redirect:/ana/findAllAnalysis";
    }
    //根据id查询analysis
    @RequestMapping(value = "/findAnalysisById/{id}",method = RequestMethod.GET)
    public ModelAndView findAnalysisById(@PathVariable("id") Integer id,String mark){
        ModelAndView mv = new ModelAndView("project-need-edit");
        Analysis analysis = analysisService.selectByPrimaryKey(id);
        mv.addObject("analysis",analysis);
        if("1".equals(mark)){
            mv.setViewName("project-need-look");
        }
        return mv;
    }
    //查询所有的analysis
    @RequestMapping(value = "/findAllAnalysis",method = RequestMethod.GET)
    public ModelAndView findAllAnalysis(){
        ModelAndView mv = new ModelAndView("project-need");
        List<Analysis> list = analysisService.selectByExample(new AnalysisExample());
        mv.addObject("list",list);
        return mv;
    }
    //添加新的Analysis
    @RequestMapping(value = "/addAnalysis",method = RequestMethod.POST)
    public String addAnalysis(Analysis analysis){
        analysis.setAddtime(new Date());
        analysis.setUpdatetime(new Date());
        analysisService.insertSelective(analysis);
        return "redirect:/ana/findAllAnalysis";
    }
}
