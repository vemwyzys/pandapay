package com.pandapay.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iqmkj.utils.DateUtil;
import com.iqmkj.utils.LogUtil;
import com.pandapay.dao.IUserDao;
import com.pandapay.entity.DO.UserDO;
import com.pandapay.entity.VO.UserBackVO;

/**
 * 用户信息
 * @author gql
 *
 */
@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 新增用户信息
	 * @param user 用户信息
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean insertUser(UserDO user){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.insert("User_insertUser", user);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		
		if(resultNumber > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 查询用户信息
	 * @param userId 用户Id
	 * @return 操作成功：返回用户信息，失败：返回null
	 */
	public UserDO queryUserByUserId(int userId){
		UserDO user = null;
		try{
			user = sqlSessionTemplate.selectOne("User_queryUserByUserId", userId);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return user;
	}
	
	/**
	 * 查询用户信息
	 * @param userAccount 用户Id
	 * @return 操作成功：返回用户信息，失败：返回null
	 */
	public UserDO queryUserByUserAccount(String userAccount){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userAccount", userAccount);
		
		UserDO user = null;
		try{
			user = sqlSessionTemplate.selectOne("User_queryUserByUserAccount", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return user;
	}
	
	/**
	 * 验证用户帐号是否已存在
	 * @param userAccount 用户帐号
	 * @return 已存在：返回true，不存在：返回false
	 */
	public boolean validataUserAccount(String userAccount){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userAccount", userAccount);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.selectOne("User_validataUserAccount", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		
		if(resultNumber > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 验证用户登录
	 * @param userAccount 用户账号
	 * @param password 账号密码
	 * @return 操作成功：返回用户信息，失败：返回null
	 */
	public UserDO validateUserLogin(String userAccount, String password){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userAccount", userAccount);
		map.put("password", password);
		
		UserDO user = null;
		try{
			user = sqlSessionTemplate.selectOne("User_validateUserLogin", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return user;
	}
	
	/**
	 * 增加钱包金额
	 * @param userId 用户Id
	 * @param addAmount 增加的钱包金额，单位：元
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean addWalletAmount(int userId, double addAmount){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("addAmount", addAmount);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.update("User_addWalletAmount", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		
		if(resultNumber > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 减少钱包金额
	 * @param userId 用户Id
	 * @param reduceAmount 减少的钱包金额，单位：元
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean reduceWalletAmount(int userId, double reduceAmount){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("reduceAmount", reduceAmount);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.update("User_reduceWalletAmount", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		
		if(resultNumber > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 增加红包金额
	 * @param userId 用户Id
	 * @param addAmount 增加的红包金额，单位：元
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean addRedPacketAmount(int userId, double addAmount){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("addAmount", addAmount);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.update("User_addRedPacketAmount", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		
		if(resultNumber > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 减少红包金额
	 * @param userId 用户Id
	 * @param reduceAmount 减少的红包金额，单位：元
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean reduceRedPacketAmount(int userId, double reduceAmount){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("reduceAmount", reduceAmount);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.update("User_reduceRedPacketAmount", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		
		if(resultNumber > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 修改用户密码
	 * @param userId 用户Id
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean updateUserPassword(int userId, String oldPassword, String newPassword){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("oldPassword", oldPassword);
		map.put("newPassword", newPassword);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.update("User_updateUserPassword", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		
		if(resultNumber > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 修改用户密码
	 * @param userId 用户Id
	 * @param newPassword 新密码
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean updateUserPasswordModify(int userId, String newPassword){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("newPassword", newPassword);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.update("User_updateUserPasswordModify", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		
		if(resultNumber > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 修改认证状态
	 * @param userId 用户Id
	 * @param authenStatus 认证状态，0：待认证，1：认证通过，2：认证失败
	 * @param updateTime 最后修改时间
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean updateAuthenStatus(int userId, int authenStatus, Timestamp updateTime){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("authenStatus", authenStatus);
		map.put("updateTime", updateTime);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.update("User_updateAuthenStatus", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		
		if(resultNumber > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 修改会员身份
	 * @param userId 用户Id
	 * @param vipIdentity 会员身份，0：普通会员，1：VIP会员
	 * @param vipOutTime 会员到期时间
	 * @param receiptRate 收款费率
	 * @param updateTime 最后修改时间
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean updateVipIdentity(int userId, int vipIdentity, Timestamp vipOutTime, double receiptRate, Timestamp updateTime){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("vipIdentity", vipIdentity);
		map.put("vipOutTime", vipOutTime);
		map.put("receiptRate", receiptRate);
		map.put("updateTime", updateTime);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.update("User_updateVipIdentity", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		
		if(resultNumber > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 修改交易信息
	 * @param userId 用户Id
	 * @param singleLimitAmount 单笔限额，单位：元
	 * @param receiptRate 收款费率
	 * @param updateTime 最后修改时间
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean updatePayInfo(int userId, double singleLimitAmount, double receiptRate, Timestamp updateTime){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("singleLimitAmount", singleLimitAmount);
		map.put("receiptRate", receiptRate);
		map.put("updateTime", updateTime);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.update("User_updatePayInfo", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		
		if(resultNumber > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 查询用户总数（后台操作）
	 * @param userAccount 用户帐号
	 * @param inviteUserAccount 邀请人账号
	 * @param idCard 身份证号
	 * @param bankCard 结算银行卡号
	 * @param authenStatus 认证状态，0：待认证，1：认证通过，2：认证失败，查询全部填-1
	 * @param vipIdentity 会员身份，0：普通会员，1：VIP会员，查询全部填-1
	 * @param startTime 注册时间的开始时间
	 * @param endTime 注册时间的结束时间
	 * @return 操作成功：返回用户总数，操作失败：返回0
	 */
	public int queryUserTotalOfBack(String userAccount, String inviteUserAccount, String idCard, String bankCard, int authenStatus, int vipIdentity,
			Timestamp startTime, Timestamp endTime){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userAccount", userAccount);
		map.put("inviteUserAccount", inviteUserAccount);
		map.put("idCard", idCard);
		map.put("bankCard", bankCard);
		map.put("authenStatus", authenStatus);
		map.put("vipIdentity", vipIdentity);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.selectOne("User_queryUserTotalOfBack", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultNumber;
	}
	
	/**
	 * 查询用户信息列表（后台操作）
	 * @param userAccount 用户帐号
	 * @param inviteUserAccount 邀请人账号
	 * @param idCard 身份证号
	 * @param bankCard 结算银行卡号
	 * @param authenStatus 认证状态，0：待认证，1：认证通过，2：认证失败，查询全部填-1
	 * @param vipIdentity 会员身份，0：普通会员，1：VIP会员，查询全部填-1
	 * @param startTime 注册时间的开始时间
	 * @param endTime 注册时间的结束时间
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回用户信息列表，操作失败：返回null
	 */
	public List<UserBackVO> queryUserListOfBack(String userAccount, String inviteUserAccount, String idCard, String bankCard, int authenStatus, int vipIdentity,
			Timestamp startTime, Timestamp endTime, int pageNumber, int pageSize){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userAccount", userAccount);
		map.put("inviteUserAccount", inviteUserAccount);
		map.put("idCard", idCard);
		map.put("bankCard", bankCard);
		map.put("authenStatus", authenStatus);
		map.put("vipIdentity", vipIdentity);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		
		map.put("startNumber", pageNumber*pageSize);
		map.put("pageSize", pageSize);
		
		List<UserBackVO> userList = null;
		try{
			userList = sqlSessionTemplate.selectList("User_queryUserListOfBack", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return userList;
	}
	
	/**
	 * 查询VIP过期的用户列表（定时器操作，要将数据未过期但实际已过期的变成已过期）
	 * @param pageSize 每次查询的数量
	 * @return 操作成功：返回用户信息列表，操作失败：返回null
	 */
	public List<UserDO> queryOutVipList(int pageSize){
		Timestamp curTime = DateUtil.getCurrentTime();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("curTime", curTime);
		map.put("pageSize", pageSize);
		
		List<UserDO> userList = null;
		try{
			userList = sqlSessionTemplate.selectList("User_queryOutVipList", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return userList;
	}
	
}
