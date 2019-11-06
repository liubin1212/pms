package com.ujiuye.usual.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * baoxiao
 * @author 
 */
public class Baoxiao implements Serializable {
    private String bxid;

    /**
     * 类型
     */
    private String paymode;

    /**
     * 总金额
     */
    private Double totalmoney;

    /**
     * 报销时间
     */
    private Date bxtime;

    /**
     * 报销备注
     */
    private String bxremark;

    /**
     * 报销状态
     */
    private Integer bxstatus;

    /**
     * 发起报销人
     */
    private Integer empFk;

    private String result;

    private static final long serialVersionUID = 1L;

    public String getBxid() {
        return bxid;
    }

    public void setBxid(String bxid) {
        this.bxid = bxid;
    }

    public String getPaymode() {
        return paymode;
    }

    public void setPaymode(String paymode) {
        this.paymode = paymode;
    }

    public Double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(Double totalmoney) {
        this.totalmoney = totalmoney;
    }

    public Date getBxtime() {
        return bxtime;
    }

    public void setBxtime(Date bxtime) {
        this.bxtime = bxtime;
    }

    public String getBxremark() {
        return bxremark;
    }

    public void setBxremark(String bxremark) {
        this.bxremark = bxremark;
    }

    public Integer getBxstatus() {
        return bxstatus;
    }

    public void setBxstatus(Integer bxstatus) {
        this.bxstatus = bxstatus;
    }

    public Integer getEmpFk() {
        return empFk;
    }

    public void setEmpFk(Integer empFk) {
        this.empFk = empFk;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}