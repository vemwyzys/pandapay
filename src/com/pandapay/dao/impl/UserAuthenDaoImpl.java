package com.pandapay.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iqmkj.utils.LogUtil;
import com.pandapay.dao.IUserAuthenDao;
import com.pandapay.entity.DO.UserAuthenDO;

/**
 * 用户认证信息
 * @author 豆豆
 */
@Repository
public class UserAuthenDaoImpl implements IUserAuthenDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 新增用户认证信息
	 * @param userAuthen 用户认证信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean insertUserAuthen(UserAuthenDO userAuthen){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.insert("UserAuthen_insertUserAuthen", userAuthen);
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
	 * 查询认证信息
	 * @param userAuthenId 用户认证id
	 * @return 操作成功：返回认证信息，操作失败：返回null
	 */
	public UserAuthenDO queryUserAuthen(int userAuthenId){
		UserAuthenDO userAuthen = null;
		try{
			userAuthen = sqlSessionTemplate.selectOne("UserAuthen_queryUserAuthen", userAuthenId);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return userAuthen;
	}
	
	/**
	 * 查询最新认证通过的信息
	 * @param userId 用户Id
	 * @return 操作成功：返回认证信息，操作失败：返回null
	 */
	public UserAuthenDO queryLastAuthened(int userId){
		UserAuthenDO userAuthen = null;
		try{
			userAuthen = sqlSessionTemplate.selectOne("UserAuthen_queryLastAuthened", userId);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return userAuthen;
	}
	
	/**
	 * 查询最新的认证信息（以提交认证时间为准的最新）
	 * @param userId 用户Id
	 * @return 操作成功：返回认证信息，操作失败：返回null
	 */
	public UserAuthenDO queryNewAuthen(int userId){
		UserAuthenDO userAuthen = null;
		try{
			userAuthen = sqlSessionTemplate.selectOne("UserAuthen_queryNewAuthen", userId);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return userAuthen;
	}
	
	/**
	 * 修改认证状态
	 * @param userAuthenId 用户认证id
	 * @param authenStatus 认证状态，0：待认证，1：认证通过，2：认证失败
	 * @param authenTime 审核时间
	 * @param userPayId 支付平台的商户编号，可以为空
	 * @param remarks 备注，可以为空
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean updateAuthenStatus(int userAuthenId, int authenStatus, Timestamp authenTime, String userPayId, String remarks){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userAuthenId", userAuthenId);
		map.put("authenStatus", authenStatus);
		map.put("authenTime", authenTime);
		map.put("userPayId", userPayId);
		map.put("remarks", remarks);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.update("UserAuthen_updateAuthenStatus", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		
		if(resultNumber > 0){
			return true;
		}else{
			return false;
		}
	}
	
}
