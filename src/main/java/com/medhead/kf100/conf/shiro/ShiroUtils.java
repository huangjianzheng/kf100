package com.medhead.kf100.conf.shiro;

import com.medhead.kf100.common.util.contant.CommonConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;


/**
 * shiro 过滤器工具类
 */
public class ShiroUtils {


    private ShiroUtils() {
    }

    /**
     * 判断是否是options请求
     */
    public static boolean isOptions(HttpServletRequest request) {
        String method = request.getMethod();
        return "options".equalsIgnoreCase(method);
    }

    /**
     * 判断是否是ajax请求
     */
    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    /**
     * 设置跨域请求响应头
     */
    public static void setCorsResponse(HttpServletResponse response, HttpServletRequest request) {
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", origin);
    }


    /**
     * 判断是否是跨域请求(注意:可能会有问题)
     */
    public static boolean isCors(HttpServletRequest request) throws MalformedURLException {

        String protocol = request.getProtocol();

        if("chrome-extension".equalsIgnoreCase(protocol)) {
            return false;
        }
        String origin = request.getHeader("Origin");
        StringBuffer stringBuffer = request.getRequestURL();
        URL hostUrl = new URL(stringBuffer.toString());
        if(StringUtils.isNotEmpty(origin)) {
            URL url = new URL(origin);
            return !url.getHost().equalsIgnoreCase(hostUrl.getHost()) || url.getPort() != hostUrl.getPort();
        }
        return false;
    }

    /**
     * 判断是否是jwt请求
     */
    public static boolean isJwtSubmission(ServletRequest request) {
        String jwt = WebUtils.toHttp(request).getHeader(CommonConstants.DEFAULT_JWT_PARAM);
        Cookie[] cookies = WebUtils.toHttp(request).getCookies();
        if(StringUtils.isBlank(jwt) && cookies != null) {
            jwt = Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals(CommonConstants.DEFAULT_JWT_PARAM))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
        }
        return StringUtils.isNotEmpty(jwt);
    }

    public static String getJwt(ServletRequest request) {
        String jwt = WebUtils.toHttp(request).getHeader(CommonConstants.DEFAULT_JWT_PARAM);
        Cookie[] cookies = WebUtils.toHttp(request).getCookies();
        if(StringUtils.isBlank(jwt) && cookies != null) {
            jwt = Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals(CommonConstants.DEFAULT_JWT_PARAM))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
        }
        return jwt;
    }


    @Bean
    public static SimpleMappingExceptionResolver getSimpleMappingExceptionResolver(String unauthorizedUrl) {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties prop = new Properties();
        if(StringUtils.isNotEmpty(unauthorizedUrl)) {
            prop.setProperty("org.apache.shiro.authz.UnauthorizedException", unauthorizedUrl);
        }
        resolver.setExceptionMappings(prop);
        return resolver;
    }


}
