package com.jen.exceldevice.service.serviceImpl;


import com.jen.exceldevice.dao.BaseDao;
import com.jen.exceldevice.pojo.Device;
import com.jen.exceldevice.service.DeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("DeviceService")
public class DeviceServiceImpl implements DeviceService {

    @Resource
    private BaseDao baseDao;

    @Override
    public List<Device> getDeviceList() {
        return baseDao.getList("deviceMapper.getDeviceList",null);
    }


    @Override
    public List<Device> getDeviceListBy(Device device) {
        return baseDao.getList("deviceMapper.getDeviceListBy",device);
    }

    @Override
    public void updateDeviceIsHigh(Device device) {
        baseDao.update("deviceMapper.updateDeviceIshigh",device);
    }
}
