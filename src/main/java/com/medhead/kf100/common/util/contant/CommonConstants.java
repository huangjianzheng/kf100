package com.medhead.kf100.common.util.contant;


/**
 * <p>
 * 通用常量类
 * Date: 18-01-11
 * <p>
 * Version: 1.0
 */
public class CommonConstants {

    public static final String RESPONSE_SUCCESS_CODE = "2000";//响应成功

    public static final String RESPONSE_ERROR_CODE = "5000";//服务器内部错误

    public static final String RESPONSE_BAD_REQUEST_CODE = "4000";//请求参数出错

    public static final String RESPONSE_NOT_FOUND_CODE = "4004";//没有此接口

    public static final String RESPONSE_NOT_ACCEPTABLE_CODE = "4006";//没有此接口

    public static final String RESPONSE_UNAUTHORIZED_CODE = "4001";//未授权

    public static final String RESPONSE_UNLOGIN_CODE = "4002";//未登录

    public static final String RESPONSE_KICKOUT_CODE = "4003";//新用户上线，踢出当前用户

    public static final String DEFAULT_JWT_PARAM = "kf100-token";

    public static final String SMS_VERIFY_CODE = "您好，您的验证码为{0}，欢迎使用康复联盟！【康复联盟】";

    public static final String RESPONSE_ERROR_INVITE_CALL = "10001";  // 对方正在通话中,请稍后再拨
    public static final String RESPONSE_ERROR_ACCEPT_CALL = "10002";  // 通话已被关闭或不存在(接受通话)
    public static final String RESPONSE_ERROR_UN_ACCEPT_CALL = "10003";  // 通话已被关闭或不存在(不接受通话)
    public static final String RESPONSE_ERROR_CANCEL_CALL = "10004";  // 通话已被关闭或不存在(取消通话)
    public static final String RESPONSE_ERROR_END_CALL = "10005";  // 通话已被关闭或不存在(结束通话)

    public static final String RESPONSE_CAN_NOT_CHANGE_CONSULTATION_SWTICH = "20001";//该科室还有会诊未审核不能修改审核开关
    public static final String RESPONSE_CAN_NOT_WITHDRAW = "20002";//该用户没有认证不能修改提现

    public static final String DEFAULT_CONSULTATION_IMAGE = "defaultConsultation.png";
    public static final String DEFAULT_USER_IMAGE = "defaultHead.png";
    public static final String APP_VERSION = "app_version";
    public static final String IOS_VERSION = "ios_version";
}

