<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0 ,user-scalable=no" />

    <link type="text/css" rel="stylesheet" href="<%=path %>/resource/css/wap/login.css" />
    <link type="text/css" rel="stylesheet" href="<%=path %>/resource/css/wap/public.css" />
    <link type="text/css" rel="stylesheet" href="<%=path %>/resource/css/wap/simpleTips.css" />
    <title>登录</title>
</head>
<body>
<div class="content">
    <img class="logo" src="<%=path %>/resource/image/wap/logo.png" />
	<form id="loginForm" action="<%=path%>/userWap/wapLogin/login" method="post">
	    <div class="login">
	        <label class="users">
	            <img src="<%=path %>/resource/image/wap/user.png" class="img" />
	            <input type="text" class="txt" placeholder="请输入登录用户名" id="userAccount" name="userAccount" value="${userAccount }"
	            onkeyup="value=value.replace(/[\W]/g,'')" onblur="value=value.replace(/[\W]/g,'')" maxlength="16"/>
	        </label>
	
	        <label class="password">
	            <img src="<%=path %>/resource/image/wap/password.png" class="img" />
	            <input type="password" class="txt" placeholder="请输入登录密码" id="password" name="password"
	            onkeyup="value=value.replace(/[\W]/g,'')" onblur="value=value.replace(/[\W]/g,'')" maxlength="16"/>
	        </label>
	    </div>
	</form>

    <input type="text" class="loginBtn" value="登 录" onclick="logined()" onfocus="this.blur()" />

    <div class="operate">
        <a href="<%=path %>/userWap/Forgetpassword/show" class="forget">忘记密码？</a>
        <a href="<%=path %>/userWap/wapRegister/show" class="register">没有账号，立即注册</a>
    </div>
</div>


<footer>浙ICP备17016571号 ©2017 熊猫支付</footer>
<script type="text/javascript" src="<%=path %>/resource/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/simpleTips_wap.js"></script>
<script type="text/javascript">
    function openTip()
    {
        openTips("阿萨德芳");
    }
</script>

<script type="text/javascript">

	window.onload = function(){
		var code = '${code}';
		var message = '${message}';
		if(code != 1 && message != ""){
			openTips(message);
			return;
		}
	}

	function keypressHandle(event)
    {
		//回车执行
    	if (event.keyCode == "13") {
			logined();
		}
    }
    
     function logined() {
    	var validate = validateFunc();
    	if(validate == true){
    		$("#loginForm").submit();
    	}
	}

	function validateFunc() {
		var usernameText = document.getElementById("userAccount");
		var passwordText = document.getElementById("password");
	
		var uname = usernameText.value;
		var upass = passwordText.value;
		
		if (uname == null || uname == "" || uname.length < 6 || uname.length > 16) {
			openTips("请检查登录用户名的填写，要求：6~16位，a~z、0~9");
			return false;
		}
		if (upass == null || upass == "" || upass.length < 6 || upass.length > 16) {
			openTips("请检查登录密码的填写，要求：6~16位，a~z、0~9");
			return false;
		}
		
		return true;
	}
</script>
</body>
</html>
