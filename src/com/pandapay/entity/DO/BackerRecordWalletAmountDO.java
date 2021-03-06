package com.pandapay.entity.DO;

import java.sql.Timestamp;

/**
 * 用户钱包账户操作记录
 * @author wss
 *
 */
public class BackerRecordWalletAmountDO {
	
	private long id; //记录Id
	private int userId; //用户Id
	private String userAccount; //用户帐号
	private int handleType; //操作类型,1:增加,2:减少
	private double handleAmount; //操作金额,单位:元
	private String remarks; //操作备注
	private int backerId; //操作的管理员id
	private String backerAccount; //操作的管理员帐号
	private String ipAddress; //操作时的ip地址
	private Timestamp addTime;//操作时间
	
	/**
	 * 记录Id
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * 记录Id
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * 用户Id
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * 用户Id
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * 用户帐号
	 * @return the userAccount
	 */
	public String getUserAccount() {
		return userAccount;
	}
	
	/**
	 * 用户帐号
	 * @param userAccount the userAccount to set
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	
	/**
	 * 操作类型,1:增加,2:减少
	 * @return the handleType
	 */
	public int getHandleType() {
		return handleType;
	}
	
	/**
	 * 操作类型,1:增加,2:减少
	 * @param handleType the handleType to set
	 */
	public void setHandleType(int handleType) {
		this.handleType = handleType;
	}
	
	/**
	 * 操作金额,单位:元
	 * @return the handleAmount
	 */
	public double getHandleAmount() {
		return handleAmount;
	}
	
	/**
	 * 操作金额,单位:元
	 * @param handleAmount the handleAmount to set
	 */
	public void setHandleAmount(double handleAmount) {
		this.handleAmount = handleAmount;
	}
	
	/**
	 * 操作备注
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	
	/**
	 * 操作备注
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	/**
	 * 操作的管理员id
	 * @return the backerId
	 */
	public int getBackerId() {
		return backerId;
	}
	
	/**
	 * 操作的管理员id
	 * @param backerId the backerId to set
	 */
	public void setBackerId(int backerId) {
		this.backerId = backerId;
	}
	
	/**
	 * 操作的管理员帐号
	 * @return the backerAccount
	 */
	public String getBackerAccount() {
		return backerAccount;
	}
	
	/**
	 * 操作的管理员帐号
	 * @param backerAccount the backerAccount to set
	 */
	public void setBackerAccount(String backerAccount) {
		this.backerAccount = backerAccount;
	}
	
	/**
	 * 操作时的ip地址
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	
	/**
	 * 操作时的ip地址
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	/**
	 *操作时间 
	 * @return the addTime
	 */
	public Timestamp getAddTime() {
		return addTime;
	}
	
	/**
	 * 操作时间
	 * @param addTime the addTime to set
	 */
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
}
