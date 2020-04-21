package com.jen.exceldevice.service.serviceImpl;

import com.jen.exceldevice.mapper.TranspondRecordMapper;
import com.jen.exceldevice.pojo.TranspondRecord;
import com.jen.exceldevice.service.TranspondRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("transpondRecordService")
public class TranspondRecordServiceImpl implements TranspondRecordService {
    @Autowired
    private TranspondRecordMapper transpondRecordMapper;


    @Override
    public List<TranspondRecord> findByPage(int limit, int page,int keyWord) {
        page = (page-1)*limit;
        return transpondRecordMapper.selectByPage(limit, page,keyWord);
    }

    @Override
    public int queryCount() {
        return transpondRecordMapper.selectCount();
    }
}
