package com.jen.exceldevice.pojo;

import java.io.Serializable;
import java.util.List;

public class DeviceRate implements Serializable {
    private final static long  serialVersionUID =1L;
    private Integer id;
    private String combine_name;
    private Double rate;

    public DeviceRate(){}

    public DeviceRate(Integer id, String combine_name, Double rate) {
        this.id = id;
        this.combine_name = combine_name;
        this.rate = rate;
    }

    public DeviceRate(String combine_name, Double rate) {
        this.combine_name = combine_name;
        this.rate = rate;
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

    public String getCombine_name() {
        return combine_name;
    }

    public void setCombine_name(String combine_name) {
        this.combine_name = combine_name;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }


    @Override
    public String toString() {
        return "DeviceRate{" +
                "id=" + id +
                ", combine_name='" + combine_name + '\'' +
                ", rate=" + rate +
                '}';
    }


}
