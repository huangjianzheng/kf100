package com.medhead.kf100.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author ming
 * @since 2018-11-27
 */
public class Orders extends Model<Orders> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 订单类型（1：会诊，2：会诊分享）
     */
	private Integer type;
    /**
     * 账单相关用户id
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 支付记录id
     */
	@TableField("pay_log_id")
	private Long payLogId;
    /**
     * 订单编号（第三方支付）
     */
	@TableField("out_trade_no")
	private String outTradeNo;
    /**
     * 账单金额
     */
	@TableField("total_fee")
	private Double totalFee;
    /**
     * 完成时间
     */
	@TableField("finish_time")
	private Date finishTime;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 状态（0：未完成，1：完成，-1：关闭）
     */
	private Integer status;
    /**
     * 账单名称
     */
	private String subject;
    /**
     * 账单描述
     */
	private String body;
    /**
     * 支付方式
     */
	@TableField("pay_type")
	private String payType;
    /**
     * 订单过期时间
     */
	@TableField("expire_time")
	private Date expireTime;
    /**
     * (微信，支付宝)二维码支付链接
     */
	@TableField("qr_code_url")
	private String qrCodeUrl;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPayLogId() {
		return payLogId;
	}

	public void setPayLogId(Long payLogId) {
		this.payLogId = payLogId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public String getQrCodeUrl() {
		return qrCodeUrl;
	}

	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Orders{" +
			", id=" + id +
			", type=" + type +
			", userId=" + userId +
			", payLogId=" + payLogId +
			", outTradeNo=" + outTradeNo +
			", totalFee=" + totalFee +
			", finishTime=" + finishTime +
			", createTime=" + createTime +
			", status=" + status +
			", subject=" + subject +
			", body=" + body +
			", payType=" + payType +
			", expireTime=" + expireTime +
			", qrCodeUrl=" + qrCodeUrl +
			"}";
	}
}
