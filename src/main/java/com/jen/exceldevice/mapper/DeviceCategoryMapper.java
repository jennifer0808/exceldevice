package com.jen.exceldevice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DeviceCategoryMapper {
    int getCategoryListByCode(@Param("deviceCategoryShszCode") String deviceCategoryShszCode);
}
