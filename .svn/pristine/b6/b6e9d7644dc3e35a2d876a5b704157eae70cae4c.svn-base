package com.medhead.kf100.exception;

import com.medhead.kf100.common.dto.ResponseResult;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ExceptionHandle {

    private final static Logger LOG = LoggerFactory.getLogger(ExceptionHandle.class);

    public ExceptionHandle() {
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult Handle(Exception e, ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        StringBuffer requestURL = servletRequest.getRequestURL();
        if(e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            return new ResponseResult<>(businessException.getCode(), businessException.getMessage(), null);
        }
        if(e instanceof WxErrorException) {
            return ResponseResult.badRequest("微信授权登录失败,请重试");
        }
        if(e instanceof NoHandlerFoundException) {
            if(requestURL.toString().matches(".*api.*")) {
                return ResponseResult.notFound("找不到该接口或页面,请检查链接是否正确");
            } else {
                HttpServletResponse servletResponse = (HttpServletResponse) response;
                servletResponse.sendRedirect("/page/admin/index.html#/404");
                return null;
            }
        }
        if(e instanceof HttpMediaTypeNotAcceptableException) {
            if(requestURL.toString().matches(".*static.*")) {
                HttpServletResponse servletResponse = (HttpServletResponse) response;
                servletResponse.sendRedirect("/page/admin/index.html#/404");
                return null;
            }
            return ResponseResult.notAcceptable("Could not find acceptable representation");
        }
        if(e instanceof MultipartException) {
            return ResponseResult.badRequest(e.getMessage());
        }
        if(e instanceof MethodArgumentTypeMismatchException) {
            return ResponseResult.badRequest("参数类型不正确");
        }
        if(e instanceof IllegalStateException) {
            return ResponseResult.badRequest("参数不能为空");
        }
        //将系统异常以打印出来
        LOG.error(requestURL + "--->", e);
        return ResponseResult.error("系统异常:" + e.getMessage());

    }
}
