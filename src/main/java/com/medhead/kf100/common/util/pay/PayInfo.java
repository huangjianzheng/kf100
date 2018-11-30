package com.medhead.kf100.common.util.pay;

public class PayInfo {
    private String body;                //内容
    private String subject;             //主题
    private String outTradeNo;          //订单号
    private Double fee;                 //需支付的金额
    private String thirdPartyPayId;     //第三方支付账单编号
    private String remoteIp;            //远程及其ip
    private Long timeoutExpress = 30L;  //3分钟有效时间
    /***
     * 本地业务数据
     */
//    private OrdersItemTypeEnum itemType;//物品类型
    private Long buyerId;               //付费/支付用户ID
    private Long sellerId;              //收费/收款用户ID
    private Long orderId;               //订单ID
    private Double coupon;              //使用代金卡的额度
    private Double totalAmount;         //总金额
    private Double originalPrice;         //总金额
    private Boolean isTest = false;     //是否测试
    private Integer shareType;
    private Long itemId;                //物品id
    private PayType payType;            //支付方式
    private Boolean isCoupon;   //是否使用代金卡
    private String returnUrl;           //重定向地址
    private String openId;                //公众号openid
 //   private PromotionDetails promotionDetails;
    private boolean membershipBuyingFlag;

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

//    public String getOutTradeNo() {
//        if(StringUtils.isNotEmpty(outTradeNo)) {
//            return outTradeNo;
//        } else {
//            outTradeNo = createOutTradeNo(itemType, orderId);
//        }
//        return outTradeNo;
//    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getThirdPartyPayId() {
        return thirdPartyPayId;
    }

    public void setThirdPartyPayId(String thirdPartyPayId) {
        this.thirdPartyPayId = thirdPartyPayId;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public Long getTimeoutExpress() {
        return timeoutExpress;
    }

    public void setTimeoutExpress(Long timeoutExpress) {
        this.timeoutExpress = timeoutExpress;
    }

//    public OrdersItemTypeEnum getItemType() {
//        return itemType;
//    }
//
//    public void setItemType(OrdersItemTypeEnum itemType) {
//        this.itemType = itemType;
//    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Boolean getTest() {
        return isTest;
    }

    public void setTest(Boolean test) {
        isTest = test;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
//
//    public PromotionDetails getPromotionDetails() {
//        return promotionDetails;
//    }
//
//    public void setPromotionDetails(PromotionDetails promotionDetails) {
//        this.promotionDetails = promotionDetails;
//    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getRealFee() {
        if(this.isTest) {
            return 0.01D;
        }
        return this.fee;
    }

