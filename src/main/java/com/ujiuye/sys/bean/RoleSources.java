package com.ujiuye.sys.bean;

public class RoleSources {
    private Integer sid;

    private Integer rid;

    private static final long serialVersionUID = 1L;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public RoleSources(Integer sid, Integer rid){
        this.rid = rid;
        this.sid = sid;
    }
    public RoleSources(){}
}