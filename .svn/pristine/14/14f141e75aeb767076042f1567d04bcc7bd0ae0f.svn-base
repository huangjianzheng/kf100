package com.medhead.kf100.common.util.sms;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

public class SmsCacheUtil {

    private static final int MEMORY_CACHE_SIZE = 500;
    private static final long CACHE_TIME_OUT = 300; // secends.

    // 短信验证码cache，5分钟有效
    public static Cache<String, String> loginVerifyCodeCache = CacheBuilder.newBuilder().maximumSize(MEMORY_CACHE_SIZE).expireAfterAccess(CACHE_TIME_OUT, TimeUnit.SECONDS).build();
    public static Cache<String, String> bindingVerifyCodeCache = CacheBuilder.newBuilder().maximumSize(MEMORY_CACHE_SIZE).expireAfterAccess(CACHE_TIME_OUT, TimeUnit.SECONDS).build();
    public static Cache<String, String> verifyWithdrawCodeCache = CacheBuilder.newBuilder().maximumSize(MEMORY_CACHE_SIZE).expireAfterAccess(CACHE_TIME_OUT, TimeUnit.SECONDS).build();
    // 短信验防过多请求cache，5分钟有效
    public static Cache<String, Integer> ipCache = CacheBuilder.newBuilder().maximumSize(MEMORY_CACHE_SIZE).expireAfterAccess(CACHE_TIME_OUT, TimeUnit.SECONDS).build();

}
