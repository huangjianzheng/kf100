package com.medhead.kf100.common.util.pay.properties;

import com.medhead.kf100.common.util.MD5Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Random;

@Component
public class WxProperties {


    @Value("${wxpay.sdk.appId}")
    private String appId;
    @Value("${wxpay.sdk.appSecret}")
    private String appSecret;
    @Value("${wxpay.sdk.mchId}")
    private String mchId;
    @Value("${wxpay.sdk.mchKey}")
    private String mchKey;
    @Value("${wxpay.sdk.notifyUrl}")
    private String notifyUrl;
    @Value("${wxmp.sdk.appId}")
    private String mpAppId;
    @Value("${wxmp.sdk.appSecret}")
    private String mpAppSecret;

    public String getAppID() {
        return appId;
    }

    public String getMchID() {
        return mchId;
    }

    public String getKey() {
        return mchKey;
    }

    public InputStream getCertStream() {
        return null;
    }

    public int getHttpConnectTimeoutMs() {
        return 0;
    }

    public int getHttpReadTimeoutMs() {
        return 0;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getMchKey() {
        return mchKey;
    }

    public void setMchKey(String mchKey) {
        this.mchKey = mchKey;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getMpAppId() {
        return mpAppId;
    }

    public void setMpAppId(String mpAppId) {
        this.mpAppId = mpAppId;
    }

    public String getMpAppSecret() {
        return mpAppSecret;
    }

    public void setMpAppSecret(String mpAppSecret) {
        this.mpAppSecret = mpAppSecret;
    }

    // 获取随机验证参数
    private String genNonceStr() {
        Random random = new Random();
        return MD5Utils.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }


}
