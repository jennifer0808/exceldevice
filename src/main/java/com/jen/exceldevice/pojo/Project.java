package com.jen.exceldevice.pojo;

import java.io.Serializable;

public class Project implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Integer build_id;
    private String describe ;
    private String ipaddress;
    private Integer port;

    public Project(){}
    public Project(Integer id, String name, Integer build_id, String describe, String ipaddress, Integer port) {
        this.id = id;
        this.name = name;
        this.build_id = build_id;
        this.describe = describe;
        this.ipaddress = ipaddress;
        this.port = port;
    }

    public Project(String name) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBuild_id() {
        return build_id;
    }

    public void setBuild_id(Integer build_id) {
        this.build_id = build_id;
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

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", build_id=" + build_id +
                ", describe='" + describe + '\'' +
                ", ipaddress='" + ipaddress + '\'' +
                ", port=" + port +
                '}';
    }
}
