package com.ujiuye.usual.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * forumpost
 * @author 
 */
public class Forumpost implements Serializable {
    /**
     * 帖子id
     */
    private Integer forumid;

    /**
     * 帖子的标题
     */
    private String forumtitle;

    /**
     * 帖子的内容
     */
    private String forumcontent;

    /**
     * 发帖人
     */
    private Integer empFk;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 帖子状态
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    public Integer getForumid() {
        return forumid;
    }

    public void setForumid(Integer forumid) {
        this.forumid = forumid;
    }

    public String getForumtitle() {
        return forumtitle;
    }

    public void setForumtitle(String forumtitle) {
        this.forumtitle = forumtitle;
    }

    public String getForumcontent() {
        return forumcontent;
    }

    public void setForumcontent(String forumcontent) {
        this.forumcontent = forumcontent;
    }

    public Integer getEmpFk() {
        return empFk;
    }

    public void setEmpFk(Integer empFk) {
        this.empFk = empFk;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}