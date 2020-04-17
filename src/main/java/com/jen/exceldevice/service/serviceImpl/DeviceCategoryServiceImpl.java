package com.jen.exceldevice.service.serviceImpl;

import com.jen.exceldevice.mapper.DeviceCategoryMapper;
import com.jen.exceldevice.service.DeviceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deviceCategoryService")
public class DeviceCategoryServiceImpl implements DeviceCategoryService {
    @Autowired
    DeviceCategoryMapper deviceCategoryMapper;


    @Override
    public int queryCategoryListByCode(String deviceCategoryShszCode) {
        return deviceCategoryMapper.getCategoryListByCode(deviceCategoryShszCode);
    }
}
