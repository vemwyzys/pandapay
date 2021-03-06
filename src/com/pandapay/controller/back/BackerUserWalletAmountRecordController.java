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
import com.pandapay.entity.DO.BackerRecordWalletAmountDO;
import com.pandapay.interceptor.BackerWebInterceptor;
import com.pandapay.service.IBackerRecordWalletAmountService;

import config.FileUrlConfig;

/**
 * 用户钱包操作记录
 * @author zjl
 *
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("/backerWeb/backerUserWalletAmountRecord/")
public class BackerUserWalletAmountRecordController {
	
	/** 用户账户记录*/
	@Autowired
	private IBackerRecordWalletAmountService backerRecordWalletAmountService;
	
	/** 查询数据*/
	public void showList(HttpServletRequest request){
		
		String userAccount = StringUtil.stringNullHandle(request.getParameter("userAccount"));
		String handleTypeStr = StringUtil.stringNullHandle(request.getParameter("handleType"));
		String remarks = StringUtil.stringNullHandle(request.getParameter("remarks"));
		String backerAccount = StringUtil.stringNullHandle(request.getParameter("backerAccount"));
		String startTimeStr = StringUtil.stringNullHandle(request.getParameter("startTime"));
		String endTimeStr = StringUtil.stringNullHandle(request.getParameter("endTime"));
		String pageNumberStr = StringUtil.stringNullHandle(request.getParameter("pageNumber"));
		
		int handleType = -1;
		if(StringUtil.isNotNull(handleTypeStr)){
			handleType = Integer.parseInt(handleTypeStr);
		}
		
		Timestamp startTime = null;
		if(StringUtil.isNotNull(startTimeStr)){
			startTime = Timestamp.valueOf(startTimeStr);
		}
		
		Timestamp endTime = null;
		if(StringUtil.isNotNull(endTimeStr)){
			endTime = Timestamp.valueOf(endTimeStr);
		}
		
		int pageNumber = 0;
		if(StringUtil.isNotNull(pageNumberStr)){
			pageNumber = Integer.parseInt(pageNumberStr);
		}
		
		int pageSize = 20;
		
		int totalNumber = backerRecordWalletAmountService.queryRecordTotalOfBack(userAccount, handleType, remarks, backerAccount, startTime, endTime);
		//总页码数
		int totalPageNumber = (int) (totalNumber/1.0/pageSize);
		//若页码总数恰为页码大小的整数倍，则总数减一
		if (totalNumber > 0 && Math.ceil(totalNumber/1.0/pageSize) == totalPageNumber) {
			totalPageNumber--;
		}
		if(pageNumber > totalPageNumber){
			pageNumber = totalPageNumber;
		}
		
		List<BackerRecordWalletAmountDO> list = null;
		
		if(totalNumber > 0){
			list = backerRecordWalletAmountService.queryRecordListOfBack(userAccount, handleType, remarks, backerAccount, startTime, endTime, pageNumber, pageSize);
		}
		
		request.setAttribute("userAccount", userAccount);
		request.setAttribute("handleType", handleType);
		request.setAttribute("remarks", remarks);
		request.setAttribute("backerAccount", backerAccount);
		request.setAttribute("startTime", startTimeStr);
		request.setAttribute("endTime", endTimeStr);
		
		request.setAttribute("totalNumber", totalNumber);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("totalPageNumber", totalPageNumber);
		
		request.setAttribute("list", list);
		
		//添加当前页面，页面权限码
		request.getSession().setAttribute("backer_pagePowerId", 181100);
	}
	
	/** 展示页面*/
	@RequestMapping("show.htm")
	public String show(HttpServletRequest request){
		boolean result = BackerWebInterceptor.validatePower(request, 181101);
		if (!result) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "您没有该权限！");
			request.getSession().setAttribute("backer_pagePowerId", 0);
			return "page/back/index";
		}
		
		showList(request);
		
		request.setAttribute("code", 1);
		request.setAttribute("message", "操作成功！");
		return "page/back/backerRecordWalletAmount";
		
	}
	
	/** 导出数据*/
	@RequestMapping(value = "exportData", method = RequestMethod.POST)
	public @ResponseBody JsonObjectBO exportData(HttpServletRequest request){
		
		JsonObjectBO responseJson = new JsonObjectBO();
		
		boolean result = BackerWebInterceptor.validatePower(request, 181102);
		if(!result){
			responseJson.setCode(2);
			responseJson.setMessage("您没有该权限");
			return responseJson;
		}
		
		String userAccount = StringUtil.stringNullHandle(request.getParameter("userAccount"));
		String handleTypeStr = StringUtil.stringNullHandle(request.getParameter("handleType"));
		String remarks = StringUtil.stringNullHandle(request.getParameter("remarks"));
		String backerAccount = StringUtil.stringNullHandle(request.getParameter("backerAccount"));
		String startTimeStr = StringUtil.stringNullHandle(request.getParameter("startTime"));
		String endTimeStr = StringUtil.stringNullHandle(request.getParameter("endTime"));
		
		int handleType = -1;
		if(StringUtil.isNotNull(handleTypeStr)){
			handleType = Integer.parseInt(handleTypeStr);
		}
		
		Timestamp startTime = null;
		if(StringUtil.isNotNull(startTimeStr)){
			startTime = Timestamp.valueOf(startTimeStr);
		}
		
		Timestamp endTime = null;
		if(StringUtil.isNotNull(endTimeStr)){
			endTime = Timestamp.valueOf(endTimeStr);
		}
		
		int pageNumber = 0;
		int pageSize = 20;
		
		int totalNumber = backerRecordWalletAmountService.queryRecordTotalOfBack(userAccount, handleType, remarks, backerAccount, startTime, endTime);
		//总页码数
		int totalPageNumber = (int) (totalNumber/1.0/pageSize);
		//若页码总数恰为页码大小的整数倍，则总数减一
		if (totalNumber > 0 && Math.ceil(totalNumber/1.0/pageSize) == totalPageNumber) {
			totalPageNumber--;
		}
		
		List<BackerRecordWalletAmountDO> list = null;
		
		if(totalNumber > 0){
			list = backerRecordWalletAmountService.queryRecordListOfBack(userAccount, handleType, remarks, backerAccount, startTime, endTime, pageNumber, pageSize);
		}
		
		XSSFWorkbook workBook = null;
		OutputStream stream = null;
		
		try {
			workBook = new XSSFWorkbook();
			XSSFSheet sheet1 = workBook.createSheet("提现记录");
			
			//创建第一行表头
			XSSFRow row = sheet1.createRow(0);
			XSSFCell cell = row.createCell(0);
			cell.setCellValue("操作时间");
			cell = row.createCell(1);
			cell.setCellValue("用户账号");
			cell = row.createCell(2);
			cell.setCellValue("操作金额");
			cell = row.createCell(3);
			cell.setCellValue("操作类型");
			cell = row.createCell(4);
			cell.setCellValue("操作备注");
			cell = row.createCell(5);
			cell.setCellValue("操作管理员");
			cell = row.createCell(6);
			cell.setCellValue("操作时IP");
			cell = row.createCell(7);
			
			int i = 1;
			for(BackerRecordWalletAmountDO backerRecordWalletAmountDO : list){
				XSSFRow row2 = sheet1.createRow(i);
	            //循环写入列数据     
	            for (int j = 0; j <= 8; j++) 
	            {
	            	cell = row2.createCell(j);    
	            	switch (j) 
	            	{
						case 0:
							cell.setCellValue(DateUtil.longToTimeStr(backerRecordWalletAmountDO.getAddTime().getTime(), DateUtil.dateFormat2));
							break;
						case 1:
							cell.setCellValue(backerRecordWalletAmountDO.getUserAccount());
							break;
						case 2:
							cell.setCellValue(backerRecordWalletAmountDO.getHandleAmount());
							break;
						case 3:
							if(backerRecordWalletAmountDO.getHandleType() == 1){
								cell.setCellValue("增加");
							}else if(backerRecordWalletAmountDO.getHandleType() == 2){
								cell.setCellValue("减少");
							}
							break;
						case 4:
							cell.setCellValue(backerRecordWalletAmountDO.getRemarks());
							break;
						case 5:
							cell.setCellValue(backerRecordWalletAmountDO.getBackerAccount());
							break;
						case 6:
							cell.setCellValue(backerRecordWalletAmountDO.getIpAddress());
							break;
						default:
							break;
					}
	            }
	            i++;
			}
			String fileName = "backerUserWalletAmountRecord.xlsx";
			
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
