package com.pandapay.entity.VO;

import java.sql.Timestamp;

/**
 * 用户信息（后台管理查看）
 * @author gql
 */
public class UserBackVO {
	
	private int userId;  //用户Id
	private String userAccount;  //用户帐号
	private String inviteUserAccount;  //邀请人账号
	private Timestamp addTime; //注册时间
	private double walletAmount;  //钱包余额，单位：元
	private double redPacketAmount;  //红包余额，单位：元
	private String contactsName; //联系人
	private String address; //地址
	private String idCard; //身份证号
	private String bankCard; //结算银行卡号
	private double singleLimitAmount;  //单笔限额，单位：元
	private double receiptRate;  //收款费率
	private String storeName; //店铺名称
	private int authenStatus; //认证状态，0：待认证，1：认证通过，2：认证失败
	private int vipIdentity; //会员身份，0：普通会员，1：VIP会员
	private Timestamp vipOutTime; //会员过期时间
	
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
	 * 邀请人账号
	 * @return the inviteUserAccount
	 */
	public String getInviteUserAccount() {
		return inviteUserAccount;
	}
	
	/**
	 * 邀请人账号
	 * @param inviteUserAccount the inviteUserAccount to set
	 */
	public void setInviteUserAccount(String inviteUserAccount) {
		this.inviteUserAccount = inviteUserAccount;
	}
	
	/**
	 * 注册时间
	 * @return the addTime
	 */
	public Timestamp getAddTime() {
		return addTime;
	}
	
	/**
	 * 注册时间
	 * @param addTime the addTime to set
	 */
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
	/**
	 * 钱包余额，单位：元
	 * @return the walletAmount
	 */
	public double getWalletAmount() {
		return walletAmount;
	}
	
	/**
	 * 钱包余额，单位：元
	 * @param walletAmount the walletAmount to set
	 */
	public void setWalletAmount(double walletAmount) {
		this.walletAmount = walletAmount;
	}
	
	/**
	 * 红包余额，单位：元
	 * @return the redPacketAmount
	 */
	public double getRedPacketAmount() {
		return redPacketAmount;
	}
	
	/**
	 * 红包余额，单位：元
	 * @param redPacketAmount the redPacketAmount to set
	 */
	public void setRedPacketAmount(double redPacketAmount) {
		this.redPacketAmount = redPacketAmount;
	}
	
	/**
	 * 联系人
	 * @return the contactsName
	 */
	public String getContactsName() {
		return contactsName;
	}
	
	/**
	 * 联系人
	 * @param contactsName the contactsName to set
	 */
	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}
	
	/**
	 * 地址
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * 地址
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 身份证号
	 * @return the idCard
	 */
	public String getIdCard() {
		return idCard;
	}
	
	/**
	 * 身份证号
	 * @param idCard the idCard to set
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	/**
	 * 结算银行卡号
	 * @return the bankCard
	 */
	public String getBankCard() {
		return bankCard;
	}
	
	/**
	 * 结算银行卡号
	 * @param bankCard the bankCard to set
	 */
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	
	/**
	 * 单笔限额，单位：元
	 * @return the singleLimitAmount
	 */
	public double getSingleLimitAmount() {
		return singleLimitAmount;
	}
	
	/**
	 * 单笔限额，单位：元
	 * @param singleLimitAmount the singleLimitAmount to set
	 */
	public void setSingleLimitAmount(double singleLimitAmount) {
		this.singleLimitAmount = singleLimitAmount;
	}
	
	/**
	 * 收款费率
	 * @return the receiptRate
	 */
	public double getReceiptRate() {
		return receiptRate;
	}
	
	/**
	 * 收款费率
	 * @param receiptRate the receiptRate to set
	 */
	public void setReceiptRate(double receiptRate) {
		this.receiptRate = receiptRate;
	}
	
	/**
	 * 店铺名称
	 * @return the storeName
	 */
	public String getStoreName() {
		return storeName;
	}
	
	/**
	 * 店铺名称
	 * @param storeName the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	/**
	 * 认证状态，0：待认证，1：认证通过，2：认证失败
	 * @return the authenStatus
	 */
	public int getAuthenStatus() {
		return authenStatus;
	}
	
	/**
	 * 认证状态，0：待认证，1：认证通过，2：认证失败
	 * @param authenStatus the authenStatus to set
	 */
	public void setAuthenStatus(int authenStatus) {
		this.authenStatus = authenStatus;
	}

	/**
	 * 会员身份，0：普通会员，1：VIP会员
	 * @return the vipIdentity
	 */
	public int getVipIdentity() {
		return vipIdentity;
	}

	/**
	 * 会员身份，0：普通会员，1：VIP会员
	 * @param vipIdentity the vipIdentity to set
	 */
	public void setVipIdentity(int vipIdentity) {
		this.vipIdentity = vipIdentity;
	}

	/**
	 * 会员过期时间
	 * @return the vipOutTime
	 */
	public Timestamp getVipOutTime() {
		return vipOutTime;
	}

	/**
	 * 会员过期时间
	 * @param vipOutTime the vipOutTime to set
	 */
	public void setVipOutTime(Timestamp vipOutTime) {
		this.vipOutTime = vipOutTime;
	}
	
}
