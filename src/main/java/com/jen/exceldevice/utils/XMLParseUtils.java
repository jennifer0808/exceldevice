package com.jen.exceldevice.utils;

import org.dom4j.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class XMLParseUtils {

    public static Map<String, Object> ContentParse(String content) {
        System.err.println("content:" + content);
        Map<String, Object> map = new HashMap<>();
        try {
            //Document接收
            Document document = DocumentHelper.parseText(content);
            //获取根节点元素对象
            if (document != null) {
                Element root = document.getRootElement();
                Element element = root.element("data").element("energy_items");

                List<Element> list = element.elements();
                for (Element element1 : list) {
                    String code = element1.attribute("code").getValue();
                    if ("10000".equalsIgnoreCase(code) || "10A00".equalsIgnoreCase(code) || "10B00".equalsIgnoreCase(code) || "10C00".equalsIgnoreCase(code) || "10D00".equalsIgnoreCase(code)) {
                        String value = element1.getText();
                        map.put(code, value);
                        if ("10D00".equalsIgnoreCase(code)) {
                            break;
                        }
                    }
                }
            }



        } catch (DocumentException e) {
            e.printStackTrace();
        }


        return map;
    }

}
