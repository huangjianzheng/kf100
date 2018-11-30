package com.medhead.kf100.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 支付记录
 * </p>
 *
 * @author ming
 * @since 2018-11-27
 */
@TableName("pay_log")
public class PayLog extends Model<PayLog> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 商品分类（1：会诊，2：会诊分享...）
     */
	@TableField("item_type")
	private Integer itemType;
    /**
     * 支付商品id
     */
	@TableField("item_id")
	private Long itemId;
    /**
     * 账单相关用户id
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 订单编号
     */
	@TableField("out_trade_no")
	private String outTradeNo;
    /**
     * 第三方支付订单编号
     */
	@TableField("trade_no")
	private String tradeNo;
    /**
     * 支付方式（wxqr,wxsdk,alipaysdk...）
     */
	@TableField("pay_type")
	private String payType;
    /**
     * 支付时间
     */
	@TableField("pay_time")
	private Date payTime;
    /**
     * 第三方交易状态
     */
	@TableField("trade_status")
	private String tradeStatus;
    /**
     * 第三方支付用户编号
     */
	@TableField("buyer_id")
	private String buyerId;
    /**
     * 支付总金额
     */
	@TableField("total_fee")
	private Double totalFee;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "PayLog{" +
			", id=" + id +
			", itemType=" + itemType +
			", itemId=" + itemId +
			", userId=" + userId +
			", outTradeNo=" + outTradeNo +
			", tradeNo=" + tradeNo +
			", payType=" + payType +
			", payTime=" + payTime +
			", tradeStatus=" + tradeStatus +
			", buyerId=" + buyerId +
			", totalFee=" + totalFee +
			"}";
	}
}
