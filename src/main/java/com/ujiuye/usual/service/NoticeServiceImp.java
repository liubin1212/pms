package com.ujiuye.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.bean.BaoxiaoExample;
import com.ujiuye.usual.bean.Notice;
import com.ujiuye.usual.bean.NoticeExample;
import com.ujiuye.usual.mapper.NoticeMapper;
import com.ujiuye.utils.ParseToString;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImp implements NoticeService {
    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public long countByExample(NoticeExample example) {
        return noticeMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(NoticeExample example) {
        return noticeMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer nid) {
        return noticeMapper.deleteByPrimaryKey(nid);
    }

    @Override
    public int insert(Notice record) {
        return noticeMapper.insert(record);
    }

    @Override
    public int insertSelective(Notice record) {
        return noticeMapper.insertSelective(record);
    }

    @Override
    public List<Notice> selectByExample(NoticeExample example) {
        return noticeMapper.selectByExample(example);
    }

    @Override
    public Notice selectByPrimaryKey(Integer nid) {
        return noticeMapper.selectByPrimaryKey(nid);
    }

    @Override
    public int updateByExampleSelective(Notice record, NoticeExample example) {
        return noticeMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Notice record, NoticeExample example) {
        return noticeMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Notice record) {
        return noticeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Notice record) {
        return noticeMapper.updateByPrimaryKey(record);
    }

    public PageInfo<Notice> findAllJsonNotice(String pageNum, Map<String,Object> parameterMap) {
        //设置分页，第一个参数是当前页数，第二个参数是一页有几条数据
        PageHelper.startPage(Integer.parseInt(pageNum),3);
        Map<String,String> mybatisMap = ParseToString.parseParameterMapToMybatisMap(parameterMap);
        String status = mybatisMap.get("status");
        String keyword = mybatisMap.get("keyword");
        NoticeExample example = new NoticeExample();
        NoticeExample.Criteria criteria = example.createCriteria();
        /*if (status != null && status != ""){
            criteria.andBxstatusEqualTo(Integer.parseInt(status));
        }
        if (keyword != null && keyword != ""){
            criteria.andBxremarkLike(keyword);
        }*/
        //查询数据库里面的数据
        List<Notice> notices = noticeMapper.selectByExample(example);
        //页面的详细信息，第一个参数是页面的数据，第二个参数是设置显示的页码有多少个
        PageInfo<Notice> page = new PageInfo<Notice>(notices,5);
        return page;
    }
}
