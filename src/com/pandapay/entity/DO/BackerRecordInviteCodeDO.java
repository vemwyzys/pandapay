package com.pandapay.entity.DO;

import java.sql.Timestamp;

/**
 * 邀请码发放记录
 * @author wss
 *
 */
public class BackerRecordInviteCodeDO {
	
	private long id; //记录Id
	private int userId; //用户id
	private String userAccount; //用户帐号
	private int amount; //发放数量
	private String remarks; //操作备注
	private int backerId; //操作的管理员id
	private String backerAccount; //操作的管理员帐号
	private String ipAddress; //操作时的ip地址
	private Timestamp addTime; //操作时间
	
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
	 * 发放数量
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}
	
	/**
	 * 发放数量
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
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
	 * 操作时间
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
