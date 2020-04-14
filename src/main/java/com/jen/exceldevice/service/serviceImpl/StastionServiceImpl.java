package com.jen.exceldevice.service.serviceImpl;

import com.jen.exceldevice.dao.BaseDao;
import com.jen.exceldevice.service.StastionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stastionService")
public class StastionServiceImpl implements StastionService {

    @Autowired
    BaseDao baseDao;
    @Override
    public int  getStastionListById(int id) {
        return baseDao.save("StastionMapper.getStastionListById",id);
    }
}
