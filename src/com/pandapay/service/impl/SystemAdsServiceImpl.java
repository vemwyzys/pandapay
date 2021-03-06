package com.pandapay.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONObject;
import com.pandapay.dao.ISystemAdsDao;
import com.pandapay.entity.BO.JsonObjectBO;
import com.pandapay.entity.DO.SystemAdsDO;
import com.pandapay.service.ISystemAdsService;

/**
 * 首页广告
 * @author 豆豆
 *
 */
@Service("systemAdsService")
public class SystemAdsServiceImpl implements ISystemAdsService {

	/**首页广告*/
	@Autowired
	private ISystemAdsDao systemAdsDao;
	
	/**
	 * 新增广告
	 * @param systemAds 广告信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	@Transactional
	public boolean addAds(SystemAdsDO systemAds){
		//业务执行状态
		boolean executeSuccess = false;
		
		systemAds.setPreAdsId(0);
		SystemAdsDO firstSystemAds = systemAdsDao.queryPreAds(systemAds.getShowPosition());
		if(firstSystemAds != null){
			//添加到第一位并将先前的第一位链接到新添加的后面
			SystemAdsDO addSystemAds = systemAdsDao.insertAds(systemAds);
			if(addSystemAds != null){
				executeSuccess = systemAdsDao.updateAdsPreAdsId(firstSystemAds.getAdsId(), addSystemAds.getAdsId());
			}
		}else{
			//添加到第一位
			SystemAdsDO addSystemAds = systemAdsDao.insertAds(systemAds);
			if(addSystemAds != null){
				executeSuccess = true;
			}else{
				executeSuccess = false;
			}
		}

		//数据回滚
		if(!executeSuccess){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return executeSuccess;
	}
	
	/**
	 * 查询广告信息
	 * @param adsId 广告id
	 * @return 操作成功：返回广告信息，操作失败：返回null
	 */
	public SystemAdsDO queryAdsByAdsId(int adsId){
		return systemAdsDao.queryAdsByAdsId(adsId);
	}
	
