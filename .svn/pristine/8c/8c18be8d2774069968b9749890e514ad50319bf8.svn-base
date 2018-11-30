package com.medhead.kf100.common.util;


import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    /**
     *  md5加密产生，产生128位（bit）的mac 28 将128bit Mac转换成16进制代码 29
     *
     * @param strSrc 字符串
     * @return 加密结果
     */
    public static String MD5Encode(String strSrc) {
        return MD5Encode(strSrc, "");
    }

    /**
     * md5加密产生，产生128位（bit）的mac 28 将128bit Mac转换成16进制代码 29
     *
     * @param strSrc 字符串
     * @param key    key
     * @return 加密结果
     */
    public static String MD5Encode(String strSrc, String key) {

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(strSrc.getBytes("UTF-8"));
            StringBuilder result = new StringBuilder(32);
            byte[] temp;
            temp = md5.digest(key.getBytes("UTF-8"));
            for (byte aTemp : temp) {
                result.append(Integer.toHexString((0x000000ff & aTemp) | 0xffffff00).substring(6));
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    /**
     * 加密
     *
     * @param pwd 密码
     * @param salt 盐值
     * @return 返回MD5摘要
     */
    public static String MD5EncodePwd(String pwd, String salt) {
        String hashAlgorithmName = "MD5";
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        Object obj = new SimpleHash(hashAlgorithmName, pwd, credentialsSalt, 2);
        return obj.toString();
    }


    public static String getMessageDigest(byte[] buffer) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(buffer);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param str 字符串
     * @return 32位小写MD5
     */
    public static String parseStrToMd5L32(String str) {
        if(str == null){
            return null;
        }
        String reStr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                int bt = b & 0xff;
                if(bt < 16) {
                    sb.append(0);
                }
                sb.append(Integer.toHexString(bt));
            }
            reStr = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return reStr;
    }
}

