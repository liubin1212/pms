package com.ujiuye.sys.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * archives
 * @author 
 */
public class Archives implements Serializable {
    private String dnum;

    /**
     * 固话
     */
    private String landline;

    /**
     * 毕业院校
     */
    private String school;

    /**
     * 专业
     */
    private String zhuanye;

    /**
     * 紧急联系人
     */
    private String sosperson;

    /**
     * 毕业时间
     */
    private Date biyedate;

    /**
     * 政治面貌
     */
    private String zzmm;

    /**
     * 民族
     */
    private String minzu;

    /**
     * 学历
     */
    private String xueli;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 员工外键
     */
    private Integer empFk;

    /**
     * 备注
     */
    private String remark;

    /**
     * 入职日期
     */
    private Date hirdate;

    private static final long serialVersionUID = 1L;

    public String getDnum() {
        return dnum;
    }

    public void setDnum(String dnum) {
        this.dnum = dnum;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getZhuanye() {
        return zhuanye;
    }

    public void setZhuanye(String zhuanye) {
        this.zhuanye = zhuanye;
    }

    public String getSosperson() {
        return sosperson;
    }

    public void setSosperson(String sosperson) {
        this.sosperson = sosperson;
    }

    public Date getBiyedate() {
        return biyedate;
    }

    public void setBiyedate(Date biyedate) {
        this.biyedate = biyedate;
    }

    public String getZzmm() {
        return zzmm;
    }

    public void setZzmm(String zzmm) {
        this.zzmm = zzmm;
    }

    public String getMinzu() {
        return minzu;
    }

    public void setMinzu(String minzu) {
        this.minzu = minzu;
    }

    public String getXueli() {
        return xueli;
    }

    public void setXueli(String xueli) {
        this.xueli = xueli;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEmpFk() {
        return empFk;
    }

    public void setEmpFk(Integer empFk) {
        this.empFk = empFk;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getHirdate() {
        return hirdate;
    }

    public void setHirdate(Date hirdate) {
        this.hirdate = hirdate;
    }
}