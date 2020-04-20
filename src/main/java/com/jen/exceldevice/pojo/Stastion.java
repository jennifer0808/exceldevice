package com.jen.exceldevice.pojo;

import java.io.Serializable;

public class Stastion implements Serializable {
    private final static long serialVersionUID = 1L;
    private Integer id;
    private Integer project_id;
    private String name;
    private String describe;
    private Integer number;
    private String ipaddress;
    private Integer port;



    public Stastion(){}

    public Stastion(String name) {
        this.name = name;
    }

    public Stastion(Integer project_id, String name) {
        this.project_id = project_id;
        this.name = name;
    }

    public Stastion(Integer id, Integer project_id, String name) {
        this.id = id;
        this.project_id = project_id;
        this.name = name;
    }

    public Stastion(Integer id, Integer project_id, String name, String describe, Integer number, String ipaddress, Integer port) {
        this.id = id;
        this.project_id = project_id;
        this.name = name;
        this.describe = describe;
        this.number = number;
        this.ipaddress = ipaddress;
        this.port = port;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
        return "Stastion{" +
                "id=" + id +
                ", project_id=" + project_id +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", number=" + number +
                ", ipaddress='" + ipaddress + '\'' +
                ", port=" + port +
                '}';
    }
}
