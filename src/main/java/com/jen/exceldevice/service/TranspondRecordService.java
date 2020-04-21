package com.jen.exceldevice.service;

import com.jen.exceldevice.pojo.TranspondRecord;

import java.util.List;

public interface TranspondRecordService {
    List<TranspondRecord> findByPage(int limit,int page,int keyWord);
    int queryCount();
}
