package com.cqupt.project.utils;

import java.security.MessageDigest;

public class MD5 {
	private static final char[] hex = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'}; 
    public static String Encryption(String str) {
    	String result=null;
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");//申明使用MD5算法
            result= byte2str(md5.digest(str.getBytes("UTF-8")));
        }catch(Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
   
    /**
     * 
     * 把通过MD5算法转成的字节数组转换成十六进制字符串
     * @param bytes
     * @return
     */
    private static String byte2str(byte []bytes){
        int len = bytes.length;   
        StringBuffer result = new StringBuffer();    
        for (int i = 0; i < len; i++) {   
            byte byte0 = bytes[i];   
            result.append(hex[byte0 >>> 4 & 0xf]);   
            result.append(hex[byte0 & 0xf]);   
        }
        return result.toString();
    }
}
