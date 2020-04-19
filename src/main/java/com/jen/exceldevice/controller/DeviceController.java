package com.jen.exceldevice.controller;

import com.jen.exceldevice.common.globalConfig.GlobalConstants;
import com.jen.exceldevice.pojo.*;
import com.jen.exceldevice.service.*;
import com.jen.exceldevice.utils.WriteExcelUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Controller
public class DeviceController {
    private final static Logger logger = LoggerFactory.getLogger(DeviceController.class);
    @Autowired
    DeviceService deviceService;
    @Autowired
    ProjectService projectService;
    @Autowired
    LinkService linkService;
    @Autowired
    DeviceRateService deviceRateService;
    @Autowired
    StastionService stastionService;
    @Autowired
    DriveService driveService;


    @RequestMapping("")
    public String index() {
        return "main";
    }

    @RequestMapping("/testexcel")
    public String excel() {
        return "html/testexcel";
    }

    @ResponseBody
    @RequestMapping("showData")
    public Map<String, Object> StudentManagerPage(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "15") int limit,
            String sUsername) {
//        System.out.println("sUsername:" + sUsername);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Device> deviceList = deviceService.findByPage(page, limit, sUsername);
            int count = deviceService.queryCount();
            map.put("code", 0);
            map.put("msg", "");
            map.put("count", count);
            map.put("data", deviceList);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return map;
    }


    //导出
    @RequestMapping("export")
    public void export(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=dpc.xlsx");
            OutputStream outputStream = null;
            //创建一个Excel文件
            SXSSFWorkbook workbook = new SXSSFWorkbook();//Excel2007

            //设置单元格格式居中
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);

            //创建工作表sheet1
            SXSSFSheet sheet1 = workbook.createSheet("中骏浦江项目");
            writeExcelSheet1(sheet1, cellStyle);
            //创建工作表sheet2
            SXSSFSheet sheet2 = workbook.createSheet("设备信息");
            writeExcelSheet2(sheet2, cellStyle);
            //创建工作表sheet3
            SXSSFSheet sheet3 = workbook.createSheet("设备变比");
            writeExcelSheet3(sheet3, cellStyle);

            //保存Excel文件
            outputStream = response.getOutputStream();
            //默认Excel名称
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //sheet1
    public void writeExcelSheet1(SXSSFSheet sheet1, CellStyle cellStyle) {
        for(int k=0;k<sheet1.getLastRowNum();k++){
            SXSSFRow row =  sheet1.getRow(k);
            if(k==0){
                writeExcelProject(row,cellStyle);
            }else if(k==1){
                writeExcelStastion(row,cellStyle);
            }else if(k==2){
                writeExcelDrive(row,cellStyle);
            }else if(k==3){
                writeExcelLink(row,cellStyle);
            }else if(k==4){
                writeExcelLinkIP(row,cellStyle);
            }
        }

    }

    //导入项目行
    public void writeExcelProject(SXSSFRow row, CellStyle cellStyle){

        SXSSFCell headCell = row.getCell(0);
        headCell.setCellValue("项目名称");
        headCell.setCellStyle(cellStyle);

        List<Project> projectList =  projectService.getProjectList();
        SXSSFCell DataCell = row.getCell(1);
        DataCell.setCellValue(projectList.get(0).getName());
        DataCell.setCellStyle(cellStyle);
    }

    //导入站点行
    public void writeExcelStastion(SXSSFRow row, CellStyle cellStyle){
        SXSSFCell headCell = row.getCell(0);
        headCell.setCellValue("站点名称");
        headCell.setCellStyle(cellStyle);

        //todo 2表联查
      List<Stastion>  stastionList =  stastionService.getStastionList(new Stastion(0,null));
        for(int i=0 ; i< stastionList.size();i++){
            SXSSFCell cell= row.getCell(i+1);
            cell.setCellValue(stastionList.get(i).getName());
            cell.setCellStyle(cellStyle);
        }
    }

    //导入驱动行
    public void writeExcelDrive(SXSSFRow row, CellStyle cellStyle){
        SXSSFCell headCell = row.getCell(0);
        headCell.setCellValue("站点协议信息");
        headCell.setCellStyle(cellStyle);


        //TODO 3表联查
       List<Drive> driveList = driveService.getDriveList(new Drive(0,0,null));
       for(int i=0 ; i< driveList.size();i++){
           SXSSFCell cell= row.getCell(i+1);
           cell.setCellValue(driveList.get(i).getProtocol_name());
           cell.setCellStyle(cellStyle);
       }
    }

    //导入链路行1
    public void writeExcelLink(SXSSFRow row, CellStyle cellStyle){
        SXSSFCell headCell = row.getCell(0);
        headCell.setCellValue("串口信息");
        headCell.setCellStyle(cellStyle);



    }
    //导入链路行2
    public void writeExcelLinkIP(SXSSFRow row, CellStyle cellStyle){

        SXSSFCell headCell = row.getCell(0);
        headCell.setCellValue("串口ip信息");
        headCell.setCellStyle(cellStyle);
    }

    //sheet2
    public void writeExcelSheet2(SXSSFSheet sheet2, CellStyle cellStyle) {

    }

    //sheet3
    public void writeExcelSheet3(SXSSFSheet sheet3, CellStyle cellStyle) {
        //添加表头行
        SXSSFRow row = sheet3.createRow(0);
        //添加表头内容
        SXSSFCell headCell = row.createCell(0);
        headCell.setCellValue("测点表");
        headCell.setCellStyle(cellStyle);

        headCell = row.createCell(1);
        headCell.setCellValue("倍率");
        headCell.setCellStyle(cellStyle);

        //添加数据内容
        List<DeviceRate> deviceRateList = deviceRateService.getDeviceRateListAll();
        for (int i = 0; i < deviceRateList.size(); i++) {
            row = sheet3.createRow(i + 1);
            DeviceRate deviceRate = deviceRateList.get(i);

            SXSSFCell cellContent = row.createCell(0);
            cellContent.setCellValue(deviceRate.getCombine_name());
            cellContent.setCellStyle(cellStyle);

            cellContent = row.createCell(1);
            cellContent.setCellValue(deviceRate.getRate());
            cellContent.setCellStyle(cellStyle);

        }

    }

    //导入
    @RequestMapping("readExcel")
    @ResponseBody
    public Map readExcel(@RequestParam MultipartFile file) {

        String fileName = file.getOriginalFilename();
        logger.info("fileName:" + fileName);
        Map map = new HashMap();
        try {
            GlobalConstants.isImportSuccess = linkService.readExcel(fileName, file);

            if (GlobalConstants.isImportSuccess) {
                map.put("code", 0);
                map.put("msg", "导入EXCEL成功！");
                return map;
            } else {
                map.put("code", 1);
                map.put("msg", "导入EXCEL失败！");
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


}
