package com.jen.exceldevice.common.globalConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局变量
 */
public class GlobalConstants {
    //导入成功判断标识符
    public static boolean isImportSuccess = false;
    //链路串口判断标识符
    public static boolean serverComFlag = false;

    public static Map<String, Integer> stastionMap = new HashMap<>();
    public static Map<String, Integer> driveMap = new HashMap<>();
    public static Map<String, Integer> linkMap = new HashMap<>();


}
