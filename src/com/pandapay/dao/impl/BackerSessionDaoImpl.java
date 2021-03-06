package com.pandapay.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iqmkj.utils.LogUtil;
import com.pandapay.dao.IBackerSessionDao;
import com.pandapay.entity.DO.BackerSessionDO;

/**
 * 后台管理员登录记录
 * @author whx
 *
 */
@Repository
public class BackerSessionDaoImpl implements IBackerSessionDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 新增后台管理员登录记录
	 * @param backerSession 后台管理员登录记录信息
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean insertBackerSession(BackerSessionDO backerSession){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.insert("BackerSession_insertBackerSession", backerSession);
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
	 * 查询后台管理员登录记录信息
	 * @param sessionId sessionId
	 * @return 操作成功：返回后台管理员登录记录信息，失败：返回null
	 */
	public BackerSessionDO queryBackerSessionBySessionId(String sessionId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sessionId", sessionId);
		
		BackerSessionDO backerSession = null;
		try{
			backerSession = sqlSessionTemplate.selectOne("BackerSession_queryBackerSessionBySessionId", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return backerSession;
	}
	
	/**
	 * 查询后台管理员登录记录总数
	 * @param backerAccount 后台管理员账号，查询全部填null
	 * @param startTime 登录时间的开始时间，查询全部填null
	 * @param endTime 登录时间的结束时间，查询全部填null
	 * @return 操作成功：返回后台管理员登录记录总数，失败：返回0
	 */
	public int queryBackerSesionTotalOfBack(String backerAccount, Timestamp startTime, Timestamp endTime){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("backerAccount", backerAccount);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.selectOne("BackerSession_queryBackerSesionTotalOfBack", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultNumber;
	}
	
	/**
	 * 查询后台管理员登录记录列表
	 * @param backerAccount 后台管理员账号，查询全部填null
	 * @param startTime 登录时间的开始时间，查询全部填null
	 * @param endTime 登录时间的结束时间，查询全部填null
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回后台管理员登录记录列表，失败：返回null
	 */
	public List<BackerSessionDO> queryBackerSessionListOfBack(String backerAccount, Timestamp startTime, Timestamp endTime,
			int pageNumber, int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("backerAccount", backerAccount);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("startNumber", pageNumber*pageSize);
		map.put("pageSize", pageSize);
		
		List<BackerSessionDO> backerSessionList = null;
		try{
			backerSessionList = sqlSessionTemplate.selectList("BackerSession_queryBackerSessionListOfBack", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return backerSessionList;
	}
	
}
