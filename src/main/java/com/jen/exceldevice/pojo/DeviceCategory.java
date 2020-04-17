package com.jen.exceldevice.pojo;

import java.io.Serializable;

public class DeviceCategory implements Serializable {

    private final static long serialVersionUID = 1L;

    private Integer id;
    private Integer parent_id;
    private Integer emengry_id;
    private Integer hasChild;
    private String name;
    private String describe;
    private Integer type;
    private String shsz_code;

    public DeviceCategory(){}


    public DeviceCategory(Integer id, Integer parent_id, Integer emengry_id, Integer hasChild, String name, String describe, Integer type, String shsz_code) {
        this.id = id;
        this.parent_id = parent_id;
        this.emengry_id = emengry_id;
        this.hasChild = hasChild;
        this.name = name;
        this.describe = describe;
        this.type = type;
        this.shsz_code = shsz_code;
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

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getEmengry_id() {
        return emengry_id;
    }

    public void setEmengry_id(Integer emengry_id) {
        this.emengry_id = emengry_id;
    }

    public Integer getHasChild() {
        return hasChild;
    }

    public void setHasChild(Integer hasChild) {
        this.hasChild = hasChild;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getShsz_code() {
        return shsz_code;
    }

    public void setShsz_code(String shsz_code) {
        this.shsz_code = shsz_code;
    }

    @Override
    public String toString() {
        return "DeviceCategory{" +
                "id=" + id +
                ", parent_id=" + parent_id +
                ", emengry_id=" + emengry_id +
                ", hasChild=" + hasChild +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", type=" + type +
                ", shsz_code='" + shsz_code + '\'' +
                '}';
    }
}
