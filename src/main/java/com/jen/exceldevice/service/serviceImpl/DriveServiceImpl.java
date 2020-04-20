package com.jen.exceldevice.service.serviceImpl;

import com.jen.exceldevice.dao.BaseDao;
import com.jen.exceldevice.pojo.Drive;
import com.jen.exceldevice.service.DriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("driveService")
public class DriveServiceImpl implements DriveService {

    @Autowired
    BaseDao baseDao;

    @Override
    public List<Drive> getDriveList(Drive drive) {
        return baseDao.getList("DriveMapper.getDriveList",drive);
    }


    @Override
    public List<Drive> getDriveListID(List<String> list) {

        return baseDao.getList("DriveMapper.getDriveListID",list);
    }

    @Override
    public int insertDrive(Drive drive) {
        return baseDao.save("DriveMapper.insertDrive",drive);
    }

    @Override
    public List<Drive> getDriveInnerAll() {
        return baseDao.getList("DriveMapper.getDriveInnerAll",null);
    }
}
