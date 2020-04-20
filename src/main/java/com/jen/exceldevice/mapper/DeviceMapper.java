package com.jen.exceldevice.mapper;

import com.jen.exceldevice.pojo.Device;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DeviceMapper  {

    List<Device> selectByPage(@Param("page") int page, @Param("limit") int limit, @Param("keyWord") String keyWord);

    int selectCount();

    int selectInnerAll(@Param("projectId")int projectId, @Param("stastionName")String stastionName, @Param("driveProtocolName")String driveProtocolName, @Param("linkName")String linkName);

    int insertDevice(Device device);

    List<Device> selectDeviceInnerAll();

}
