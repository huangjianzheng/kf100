package com.medhead.kf100.common.util.sms;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class SmsUtils {

    private static final Logger LOG = LoggerFactory.getLogger(SmsUtils.class);

    /**
     * 批量发送短信方法
     *
     * @param phoneNumList 最大数为1000；
     * @param content      发送内容， 65个汉字为一条。
     */
    public static void batchSendSms(List<String> phoneNumList, String content) {
        if(phoneNumList.size() > 1000) {
            //需要拆分
            int i = phoneNumList.size() / 1000;
            for (int j = 0; j < i; j++) {
                sendSms(phoneNumList.subList(j * 1000, j * 1000 + 1000), content);
            }
            //把一千整数部分的后面的发掉。
            sendSms(phoneNumList.subList(i * 1000, phoneNumList.size()), content);

        } else {
            sendSms(phoneNumList, content);
        }
    }


    private static void sendSms(List phoneNumList, String content) {
        String info = null;
        String result = null;
        if(phoneNumList == null || phoneNumList.size() == 0) {
            info = "手机号码为空";
            LOG.error(info);
        }
        try {
            SmsClient client = new SmsClient();
            result = client.mt(URLEncoder.encode(content, "UTF-8"), StringUtils.join(phoneNumList.toArray(), ","), "", "", "", "");
            LOG.debug(result);
            String strCode = result.split("\n")[0];
            long code = 0;
            code = Long.valueOf(strCode);
            if(code > 0) {// 成功提交
                info = "发送成功";
            } else if(code == 0) {
                info = "发送失败";
            } else if(code == -1) { // 用户名密码不正确
                info = "用户名密码不正确";
            } else if(code == -2) { // 必填选项为空
                info = "必填选项为空";
            } else if(code == -3) { // 短信内容0个字节
                info = "短信内容0个字节";
            } else if(code == -4) { // 0个有效号码
                info = "0个有效号码";
            } else if(code == -5) { // 余额不够
                info = "余额不够";
            } else if(code == -10) { // 用户被禁用
                info = "用户被禁用";
            } else if(code == -11) { // 短信内容过长
                info = "短信内容过长";
            } else if(code == -12) { // 用户无扩展权限
                info = "无扩展权限";
            } else if(code == -13) { // IP地址校验错
                info = "IP校验错误";
            } else if(code == -14) { // 内容解析异常
                info = "内容解析异常";
            } else {
                info = "未知错误";
            }
            LOG.info("sms has been send to [{}], content is [{}], return value is [{}]", phoneNumList, content, info);
        } catch (UnsupportedEncodingException e) {
            info = "短信内容编码错误";
            LOG.error(e.getMessage(), info);
        }
    }


    /**
     * 发送一条短信给一个号码。
     *
     * @param phoneNumber 手机号码
     * @param content 内容
     */
    public static void sendSms(String phoneNumber, String content) {
        List<String> list = new ArrayList<String>();
        list.add(phoneNumber);
        sendSms(list, content);
    }


    public static String getBanlance() {
        SmsClient smsClient = new SmsClient();
        return smsClient.getBalance();
    }

}
