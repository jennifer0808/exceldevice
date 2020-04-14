package com.jen.exceldevice.utils;

import com.jen.exceldevice.pojo.Drive;
import com.jen.exceldevice.pojo.Link;
import com.jen.exceldevice.pojo.Project;
import com.jen.exceldevice.pojo.Stastion;
import com.jen.exceldevice.service.ProjectService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;


public class ReadExcelUtils {



    //读取excel项目信息
    public static Project readProject(Row row) {

        String projectName = row.getCell(1).getStringCellValue();
        System.err.println(projectName);
        Project project = new Project();
        project.setName(projectName);
        return project;
    }

    //读取excel站点信息
    public static List<Stastion> readStastion(Row row) {
        List<Stastion> list = new ArrayList<>();
        for (int k = 1; k < row.getLastCellNum(); k++) {
            String SatastionName = row.getCell(k).getStringCellValue();
            if (row.getCell(k) == null || "".equals(SatastionName)) {
                break;
            }
            Stastion stastion = new Stastion();
            stastion.setName(SatastionName);
            list.add(stastion);
        }
        System.out.println(list.size());
        return list;
    }


    //读取excel驱动信息
    public static List<Drive> readDrive(Row row) {
        List<Drive> driveList = new ArrayList<>();
        for (int k = 0; k < row.getLastCellNum(); k++) {
            Cell cell = row.getCell(k+1);
            String driveStr = cell.getStringCellValue();
            if (cell == null || "".equals(driveStr)) {
                break;
            }
            Drive drive = new Drive();
            String s= driveStr.split(":")[1];
            System.out.println(s);

            drive.setProtocol_name(s);
            driveList.add(drive);
        }
        return driveList;
    }

    //读取excel链路信息
    public static List<Link> readLink(Sheet sheet) {
        List<Link> linkList = new ArrayList<>();
        Link link = new Link();

        for(int i=3;i<4;i++){
            Row row = sheet.getRow(i);
            for (int k = i; k < row.getLastCellNum(); k++) {
                Cell cell = row.getCell(k-2);
                String cellStr = cell.getStringCellValue();
                if (cell == null || "".equals(cellStr)) {
                    break;
                }
                if(i==3){
                    link.setName(cellStr.split("_")[1]);
                }if(i==4){
                    link.setIpaddress(cellStr.split(":")[0]);
                    link.setPort(Integer.valueOf(cellStr.split(":")[1]));
                    link.setType(Integer.valueOf(cellStr.split(":")[2]));
                }
            }

            linkList.add(link);
        }

        System.out.println(linkList);
        return linkList;
    }

}
