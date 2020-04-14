package com.jen.exceldevice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface LinkMapper {

     Map<String,Object> selectLinkAll();
}
