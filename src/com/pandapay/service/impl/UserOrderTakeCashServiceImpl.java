package com.pandapay.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pandapay.dao.IUserOrderTakeCashDao;
import com.pandapay.entity.DO.UserOrderTakeCashDO;
import com.pandapay.service.IUserOrderTakeCashService;

/**
 * 用户提现订单
 * @author 豆豆
 *
 */
@Service("userOrderTakeCashService")
public class UserOrderTakeCashServiceImpl implements IUserOrderTakeCashService {
	
	/** 用户提现订单 */
	@Autowired
	private IUserOrderTakeCashDao userOrderTakeCashDao;
	
	/**
	 * 新增提现记录
	 * @param userOrderTakeCash 提现记录
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean insertTakeCash(UserOrderTakeCashDO userOrderTakeCash){
		return userOrderTakeCashDao.insertTakeCash(userOrderTakeCash);
	}
	
	/**
	 * 修改提现状态
	 * @param orderNo 订单号
	 * @param takeCashStatus 提现状态,0:待打款,1:打款成功,2:打款失败
	 * @param remarks 提现备注
	 * @param updateTime 更新时间
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean updateTakeCashStatus(String orderNo, int takeCashStatus, String remarks, Timestamp updateTime){
		return userOrderTakeCashDao.updateTakeCashStatus(orderNo, takeCashStatus, remarks, updateTime);
	}
	
	/**
	 * 查询用户提现记录总数（用户操作）
	 * @param userId 用户id
	 * @return 操作成功：返回记录总数，操作失败：返回0
	 */
	public int queryTakeCashTotalOfUser(int userId){
		return userOrderTakeCashDao.queryTakeCashTotalOfUser(userId);
	}
	
	/**
	 * 查询用户提现记录列表（用户操作）
	 * @param userId 用户id
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回记录列表，操作失败：返回null
	 */
	public List<UserOrderTakeCashDO> queryTakeCashListOfUser(int userId, int pageNumber, int pageSize){
		return userOrderTakeCashDao.queryTakeCashListOfUser(userId, pageNumber, pageSize);
	}
	
	/**
	 * 查询用户提现记录总数（后台操作）
	 * @param orderNo 订单号
	 * @param userAccount 用户帐号
	 * @param takeCashWay 收款方式,1:支付宝, 2:微信,查询全部填0
	 * @param takeCashStatus 提现状态,0:待打款,1:打款成功,2:打款失败,查询全部填-1
	 * @param startTime 提现申请时间的开始时间
	 * @param endTime 提现申请时间的结束时间
	 * @return 操作成功：返回记录总数，操作失败：返回0
	 */
	public int queryTakeCashTotalOfBack(String orderNo, String userAccount, int takeCashWay, int takeCashStatus, Timestamp startTime, Timestamp endTime){
		return userOrderTakeCashDao.queryTakeCashTotalOfBack(orderNo, userAccount, takeCashWay, takeCashStatus, startTime, endTime);
	}
	
	/**
	 * 查询用户提现记录列表（后台操作）
	 * @param orderNo 订单号
	 * @param userAccount 用户帐号
	 * @param takeCashWay 收款方式,1:支付宝, 2:微信,查询全部填0
	 * @param takeCashStatus 提现状态,0:待打款,1:打款成功,2:打款失败,查询全部填-1
	 * @param startTime 提现申请时间的开始时间
	 * @param endTime 提现申请时间的结束时间
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回记录列表，操作失败：返回null
	 */
	public List<UserOrderTakeCashDO> queryTakeCashListOfBack(String orderNo, String userAccount, int takeCashWay, int takeCashStatus,
			Timestamp startTime, Timestamp endTime, int pageNumber, int pageSize){
		return userOrderTakeCashDao.queryTakeCashListOfBack(orderNo, userAccount, takeCashWay, takeCashStatus,
				startTime, endTime, pageNumber, pageSize);
	}
	
}
