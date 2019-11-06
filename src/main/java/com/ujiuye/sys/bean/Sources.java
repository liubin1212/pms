package com.ujiuye.sys.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * sources
 * @author 
 */
public class Sources implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    private boolean isParent = false;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 资源备注
     */
    private String remark;

    /**
     * 父菜单id
     */
    private Integer pid;

    /**
     * 图标
     */
    private String icon;

    private boolean open = false;



    private List<Sources> children = new ArrayList<>();

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<Sources> getChildren() {
        return children;
    }

    public void setChildren(List<Sources> children) {
        this.children = children;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}