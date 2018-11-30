package com.medhead.kf100.common.util.pay.properties;

import org.springframework.stereotype.Component;


@Component
public class AlipayProperties {
//
//    private static final Logger logger = LoggerFactory.getLogger(AlipayProperties.class);
//    @Value("${alipay.sdk.appid}")
//    String appid;
//    @Value("${alipay.sdk.privatekey}")
//    String privatekey;
//    @Value("${alipay.sdk.publickey}")
//    String publickey;
//    @Value("${alipay.sdk.gatewayUrl}")
//    String gatewayurl;
//    @Value("${alipay.sdk.signtype}")
//    String signtype;
//    @Value("${alipay.sdk.chartset}")
//    String chartset;
//    @Value("${alipay.sdk.notifyurl}")
//    String notifyurl;
//
//
//    public String getAppid() {
//        return appid;
//    }
//
//    public void setAppid(String appid) {
//        this.appid = appid;
//    }
//
//    public String getPrivatekey() {
//        return privatekey;
//    }
//
//    public void setPrivatekey(String privatekey) {
//        this.privatekey = privatekey;
//    }
//
//    public String getPublickey() {
//        return publickey;
//    }
//
//    public void setPublickey(String publickey) {
//        this.publickey = publickey;
//    }
//
//    public String getGatewayurl() {
//        return gatewayurl;
//    }
//
//    public void setGatewayurl(String gatewayurl) {
//        this.gatewayurl = gatewayurl;
//    }
//
//    public String getSigntype() {
//        return signtype;
//    }
//
//    public void setSigntype(String signtype) {
//        this.signtype = signtype;
//    }
//
//    public String getChartset() {
//        return chartset;
//    }
//
//    public void setChartset(String chartset) {
//        this.chartset = chartset;
//    }
//
//    public String getNotifyurl() {
//        return notifyurl;
//    }
//
//    public void setNotifyurl(String notifyurl) {
//        this.notifyurl = notifyurl;
//    }
//
//
//    /***
//     * 订单查询
//     * @param outTradeNo 订单号
//     */
//    public AlipayTradeQueryResponse query(String outTradeNo) {
//        AlipayClient alipayClient = getAlipayClient();
//        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
//        request.setBizContent("{" +
//                "\"out_trade_no\":\"" + outTradeNo + "\"," +
//                "\"trade_no\":\"\"}");
//        AlipayTradeQueryResponse response = null;
//        try {
//            response = alipayClient.execute(request);
//        } catch (AlipayApiException e) {
//            logger.error(e.getMessage(), e);
//        }
//
//        return response;
//    }
//
//    /**
//     * 订单关闭
//     *
//     * @param outTradeNo 订单号
//     */
//    public AlipayTradeCloseResponse close(String outTradeNo) throws AlipayApiException {
//        AlipayClient alipayClient = getAlipayClient();
//        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
//        request.setBizContent("{" +
//                "\"trade_no\":\"\"," +
//                "\"out_trade_no\":\"" + outTradeNo + "\"," +
//                "\"operator_id\":\"\"" +
//                "  }");
//        return alipayClient.execute(request);
//    }
//
//    /***
//     * 支付宝二维码支付
//     * @param payInfo 支付信息
//     */
//    public AlipayTradePrecreateResponse qrOrder(PayInfo payInfo) {
//        //获得初始化的AlipayClient
//        AlipayClient alipayClient = getAlipayClient();
//        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();//创建API对应的request类
////        request.setReturnUrl(this.getReturnurl());
//        request.setNotifyUrl(this.getNotifyurl());
//        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
//        model.setOutTradeNo(payInfo.getOutTradeNo());                           //商户订单号，商户网站订单系统中唯一订单号，必填
//        model.setTotalAmount(payInfo.getRealFee().toString());                  //付款金额，必填
//        model.setSubject(payInfo.getSubject());                                 //订单名称，必填
//        model.setBody(payInfo.getBody());                                       //商品描述，可空
//        model.setTimeoutExpress(payInfo.getTimeoutExpress() + "m");             //过期时间，可空
//        request.setBizModel(model);                                             //设置业务参数
//        AlipayTradePrecreateResponse response = null;
//        try {
//            response = alipayClient.execute(request);
//        } catch (AlipayApiException e) {
//            logger.error("错误码：" + e.getErrCode() + "，详细错误信息：" + e.getErrMsg());
//            logger.error(e.getMessage(), e);
//        }
//        return response;
//
//    }
//
//    /**
//     * 支付宝app支付
//     *
//     * @param payInfo 订单信息
//     */
//    public AlipayTradeAppPayResponse appOrder(PayInfo payInfo) {
//        //实例化客户端
//        AlipayClient alipayClient = getAlipayClient();
//        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
//        model.setBody(payInfo.getBody());                              //商品描述，可空
//        model.setSubject(payInfo.getSubject());                        //订单名称，必填
//        model.setOutTradeNo(payInfo.getOutTradeNo());                  //商户订单号，商户网站订单系统中唯一订单号，必填
//        model.setTimeoutExpress(payInfo.getTimeoutExpress() + "m");    //过期时间，可空
//        model.setTotalAmount(payInfo.getRealFee().toString());         //付款金额，必填
//        model.setProductCode("QUICK_MSECURITY_PAY");
////        model.setExtendParams();                                     //业务参数，可空
//        request.setBizModel(model);
//        request.setNotifyUrl(this.getNotifyurl());
//        AlipayTradeAppPayResponse response = null;
//        try {
//            response = alipayClient.sdkExecute(request);
//        } catch (AlipayApiException e) {
//            logger.error("错误码：" + e.getErrCode() + "，详细错误信息：" + e.getErrMsg());
//            logger.error(e.getMessage(), e);
//        }
//        return response;
//    }
//
//    /**
//     * 支付宝h5支付
//     *
//     * @param payInfo 订单信息
//     */
//    public String webAppOrder(PayInfo payInfo) {
//        AlipayClient alipayClient = getAlipayClient(); //获得初始化的AlipayClient
//        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
//        alipayRequest.setReturnUrl(payInfo.getReturnUrl());
//        alipayRequest.setNotifyUrl(this.getNotifyurl());//在公共参数中设置回跳和通知地址
//        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
//        model.setBody(payInfo.getBody());                              //商品描述，可空
//        model.setSubject(payInfo.getSubject());                        //订单名称，必填
//        model.setOutTradeNo(payInfo.getOutTradeNo());                  //商户订单号，商户网站订单系统中唯一订单号，必填
//        model.setTimeoutExpress(payInfo.getTimeoutExpress() + "m");    //过期时间，可空
//        model.setTotalAmount(payInfo.getRealFee().toString());         //付款金额，必填
//        model.setProductCode("QUICK_WAP_WAY");
//        alipayRequest.setBizModel(model);
//        try {
//            return alipayClient.pageExecute(alipayRequest).getBody();
//        } catch (AlipayApiException e) {
//            logger.error("错误码：" + e.getErrCode() + "，详细错误信息：" + e.getErrMsg());
//            logger.error(e.getMessage(), e);
//        }
//        return null;
//    }
//
//    private AlipayClient getAlipayClient() {
//        return new DefaultAlipayClient(this.getGatewayurl(),
//                this.getAppid(), this.getPrivatekey(), "json",
//                this.getChartset(), this.getPublickey(), this.getSigntype());
//    }
}
