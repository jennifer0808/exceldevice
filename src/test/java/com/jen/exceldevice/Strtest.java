package com.jen.exceldevice;

public class Strtest {
    public static void main(String[] args) {
        String str = "new";
        StringBuffer sb = new StringBuffer();
        sb.append("n").append("e").append("w");
//        sb.delete(0,sb.length());
//        System.out.println(str.equals(sb));
//
//        System.out.println(str.equals(sb.toString()));
        System.out.println(sb!=null);//true
        System.out.println("".contains(sb));//false
    }
}
