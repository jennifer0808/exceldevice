package com.jen.exceldevice.controller;

import com.jen.exceldevice.common.globalConfig.GlobalConstants;
import com.jen.exceldevice.pojo.*;
import com.jen.exceldevice.service.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

        //读项目行
        SXSSFRow row = sheet1.createRow(0);

        SXSSFCell headCell = row.createCell(0);
        headCell.setCellValue("项目名称");
        headCell.setCellStyle(cellStyle);

        List<Project> projectList = projectService.getProjectList();
        int project_id = projectList.get(0).getId();
        SXSSFCell DataCell = row.createCell(1);
        DataCell.setCellValue(projectList.get(0).getName());
        DataCell.setCellStyle(cellStyle);

        //读站点行
        row = sheet1.createRow(1);

        headCell = row.createCell(0);
        headCell.setCellValue("站点名称");
        headCell.setCellStyle(cellStyle);

        List<Stastion> stastionList = stastionService.getStastionInnerAll();
        for (int i = 0; i < stastionList.size(); i++) {
            SXSSFCell cell = row.createCell(i + 1);
            cell.setCellValue(stastionList.get(i).getName());
            cell.setCellStyle(cellStyle);
        }

        //读驱动行
        row = sheet1.createRow(2);

        headCell = row.createCell(0);
        headCell.setCellValue("站点协议信息");
        headCell.setCellStyle(cellStyle);

        List<Drive> driveList = driveService.getDriveInnerAll();
        for (int i = 0; i < driveList.size(); i++) {
            SXSSFCell cell = row.createCell(i + 1);
            StringBuffer sb = new StringBuffer();
            sb.append(driveList.get(i).getStastion().getName()).append(":").append(driveList.get(i).getProtocol_name());
            cell.setCellValue(sb.toString());
            cell.setCellStyle(cellStyle);
        }

        //导入链路行
        row = sheet1.createRow(3);

        headCell = row.createCell(0);
        headCell.setCellValue("串口信息");
        headCell.setCellStyle(cellStyle);

        SXSSFRow row4 = sheet1.createRow(4);

        headCell = row4.createCell(0);
        headCell.setCellValue("串口ip信息");
        headCell.setCellStyle(cellStyle);

       List<Link> linkList = linkService.getLinkInnerAll();
       for(int i=0;i<linkList.size();i++){
           SXSSFCell cell = row.createCell(i + 1);
           StringBuffer sb = new StringBuffer();
           sb.append(linkList.get(i).getStastion().getName()).append(":").append(linkList.get(i).getDrive().getProtocol_name()).append("_").append(linkList.get(i).getName());
           cell.setCellValue(sb.toString());
           cell.setCellStyle(cellStyle);

           cell = row4.createCell(i + 1);
           StringBuffer sbIP = new StringBuffer();
           int type = linkList.get(i).getType();
           if(type==1){
               sbIP.append(linkList.get(i).getPortid());
           }else{//type==0
               sbIP.append(linkList.get(i).getIpaddress()).append(":").append(linkList.get(i).getPort());
           }
           cell.setCellValue(sbIP.toString());
           cell.setCellStyle(cellStyle);
       }

    }

    //sheet2
    public void writeExcelSheet2(SXSSFSheet sheet2, CellStyle cellStyle) {
        //读项目行
        SXSSFRow row = sheet2.createRow(0);

        SXSSFCell headCell = row.createCell(0);
        headCell.setCellValue("项目名称");
        headCell.setCellStyle(cellStyle);

        List<Project> projectList = projectService.getProjectList();
        int project_id = projectList.get(0).getId();
        SXSSFCell DataCell = row.createCell(1);
        DataCell.setCellValue(projectList.get(0).getName());
        DataCell.setCellStyle(cellStyle);

        //表头行
        row = sheet2.createRow(1);

        //添加表头内容
        headCell = row.createCell(0);
        headCell.setCellValue("序号");
        headCell.setCellStyle(cellStyle);

        headCell = row.createCell(1);
        headCell.setCellValue("回路名称");
        headCell.setCellStyle(cellStyle);

        headCell = row.createCell(2);
        headCell.setCellValue("设备编号");
        headCell.setCellStyle(cellStyle);

        headCell = row.createCell(3);
        headCell.setCellValue("物理地址");
        headCell.setCellStyle(cellStyle);


        headCell = row.createCell(4);
        headCell.setCellValue("站点");
        headCell.setCellStyle(cellStyle);


        headCell = row.createCell(5);
        headCell.setCellValue("协议 ");
        headCell.setCellStyle(cellStyle);


        headCell = row.createCell(6);
        headCell.setCellValue("串口");
        headCell.setCellStyle(cellStyle);

        headCell = row.createCell(7);
        headCell.setCellValue("表型号");
        headCell.setCellStyle(cellStyle);

        headCell = row.createCell(8);
        headCell.setCellValue("分类分项代码");
        headCell.setCellStyle(cellStyle);


        headCell = row.createCell(9);
        headCell.setCellValue("默认超时时间");
        headCell.setCellStyle(cellStyle);


        headCell = row.createCell(10);
        headCell.setCellValue("是否虚拟表");
        headCell.setCellStyle(cellStyle);

        headCell = row.createCell(11);
        headCell.setCellValue("市政建筑编号");
        headCell.setCellStyle(cellStyle);

        //添加数据内容
        List<Device> deviceList = deviceService.getDeviceInnerAll();
        for(int i=0 ;i<deviceList.size();i++){
            row = sheet2.createRow(i + 2);
            Device device = deviceList.get(i);

            SXSSFCell cellContent = row.createCell(0);
            cellContent.setCellValue(i+1);
            cellContent.setCellStyle(cellStyle);


            cellContent = row.createCell(1);
            cellContent.setCellValue(device.getDescribe());
            cellContent.setCellStyle(cellStyle);

            cellContent = row.createCell(2);
            cellContent.setCellValue(device.getName());
            cellContent.setCellStyle(cellStyle);


            cellContent = row.createCell(3);
            cellContent.setCellValue(device.getAddress());
            cellContent.setCellStyle(cellStyle);

            cellContent = row.createCell(4);
            cellContent.setCellValue(device.getStastion().getName());
            cellContent.setCellStyle(cellStyle);


            cellContent = row.createCell(5);
            cellContent.setCellValue(device.getDrive().getProtocol_name());
            cellContent.setCellStyle(cellStyle);


            cellContent = row.createCell(6);
            cellContent.setCellValue(device.getLink().getName());
            cellContent.setCellStyle(cellStyle);


            cellContent = row.createCell(7);
            cellContent.setCellValue(device.getDevice_type_name());
            cellContent.setCellStyle(cellStyle);


            cellContent = row.createCell(8);
            cellContent.setCellValue(device.getDeviceCategory().getShsz_code());
            cellContent.setCellStyle(cellStyle);

            cellContent = row.createCell(9);
            cellContent.setCellValue(device.getOvertime_period());
            cellContent.setCellStyle(cellStyle);

            cellContent = row.createCell(10);
            cellContent.setCellValue(device.getIs_virtual());
            cellContent.setCellStyle(cellStyle);


            cellContent = row.createCell(11);
            cellContent.setCellValue(device.getShsz_id());
            cellContent.setCellStyle(cellStyle);


        }

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
