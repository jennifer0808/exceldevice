package com.jen.exceldevice.service;


import com.jen.exceldevice.pojo.Device;

import java.util.List;

public interface DeviceService {


    List<Device>  findByPage(int page, int limit, String sUsername);

    int queryCount();
}
