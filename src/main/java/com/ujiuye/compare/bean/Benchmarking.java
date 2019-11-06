package com.ujiuye.compare.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * benchmarking
 * @author 
 */
public class Benchmarking implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 营业额
     */
    private Double salesAmount;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 主要业务
     */
    private String business;

    /**
     * 优势
     */
    private String priority;

    /**
     * 劣势
     */
    private String disadvantage;

    /**
     * 行业地位
     */
    private String status;

    /**
     * 员工数量
     */
    private Integer empCount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 简单描述
     */
    private String simpleDesc;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Double getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Double salesAmount) {
        this.salesAmount = salesAmount;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDisadvantage() {
        return disadvantage;
    }

    public void setDisadvantage(String disadvantage) {
        this.disadvantage = disadvantage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getEmpCount() {
        return empCount;
    }

    public void setEmpCount(Integer empCount) {
        this.empCount = empCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSimpleDesc() {
        return simpleDesc;
    }

    public void setSimpleDesc(String simpleDesc) {
        this.simpleDesc = simpleDesc;
    }
}