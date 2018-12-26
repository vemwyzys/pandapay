package com.pandapay.dao;

import java.sql.Timestamp;

import com.pandapay.entity.DO.UserMessageCodeDO;

/**
 * 用户短信验证码
 * @author 豆豆
 *
 */
public interface IUserMessageCodeDao {
	
	/**
	 * 新增短信验证码
	 * @param userMessageCode 短信验证码信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean insertMessageCode(UserMessageCodeDO userMessageCode);
	
	/**
	 * 查询短信验证码（最新的）
	 * @param phoneNo 手机号码
	 * @param messageCode 验证码
	 * @return 操作成功：返回短信验证码信息，操作失败：返回null
	 */
	public UserMessageCodeDO queryMessageCode(String phoneNo, String messageCode);
	
	/**
	 * 查询今天已发短信验证码条数
	 * @param phoneNo 手机号码
	 * @param todayDate 今天凌晨的时间
	 * @return 操作成功：返回已发条数，操作失败：返回0
	 */
	public int queryTodayNum(String phoneNo, Timestamp todayDate);
	
	/**
	 * 修改短信验证码使用状态
	 * @param id 记录Id
	 * @param useStatus 使用状态,0:未使用,1:已使用
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean updateUseStatus(long id, int useStatus);
	
}
