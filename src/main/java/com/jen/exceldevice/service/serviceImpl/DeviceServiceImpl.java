package com.jen.exceldevice.service.serviceImpl;


import com.jen.exceldevice.mapper.DeviceMapper;
import com.jen.exceldevice.pojo.Device;
import com.jen.exceldevice.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {
    private final static Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

    @Autowired
    DeviceMapper deviceMapper;


    @Override
    public List<Device> findByPage(int page, int limit, String keyWord) {
        page = (page - 1) * limit;
        List<Device> list = deviceMapper.selectByPage(page, limit, keyWord);
        System.out.println(list.size());
        return list;
    }

    @Override
    public int queryCount() {

        int count = 0;
        count = deviceMapper.selectCount();
        return count;
    }

    @Override
    public int queryInnerAll(int projectId, String stastionName, String driveProtocolName, String linkName) {
        return deviceMapper.selectInnerAll(projectId, stastionName, driveProtocolName, linkName);
    }

    @Override
    public int saveDevice(Device device) {
        return deviceMapper.insertDevice(device);
    }

    @Override
    public List<Device> getDeviceInnerAll() {

        return deviceMapper.selectDeviceInnerAll();
    }
}
