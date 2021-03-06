package com.pandapay.entity.DO;

import java.sql.Timestamp;

/**
 * 用户支付订单
 * @author 豆豆
 *
 */
public class UserOrderPayDO {
	
	private String orderNo; //订单号
	private int userId; //用户id
	private String userAccount; //用户账号
	private double payAmount; //支付金额,单位:元
	private int serviceType; //业务类型,1:商户创建订单（用户下单）,2:充值
	private int payWay; //支付渠道,1：支付宝，2：微信
	private int orderRebateModel; //订单返利模式,0：无返利，1：有返利
    private Timestamp addTime; //申请时间
    private int payStatus; //支付状态,0：未知，1：支付成功, 2:支付失败, 3:其他
    private String remarks; //支付备注
    private Timestamp updateTime; //最后更新时间
    
	/**
	 * 订单号
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}
	
	/**
	 * 订单号
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	/**
	 * 用户id
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * 用户id
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * 用户账号
	 * @return the userAccount
	 */
	public String getUserAccount() {
		return userAccount;
	}
	
	/**
	 * 用户账号
	 * @param userAccount the userAccount to set
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	
	/**
	 * 支付金额,单位:元
	 * @return the payAmount
	 */
	public double getPayAmount() {
		return payAmount;
	}
	
	/**
	 * 支付金额,单位:元
	 * @param payAmount the payAmount to set
	 */
	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}
	
	/**
	 * 业务类型,1:商户创建订单（用户下单）,2:充值
	 * @return the serviceType
	 */
	public int getServiceType() {
		return serviceType;
	}
	
	/**
	 * 业务类型,1:商户创建订单（用户下单）,2:充值
	 * @param serviceType the serviceType to set
	 */
	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}
	
	/**
	 * 支付渠道,1：支付宝，2：微信
	 * @return the payWay
	 */
	public int getPayWay() {
		return payWay;
	}
	
	/**
	 * 支付渠道,1：支付宝，2：微信
	 * @param payWay the payWay to set
	 */
	public void setPayWay(int payWay) {
		this.payWay = payWay;
	}
	
	/**
	 * 订单返利模式,0：无返利，1：有返利
	 * @return the orderRebateModel
	 */
	public int getOrderRebateModel() {
		return orderRebateModel;
	}

	/**
	 * 订单返利模式,0：无返利，1：有返利
	 * @param orderRebateModel the orderRebateModel to set
	 */
	public void setOrderRebateModel(int orderRebateModel) {
		this.orderRebateModel = orderRebateModel;
	}

	/**
	 * 申请时间
	 * @return the addTime
	 */
	public Timestamp getAddTime() {
		return addTime;
	}
	
	/**
	 * 申请时间
	 * @param addTime the addTime to set
	 */
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
	/**
	 * 支付状态,0：未知，1：支付成功, 2:支付失败, 3:其他
	 * @return the payStatus
	 */
	public int getPayStatus() {
		return payStatus;
	}
	
	/**
	 * 支付状态,0：未知，1：支付成功, 2:支付失败, 3:其他
	 * @param payStatus the payStatus to set
	 */
	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}
	
	/**
	 * 支付备注
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	
	/**
	 * 支付备注
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	/**
	 * 最后更新时间
	 * @return the updateTime
	 */
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	
	/**
	 * 最后更新时间
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
    
    
}