//    public Double getTotalAmount() {
//        if(this.totalAmount == null) {
//            this.totalAmount = CommonUtil.add(this.fee, this.coupon);
//        }
//        return this.totalAmount;
//    }

    public Double getCoupon() {
        if(isCoupon) {
            return coupon;
        } else {
            return null;
        }
    }

    public void setCoupon(Double coupon) {
        this.coupon = coupon;
    }

    public Boolean getIsCoupon() {
        return isCoupon;
    }

    public void setIsCoupon(Boolean coupon) {
        isCoupon = coupon;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public Integer getShareType() {
        return shareType;
    }

    public void setShareType(Integer shareType) {
        this.shareType = shareType;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public boolean getMembershipBuyingFlag() {
        return membershipBuyingFlag;
    }

    public void setMembershipBuyingFlag(boolean membershipBuyingFlag) {
        this.membershipBuyingFlag = membershipBuyingFlag;
    }

    /***
     * 生成订单号
     * @param itemType 商品类型
//     */
//    private String createOutTradeNo(OrdersItemTypeEnum itemType, Long orderId) {
//        if(itemType == null) {
//            throw new BusinessException(CommonConstants.RESPONSE_BAD_REQUEST_CODE, "订单生成错误");
//        }
//        if(orderId == null) {
//            throw new BusinessException(CommonConstants.RESPONSE_BAD_REQUEST_CODE, "订单生成错误");
//        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String typeString = String.format("%04d", itemType.getValue());//4位
//        String dateString = sdf.format(new Date());
//        String itemString = String.format("%04d", orderId);//4位
//        return typeString + dateString + itemString;
//    }


    public static class Builder {
        private String body;                //内容
        private String subject;             //主题
        private String outTradeNo;          //订单号
        private Double fee;                 //金额
        private String thirdPartyPayId;     //第三方支付账单编号
        private String remoteIp;            //远程及其ip
        private Long timeoutExpress = 30L;  //3分钟有效时间
        /***
         * 本地业务数据
         */

  //      private OrdersItemTypeEnum itemType;//物品类型
        private Long buyerId;               //付费/支付用户ID
        private Long sellerId;              //收费/收款用户ID
        private Long orderId;               //订单ID
        private Double coupon;              //使用代金卡的额度
        private Double totalAmount;         //总金额
        private Double originalPrice;         //原始价格
        private Boolean isTest = false;     //是否测试
        private Integer shareType;

        private Long itemId;                //物品id
        private PayType payType;            //支付方式
        private Boolean isCoupon = false;   //是否使用代金卡
        private String returnUrl;           //重定向地址
        private String openId;                //openid
//        private PromotionDetails promotionDetails;

        public Builder() {
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }
//
//        public Builder promotionDetails(PromotionDetails promotionDetails) {
//            this.promotionDetails = promotionDetails;
//            return this;
//        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder outTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
            return this;
        }

        public Builder fee(Double fee) {
            this.fee = fee;
            return this;
        }

        public Builder thirdPartyPayId(String thirdPartyPayId) {
            this.thirdPartyPayId = thirdPartyPayId;
            return this;
        }

        public Builder originalPrice(Double originalPrice) {
            this.originalPrice = originalPrice;
            return this;
        }

        public Builder remoteIp(String remoteIp) {
            this.remoteIp = remoteIp;
            return this;
        }

        public Builder timeoutExpress(Long timeoutExpress) {
            this.timeoutExpress = timeoutExpress;
            return this;
        }

//        public Builder itemType(OrdersItemTypeEnum itemType) {
//            this.itemType = itemType;
//            return this;
//        }

        public Builder buyerId(Long buyerId) {
            this.buyerId = buyerId;
            return this;
        }

        public Builder sellerId(Long sellerId) {
            this.sellerId = sellerId;
            return this;
        }

        public Builder orderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder coupon(Double coupon) {
            this.coupon = coupon;
            return this;
        }

        public Builder totalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder isTest(Boolean test) {
            this.isTest = test;
            return this;
        }

        public Builder shareType(Integer shareType) {
            this.shareType = shareType;
            return this;
        }

        public Builder itemId(Long itemId) {
            this.itemId = itemId;
            return this;
        }

        public Builder payType(PayType payType) {
            this.payType = payType;
            return this;
        }

        public Builder isCoupon(Boolean isCoupon) {
            this.isCoupon = isCoupon;
            return this;
        }

        public Builder returnUrl(String returnUrl) {
            this.returnUrl = returnUrl;
            return this;
        }


        public Builder openId(String openId) {
            this.openId = openId;
            return this;
        }

        public PayInfo build() {
            return new PayInfo(this);
        }
    }


    public PayInfo(Builder builder) {
        this.payType = builder.payType;
        this.body = builder.body;
        this.subject = builder.subject;
        this.outTradeNo = builder.outTradeNo;
        this.fee = builder.fee;
        this.thirdPartyPayId = builder.thirdPartyPayId;
        this.remoteIp = builder.remoteIp;
        this.timeoutExpress = builder.timeoutExpress;
     //   this.itemType = builder.itemType;
        this.itemId = builder.itemId;
        this.buyerId = builder.buyerId;
        this.sellerId = builder.sellerId;
        this.orderId = builder.orderId;
        if(isTest != null) {
            this.isTest = builder.isTest;
        }
        isCoupon = false;
        this.isCoupon = builder.isCoupon;
        this.coupon = builder.coupon;
        this.openId = builder.openId;
 //       this.promotionDetails = builder.promotionDetails;
        this.returnUrl = builder.returnUrl;
        this.originalPrice = builder.originalPrice;
    }

    @Override
    public String toString() {
        return "PayInfo{" +
                "body='" + body + '\'' +
                ", subject='" + subject + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", fee=" + fee +
                ", thirdPartyPayId='" + thirdPartyPayId + '\'' +
                ", remoteIp='" + remoteIp + '\'' +
                ", timeoutExpress=" + timeoutExpress +
           //     ", itemType=" + itemType +
                ", buyerId=" + buyerId +
                ", sellerId=" + sellerId +
                ", orderId=" + orderId +
                ", coupon=" + coupon +
                ", totalAmount=" + totalAmount +
                ", originalPrice=" + originalPrice +
                ", isTest=" + isTest +
                ", shareType=" + shareType +
                ", itemId=" + itemId +
                ", payType=" + payType +
                ", isCoupon=" + isCoupon +
                ", returnUrl='" + returnUrl + '\'' +
                ", openId='" + openId + '\'' +
           //     ", promotionDetails=" + promotionDetails +
                ", membershipBuyingFlag=" + membershipBuyingFlag +
                '}';
    }
}
