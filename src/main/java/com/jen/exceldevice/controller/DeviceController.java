package com.jen.exceldevice.controller;

import com.jen.exceldevice.pojo.Device;
import com.jen.exceldevice.service.DeviceService;
import org.apache.logging.slf4j.Log4jLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DeviceController {
    private final static Logger logger = LoggerFactory.getLogger(DeviceController.class);
    @Autowired
    DeviceService deviceService;

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
        System.out.println("sUsername:" + sUsername);
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
    public void export(HttpServletResponse response){

    }





}
