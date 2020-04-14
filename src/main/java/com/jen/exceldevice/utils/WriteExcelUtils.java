package com.jen.exceldevice.utils;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.List;
import java.util.Map;

/**
 * 导出excel文件
 */
public class WriteExcelUtils {

    public static void createExcelSheet(SXSSFWorkbook workbook, String sheetName, List<String> listHead,Map<String,Map<String,Object>> resultMap){
        SXSSFSheet sheet = workbook.createSheet(sheetName);
        SXSSFRow row = sheet.createRow(5);




    }

}
