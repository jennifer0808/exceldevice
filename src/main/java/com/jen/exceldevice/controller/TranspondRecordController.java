package com.jen.exceldevice.controller;

import com.jen.exceldevice.pojo.TranspondRecord;
import com.jen.exceldevice.service.TranspondRecordService;
import com.jen.exceldevice.utils.XMLParseUtils;
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
    public String transpond() {
        return "html/transpond";
    }

    @ResponseBody
    @RequestMapping("showTranspondData")
    public Map<String, Object> StudentManagerPage(
            @RequestParam(required = false, defaultValue = "15") int limit,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, value = "keyWord") int keyWord) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<TranspondRecord> transpondRecordList = transpondRecordService.findByPage(limit, page, keyWord);
            int count = transpondRecordService.queryCount(keyWord);
            map.put("code", 0);
            map.put("msg", "");
            map.put("count", count);
            map.put("data", transpondRecordList);
        } catch (Exception e) {
            logger.error("transpondRecord分页异常:", e.getMessage());
        }
        return map;
    }


    @RequestMapping("contentDetails")
    public String contentDetails(@RequestParam(required = false, value = "id") int id, HttpServletRequest request, Map<String, String> map) {
        TranspondRecord transpondRecord = transpondRecordService.findById(id);
        request.setAttribute("transpondRecord", transpondRecord);
        //xml解析
        if (transpondRecord.getContent() != null) {
            Map<String, String> map2 = XMLParseUtils.ContentParse(transpondRecord.getContent());
            map.put("a", map2.get("10000"));
            map.put("b", map2.get("10A00"));
            map.put("c", map2.get("10B00"));
            map.put("d", map2.get("10C00"));
            map.put("e", map2.get("10D00"));
        }
        return "html/contentDetails";
    }

    @RequestMapping("testxml")
    public String processXml() {
        return "html/testxml";
    }


}
