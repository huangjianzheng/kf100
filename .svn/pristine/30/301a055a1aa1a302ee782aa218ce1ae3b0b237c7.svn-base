package com.medhead.kf100.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.medhead.kf100.common.util.contant.CommonConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "请求响应")
public class ResponseResult<T> {

    /***
     *
     *  4000->请求参数错误
     *  4001->未授权
     *  4002->用户未登录
     *  4003->kickout
     *  5000->服务器错误
     *  4004->重复进入会诊室
     *
     *  ...其余状态之后再约定吧
     */

    @ApiModelProperty(notes = "响应状态码(2000->请求成功,5000->服务器内部错误,4000->请求参数错误,4001->未授权,4002->用户未登录,4040->找不到页面或接口)" +
            "特殊响应吗(10001->对方正在通话中,请稍后再拨。10002->通话已被关闭或不存在(接受通话)。10003->通话已被关闭或不存在(不接受通话)。10004->通话已被关闭或不存在(取消通话)。10005->通话已被关闭或不存在(结束通话)。" +
            "20001->该科室还有会诊未审核不能修改审核开关。20002->该用户没有认证不能修改提现)"
            , example = "响应状态码(2000->请求成功,5000->服务器内部错误,4000->请求参数错误,4001->未授权,4002->用户未登录,4040->找不到页面或接口)" +
            "特殊响应吗(10001->对方正在通话中,请稍后再拨。10002->通话已被关闭或不存在(接受通话)。10003->通话已被关闭或不存在(不接受通话)。10004->通话已被关闭或不存在(取消通话)。10005->通话已被关闭或不存在(结束通话)。" +
            "20001->该科室还有会诊未审核不能修改审核开关。20002->该用户没有认证不能修改提现)", position = 1)
    private String code;//请求状态

    @ApiModelProperty(notes = "错误信息:当code为2000，errorMsg为null"
            , example = "错误信息:当code为2000，errorMsg为null", position = 2)
    private String errorMsg;//错误信息

    @ApiModelProperty(notes = "请求响应数据", position = 3)
    private T data;// 响应数据

    public ResponseResult() {
    }

    /***
     *
     * @param code  请求状态码（2000成功,4000请求参数错误,4001未授权，4002未登录，4003踢用户，5000服务器错误）
     * @param errorMsg 错误信息（选填）
     * @param data  请求数据（选填）
     */
    public ResponseResult(String code, String errorMsg, T data) {
        this.code = code;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    /***
     * 返回请求成功响应体
     * @param result 数据体
     */
    public static <T> ResponseResult<T> success(T result) {
        return new ResponseResult<>(CommonConstants.RESPONSE_SUCCESS_CODE, null, result);
    }

    /***
     * 返回数据体为空的请求成功响应体
     */
    public static ResponseResult success() {
        return new ResponseResult<>(CommonConstants.RESPONSE_SUCCESS_CODE, null, null);
    }

    /***
     * 返回服务器错误响应体
     * @param errorMsg 错误信息
     */
    public static ResponseResult error(String errorMsg) {
        return new ResponseResult<>(CommonConstants.RESPONSE_ERROR_CODE, errorMsg, null);
    }

    /***
     * 返回未授权响应体
     *@param errorMsg 错误信息
     */
    public static ResponseResult Unauthorized(String errorMsg) {
        return new ResponseResult<>(CommonConstants.RESPONSE_UNAUTHORIZED_CODE, errorMsg, null);
    }

    /***
     * 返回被踢出响应体
     * @param errorMsg 错误信息
     */
    public static ResponseResult kickOut(String errorMsg) {
        return new ResponseResult<>(CommonConstants.RESPONSE_KICKOUT_CODE, errorMsg, null);
    }

    /***
     * 返回未登录响应体
     * @param errorMsg 错误信息
     */
    public static ResponseResult unLogin(String errorMsg) {
        return new ResponseResult<>(CommonConstants.RESPONSE_UNLOGIN_CODE, errorMsg, null);
    }

    /***
     * 返回服务器错误响应体(错误请求参数)
     * @param errorMsg 错误信息
     */
    public static ResponseResult badRequest(String errorMsg) {
        return new ResponseResult<>(CommonConstants.RESPONSE_BAD_REQUEST_CODE, errorMsg, null);
    }

    /***
     * 返回服务器404响应体
     * @param errorMsg 错误信息
     */
    public static ResponseResult notFound(String errorMsg) {
        return new ResponseResult<>(CommonConstants.RESPONSE_NOT_FOUND_CODE, errorMsg, null);
    }

    /***
     * 返回服务器406响应体
     * @param errorMsg 错误信息
     */
    public static ResponseResult notAcceptable(String errorMsg) {
        return new ResponseResult<>(CommonConstants.RESPONSE_NOT_ACCEPTABLE_CODE, errorMsg, null);
    }

    /***
     * 自定义错误码结果
     * @param code
     * @param errorMsg
     * @return
     */
    public static ResponseResult customResponse(String code, String errorMsg) {
        return new ResponseResult<>(code, errorMsg, null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
