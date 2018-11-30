package com.medhead.kf100.common.util.pay;

import org.springframework.stereotype.Component;

@Component
public class PayUtils {
//
//    private Logger log = LoggerFactory.getLogger(this.getClass());
//    @Autowired
//    private WxProperties wxProperties;
////    @Autowired
////    private AlipayProperties alipayProperties;
//    @Resource(name = "wxPayService")
//    private WxPayService wxPayService;
//    @Resource(name = "wxPay2Service")
//    private WxPayService wxPay2Service;
//    @Autowired
//    private OrdersService ordersService;
//    private static final Map<String, TradeStatus> TRADE_STATUS_MAP = new HashMap<>();
//
//    static {
//        TRADE_STATUS_MAP.put("SUCCESS", TradeStatus.SUCCESS);
//        TRADE_STATUS_MAP.put("REFUND", TradeStatus.REFUND);
//        TRADE_STATUS_MAP.put("NOTPAY", TradeStatus.NOTPAY);
//        TRADE_STATUS_MAP.put("CLOSED", TradeStatus.CLOSED);
//        TRADE_STATUS_MAP.put("PAYERROR", TradeStatus.PAYERROR);
//        TRADE_STATUS_MAP.put("USERPAYING", TradeStatus.USERPAYING);
//        TRADE_STATUS_MAP.put("WAIT_BUYER_PAY", TradeStatus.NOTPAY);
//        TRADE_STATUS_MAP.put("TRADE_CLOSED", TradeStatus.CLOSED);
//        TRADE_STATUS_MAP.put("TRADE_SUCCESS", TradeStatus.SUCCESS);
//        TRADE_STATUS_MAP.put("TRADE_FINISHED", TradeStatus.FINISHED);
//    }
//
//    public boolean isPayOrPaying(String payType, String outTradeNo) {
//        PayType type = PayType.valueOf(payType);
//        com.medhead.alliance.model.vo.QueryVo queryVo = null;
//        switch (type) {
//            case AliPayAPP:
//            case AliPayQR:
//                queryVo = alipayQuery(outTradeNo);
//                break;
//            case WXPayAPP:
//            case WXPayQR:
//            case WXPAYH5:
//            case WXMPPAY:
//                queryVo = wxQuery(outTradeNo);
//                break;
//        }
//        return queryVo != null && (com.medhead.alliance.model.vo.QueryVo.TradeStatus.SUCCESS.equals(queryVo.getTradeState()) || com.medhead.alliance.model.vo.QueryVo.TradeStatus.USERPAYING.equals(queryVo.getTradeState()));
//    }
//
//    /**
//     * 微信支付（二维码&app）
//     *
//     * @param payInfo 支付信息
//     */
//    public PaymentVo wxPay(PayInfo payInfo) throws WxPayException, UnsupportedEncodingException {
//        boolean isMP = false;
//        Date expireTime = new Date(System.currentTimeMillis() + payInfo.getTimeoutExpress() * 60L * 1000L);
//        Double totalFee = CommonUtil.mul(payInfo.getRealFee(), 100D);
//        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
//        request.setBody(payInfo.getBody());
//        request.setOutTradeNo(payInfo.getOutTradeNo());
//        request.setTotalFee(totalFee.intValue());
//        request.setSpbillCreateIp(payInfo.getRemoteIp());
//        request.setTradeType(payInfo.getPayType().getDesc());
//        request.setProductId(payInfo.getOutTradeNo());
//        request.setNotifyUrl(wxProperties.getNotifyUrl());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        request.setTimeExpire(sdf.format(expireTime));
//        if(payInfo.getPayType() == PayType.WXMPPAY) {
//            isMP = true;
//            request.setOpenid(payInfo.getOpenId());
//            if(!StringUtils.isNotEmpty(payInfo.getOpenId())) {
//                throw new BusinessException(CommonConstants.RESPONSE_BAD_REQUEST_CODE, "openId 为空");
//            }
//        }
//        WxPayService wxService = isMP ? wxPay2Service : wxPayService;
//        WxPayUnifiedOrderResult result = wxService.unifiedOrder(request);
//        if(result != null) {
//            PaymentVo vo = new PaymentVo();
//            String returnCode = result.getReturnCode();
//            String returnMsg = result.getReturnMsg();
//            vo.setOutTradeNo(payInfo.getOutTradeNo());
//            if("SUCCESS".equals(returnCode)) {
//                log.info("-----微信支付请求成功，支付类型：" + payInfo.getPayType() + ";订单编号：" + payInfo.getOutTradeNo());
//                switch (payInfo.getPayType()) {
//                    case WXPayQR:
//                        //微信二维码支付
//                        String code_url = result.getCodeURL();
//                        vo.setQrCode(code_url);
//                        return vo;
//                    case WXPAYH5:
//                        //h5调用微信支付
//                        String url = result.getMwebUrl();
//                        String returnUrl = URLEncoder.encode(payInfo.getReturnUrl(), "utf-8");
//                        vo.setAppSign(url + "&redirect_url=" + returnUrl);
//                        return vo;
//                    case WXPayAPP:
//                        //app调用微信支付
//                        vo.setAppId(result.getAppid());
//                        vo.setNoncestr(result.getNonceStr());//随机字符串
//                        vo.setPartnerid(result.getMchId());
//                        vo.setSign(result.getSign());
//                        vo.setPrepayid(result.getPrepayId());
//                        vo.setTimestamp(new Date().getTime() / 1000);
//                        List<NameValuePair> signParams = new LinkedList<>();
//                        signParams.add(new BasicNameValuePair("appid", vo.getAppId()));
//                        signParams.add(new BasicNameValuePair("noncestr", vo.getNoncestr()));
//                        signParams.add(new BasicNameValuePair("package", vo.getPackageField()));
//                        signParams.add(new BasicNameValuePair("partnerid", vo.getPartnerid()));
//                        signParams.add(new BasicNameValuePair("prepayid", vo.getPrepayid()));
//                        signParams.add(new BasicNameValuePair("timestamp", vo.getTimestamp().toString()));
//                        StringBuilder sb = new StringBuilder();
//                        for (NameValuePair signParam : signParams) {
//                            sb.append(signParam.getName());
//                            sb.append('=');
//                            sb.append(signParam.getValue());
//                            sb.append('&');
//                        }
//                        sb.append("key=");
//                        sb.append(wxService.getConfig().getMchKey());//该字段很重要
//                        String appSign = Objects.requireNonNull(MD5Utils.getMessageDigest(sb.toString().getBytes("UTF-8"))).toUpperCase();
//                        vo.setAppSign(appSign);
//                        return vo;
//                    case WXMPPAY:
//                        //微信内公众号支付
//                        vo.setAppId(result.getAppid());
//                        vo.setNoncestr(result.getNonceStr());//随机字符串
//                        vo.setPrepayid(result.getPrepayId());
//                        vo.setTimestamp(new Date().getTime() / 1000);
//                        List<NameValuePair> signParams2 = new LinkedList<>();
//                        signParams2.add(new BasicNameValuePair("appId", wxService.getConfig().getAppId()));
//                        signParams2.add(new BasicNameValuePair("nonceStr", result.getNonceStr()));
//                        signParams2.add(new BasicNameValuePair("package", "prepay_id=" + result.getPrepayId()));
//                        signParams2.add(new BasicNameValuePair("signType", "MD5"));
//                        signParams2.add(new BasicNameValuePair("timeStamp", vo.getTimestamp().toString()));
//                        String paySign = Objects.requireNonNull(MD5Utils.getMessageDigest(signParams(signParams2).getBytes("UTF-8"))).toUpperCase();
//                        vo.setPaySign(paySign);
//                        return vo;
//                    default:
//                        throw new BusinessException(CommonConstants.RESPONSE_BAD_REQUEST_CODE, "找不到相应的支付方式");
//                }
//            } else if(returnCode.equals("FAIL")) {
//                log.error("-----微信支付请求错误，支付类型：" + payInfo.getPayType() +
//                        ";错误信息：" + returnMsg + ";订单编号：" + payInfo.getOutTradeNo());
//            } else {
//                log.error("-----微信支付错误，错误信息：" + returnMsg);
//            }
//        }
//        return null;
//    }
//
//    private String signParams(List<NameValuePair> params) {
//        StringBuilder sb = new StringBuilder();
//        for (NameValuePair parm : params) {
//            sb.append(parm.getName());
//            sb.append('=');
//            sb.append(parm.getValue());
//            sb.append('&');
//        }
//        sb.append("key=");
//        sb.append(wxProperties.getMchKey());//该字段很重要</span>
//        return sb.toString();
//    }
//
//    /**
//     * 微信支付查询
//     *
//     * @param outTradeNo 订单号
//     */
//    public QueryVo wxQuery(String outTradeNo) {
//        WxPayOrderQueryResult result = null;
//        try {
//            WxPayService wxService = isMPOrder(outTradeNo) ? wxPay2Service : wxPayService;
//            result = wxService.queryOrder("", outTradeNo);
//        } catch (WxPayException e) {
//            if(e.getReturnCode().equals("SUCCESS")) {
//                log.info(e.getErrCodeDes());
//            } else {
//                log.error(e.getErrCodeDes(), e);
//            }
//        }
//        QueryVo vo = new QueryVo();
//        if(result != null) {
//            String returnCode = result.getReturnCode();
//            String returnMsg = result.getReturnMsg();
//            switch (returnCode) {
//                case "SUCCESS":
//                    String tradeState = result.getTradeState();//交易状态
//                        TradeStatus tradeStatus = TRADE_STATUS_MAP.get(tradeState);
//                        if(tradeStatus == null) {
//                            vo.setTradeState(TradeStatus.CLOSED);
//                        }
//                        vo.setTradeState(tradeStatus);
//                    break;
//                case "FAIL":
//                    log.error("-----微信查询请求错误;错误信息：" + returnMsg +
//                            ";订单编号：" + outTradeNo +
//                            ";错误代码：" + result.getErrCode() +
//                            ";详细信息" + result.getErrCodeDes());
//                    break;
//                default:
//                    log.error("-----微信查询错误，错误信息：" + returnMsg);
//                    break;
//            }
//        }
//        return vo;
//    }
//
//    /***
//     * 微信关闭订单
//     * @param outTradeNo 订单号
//     * @return 返回true 关闭成功 false 关闭失败
//     */
//    public Boolean wxClose(String outTradeNo) {
//        WxPayOrderCloseResult result = null;
//        QueryVo vo = wxQuery(outTradeNo);
//        try {
//            WxPayService wxService = isMPOrder(outTradeNo) ? wxPay2Service : wxPayService;
//            if(vo.getTradeState() == QueryVo.TradeStatus.NOTPAY) {
//                result = wxService.closeOrder(outTradeNo);
//                log.info("微信返回信息：" + result.getResultMsg()+",订单号:"+outTradeNo);
//                return true;
//            } else {
//                return false;
//            }
//        } catch (WxPayException e) {
//            log.error(e.getCustomErrorMsg(), e);
//            return false;
//        }
//    }
//
//    /***
//     * 支付宝关闭订单
//     * @param outTradeNo 订单号
//     * @return 返回true 关闭成功 false 关闭失败
//     */
//    public Boolean alipayClose(String outTradeNo) {
//        AlipayTradeCloseResponse response = null;
//        QueryVo vo = alipayQuery(outTradeNo);
//        try {
//            if(vo.getTradeState() == QueryVo.TradeStatus.NOTPAY) {
//                response = this.alipayProperties.close(outTradeNo);
//                if(response.isSuccess()) {
//                    log.info("支付宝关闭订单关闭成功"+ ",订单号:"+outTradeNo);
//                    log.info("支付宝返回信息：" + response.getMsg() + ",订单号:"+outTradeNo);
//                    return true;
//                } else {
//                    log.error("错误码：" + response.getSubCode() + "，详细错误信息：" + response.getSubMsg());
//                    return false;
//                }
//            } else {
//                return false;
//            }
//        } catch (AlipayApiException e) {
//            log.error("错误码：" + e.getErrCode() + "，详细错误信息：" + e.getErrMsg());
//            log.error(e.getMessage(), e);
//            return false;
//        }
//    }
//
//    /***
//     * 支付宝app支付
//     * @param payInfo 支付信息
//     */
//    public PaymentVo alipayAppOrder(PayInfo payInfo) {
//        AlipayTradeAppPayResponse response = this.alipayProperties.appOrder(payInfo);
//        if(response != null) {
//            String responseBody = response.getBody();
//            String code = response.getCode();               //返回码
//            String msg = response.getMsg();                 //返回码描述
//            String subMsg = response.getSubMsg();           //错误详细描述
//            if(StringUtils.isNotEmpty(responseBody)) {
//                log.info("-----支付宝支付请求成功，支付类型：" + payInfo.getPayType() +
//                        ";订单编号：" + payInfo.getOutTradeNo());
//                PaymentVo vo = new PaymentVo();
//                vo.setAppSign(responseBody);
//                return vo;
//            } else {
//                log.error("-----支付宝支付错误：，返回码：" + code + "；返回码描述：" +
//                        msg + "；错误详细描述：" + subMsg + ";订单编号：" + payInfo.getOutTradeNo());
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 支付宝查询
//     *
//     * @param outTradeNo 订单号
//     * @return 查询结果
//     */
//    private QueryVo alipayQuery(String outTradeNo) {
//        AlipayTradeQueryResponse response = this.alipayProperties.query(outTradeNo);
//        QueryVo vo = new QueryVo();
//        if(response != null) {
//            String code = response.getCode();               //返回码
//            String msg = response.getMsg();                 //返回码描述
//            String subMsg = response.getSubMsg();           //错误详细描述
//            if("10000".equals(code)) {
//                String tradeStatus = response.getTradeStatus();
//                TradeStatus status = TRADE_STATUS_MAP.get(tradeStatus);
//                if(status == null) {
//                    vo.setTradeState(TradeStatus.CLOSED);
//                }
//                vo.setTradeState(status);
//                return vo;
//            } else {
//                log.error("-----支付宝查询错误：，返回码：" + code + "；返回码描述：" +
//                        msg + "；错误详细描述：" + subMsg + ";订单编号：" + outTradeNo);
//                if(response.getSubCode().equals("ACQ.TRADE_NOT_EXIST")) {
//                    vo.setTradeState(TradeStatus.NOTFOUND);
//                    vo.setMessage(subMsg);
//                }
//            }
//        }
//        return vo;
//    }
//
//    /***
//     * 支付宝二维码支付
//     * @param payInfo 支付信息
//     */
//    public PaymentVo alipayQROrder(PayInfo payInfo) {
//        AlipayTradePrecreateResponse response = this.alipayProperties.qrOrder(payInfo);
//        if(response != null) {
//            String code = response.getCode();               //返回码
//            String msg = response.getMsg();                 //返回码描述
//            String subMsg = response.getSubMsg();           //错误详细描述
//            if(StringUtils.isNotEmpty(response.getQrCode())) {
//                log.info("-----支付宝支付请求成功，支付类型：" + payInfo.getPayType() + ";订单编号：" + payInfo.getOutTradeNo());
//                PaymentVo vo = new PaymentVo();
//                vo.setQrCode(response.getQrCode());
//                return vo;
//            } else {
//                log.error("-----支付宝支付错误：，返回码：" + code + "；返回码描述：" +
//                        msg + "；错误详细描述：" + subMsg + ";订单编号：" + payInfo.getOutTradeNo());
//            }
//        }
//        return null;
//    }
//
//    /***
//     * 支付宝二维码支付
//     * @param payInfo 支付信息
//     */
//    public PaymentVo alipayWEBOrder(PayInfo payInfo) {
//        String response = this.alipayProperties.webAppOrder(payInfo);
//        if(response != null) {
//            PaymentVo vo = new PaymentVo();
//            vo.setAppSign(response);
//            return vo;
//        }
//        return null;
//    }
//
//    private Boolean isMPOrder(String outTradeNo) {
//        return ordersService.selectCount(new EntityWrapper<Orders>().
//                eq("out_trade_no", outTradeNo).
//                eq("pay_type", PayType.WXMPPAY.getDesc())) > 0;
//    }
}