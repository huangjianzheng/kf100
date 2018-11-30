package com.medhead.kf100.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期处理工具类
 */
public class DateUtils {

    /**
     * 定义常量
     **/
    public static final String DATE_JFP_STR = "yyyyMM";
    public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_SMALL_STR = "yyyy-MM-dd";
    public static final String DATE_STR = "yyyyMMdd";
    public static final String DATE_KEY_STR = "yyMMddHHmmss";

    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     */
    public static Date parse(String strDate) {
        return parse(strDate, DATE_FULL_STR);
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 两个时间比较
     *
     */
    public static int compareDateWithNow(Date date) {
        Date date2 = new Date();
        return date.compareTo(date2);
    }

    /**
     * 两个时间比较(时间戳比较)
     *
     */
    public static int compareDateWithNow(long date) {
        long date2 = dateToUnixTimestamp();
        return Long.compare(date, date2);
    }


    /**
     * 获取系统当前时间
     *
     */
    public static String getNowTime() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_STR);
        return df.format(new Date());
    }

    public static String getDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_SMALL_STR);
        return df.format(date);
    }

    public static String getDateWithSecnd(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_STR);
        return df.format(date);
    }

    public static String getCurDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_STR);
        return df.format(date);
    }


    /**
     * 获取系统当前时间
     *
     */
    public static String getNowTime(String type) {
        SimpleDateFormat df = new SimpleDateFormat(type);
        return df.format(new Date());
    }

    /**
     * 获取系统当前计费期
     *
     */
    public static String getJFPTime() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_JFP_STR);
        return df.format(new Date());
    }

    /**
     * 将指定的日期转换成Unix时间戳
     *
     * @param date 需要转换的日期 yyyy-MM-dd HH:mm:ss
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(DATE_FULL_STR).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 将指定的日期转换成Unix时间戳
     *
     * @param date 需要转换的日期 yyyy-MM-dd
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date, String dateFormat) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(dateFormat).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 将当前日期转换成Unix时间戳
     *
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp() {
        return new Date().getTime();
    }


    /**
     * 将Unix时间戳转换成日期
     *
     * @param timestamp 时间戳
     * @return String 日期字符串
     */
    public static String unixTimestampToDate(long timestamp) {
        SimpleDateFormat sd = new SimpleDateFormat(DATE_FULL_STR);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sd.format(new Date(timestamp));
    }


    public static String dateToString(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date currentStartDate = calendar.getTime();
        // 获取昨天起始时间
        calendar.add(Calendar.DATE, -1);
        Date yesterdayStartDate = calendar.getTime();
        // 获取前天起始时间
        calendar.add(Calendar.DATE, -1);
        Date beforeYesterdayStartDate = calendar.getTime();
        // 获取今天起始时间

        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        if(date.compareTo(currentStartDate) >= 0) {
            return df.format(date);
        } else if(date.compareTo(yesterdayStartDate) >= 0) {
            return "昨天 " + df.format(date);
        } else if(date.compareTo(beforeYesterdayStartDate) >= 0) {
            return "前天" + df.format(date);
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormat.format(date);
        }
    }


}