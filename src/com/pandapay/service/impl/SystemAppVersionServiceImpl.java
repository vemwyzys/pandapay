package com.pandapay.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pandapay.dao.ISystemAppVersionDao;
import com.pandapay.entity.DO.SystemAppVersionDO;
import com.pandapay.service.ISystemAppVersionService;

/**
 * APP版本管理
 * @author 豆豆
 *
 */
@Service("systemAppVersionService")
public class SystemAppVersionServiceImpl implements ISystemAppVersionService {
	
	/** APP版本管理 */
	@Autowired
	private ISystemAppVersionDao systemAppVersionDao;
	
	/**
	 * 新增APP版本信息
	 * @param systemAppVersion APP版本信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean insertSystemAppVersion(SystemAppVersionDO systemAppVersion){
		return systemAppVersionDao.insertSystemAppVersion(systemAppVersion);
	}
	
	/**
	 * 查询APP版本信息
	 * @param versionId 版本Id
	 * @return 操作成功：返回APP版本信息，操作失败：返回null
	 */
	public SystemAppVersionDO queryVersionByVersionId(int versionId){
		return systemAppVersionDao.queryVersionByVersionId(versionId);
	}
	
	/**
	 * 查询最新的APP版本信息
	 * @param versionType 版本类型,1:Android,2:IOS
	 * @return 操作成功：返回APP版本信息，操作失败：返回null
	 */
	public SystemAppVersionDO queryNewByVersionType(int versionType){
		return systemAppVersionDao.queryNewByVersionType(versionType);
	}
	
	/**
	 * 验证APP版本是否已存在
	 * @param versionType 版本类型,1:Android,2:IOS
	 * @param versionNumber 版本号
	 * @return 已存在：返回true，不存在：返回false
	 */
	public boolean validateSystemAppVersion(int versionType, String versionNumber){
		return systemAppVersionDao.validateSystemAppVersion(versionType, versionNumber);
	}
	
	/**
	 * 查询APP版本总数（后台操作）
	 * @param versionType 版本类型,1:Android,2:IOS,查询全部填0
	 * @param versionNumber 版本号
	 * @param startTime 添加时间的开始时间
	 * @param endTime 添加时间的结束时间
	 * @return 操作成功：返回APP版本总数，操作失败：返回0
	 */
	public int querySystemAppVersionTotalOfBack(int versionType, String versionNumber, Timestamp startTime, Timestamp endTime){
		return systemAppVersionDao.querySystemAppVersionTotalOfBack(versionType, versionNumber, startTime, endTime);
	}
	
	/**
	 * 查询APP版本列表（后台操作）
	 * @param versionType 版本类型,1:Android,2:IOS,查询全部填0
	 * @param versionNumber 版本号
	 * @param startTime 添加时间的开始时间
	 * @param endTime 添加时间的结束时间
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回APP版本列表，操作失败：返回null
	 */
	public List<SystemAppVersionDO> querySystemAppVersionListOfBack(int versionType, String versionNumber,
			Timestamp startTime, Timestamp endTime, int pageNumber, int pageSize){
		return systemAppVersionDao.querySystemAppVersionListOfBack(versionType, versionNumber,
				startTime, endTime, pageNumber, pageSize);
	}
	
	/**
	 * 删除APP版本信息
	 * @param versionId 版本Id
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean deleteSystemAppVersion(int versionId){
		return systemAppVersionDao.deleteSystemAppVersion(versionId);
	}
	
}
