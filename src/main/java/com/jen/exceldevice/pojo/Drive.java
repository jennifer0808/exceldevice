package com.jen.exceldevice.pojo;

import java.io.Serializable;

public class Drive implements Serializable {
    private final static long serialVersionUID = 1L;
    private Integer id;
    private Integer project_id;
    private Integer stastion_id;
    private String protocol_name;

    public Drive(){}

    public Drive(String protocol_name) {
        this.protocol_name = protocol_name;
    }

    public Drive(Integer project_id, Integer stastion_id, String protocol_name) {
        this.project_id = project_id;
        this.stastion_id = stastion_id;
        this.protocol_name = protocol_name;
    }

    public Drive(Integer id, Integer project_id, Integer stastion_id, String protocol_name) {

        this.id = id;
        this.project_id = project_id;
        this.stastion_id = stastion_id;
        this.protocol_name = protocol_name;
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

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public Integer getStastion_id() {
        return stastion_id;
    }

    public void setStastion_id(Integer stastion_id) {
        this.stastion_id = stastion_id;
    }

    public String getProtocol_name() {
        return protocol_name;
    }

    public void setProtocol_name(String protocol_name) {
        this.protocol_name = protocol_name;
    }

    @Override
    public String toString() {
        return "Drive{" +
                "id=" + id +
                ", project_id=" + project_id +
                ", stastion_id=" + stastion_id +
                ", protocol_name='" + protocol_name + '\'' +
                '}';
    }
}
