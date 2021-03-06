package com.pandapay.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.iqmkj.utils.DateUtil;
import com.iqmkj.utils.NumberUtil;
import com.pandapay.dao.IUserInviteCodeDao;
import com.pandapay.entity.BO.JsonObjectBO;
import com.pandapay.entity.DO.BackerRecordInviteCodeDO;
import com.pandapay.entity.DO.UserDO;
import com.pandapay.entity.DO.UserInviteCodeDO;
import com.pandapay.service.IBackerRecordInviteCodeService;
import com.pandapay.service.IUserInviteCodeService;
import com.pandapay.service.IUserService;

/**
 * 邀请码
 * @author 豆豆
 *
 */
@Service("userInviteCodeService")
public class UserInviteCodeServiceImpl implements IUserInviteCodeService {

	/**邀请码*/
	@Autowired
	private IUserInviteCodeDao userInviteCodeDao;
	
	/** 用户信息 */
	@Autowired
	private IUserService userService;
	
	/** 邀请码发放记录 */
	@Autowired
	private IBackerRecordInviteCodeService backerRecordInviteCodeService;
	
	/**
	 * 新增邀请码
	 * @param userInviteCode 邀请码信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean insertInviteCode(UserInviteCodeDO userInviteCode){
		return userInviteCodeDao.insertInviteCode(userInviteCode);
	}
	
	/**
	 * 批量添加邀请码（主要用于后台发放）
	 * @param userIdOwn 拥有者用户Id
	 * @param addNumber 添加的数量，单次最大999
	 * @param outTime 过期时间
	 * @param remarks 操作备注
	 * @param backerId 操作的管理员id
	 * @param backerAccount 操作的管理员帐号
	 * @param ipAddress 操作时的ip地址
	 * @return 操作成功：返回code=1，操作失败：返回code!=1
	 */
	@Transactional
	public JsonObjectBO addInviteCodeList(int userIdOwn, int addNumber, Timestamp outTime, String remarks,
			int backerId, String backerAccount, String ipAddress){
		JsonObjectBO jsonObjectBO = new JsonObjectBO();
		if(addNumber <= 0 || addNumber >= 1000){
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("添加数量错误，单次最大999");
			return jsonObjectBO;
		}
		
		UserDO ownUser = userService.queryUserByUserId(userIdOwn);
		if(ownUser == null){
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("未查询到被添加人信息");
			return jsonObjectBO;
		}
		
		Timestamp curTime = DateUtil.getCurrentTime();
		String timeMark = DateUtil.longToTimeStr(curTime.getTime(), DateUtil.dateFormat6);
		
		List<UserInviteCodeDO> inviteCodeList = new ArrayList<UserInviteCodeDO>();
		for (int i = 0; i < addNumber; i++) {
			String randomStr = NumberUtil.createNumberStr(11);
			String inviteCode = timeMark + randomStr;
			
			UserInviteCodeDO userInviteCode = new UserInviteCodeDO();
			userInviteCode.setInviteCode(inviteCode);
			userInviteCode.setUserIdOwn(ownUser.getUserId());
			userInviteCode.setUserAccountOwn(ownUser.getUserAccount());
			userInviteCode.setAddTime(curTime);
			userInviteCode.setOutTime(outTime);
			userInviteCode.setUseStatus(0);
			
			inviteCodeList.add(userInviteCode);
		}
		
		//业务执行状态
		boolean executeSuccess = false;
		
		boolean addInviteCode = userInviteCodeDao.addInviteCodeList(inviteCodeList);
		if(addInviteCode){
			BackerRecordInviteCodeDO backerRecordInviteCode = new BackerRecordInviteCodeDO();
			backerRecordInviteCode.setUserId(ownUser.getUserId());
			backerRecordInviteCode.setUserAccount(ownUser.getUserAccount());
			backerRecordInviteCode.setAmount(addNumber);
			backerRecordInviteCode.setRemarks(remarks);
			backerRecordInviteCode.setBackerId(backerId);
			backerRecordInviteCode.setBackerAccount(backerAccount);
			backerRecordInviteCode.setIpAddress(ipAddress);
			backerRecordInviteCode.setAddTime(curTime);
			
			boolean addInviteCodeRecord = backerRecordInviteCodeService.insertRecord(backerRecordInviteCode);
			if(addInviteCodeRecord){
				executeSuccess = true;
			}
		}
		
		//数据回滚
		if(!executeSuccess){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		if(executeSuccess){
			jsonObjectBO.setCode(1);
			jsonObjectBO.setMessage("添加成功");
			return jsonObjectBO;
		}else{
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("添加失败");
			return jsonObjectBO;
		}
	}
	
	/**
	 * 使用邀请码
	 * @param inviteCode 邀请码
	 * @param userIdUsed 使用者用户Id
	 * @return 操作成功：返回code=1，操作失败：返回code!=1
	 */
	@Transactional
	public JsonObjectBO useInviteCode(String inviteCode, int userIdUsed){
		JsonObjectBO jsonObjectBO = new JsonObjectBO();
		Timestamp curTime = DateUtil.getCurrentTime();
		
		UserInviteCodeDO userInviteCode = userInviteCodeDao.queryInviteCodeByInviteCode(inviteCode);
		if(userInviteCode == null){
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("邀请码不存在");
			return jsonObjectBO;
		}
		
		if(userInviteCode.getUseStatus() != 0){
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("邀请码已失效");
			return jsonObjectBO;
		}
		
		if(userInviteCode.getOutTime().getTime() < curTime.getTime()){
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("邀请码已过期");
			return jsonObjectBO;
		}
		
		UserDO useUser = userService.queryUserByUserId(userIdUsed);
		if(useUser == null){
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("使用者用户不存在");
			return jsonObjectBO;
		}
		
		//业务执行状态
		boolean executeSuccess = false;
		
		boolean inviteCodeUse = userInviteCodeDao.updateInviteCodeUseStatus(inviteCode, 1, useUser.getUserId(), useUser.getUserAccount(), curTime);
		if(inviteCodeUse){
			executeSuccess = userService.addRedPacketAmount(useUser.getUserId(), 2);
		}
		
		//数据回滚
		if(!executeSuccess){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		if(executeSuccess){
			jsonObjectBO.setCode(1);
			jsonObjectBO.setMessage("操作成功");
			return jsonObjectBO;
		}else{
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("操作失败");
			return jsonObjectBO;
		}
	}
	
	/**
	 * 查询邀请码信息
	 * @param inviteCode 邀请码
	 * @return 操作成功：返回邀请码信息，操作失败：返回null
	 */
	public UserInviteCodeDO queryInviteCodeByInviteCode(String inviteCode){
		return userInviteCodeDao.queryInviteCodeByInviteCode(inviteCode);
	}
	
	/**
	 * 查询邀请码总数（用户操作）
	 * @param userIdOwn 所属用户id
	 * @param useStatus 使用状态,0:未使用,1:已使用,查询全部填-1
	 * @return 操作成功：返回记录总数，操作失败：返回0
	 */
	public int queryInviteCodeTotalOfUser(int userIdOwn,  int useStatus){
		return userInviteCodeDao.queryInviteCodeTotalOfUser(userIdOwn, useStatus);
	}
	
	/**
	 * 查询邀请码列表（用户操作）
	 * @param userIdOwn 所属用户id
	 * @param useStatus 使用状态,0:未使用,1:已使用,查询全部填-1
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回邀请码列表，操作失败：返回null
	 */
	public List<UserInviteCodeDO> queryInviteCodeListOfUser(int userIdOwn, int useStatus, int pageNumber, int pageSize){
		return userInviteCodeDao.queryInviteCodeListOfUser(userIdOwn, useStatus, pageNumber, pageSize);
	}
	
	/**
	 * 查询邀请码总数（后台操作）
	 * @param inviteCode 邀请码
	 * @param userAccountOwn 所属账号
	 * @param useStatus 使用状态,0:未使用,1:已使用,查询全部填-1
	 * @param startTime 使用时间的开始时间
	 * @param endTime 使用时间的结束时间
	 * @return 操作成功：返回记录总数，操作失败：返回0
	 */
	public int queryInviteCodeTotalOfBack(String inviteCode, String userAccountOwn, int useStatus, Timestamp startTime, Timestamp endTime){
		return userInviteCodeDao.queryInviteCodeTotalOfBack(inviteCode, userAccountOwn, useStatus, startTime, endTime);
	}
	
	/**
	 * 查询邀请码列表（后台操作）
	 * @param inviteCode 邀请码
	 * @param userAccountOwn 所属账号
	 * @param useStatus 使用状态,0:未使用,1:已使用,查询全部填-1
	 * @param startTime 使用时间的开始时间
	 * @param endTime 使用时间的结束时间
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回邀请码列表，操作失败：返回null
	 */
	public List<UserInviteCodeDO> queryInviteCodeListOfBack(String inviteCode, String userAccountOwn, int useStatus,
			Timestamp startTime, Timestamp endTime, int pageNumber, int pageSize){
		return userInviteCodeDao.queryInviteCodeListOfBack(inviteCode, userAccountOwn, useStatus, startTime, endTime, pageNumber, pageSize);
	}
	
}
