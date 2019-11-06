package com.ujiuye.usual.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.bean.Notice;
import com.ujiuye.usual.bean.NoticeExample;
import com.ujiuye.usual.service.NoticeService;
import com.ujiuye.utils.ParseToString;
import com.ujiuye.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    //查询一条公告
    @RequestMapping(value = "/findNotice/{nid}",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity findNotice(@PathVariable("nid") Integer nid){
        Notice notice = noticeService.selectByPrimaryKey(nid);
        return ResultEntity.success().put("notice",notice);
    }
    //查询最新的三条公告
    @RequestMapping(value = "/findNewThreeNotice",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity findNewThreeNotice(){
        NoticeExample example = new NoticeExample();
        example.setOrderByClause("ndate desc limit 3");
        List<Notice> notices = noticeService.selectByExample(example);
        return ResultEntity.success().put("list",notices);
    }
    //查询公告带分页效果  返回json数据
    @RequestMapping(value = "/findAllJsonNotice",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity findAllJsonNotice(HttpServletRequest request, @RequestParam(value = "pageNum",defaultValue = "1") String pageNum){
        Map<String, Object> parameterMap = WebUtils.getParametersStartingWith(request, "search_");
        String requestURI = request.getRequestURI();
        String queryStr = ParseToString.parseParameterToString(parameterMap);
        PageInfo<Notice> pageInfo = (PageInfo<Notice>) noticeService.findAllJsonNotice(pageNum,parameterMap);
        return ResultEntity.success().put("page",pageInfo).put("queryStr",queryStr).put("requestURI",requestURI);
    }

    //查询所有的公告
    /*@RequestMapping(value = "/findAllJsonNotice",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity findAllJsonNotice(){
        List<Notice> list = noticeService.selectByExample(new NoticeExample());
        return ResultEntity.success().put("list",list);
    }*/

    //添加一个新的公告
    @RequestMapping(value = "/addNotice",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity addNotice(Notice notice){
        notice.setNdate(new Date());
        int insert = noticeService.insertSelective(notice);
        System.out.println(insert);
        if (insert <= 0){
            return ResultEntity.error().put("message","添加失败");
        }
        return ResultEntity.success();
    }
}
