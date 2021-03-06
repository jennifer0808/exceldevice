package com.jen.exceldevice.service;

import com.jen.exceldevice.pojo.DeviceRate;

import java.util.List;

public interface DeviceRateService {

    List<DeviceRate> getRateListBy(DeviceRate rate);

    int insertDeviceRate(DeviceRate rate);

    List<DeviceRate> getDeviceRateListAll();
}
