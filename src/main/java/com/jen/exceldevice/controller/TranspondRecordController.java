package com.jen.exceldevice.controller;

import com.jen.exceldevice.pojo.TranspondRecord;
import com.jen.exceldevice.service.TranspondRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TranspondRecordController {
    private final static Logger logger = LoggerFactory.getLogger(TranspondRecordController.class);

    @Autowired
    private TranspondRecordService transpondRecordService;

    @RequestMapping("/transpond")
    public String transpond(){
        return "html/transpond";
    }

    @ResponseBody
    @RequestMapping("showTranspondData")
    public Map<String, Object> StudentManagerPage(
            @RequestParam(required = false, defaultValue = "15") int limit,
            @RequestParam(required = false,defaultValue = "1") int page,
           @RequestParam(required=false,value = "keyWord") int keyWord){
        System.err.println("keyWord:"+keyWord);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<TranspondRecord> transpondRecordList = transpondRecordService.findByPage(limit, page, keyWord);
//            logger.info(transpondRecordList.get(0).getContent());
            int count = transpondRecordService.queryCount();
            map.put("code",0);
            map.put("msg","");
            map.put("count",count);
            map.put("data",transpondRecordList);
        }catch (Exception e){
            logger.error("transpondRecord分页异常:",e.getMessage());
        }
        return map;
    }



    @RequestMapping("contentDetails")
    public String contentDetails(@RequestParam(required=false,value = "id")int id, HttpServletRequest request){
        System.err.println("id:"+id);

        TranspondRecord transpondRecord = transpondRecordService.findById(id);
        System.err.println("content:"+transpondRecord.getContent());
        request.setAttribute("transpondRecord", transpondRecord);
        return "html/contentDetails";
    }



}
