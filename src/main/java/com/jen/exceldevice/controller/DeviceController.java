package com.jen.exceldevice.controller;

import com.jen.exceldevice.pojo.Device;
import com.jen.exceldevice.service.DeviceService;
import com.jen.exceldevice.service.LinkService;
import com.jen.exceldevice.service.ProjectService;
import com.jen.exceldevice.utils.WriteExcelUtils;
import org.apache.logging.slf4j.Log4jLogger;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
//        throw new MyException(11, e.getMessage());
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
//            Map<String,Object> resultMap = new HashMap<>();
            List<String> listHead = new ArrayList<>();
            Map<String,Map<String,Object>> resultMap = new HashMap<>();


//            resultMap.put("项目名称",);//queryProject();

            //判空
//           Map<String, List<School>> resultMap = School.getJsonMap();
//            if (null == resultMap) {
//                workbook.write(outputStream);
//                outputStream.flush();
//                outputStream.close();
//                return;
//            }

            WriteExcelUtils.createExcelSheet(workbook,"中骏浦江项目",listHead,resultMap);
//            createExcelSheet(workbook,"设备信息",listHead,resultMap);
//            createExcelSheet(workbook,"设备变比",listHead,resultMap);
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


    //导入
    @RequestMapping("readExcel")
    @ResponseBody
    public Map readExcel(@RequestParam MultipartFile file) {
        boolean isSuccess = false;
        String fileName = file.getOriginalFilename();
        logger.info("fileName:" + fileName);
        Map map = new HashMap();
        try {
            isSuccess = linkService.readExcel(fileName, file);

            if (isSuccess) {
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
