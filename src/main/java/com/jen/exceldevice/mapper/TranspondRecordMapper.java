package com.jen.exceldevice.mapper;

import com.jen.exceldevice.pojo.TranspondRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TranspondRecordMapper {

List<TranspondRecord>  selectByPage(@Param("limit")int limit , @Param("page")int page,@Param("keyWord")int keyWord);

int selectCount();


}
