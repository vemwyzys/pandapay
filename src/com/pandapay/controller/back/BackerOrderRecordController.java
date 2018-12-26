package com.pandapay.controller.back;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.iqmkj.utils.DateUtil;
import com.iqmkj.utils.FileWriteLocalUtil;
import com.iqmkj.utils.LogUtil;
import com.iqmkj.utils.StringUtil;
import com.pandapay.entity.BO.JsonObjectBO;
import com.pandapay.entity.DO.UserOrderPayDO;
import com.pandapay.interceptor.BackerWebInterceptor;
import com.pandapay.service.IUserOrderPayService;

import config.FileUrlConfig;

/**
 * 用户支付订单管理
 * @author zjl
 *
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("/backerWeb/backerOrderRecord")
public class BackerOrderRecordController {
	
	/** 用户支付服务*/
	@Autowired
	private IUserOrderPayService userOrderPayService;
	
	/** 查询数据*/
	public void showList(HttpServletRequest request){
		String orderNo = StringUtil.stringNullHandle(request.getParameter("orderNo"));
		String userAccount = StringUtil.stringNullHandle(request.getParameter("userAccount"));
		String serviceTypeStr = StringUtil.stringNullHandle(request.getParameter("serviceType"));
		String payWayStr = StringUtil.stringNullHandle(request.getParameter("payWay"));
		String startTimeStr = StringUtil.stringNullHandle(request.getParameter("startTime"));
		String endTimeStr = StringUtil.stringNullHandle(request.getParameter("endTime"));
		String payStatusStr = StringUtil.stringNullHandle(request.getParameter("payStatus"));
		String pageNumberStr = StringUtil.stringNullHandle(request.getParameter("pageNumber"));
		
		int serviceType = -1;
		if(StringUtil.isNotNull(serviceTypeStr)){
			serviceType = Integer.parseInt(serviceTypeStr);
		}
		
		int payWay = -1;
		if(StringUtil.isNotNull(payWayStr)){
			payWay = Integer.parseInt(payWayStr);
		}
		
		Timestamp startTime = null;
		if(StringUtil.isNotNull(startTimeStr)){
			startTime = Timestamp.valueOf(startTimeStr);
		}
		
		Timestamp endTime = null;
		if(StringUtil.isNotNull(endTimeStr)){
			endTime = Timestamp.valueOf(endTimeStr);
		}
		
		int payStatus = -1;
		if(StringUtil.isNotNull(payStatusStr)){
			payStatus = Integer.parseInt(payStatusStr);
		}
		
		int pageNumber = 0;
		if(StringUtil.isNotNull(pageNumberStr)){
			pageNumber = Integer.parseInt(pageNumberStr);
		}
		
		//单页数据条数
		int pageSize = 20;
		int totalNumber = userOrderPayService.queryOrderTotalOfBack(orderNo, userAccount, serviceType, payWay, payStatus, startTime, endTime);
		//总页码数
		int totalPageNumber = (int) (totalNumber/1.0/pageSize);
		//若页码总数恰为页码大小的整数倍，则总数减一
		if (totalNumber > 0 && Math.ceil(totalNumber/1.0/pageSize) == totalPageNumber) {
			totalPageNumber--;
		}
		if(pageNumber > totalPageNumber){
			pageNumber = totalPageNumber;
		}
		
		List<UserOrderPayDO> list = null;
		
		if(totalNumber > 0){
			list = userOrderPayService.queryOrderListOfBack(orderNo, userAccount, serviceType, payWay, payStatus, startTime, endTime, pageNumber, pageSize);
		}
		
		request.setAttribute("orderNo", orderNo);
		request.setAttribute("userAccount", userAccount);
		request.setAttribute("serviceType", serviceType);
		request.setAttribute("payWay", payWay);
		request.setAttribute("startTime", startTimeStr);
		request.setAttribute("endTime", endTimeStr);
		request.setAttribute("payStatus", payStatus);
		
		request.setAttribute("totalNumber", totalNumber);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("totalPageNumber", totalPageNumber);
		
		request.setAttribute("orderList", list);
		
		//添加当前页面，页面权限码
		request.getSession().setAttribute("backer_pagePowerId", 131000);
	}
	
	/** 展示页面*/
	@RequestMapping("show.htm")
	public String show(HttpServletRequest request){
		
		boolean result = BackerWebInterceptor.validatePower(request, 131001);
		if (!result) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "您没有该权限！");
			request.getSession().setAttribute("backer_pagePowerId", 0);
			return "page/back/index";
		}
		
		showList(request);
		request.setAttribute("code", 1);
		request.setAttribute("message", "查询成功！");
		
		return "page/back/orderRecord";
	}
	
	/** 导出数据*/
	@RequestMapping(value = "exportData", method = RequestMethod.POST)
	public @ResponseBody JsonObjectBO exportData(HttpServletRequest request){
		JsonObjectBO responseJson = new JsonObjectBO();
		
		boolean result = BackerWebInterceptor.validatePower(request, 131002);
		if(!result){
			responseJson.setCode(2);
			responseJson.setMessage("您没有该权限");
			return responseJson;
		}
		
		String orderNo = StringUtil.stringNullHandle(request.getParameter("orderNo"));
		String userAccount = StringUtil.stringNullHandle(request.getParameter("userAccount"));
		String serviceTypeStr = StringUtil.stringNullHandle(request.getParameter("serviceType"));
		String payWayStr = StringUtil.stringNullHandle(request.getParameter("payWay"));
		String startTimeStr = StringUtil.stringNullHandle(request.getParameter("startTime"));
		String endTimeStr = StringUtil.stringNullHandle(request.getParameter("endTime"));
		String payStatusStr = StringUtil.stringNullHandle(request.getParameter("payStatus"));
		
		int serviceType = -1;
		if(StringUtil.isNotNull(serviceTypeStr)){
			serviceType = Integer.parseInt(serviceTypeStr);
		}
		
		int payWay = -1;
		if(StringUtil.isNotNull(payWayStr)){
			payWay = Integer.parseInt(payWayStr);
		}
		
		Timestamp startTime = null;
		if(StringUtil.isNotNull(startTimeStr)){
			startTime = Timestamp.valueOf(startTimeStr);
		}
		
		Timestamp endTime = null;
		if(StringUtil.isNotNull(endTimeStr)){
			endTime = Timestamp.valueOf(endTimeStr);
		}
		
		int payStatus = -1;
		if(StringUtil.isNotNull(payStatusStr)){
			payStatus = Integer.parseInt(payStatusStr);
		}
		
		int pageNumber = 0;
		
		//单页数据条数
		int pageSize = 10000;
		long totalNumber = userOrderPayService.queryOrderTotalOfBack(orderNo, userAccount, serviceType, payWay, payStatus, startTime, endTime);
		//总页码数
		int totalPageNumber = (int) (totalNumber/1.0/pageSize);
		//若页码总数恰为页码大小的整数倍，则总数减一
		if (totalNumber > 0 && Math.ceil(totalNumber/1.0/pageSize) == totalPageNumber) {
			totalPageNumber--;
		}
		
		List<UserOrderPayDO> list = null;
		if(totalNumber > 0){
			list = userOrderPayService.queryOrderListOfBack(orderNo, userAccount, serviceType, payWay, payStatus, startTime, endTime, pageNumber, pageSize);
		}
		
		XSSFWorkbook workBook = null;
		OutputStream stream = null;
		
		try {
			workBook = new XSSFWorkbook();
			XSSFSheet sheet1 = workBook.createSheet("提现记录");
			
			//创建第一行表头
			XSSFRow row = sheet1.createRow(0);
			XSSFCell cell = row.createCell(0);
			cell.setCellValue("订单号");
			cell = row.createCell(1);
			cell.setCellValue("用户账号");
			cell = row.createCell(2);
			cell.setCellValue("支付金额");
			cell = row.createCell(3);
			cell.setCellValue("支付业务");
			cell = row.createCell(4);
			cell.setCellValue("支付渠道");
			cell = row.createCell(5);
			cell.setCellValue("申请时间");
			cell = row.createCell(6);
			cell.setCellValue("充值状态");
			cell = row.createCell(7);
			cell.setCellValue("提现备注");
			cell = row.createCell(8);
			
			int i = 1;
			for(UserOrderPayDO userOrderPayDO : list){
				XSSFRow row2 = sheet1.createRow(i);
	            //循环写入列数据     
	            for (int j = 0; j <= 9; j++) 
	            {
	            	cell = row2.createCell(j);    
	            	switch (j) 
	            	{
						case 0:
							cell.setCellValue(userOrderPayDO.getOrderNo());
							break;
						case 1:
							cell.setCellValue(userOrderPayDO.getUserAccount());
							break;
						case 2:
							cell.setCellValue(userOrderPayDO.getPayAmount());
							break;
						case 3:
							if(userOrderPayDO.getServiceType() == 1){
								cell.setCellValue("用户订单");
							}else if(userOrderPayDO.getServiceType() == 2){
								cell.setCellValue("充值");
							}
							break;
						case 4:
							if(userOrderPayDO.getPayWay() == 1){
								cell.setCellValue("支付宝");
							}else if(userOrderPayDO.getPayWay() == 2){
								cell.setCellValue("微信");
							}
							break;
						case 5:
							cell.setCellValue(DateUtil.longToTimeStr(userOrderPayDO.getAddTime().getTime(), DateUtil.dateFormat2));
							break;
						case 6:
							if(userOrderPayDO.getPayStatus() == 0){
								cell.setCellValue("未知");
							}else if(userOrderPayDO.getPayStatus() == 1){
								cell.setCellValue("支付成功");
							}else if(userOrderPayDO.getPayStatus() == 2){
								cell.setCellValue("支付失败");
							}else if(userOrderPayDO.getPayStatus() == 3){
								cell.setCellValue("其他");
							}
							break;
						case 7:
							cell.setCellValue(userOrderPayDO.getRemarks());
							break;
						default:
							break;
					}
	            }
	            i++;
			}
			
			String fileName = "orderRecord.xlsx";
			
			stream = new ByteArrayOutputStream();
			workBook.write(stream);
			String downLoadUrl = FileWriteLocalUtil.SaveInputStreamToFile(new ByteArrayInputStream(((ByteArrayOutputStream) stream).toByteArray()), FileUrlConfig.file_local_excel_url, fileName);
		
			JSONObject data = new JSONObject();
			data.put("url", downLoadUrl);
			
			responseJson.setCode(1);
			responseJson.setMessage("导出成功");
			responseJson.setData(data);
			
		} catch (Exception e) {
			LogUtil.printErrorLog(e);
			
			responseJson.setCode(2);
			responseJson.setMessage("导出失败，请重试");
		} finally {
			try {
				workBook.close();
				stream.close();
			} catch (IOException e) {
				LogUtil.printErrorLog(e);
			}
		}
		return responseJson;
	}
	
}
