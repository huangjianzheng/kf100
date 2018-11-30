package com.medhead.kf100.common.util.pay;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PayType implements IEnum {


    CASHCOUPON(1, "cashCoupon"),
    BALANCE(2, "balance"),
    AliPayAPP(3, "alipayAPP"),
    WXPayAPP(4, "APP"),
    WXPayQR(5, "NATIVE"),
    AliPayQR(6, "alipayQR"),
    WXPAYH5(7, "MWEB"),
    AliPayWebApp(8, "alipayWebAPP"),
    WXMPPAY(9, "JSAPI");

    private int value;
    private String desc;

    PayType(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static PayType getPayTypeEnum(Integer payType) {
        switch (payType) {
            case 1:
                return CASHCOUPON;
            case 2:
                return BALANCE;
            case 3:
                return AliPayAPP;
            case 4:
                return WXPayAPP;
            case 5:
                return WXPayQR;
            case 6:
                return AliPayQR;
            case 7:
                return WXPAYH5;
            case 8:
                return AliPayWebApp;
            case 9:
                return WXMPPAY;
            default:
                return null;
        }
    }

    public static PayType getPayTypeEnum(String payType) {
        switch (payType) {
            case "CASHCOUPON":
                return CASHCOUPON;
            case "BALANCE":
                return BALANCE;
            case "AliPayAPP":
                return AliPayAPP;
            case "WXPayAPP":
                return WXPayAPP;
            case "WXPayQR":
                return WXPayQR;
            case "AliPayQR":
                return AliPayQR;
            case "WXPAYH5":
                return WXPAYH5;
            case "AliPayWebApp":
                return AliPayWebApp;
            case "WXMPPAY":
                return WXMPPAY;
            default:
                return null;
        }
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @JsonValue
    public String getDesc() {
        return this.desc;
    }

    public Boolean isThirdPartyPay() {
        if(this == PayType.AliPayAPP
                || this == PayType.WXPayAPP
                || this == PayType.WXPayQR
                || this == PayType.AliPayQR
                || this == PayType.WXPAYH5
                || this == PayType.AliPayWebApp
                || this == PayType.WXMPPAY)
            return true;
        return false;
    }
}
