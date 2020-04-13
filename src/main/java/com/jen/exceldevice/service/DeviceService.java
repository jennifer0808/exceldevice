package com.jen.exceldevice.service;


import com.jen.exceldevice.pojo.Device;

import java.util.List;

public interface DeviceService {

    public List<Device> getDeviceList();

    public List<Device> getDeviceListBy(Device device);

    public void updateDeviceIsHigh(Device device);

}
