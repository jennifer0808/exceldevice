package com.jen.exceldevice.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class TranspondRecord implements Serializable {

    private final static  long serialVersionUID = 1L;
    private Integer  id;
    private String building_id;
    private String content;
    private Timestamp recordtime;
    private String result;
    private String scope_stat;
    private Integer type;
    private Integer c_type;
    private Integer project_id;

    public TranspondRecord(){}

    public TranspondRecord(Integer id, String building_id, String content, Timestamp recordtime, String result, String scope_stat, Integer type, Integer c_type, Integer project_id) {
        this.id = id;
        this.building_id = building_id;
        this.content = content;
        this.recordtime = recordtime;
        this.result = result;
        this.scope_stat = scope_stat;
        this.type = type;
        this.c_type = c_type;
        this.project_id = project_id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(String building_id) {
        this.building_id = building_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(Timestamp recordtime) {
        this.recordtime = recordtime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getScope_stat() {
        return scope_stat;
    }

    public void setScope_stat(String scope_stat) {
        this.scope_stat = scope_stat;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getC_type() {
        return c_type;
    }

    public void setC_type(Integer c_type) {
        this.c_type = c_type;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    @Override
    public String toString() {
        return "TranspondRecord{" +
                "id=" + id +
                ", building_id='" + building_id + '\'' +
                ", content='" + content + '\'' +
                ", recordtime=" + recordtime +
                ", result='" + result + '\'' +
                ", scope_stat='" + scope_stat + '\'' +
                ", type=" + type +
                ", c_type=" + c_type +
                ", project_id=" + project_id +
                '}';
    }
}


