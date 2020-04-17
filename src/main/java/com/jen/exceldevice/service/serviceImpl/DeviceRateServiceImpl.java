package com.jen.exceldevice.service.serviceImpl;

import com.jen.exceldevice.dao.BaseDao;
import com.jen.exceldevice.pojo.DeviceRate;
import com.jen.exceldevice.service.DeviceRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deviceRateService")
public class DeviceRateServiceImpl implements DeviceRateService {
    @Autowired
    BaseDao baseDao;

    @Override
    public List<DeviceRate> getRateListBy(DeviceRate rate) {

        return baseDao.getList("DeviceRateMapper.getRateListBy",rate);
    }

    @Override
    public int inserDeviceRate(DeviceRate rate) {
        return baseDao.delete("DeviceRateMapper.inserDeviceRate",rate);
    }
}
