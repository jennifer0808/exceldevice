package com.jen.exceldevice.utils;

import com.jen.exceldevice.pojo.Device;
import com.jen.exceldevice.pojo.Project;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ReadExcelUtils {

    //读取excel项目信息
    public static Project readProject(Row row) {

        String projectName = row.getCell(1).getStringCellValue();
        System.err.println(projectName);
        Project project = new Project();
        project.setName(projectName);
        return project;
    }

    //读取stastion,drive,link信息
    public static  List<String>  readCellByRow(Row row){
        List<String> list = new ArrayList<>();
        for(int k=1;k<row.getLastCellNum();k++){
            Cell cell =  row.getCell(k);
            String str = cell.getStringCellValue();
            if(cell == null || "".equals(str)){
                break;
            }
            list.add(str);
        }
        return list;
    }





}
