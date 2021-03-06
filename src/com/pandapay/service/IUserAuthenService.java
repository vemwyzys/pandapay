package com.pandapay.service;

import com.pandapay.entity.DO.UserAuthenDO;

/**
 * 用户认证信息
 * @author 豆豆
 */
public interface IUserAuthenService {

	/**
	 * 新增用户认证信息
	 * @param userAuthen 用户认证信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean insertUserAuthen(UserAuthenDO userAuthen);
	
	/**
	 * 查询最新认证通过的信息
	 * @param userId 用户Id
	 * @return 操作成功：返回认证信息，操作失败：返回null
	 */
	public UserAuthenDO queryLastAuthened(int userId);
	
	/**
	 * 查询最新的认证信息（以提交认证时间为准的最新）
	 * @param userId 用户Id
	 * @return 操作成功：返回认证信息，操作失败：返回null
	 */
	public UserAuthenDO queryNewAuthen(int userId);
	
	/**
	 * 认证通过
	 * @param userAuthenId 用户认证Id
	 * @param remarks 备注，可以为空
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean updateAuthenSuccess(int userAuthenId, String remarks);
	
	/**
	 * 认证拒绝
	 * @param userAuthenId 用户认证id
	 * @param remarks 备注，可以为空
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean updateAuthenFail(int userAuthenId, String remarks);
	
}