	/**
	 * 修改广告信息
	 * @param systemAds 广告信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	@Transactional
	public boolean modifyAds(SystemAdsDO systemAds){
		//业务执行状态
		boolean executeSuccess = false;
		
		//原数据
		SystemAdsDO systemAdsOld = systemAdsDao.queryAdsByAdsId(systemAds.getAdsId());
		if(systemAdsOld == null){
			return false;
		}
		
		//显示位置未变
		if(systemAdsOld.getShowPosition() == systemAds.getShowPosition()){
			return systemAdsDao.updateAds(systemAds);
		}
		
		//老位置的后链元素
		SystemAdsDO nextAdsOld = systemAdsDao.queryNextAds(systemAdsOld.getShowPosition(), systemAdsOld.getAdsId());
		//新位置的目前元素
		SystemAdsDO curPositionAds = systemAdsDao.queryPreAds(systemAds.getShowPosition());
		
		systemAds.setPreAdsId(0);
		executeSuccess = systemAdsDao.updateAds(systemAds);
		if(executeSuccess){
			if(nextAdsOld != null){
				executeSuccess = systemAdsDao.updateAdsPreAdsId(nextAdsOld.getAdsId(), systemAdsOld.getPreAdsId());
			}
			if(executeSuccess && curPositionAds != null){
				executeSuccess = systemAdsDao.updateAdsPreAdsId(curPositionAds.getAdsId(), systemAds.getAdsId());
			}
		}
		
		//数据回滚
		if(!executeSuccess){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return executeSuccess;
	}
	
	/**
	 *  删除广告
	 * @param adsId 广告id
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	@Transactional
	public boolean deleteAds(int adsId){
		//业务执行状态
		boolean executeSuccess = false;
		
		//原数据
		SystemAdsDO systemAdsOld = systemAdsDao.queryAdsByAdsId(adsId);
		if(systemAdsOld == null){
			return false;
		}
		//后链元素
		SystemAdsDO nextAdsOld = systemAdsDao.queryNextAds(systemAdsOld.getShowPosition(), systemAdsOld.getAdsId());
		
		executeSuccess = systemAdsDao.deleteAds(adsId);
		if(executeSuccess && nextAdsOld != null){
			executeSuccess = systemAdsDao.updateAdsPreAdsId(nextAdsOld.getAdsId(), systemAdsOld.getPreAdsId());
		}
		
		//数据回滚
		if(!executeSuccess){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return executeSuccess;
	}
	
	/**
	 * 上移
	 * @param adsId 广告id
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	@Transactional
	public boolean upMove(int adsId){
		//业务执行状态
		boolean executeSuccess = false;
		
		//原数据
		SystemAdsDO systemAdsOld = systemAdsDao.queryAdsByAdsId(adsId);
		if(systemAdsOld == null || systemAdsOld.getPreAdsId() <= 0){
			return false;
		}
		//前链元素
		SystemAdsDO preAdsOld = systemAdsDao.queryAdsByAdsId(systemAdsOld.getPreAdsId());
		//后链元素
		SystemAdsDO nextAdsOld = systemAdsDao.queryNextAds(systemAdsOld.getShowPosition(), systemAdsOld.getAdsId());
		
		executeSuccess = systemAdsDao.updateAdsPreAdsId(systemAdsOld.getAdsId(), preAdsOld.getPreAdsId());
		if(executeSuccess){
			executeSuccess = systemAdsDao.updateAdsPreAdsId(preAdsOld.getAdsId(), systemAdsOld.getAdsId());
			if(executeSuccess && nextAdsOld != null){
				executeSuccess = systemAdsDao.updateAdsPreAdsId(nextAdsOld.getAdsId(), preAdsOld.getAdsId());
			}
		}
		
		//数据回滚
		if(!executeSuccess){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return executeSuccess;
	}
	
	/**
	 * 下移
	 * @param adsId 广告id
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	@Transactional
	public boolean downMove(int adsId){
		//业务执行状态
		boolean executeSuccess = false;
		
		//原数据
		SystemAdsDO systemAdsOld = systemAdsDao.queryAdsByAdsId(adsId);
		if(systemAdsOld == null){
			return false;
		}
		//后链元素
		SystemAdsDO nextAdsOld = systemAdsDao.queryNextAds(systemAdsOld.getShowPosition(), systemAdsOld.getAdsId());
		if(nextAdsOld == null){
			return false;
		}
		//后链的后链元素
		SystemAdsDO nextNextAdsOld = systemAdsDao.queryNextAds(nextAdsOld.getShowPosition(), nextAdsOld.getAdsId());
		
		executeSuccess = systemAdsDao.updateAdsPreAdsId(systemAdsOld.getAdsId(), nextAdsOld.getAdsId());
		if(executeSuccess){
			executeSuccess = systemAdsDao.updateAdsPreAdsId(nextAdsOld.getAdsId(), systemAdsOld.getPreAdsId());
			if(executeSuccess && nextNextAdsOld != null){
				executeSuccess = systemAdsDao.updateAdsPreAdsId(nextNextAdsOld.getAdsId(), systemAdsOld.getAdsId());
			}
		}
		
		//数据回滚
		if(!executeSuccess){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return executeSuccess;
	}
	
	/**
	 * 查询全部的广告信息（按显示位置分组过）
	 * @return 操作成功：返回code=1，操作失败：返回code!=1
	 */
	public JsonObjectBO queryAllList(){
		JsonObjectBO jsonObjectBO = new JsonObjectBO();
		
		List<SystemAdsDO> adsList = systemAdsDao.queryAdsList();
		if(adsList == null || adsList.size() <= 0){
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("未查询到数据");
			return jsonObjectBO;
		}
		
		//找第一个元素
		SystemAdsDO firtHeadAds = null;
		SystemAdsDO firtBottomAds = null;
		for (SystemAdsDO systemAdsDO : adsList) {
			if(systemAdsDO.getShowPosition() == 0 && systemAdsDO.getPreAdsId() <= 0){
				firtHeadAds = systemAdsDO;
			}
			if(systemAdsDO.getShowPosition() == 1 && systemAdsDO.getPreAdsId() <= 0){
				firtBottomAds = systemAdsDO;
			}
		}
		
		//按顺序找出所有的顶部元素
		List<SystemAdsDO> headList = new ArrayList<SystemAdsDO>();
		if(firtHeadAds != null){
			headList.add(firtHeadAds);
			do {
				boolean hasNext = false;
				for (SystemAdsDO systemAdsDO : adsList) {
					if(systemAdsDO.getShowPosition() == 0 && systemAdsDO.getPreAdsId() == firtHeadAds.getAdsId()){
						firtHeadAds = systemAdsDO;
						hasNext = true;
						headList.add(firtHeadAds);
					}
				}
				
				if(!hasNext){
					firtHeadAds = null;
				}
			} while (firtHeadAds != null);
		}
		
		//按顺序找出所有的底部元素
		List<SystemAdsDO> bottomList = new ArrayList<SystemAdsDO>();
		if(firtBottomAds != null){
			bottomList.add(firtBottomAds);
			do {
				boolean hasNext = false;
				for (SystemAdsDO systemAdsDO : adsList) {
					if(systemAdsDO.getShowPosition() == 1 && systemAdsDO.getPreAdsId() == firtBottomAds.getAdsId()){
						firtBottomAds = systemAdsDO;
						hasNext = true;
						bottomList.add(firtBottomAds);
					}
				}
				
				if(!hasNext){
					firtBottomAds = null;
				}
			} while (firtBottomAds != null);
		}
		
		JSONObject jsonData = new JSONObject();
		jsonData.put("headList", headList);
		jsonData.put("bottomList", bottomList);
		
		jsonObjectBO.setCode(1);
		jsonObjectBO.setMessage("查询成功");
		jsonObjectBO.setData(jsonData);
		return jsonObjectBO;
	}
	
}
