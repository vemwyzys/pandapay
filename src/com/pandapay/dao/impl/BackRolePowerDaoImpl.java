package com.pandapay.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iqmkj.utils.LogUtil;
import com.pandapay.dao.IBackRolePowerDao;
import com.pandapay.entity.DO.BackRolePowerDO;
import com.pandapay.entity.DTO.BackRolePowerDTO;

/**
 * 角色权限
 * @author whx
 *
 */
@Repository
public class BackRolePowerDaoImpl implements IBackRolePowerDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 新增角色权限
	 * @param backRolePowerList 角色权限信息列表
	 * @return 操作成功：返回新增成功数量：失败：返回0
	 */
	public int insertBackRolePower(List<BackRolePowerDO> backRolePowerList){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.insert("BackRolePower_insertBackRolePower", backRolePowerList);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultNumber;
	}
	
	/**
	 * 查询角色拥有的权限
	 * @param roleId 角色Id
	 * @return 操作成功：返回权限Id列表，失败：返回null
	 */
	public List<Integer> queryPowerIdByRoleId(int roleId){
		List<Integer> powerIdList = null;
		try{
			powerIdList = sqlSessionTemplate.selectList("BackRolePower_queryPowerIdByRoleId", roleId);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return powerIdList;
	}
	
	/**
	 * 查询角色拥有的权限（角色展示）
	 * @param roleId 角色Id
	 * @return 操作成功：返回角色权限列表，失败：返回null
	 */
	public List<BackRolePowerDTO> queryBackRolePowerDTOList(int roleId){
		List<BackRolePowerDTO> backRolePowerList = null;
		try{
			backRolePowerList = sqlSessionTemplate.selectList("BackRolePower_queryBackRolePowerDTOList", roleId);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return backRolePowerList;
	}
	
	/**
	 * 验证角色是否拥有该权限
	 * @param roleId 角色Id
	 * @param powerId 权限Id
	 * @return 拥有该权限：返回true，未拥有该权限：返回false
	 */
	public boolean validateBackRolePower(int roleId, int powerId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("roleId", roleId);
		map.put("powerId", powerId);
		
		int resultNumber = 0;
		try{
			resultNumber =sqlSessionTemplate.selectOne("BackRolePower_validateBackRolePower", map);
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
	 * 删除角色下的所有权限（删除角色）
	 * @param roleId 角色Id
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean deleteAllBackRolePowerByRoleId(int roleId){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.delete("BackRolePower_deleteAllBackRolePowerByRoleId", roleId);
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
