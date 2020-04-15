package com.jen.exceldevice.service;

import com.jen.exceldevice.pojo.Drive;

import java.util.List;

public interface DriveService {
    List<Drive>  getDriveList(Drive drive);

    List<Drive> getDriveListID(List<String> list);

    int insertDrive(Drive drive);
}
