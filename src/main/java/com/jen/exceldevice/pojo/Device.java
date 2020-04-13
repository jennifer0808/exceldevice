package com.jen.exceldevice.pojo;

public class Device {

    private int id;
    private int project_id;
    private int stastion_id;
    private int drive_id;
    private int link_id;
    private String name;
    private String describe;
    private String address;
    private int start_period;
    private int overtime_period;
    private String device_type_name;
    private String protocol_name;
    private String shsz_id;
    private int category_id;
    private int is_virtual;
    private int is_highgather;

    private int parent_id = 0;

    public  Device(){}

    public Device(int id, int project_id, int stastion_id, int drive_id, int link_id, String name, String describe, String address, int start_period, int overtime_period, String device_type_name, String protocol_name, String shsz_id, int category_id, int is_virtual, int is_highgather, int parent_id) {
        this.id = id;
        this.project_id = project_id;
        this.stastion_id = stastion_id;
        this.drive_id = drive_id;
        this.link_id = link_id;
        this.name = name;
        this.describe = describe;
        this.address = address;
        this.start_period = start_period;
        this.overtime_period = overtime_period;
        this.device_type_name = device_type_name;
        this.protocol_name = protocol_name;
        this.shsz_id = shsz_id;
        this.category_id = category_id;
        this.is_virtual = is_virtual;
        this.is_highgather = is_highgather;
        this.parent_id = parent_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getStastion_id() {
        return stastion_id;
    }

    public void setStastion_id(int stastion_id) {
        this.stastion_id = stastion_id;
    }

    public int getDrive_id() {
        return drive_id;
    }

    public void setDrive_id(int drive_id) {
        this.drive_id = drive_id;
    }

    public int getLink_id() {
        return link_id;
    }

    public void setLink_id(int link_id) {
        this.link_id = link_id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStart_period() {
        return start_period;
    }

    public void setStart_period(int start_period) {
        this.start_period = start_period;
    }

    public int getOvertime_period() {
        return overtime_period;
    }

    public void setOvertime_period(int overtime_period) {
        this.overtime_period = overtime_period;
    }

    public String getDevice_type_name() {
        return device_type_name;
    }

    public void setDevice_type_name(String device_type_name) {
        this.device_type_name = device_type_name;
    }

    public String getProtocol_name() {
        return protocol_name;
    }

    public void setProtocol_name(String protocol_name) {
        this.protocol_name = protocol_name;
    }

    public String getShsz_id() {
        return shsz_id;
    }

    public void setShsz_id(String shsz_id) {
        this.shsz_id = shsz_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getIs_virtual() {
        return is_virtual;
    }

    public void setIs_virtual(int is_virtual) {
        this.is_virtual = is_virtual;
    }

    public int getIs_highgather() {
        return is_highgather;
    }

    public void setIs_highgather(int is_highgather) {
        this.is_highgather = is_highgather;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", project_id=" + project_id +
                ", stastion_id=" + stastion_id +
                ", drive_id=" + drive_id +
                ", link_id=" + link_id +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", address='" + address + '\'' +
                ", start_period=" + start_period +
                ", overtime_period=" + overtime_period +
                ", device_type_name='" + device_type_name + '\'' +
                ", protocol_name='" + protocol_name + '\'' +
                ", shsz_id='" + shsz_id + '\'' +
                ", category_id=" + category_id +
                ", is_virtual=" + is_virtual +
                ", is_highgather=" + is_highgather +
                '}';
    }
}
