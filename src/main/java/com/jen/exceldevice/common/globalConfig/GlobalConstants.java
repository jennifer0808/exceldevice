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

    //存放id
    public static Map<String, Integer> stastionMap = new HashMap<>();
    public static Map<String, Integer> driveMap = new HashMap<>();
    public static Map<String, String> linkMap = new HashMap<>();
    public static  Map<String,Integer> categoryMap = new HashMap<>();

    //设备属性初始化
    public static String deviceDescribe = null;
    public static String deviceName = null;
    public static String deviceAddress = null;
    public static String deviceStastionName = null;
    public static String deviceDriveProtocolName = null;
    public static String deviceLinkName = null;
    public static String deviceTypeName = null;
    public static  String deviceCategoryShszCode = null;
    public static  Integer deviceOvertime = null;
    public static Integer deviceIsvirtual = null;
    public static  String deviceShszId = null;



}
