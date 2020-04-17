package com.jen.exceldevice.utils;

import com.jen.exceldevice.common.globalConfig.GlobalConstants;
import com.jen.exceldevice.pojo.Project;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ReadExcelUtils {
    private final static Logger logger = LoggerFactory.getLogger(ReadExcelUtils.class);

    //sheet1:读取excel项目信息
    public static Project readProject(Row row) {

        String projectName = row.getCell(1).getStringCellValue();
        System.err.println(projectName);
        Project project = new Project();
        project.setName(projectName);
        return project;
    }

    //sheet1:读取stastion,drive,link信息
    public static List<String> readCellByRow(Row row) {
        List<String> list = new ArrayList<>();
        for (int k = 1; k < row.getLastCellNum(); k++) {
            Cell cell = row.getCell(k);
            String str = cell.getStringCellValue();
            if (cell == null || "".equals(str)) {
                break;
            }
            list.add(str);
        }
        return list;
    }

    //sheet2:处理每行数据
    public static void readDeviceData(StringBuffer stringBuffer, int projectId) {
        String[] str = stringBuffer.toString().split(";");
        for (int i = 0; i <= str.length; i++) {
            try {
                GlobalConstants.deviceDescribe = str[1].trim();
                GlobalConstants.deviceName = str[2].trim();
                GlobalConstants.deviceAddress = str[3].trim();
                GlobalConstants.deviceStastionName = str[4].trim();
                GlobalConstants.deviceDriveProtocolName = str[5].trim();
                GlobalConstants.deviceLinkName = str[6].trim();
                GlobalConstants.deviceTypeName = str[7].trim();
                GlobalConstants.deviceCategoryShszCode = str[8].trim();
                GlobalConstants.deviceOvertime = Integer.valueOf(str[9].trim());
                GlobalConstants.deviceIsvirtual = Integer.valueOf(str[10].trim());
                GlobalConstants.deviceShszId = str[11].trim();

            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    //sheet2:匹配获取ID
    public static String matchReceiveId(String stastionName, String driveProtocolName, String linkName) {
        StringBuffer sb = new StringBuffer();
        sb.append(stastionName).append(":").append(driveProtocolName).append(":").append(linkName);

        Map<String, String> map = GlobalConstants.linkMap;
        for (String key : map.keySet()) {
            if (key.equals(sb.toString())) {
                sb.delete(0, sb.length());
                sb.append(map.get(key));
                break;
            }
        }

        return sb.toString();
    }


}
