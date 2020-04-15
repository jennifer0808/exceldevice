package com.jen.exceldevice.pojo;

import java.io.Serializable;

public class Link implements Serializable {

    private final static long serialVersionUID = 1L;
    private Integer id;
    private Integer project_id;
    private Integer stastion_id;
    private Integer drive_id;
    private String name;
    private String describe;
    private String ipaddress;
    private Integer port;
    private String portid;
    private Integer type;

    public Link(){}

    public Link(String name) {
        this.name = name;
    }

    public Link(Integer project_id, Integer stastion_id, Integer drive_id, String name) {
        this.project_id = project_id;
        this.stastion_id = stastion_id;
        this.drive_id = drive_id;
        this.name = name;
    }

    public Link(Integer id, Integer project_id, Integer stastion_id, Integer drive_id, String name, String describe, String ipaddress, Integer port, String portid, Integer type) {

        this.id = id;
        this.project_id = project_id;
        this.stastion_id = stastion_id;
        this.drive_id = drive_id;
        this.name = name;
        this.describe = describe;
        this.ipaddress = ipaddress;
        this.port = port;
        this.portid = portid;
        this.type = type;
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

    public Integer getDrive_id() {
        return drive_id;
    }

    public void setDrive_id(Integer drive_id) {
        this.drive_id = drive_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPortid() {
        return portid;
    }

    public void setPortid(String portid) {
        this.portid = portid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", project_id=" + project_id +
                ", stastion_id=" + stastion_id +
                ", drive_id=" + drive_id +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", ipaddress='" + ipaddress + '\'' +
                ", port=" + port +
                ", portid='" + portid + '\'' +
                ", type=" + type +
                '}';
    }
}

