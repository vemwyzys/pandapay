package com.pandapay.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqmkj.utils.DateUtil;
import com.iqmkj.utils.NumberUtil;
import com.pandapay.dao.IUserMessageCodeDao;
import com.pandapay.entity.BO.JsonObjectBO;
import com.pandapay.entity.DO.UserMessageCodeDO;
import com.pandapay.service.IUserMessageCodeService;
import com.pandapay.utils.SendMessageUtil;

/**
 * 用户短信验证码
 * @author 豆豆
 *
 */
@Service("userMessageCodeService")
public class UserMessageCodeServiceImpl implements IUserMessageCodeService {

	/**用户短信验证码*/
	@Autowired
	private IUserMessageCodeDao userMessageCodeDao;
	
	/**
	 * 新增短信验证码
	 * @param phoneNo 手机号码
	 * @return 操作成功：返回code=1，操作失败：返回code!=1
	 */
	public JsonObjectBO addMessageCode(String phoneNo){
		Timestamp lingcen = new Timestamp(DateUtil.lingchenLong());
		JsonObjectBO jsonObjectBO = new JsonObjectBO();
		
		int todayNum = userMessageCodeDao.queryTodayNum(phoneNo, lingcen);
		if(todayNum > 10){
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("今日发送验证码次数已超过限制");
			return jsonObjectBO;
		}
		
		String messageCode = NumberUtil.createNumberStr(6);
		
		//向数据库添加短信验证码
		UserMessageCodeDO userMessageCode = new UserMessageCodeDO();
		userMessageCode.setPhoneNo(phoneNo);
		userMessageCode.setMessageCode(messageCode);
		userMessageCode.setUseStatus(0);
		userMessageCode.setAddTime(DateUtil.getCurrentTime());
		
		boolean insertMessageCode = userMessageCodeDao.insertMessageCode(userMessageCode);
		if(!insertMessageCode){
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("添加信息失败");
			return jsonObjectBO;
		}
		
		//通过短信平台下发短信
		SendMessageUtil sendMessageUtil = new SendMessageUtil();
		boolean sendCode = sendMessageUtil.sendCode(messageCode, phoneNo);
		if(sendCode){
			jsonObjectBO.setCode(1);
			jsonObjectBO.setMessage("验证码发送成功，10分钟内有效");
			return jsonObjectBO;
		}else{
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("验证码发送失败");
			return jsonObjectBO;
		}
	}
	
	/**
	 * 验证短信验证码
	 * @param phoneNo 手机号码
	 * @param messageCode 验证码
	 * @return 验证成功：返回code=1，验证失败：返回code!=1
	 */
	public JsonObjectBO validateMessageCode(String phoneNo, String messageCode){
		Timestamp curTime = DateUtil.getCurrentTime();
		JsonObjectBO jsonObjectBO = new JsonObjectBO();
		
		UserMessageCodeDO userMessageCode = userMessageCodeDao.queryMessageCode(phoneNo, messageCode);
		if(userMessageCode == null){
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("验证码信息不存在");
			return jsonObjectBO;
		}
		
		if(userMessageCode.getUseStatus() != 0){
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("验证码已失效");
			return jsonObjectBO;
		}
		
		//10分钟内有效
		if((curTime.getTime() - userMessageCode.getAddTime().getTime()) > 10*60*1000){
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("验证码已过期");
			return jsonObjectBO;
		}
		
		boolean updateUseStatus = userMessageCodeDao.updateUseStatus(userMessageCode.getId(), 1);
		if(updateUseStatus){
			jsonObjectBO.setCode(1);
			jsonObjectBO.setMessage("验证成功");
			return jsonObjectBO;
		}else{
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("验证失败");
			return jsonObjectBO;
		}
	}
	
}
