package com.pandapay.entity.DO;

import java.sql.Timestamp;

/**
 * 用户短信验证码
 * @author 豆豆
 *
 */
public class UserMessageCodeDO {
	
	private long id; //记录Id
	private String phoneNo; //手机号码
	private String messageCode; //验证码
	private int useStatus; //使用状态,0:未使用,1:已使用
	private Timestamp addTime; //获得时间
	
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
	 * 手机号码
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	
	/**
	 * 手机号码
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	/**
	 * 验证码
	 * @return the messageCode
	 */
	public String getMessageCode() {
		return messageCode;
	}
	
	/**
	 * 验证码
	 * @param messageCode the messageCode to set
	 */
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	
	/**
	 * 使用状态,0:未使用,1:已使用
	 * @return the useStatus
	 */
	public int getUseStatus() {
		return useStatus;
	}
	
	/**
	 * 使用状态,0:未使用,1:已使用
	 * @param useStatus the useStatus to set
	 */
	public void setUseStatus(int useStatus) {
		this.useStatus = useStatus;
	}
	
	/**
	 * 获得时间
	 * @return the addTime
	 */
	public Timestamp getAddTime() {
		return addTime;
	}
	
	/**
	 * 获得时间
	 * @param addTime the addTime to set
	 */
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

}
