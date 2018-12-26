package com.pandapay.controller.wap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iqmkj.utils.StringUtil;
import com.pandapay.entity.BO.JsonObjectBO;
import com.pandapay.entity.BO.UserSessionBO;
import com.pandapay.interceptor.UserWapInterceptor;
import com.pandapay.service.IUserOrderPayService;

/**
 * 提现
 * @author XEX
 *
 */
@Controller
@RequestMapping("/userWap/wapTakeCash")
@Scope(value="prototype")
public class WapTakeCashCotroller {
	
	/** 用户支付订单 */
	@Autowired
	private IUserOrderPayService userOrderPayService;
	
	/** 进入收款页面 */
	@RequestMapping(value = "/show.htm")
	public String show(HttpServletRequest request){
		return "page/wap/MyJsp";
	}
	
	/** 提交收款信息，打开支付二维码 */
	@RequestMapping(value = "/takeCash.htm")
	public String takeCash(HttpServletRequest request){
		UserSessionBO userSessionBO = UserWapInterceptor.getUser(request);
		
		String payAmountStr = StringUtil.stringNullHandle(request.getParameter("payAmount"));
		String payWayStr = StringUtil.stringNullHandle(request.getParameter("payWay"));//支付渠道,1：支付宝，2：微信
		
		if(!StringUtil.isNotNull(payAmountStr) 
    			|| !StringUtil.isNotNull(payWayStr)){
        	request.setAttribute("code", 2);
			request.setAttribute("message", "参数错误");
			return "page/wap/MyJsp";
    	}
		
		double payAmount = 0;
		if(StringUtil.isNotNull(payAmountStr)){
			payAmount = Double.parseDouble(payAmountStr);
		}
		
		int payWay = 0;
		if(StringUtil.isNotNull(payAmountStr)){
			payWay = Integer.parseInt(payAmountStr);
		}
		
    	if(payAmount <= 0 
    			|| payAmount > 1000000
    			|| payWay <= 0 
    			|| payWay > 2){
    		request.setAttribute("code", 2);
			request.setAttribute("message", "参数错误");
			return "page/wap/MyJsp";
    	}
    	
    	/*JsonObjectBO userOrderPay = userOrderPayService.creatPayOrder(userSessionBO.getUserId(), payAmount, 2, payWay);
		if (userOrderPay.getCode() != 1){
        	request.setAttribute("code", 2);
			request.setAttribute("message", "操作失败");
			return "page/wap/MyJsp";
		}
		
		request.setAttribute("code", 1);
		request.setAttribute("message", "操作成功");
		request.setAttribute("payCodeInfo", userOrderPay.getData().getString("payCodeInfo"));
		return "page/wap/MyJsp";*/
    	
    	//提现功能只是上传信息就好，不是走支付的渠道啊？
    	return "";
	}
	
}
