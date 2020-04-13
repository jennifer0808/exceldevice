package com.jen.exceldevice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeviceController {

    @RequestMapping("")
    public String index(){
        return "main.html";
    }


}
