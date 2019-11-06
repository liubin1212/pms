package com.ujiuye.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.bean.BaoxiaoExample;
import com.ujiuye.usual.mapper.BaoxiaoMapper;
import com.ujiuye.utils.ParseToString;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class BaoXiaoServiceImp implements BaoXiaoService {
    @Resource
    BaoxiaoMapper baoxiaoMapper;

    @Override
    public long countByExample(BaoxiaoExample example) {
        return baoxiaoMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(BaoxiaoExample example) {
        return baoxiaoMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(String bxid) {
        return baoxiaoMapper.deleteByPrimaryKey(bxid);
    }

    @Override
    public int insert(Baoxiao record) {
        return baoxiaoMapper.insert(record);
    }

    @Override
    public int insertSelective(Baoxiao record) {
        return baoxiaoMapper.insertSelective(record);
    }

    @Override
    public List<Baoxiao> selectByExample(BaoxiaoExample example) {
        return baoxiaoMapper.selectByExample(example);
    }

    @Override
    public Baoxiao selectByPrimaryKey(String bxid) {
        return baoxiaoMapper.selectByPrimaryKey(bxid);
    }

    @Override
    public int updateByExampleSelective(Baoxiao record, BaoxiaoExample example) {
        return baoxiaoMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Baoxiao record, BaoxiaoExample example) {
        return baoxiaoMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Baoxiao record) {
        return baoxiaoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Baoxiao record) {
        return baoxiaoMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<Baoxiao> findAllBaoxiao(Integer eid, String pageNum, Map<String,Object> parameterMap) {
        //设置分页，第一个参数是当前页数，第二个参数是一页有几条数据
        PageHelper.startPage(Integer.parseInt(pageNum),3);
        Map<String,String> mybatisMap = ParseToString.parseParameterMapToMybatisMap(parameterMap);
        String status = mybatisMap.get("status");
        String keyword = mybatisMap.get("keyword");
        BaoxiaoExample example = new BaoxiaoExample();
        BaoxiaoExample.Criteria criteria = example.createCriteria();
        if (status != null && status != ""){
            criteria.andBxstatusEqualTo(Integer.parseInt(status));
        }
        if (keyword != null && keyword != ""){
            criteria.andBxremarkLike(keyword);
        }
        criteria.andEmpFkEqualTo(eid);
        //查询数据库里面的数据
        List<Baoxiao> baoxiaos = baoxiaoMapper.selectByExample(example);
        //页面的详细信息，第一个参数是页面的数据，第二个参数是设置显示的页码有多少个
        PageInfo<Baoxiao> page = new PageInfo<>(baoxiaos,5);
        List<Baoxiao> list = page.getList();
        return page;
    }


}
