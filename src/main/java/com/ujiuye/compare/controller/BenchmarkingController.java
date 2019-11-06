package com.ujiuye.compare.controller;

import com.ujiuye.compare.bean.Benchmarking;
import com.ujiuye.compare.bean.BenchmarkingExample;
import com.ujiuye.compare.service.BenchmarkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/bench")
public class BenchmarkingController {
    @Autowired
    BenchmarkingService benchmarkingService;

    //查询公司名，销售额，id
    @RequestMapping(value = "/findAllBenchmarking",method = RequestMethod.GET)
    @ResponseBody
    public List<Benchmarking> findAllBenchmarking(){
        BenchmarkingExample example = new BenchmarkingExample();
        BenchmarkingExample.Criteria criteria = example.createCriteria();
        criteria.andYearEqualTo(2018);
        List<Benchmarking> benchmarkings = benchmarkingService.selectByExample(example);
        return benchmarkings;
    }

    //添加数据
    @RequestMapping(value = "/addBenchmarking" ,method = RequestMethod.POST)
    public String addBenchmarking(Benchmarking benchmarking){
        benchmarkingService.insertSelective(benchmarking);
        return "redirect:/indexvalue-base.jsp";
    }
}
