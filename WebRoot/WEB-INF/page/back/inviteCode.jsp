<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="<%=path%>/resource/image/back/icon.ico" type="image/x-ico">

    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/invitationCode.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/simpleTips.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/public.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/js/need/laydate.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/js/skins/dahong/laydate.css">
    <title>邀请码库</title>
</head>
<body>
<div id="header"></div>


<div class="content">
    <div id="menu"></div>

    <div class="contentRight">
        <div class="title">邀请码库</div>
		<form id="queryForm" action="<%=path%>/backerWeb/backerInviteCode/show.htm" method="post">
	        <div class="top">
	            <p class="condition">邀请码：<input name="inviteCode" value="${inviteCode }" type="text" class="query" placeholder="请输入邀请码" maxlength="25"/></p>
	            <p class="condition">所属账号：<input name="userAccount" value="${userAccount }" type="text" class="query" placeholder="请输入所属账号" maxlength="16"
	            							onkeyup="value=value.replace(/[\W]/g,'')" onblur="value=value.replace(/[\W]/g,'')"/></p>
	            <p class="condition">使用状态：
	                <select class="choose" name="useStatus">
	                	<c:if test="${useStatus == -1 }">
	                		<option value="-1" selected="selected">全部</option>
	                		<option value="0">未使用</option>
	                		<option value="1">已使用</option>
	                	</c:if>
	                	
	                	<c:if test="${useStatus == 0 }">
	                		<option value="-1">全部</option>
	                		<option value="0" selected="selected">未使用</option>
	                		<option value="1">已使用</option>
	                	</c:if>
	                	
	                	<c:if test="${useStatus == 1 }">
	                		<option value="-1">全部</option>
	                		<option value="0">未使用</option>
	                		<option value="1" selected="selected">已使用</option>
	                	</c:if>
	                </select>
	            </p>
	            <p class="condition">
	                使用时间：
	                从<input name="startTime" value="${startTime }" placeholder="请选择起始时间"  readonly="readonly" class="startTime" onClick="laydate({istime: true,format:'YYYY-MM-DD hh:mm:ss'})">
	                到<input name="endTime" value="${endTime }" placeholder="请选择结束时间"  readonly="readonly" class="endTime" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
	            </p>
				<input type="hidden" id="query_pageNumber" name="pageNumber" value="${pageNumber}">
				<c:forEach items="${backer_rolePowerList}" var="powerId">
					<c:if test="${powerId == 151001 }">
			            <input type="text" value="查&nbsp;询" class="ask" onfocus="this.blur()" onclick="queryHandle()"/>
					</c:if>
				</c:forEach>	
				<c:forEach items="${backer_rolePowerList}" var="powerId">
					<c:if test="${powerId == 151002 }">
			            <input type="text" value="导&nbsp;出" class="educe" onfocus="this.blur()" onclick="exportData()"/>
					</c:if>
				</c:forEach>	
	        </div>
		</form>
        <table class="table" cellspacing="0" cellpadding="0">
            <tr class="tableTitle">
                <td class="code">邀请码</td>
                <td class="account">所属账号</td>
                <td class="getTime">获得时间</td>
                <td class="outTime">过期时间</td>
                <td class="state">使用状态</td>
                <td class="useAccount">使用者账号</td>
                <td class="useTime">使用时间</td>
            </tr>
            <c:forEach items="${list }" var="inviteCode">
	            <tr class="tableInfo">
	                <td class="code">${inviteCode.inviteCode }</td>
	                <td class="account">${inviteCode.userAccountOwn }</td>
	                <td class="getTime"><fmt:formatDate value="${inviteCode.addTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	                <td class="outTime"><fmt:formatDate value="${inviteCode.outTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	                <td class="state">
	                	<c:if test="${inviteCode.useStatus == 0 }">
	                		未使用
	                	</c:if>
	                	<c:if test="${inviteCode.useStatus == 1 }">
	                		已使用
	                	</c:if>
	                </td>
	                <td class="useAccount">${inviteCode.userAccountUsed }</td>
	                <td class="useTime"><fmt:formatDate value="${inviteCode.usedTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	            </tr>
            </c:forEach>
        </table>

         <div class="changePage">
            <p class="total">共${totalNumber }条</p>
            <p class="jump">
            	<c:if test="${pageNumber <= totalPageNumber }">
	                <input type="text" id="goPageNumber" value="${pageNumber + 1}" onkeyup="value=value.replace(/[^\d]/g,'')" onkeyup="value=value.replace(/[^\d]/g,'')"/>
            	</c:if>
            	<c:if test="${pageNumber > totalPageNumber }">
	                <input type="text" id="goPageNumber" value="${totalPageNumber + 1}" onkeyup="value=value.replace(/[^\d]/g,'')" onkeyup="value=value.replace(/[^\d]/g,'')"/>
            	</c:if>
                <input type="text" value="跳&nbsp;转" class="jumpButton" onfocus="this.blur()" onclick="goPage()"/>
            </p>
            <p class="page">
                <input type="text" value="<上一页" onfocus="this.blur()" onclick="prePage()"/>
                <span class="pageNumber">
                	<span>${pageNumber + 1 }</span>/<span>${totalPageNumber + 1}</span>
                </span>
                <input type="text" value="下一页>" onfocus="this.blur()" onclick="nextPage()"/>
            </p>
        </div>
    </div>
</div>

<script type="text/javascript" src="<%=path %>/resource/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/simpleTips.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/laydate.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/loadBackPage.js"></script>
<script type="text/javascript">
    $(function(){
        !function(){
            laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
        }();
    });
</script>

<script type="text/javascript">
	window.onload = function(){
		var code = '${code}';
		var message = '${message}';
		
		if(code != 1){
			openTips(message);
		}
	}

	function queryHandle(){
	    //点击查询时传给控制器的页面值应人为设置为0，不直接传0是因为还有上一页下一页
		document.getElementById('query_pageNumber').value = "0";
		$('#queryForm').submit(); 
	}
	
	//上一页
	function prePage(){
	    var pageNumber = '${pageNumber}';
	    //var backerAccount = document.getElementById('backerAccount').value;
	    if(pageNumber > 0){
	        pageNumber--;
	        
	        document.getElementById("query_pageNumber").value = pageNumber;
	        $('#queryForm').submit();
	    }else{
	    	openTips("当前已是第一页");
	    }
	}
	//下一页
	function nextPage(){
		var pageNumber = '${pageNumber}';
		var totalPageNumber = '${totalPageNumber}';
		if(pageNumber < totalPageNumber){
		    pageNumber++;
		    document.getElementById("query_pageNumber").value = pageNumber;
		    $('#queryForm').submit();
		}else{
			openTips("当前已是最后一页");
		}
	}
	//跳转到指定页
	function goPage(){
	    var goPageNumber = document.getElementById('goPageNumber').value;
	    var totalPageNumber = '${totalPageNumber}';
	    var pageNumber = '${pageNumber}';
	    
	    if(goPageNumber == null || goPageNumber == "" || goPageNumber <= 0){
	        goPageNumber = 1;
	    }
	    if(goPageNumber > totalPageNumber){
	       //openTips("指定跳转页不存在")
	       goPageNumber = ++totalPageNumber;
	    }
	    //显示具体跳转到了哪一页
	    document.getElementById('goPageNumber').value = goPageNumber;
	    pageNumber = goPageNumber - 1;
	    document.getElementById('query_pageNumber').value = pageNumber;
	    $('#queryForm').submit();
	}
	var flag = true;
	//导出数据
	function exportData(){
        if(flag == false){
			return;
		}        
        flag = false;
        
        var inviteCode = '${inviteCode}';
        var userAccount = '${userAccount}';
        var useStatus = '${useStatus}';
        var startTime = '${startTime}';
        var endTime = '${endTime}';
        
        $.ajax({
            url: '<%=path%>' + "/backerWeb/backerInviteCode/exportData", //方法路径URL
            data:{
                inviteCode : inviteCode,
                userAccount : userAccount,
                useStatus : useStatus,
                startTime : startTime,
                endTime: endTime,
            },//参数
            dataType: "json",
            type: 'POST',
            async: true, //默认异步调用 (false：同步)
            success: function (data) {
                if(data.code == 1){
                     window.location.href = '<%=path%>' + data.data.url;
                }else{
                    openTips(data.message);
                }
                flag = true;
                
            },
            error: function () {
                openTips("服务器异常");
                flag = true;
            }
        });
    }

</script>

</body>
</html>
