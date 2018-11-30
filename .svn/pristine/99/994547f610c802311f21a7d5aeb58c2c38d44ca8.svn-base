package com.medhead.kf100.conf;


import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.medhead.kf100.common.util.pay.properties.WxProperties;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(WxPayService.class)
public class WeiXinConfig {

    @Autowired
    private WxProperties wxProperties;

    @Bean
    public WxMpConfigStorage WxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(wxProperties.getAppID());
        wxMpConfigStorage.setSecret(wxProperties.getAppSecret());
        return wxMpConfigStorage;
    }

    @Bean
    public WxMpConfigStorage WxMpConfigStorage2() {
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(wxProperties.getMpAppId());
        wxMpConfigStorage.setSecret(wxProperties.getMpAppSecret());
        return wxMpConfigStorage;
    }

    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(WxMpConfigStorage());
        return wxMpService;
    }

    @Bean(autowire = Autowire.BY_NAME, value = "wxMp2Service")
    public WxMpService wxMp2Service() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(WxMpConfigStorage2());
        return wxMpService;
    }

    @Bean
    @ConditionalOnMissingBean
    public WxPayService wxPayService() {
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(getPayConfig(
                wxProperties.getAppId(),
                wxProperties.getMchId(),
                wxProperties.getKey()
        ));
        return wxPayService;
    }

    @Bean(autowire = Autowire.BY_NAME, value = "wxPay2Service")
    public WxPayService wxPay2Service() {
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(getPayConfig(
                wxProperties.getMpAppId(),
                wxProperties.getMchId(),
                wxProperties.getKey()
        ));
        return wxPayService;
    }

    private WxPayConfig getPayConfig(String appId, String mchId, String key) {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(wxProperties.getAppID());
        payConfig.setMchId(wxProperties.getMchID());
        payConfig.setMchKey(wxProperties.getKey());
        return payConfig;
    }
}
