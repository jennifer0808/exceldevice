package com.jen.exceldevice.service.serviceImpl;

import com.jen.exceldevice.dao.BaseDao;
import com.jen.exceldevice.pojo.Stastion;
import com.jen.exceldevice.service.StastionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("stastionService")
public class StastionServiceImpl implements StastionService {

    @Autowired
    BaseDao baseDao;


    @Override
    public int getStastionListBy(Stastion stastion) {
        int count =0;
        count = baseDao.save("StastionMapper.getStastionListBy",stastion);
        return count;
    }

    @Override
    public int insertStastion(Stastion stastion) {
        return baseDao.save("StastionMapper.insertStastion",stastion);
    }

    @Override
    public int updateStasttion(Stastion stastion) {
        int count  =0 ;
        count = baseDao.update("StastionMapper.updateStastion",stastion);
        return count;
    }


}
