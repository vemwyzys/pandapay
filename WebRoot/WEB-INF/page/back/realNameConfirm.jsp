<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/userAccount_real.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/public.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/simpleTips.css" />

    <script type="text/javascript" src="<%=path %>/resource/js/resizeImgNew.js"></script>
    <title>实名认证审核</title>
</head>
<body>
<div id="header"></div>


<div class="content">
    <div id="menu"></div>

    <div class="contentRight">
        <div class="title">实名认证审核</div>

        <div class="list">
            <p class="listTitle">认证基础信息</p>

            <div class="details">
                <p class="listInfo">
                    <span class="info">用户账号：<span>${userDo.userAccount }</span></span>
                    <span class="info">店铺名称：<span>${user.storeName }</span></span>
                </p>
                <p class="listInfo">
                    <span class="info">联系人：<span>${user.contactsName }</span></span>
                    <span class="info">联系电话：<span>${user.userAccount }</span></span>
                </p>
                <p class="listInfo">
                    <span class="info">地址：<span>${user.address }</span></span>
                </p>
                <p class="listInfo">
                    <span class="info">身份证号：<span>${user.idCard }</span></span>
                    <span class="info">结算账户：<span>${user.bankCard }</span></span>
                    <span class="info">结算账户：<span>${user.bankName }</span></span>
                </p>
                <p class="listInfo">
                    <span class="info">单笔限额：<span><fmt:formatNumber type="number" value="${userDo.singleLimitAmount }" maxFractionDigits="2" />元</span></span>
                    <span class="info">收款费率：<span><fmt:formatNumber type="number" value="${userDo.receiptRate }" maxFractionDigits="4" /></span></span>
                </p>
            </div>
        </div>

        <div class="list">
            <p class="listTitle">认证图片信息</p>

            <div class="details">
                <p class="imgInfo">
                    <span class="pic"><img src="${user.idCardImgFull }" class="img" onload="javascript:DrawImage(this,300,300)" /></span>
                    <span class="infoTitle">身份证正面照</span>
                </p>
                <p class="imgInfo">
                    <span class="pic"><img src="${user.idCardHandleImgFull }" class="img" onload="javascript:DrawImage(this,300,300)"  /></span>
                    <span class="infoTitle">手持身份证正面照</span>
                </p>
                <p class="imgInfo">
                    <span class="pic"><img src="${user.bankCardImgFull }" class="img" onload="javascript:DrawImage(this,300,300)"  /></span>
                    <span class="infoTitle">银行卡正面照</span>
                </p>
            </div>
        </div>

        <div class="operate">
            <a class="back" href="<%=path%>/backerWeb/backerUserAccount/show.htm">返&nbsp;回</a>
            <input type="text" value="审核拒绝" class="refuse" onfocus="this.blur()" />
            <input type="text" value="审核通过" class="pass" onfocus="this.blur()" />
        </div>
    </div>
</div>


<div class="mask">
    <div class="mask_content">
        <div class="refuse_pop">
            <span class="rt"><img src="<%=path%>/resource/image/back/location_img.png">审核拒绝</span>
            <p class="mask_tips">确定审核拒绝？</p>

            <div class="Button">
                <input type="text" class="cancel" value="取&nbsp;&nbsp;消" onfocus="this.blur()" />
                <input type="text" class="yes" value="确&nbsp;&nbsp;定" onfocus="this.blur()" onclick="examine('2')" />
            </div>
        </div>

        <div class="pass_pop">
            <span class="rt"><img src="<%=path%>/resource/image/back/location_img.png">审核通过</span>
            <p class="mask_tips">确定审核通过？</p>

            <div class="Button">
                <input type="text" class="cancel" value="取&nbsp;&nbsp;消" onfocus="this.blur()" />
                <input type="text" class="yes" value="确&nbsp;&nbsp;定" onfocus="this.blur()" onclick="examine('1')" />
            </div>
        </div>
    </div>
</div>

<form id="realNameConfirmForm" action="<%=path%>/backerWeb/backerUserAccount/realNameConfirm.htm" method="post">
	<input id="authenStatus" name="authenStatus" type="hidden" />
	<input id="userId" name="userId" type="hidden" value="${user.userId }"/>
</form>

<script type="text/javascript" src="<%=path %>/resource/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/loadBackPage.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/simpleTips.js"></script>
<script type="text/javascript">
    var popObj;
    $(function(){
        $(".refuse").click(function(){
            $(".mask").css("display","block");
            $(".refuse_pop").css("display","block");
            popObj = ".refuse_pop"
        });
        $(".pass").click(function(){
            $(".mask").css("display","block");
            $(".pass_pop").css("display","block");
            popObj = ".pass_pop"
        });
        $(".cancel").click(function(){
            $(".mask").hide();
            $(popObj).hide();
        });
        $(".yes").click(function(){
            $(".mask").hide();
            $(popObj).hide();
        });
    });

    function openTip()
    {
        openTips("阿萨德芳");
    }
</script>

<script type="text/javascript">
	window.onload = function(){
		var code = '${code}';
		var message = '${message}';
		if(code == -1){
			openTips(message);
		}
		if(code == -2){
			openTips(message);
			window.location.href = "<%=path%>/backerWeb/backerUserAccount/show.htm";
		}
		
	}

	function examine(authenStatus){
		document.getElementById("authenStatus").value = authenStatus;
		
		$("#realNameConfirmForm").submit();
	}

</script>

</body>
</html>