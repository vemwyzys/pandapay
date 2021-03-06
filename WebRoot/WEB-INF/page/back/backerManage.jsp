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

    <link rel="icon" href="<%=path %>/resource/image/back/icon.ico" type="image/x-ico">

    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/accountManager.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/simpleTips.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/public.css">
    <title>管理员账号</title>
</head>
<body>
<div id="header"></div>


<div class="content">
    <div id="menu"></div>

    <div class="contentRight">
        
       	<div class="title">管理员账号<p class="add"><img src="<%=path %>/resource/image/back/add.png" />添加管理员</p></div>
       	<form id="queryForm" action="<%=path %>/backerWeb/backerManage/show.htm" method="post">
       	    <input type="hidden" id="pageNumber" value="${pageNumber}" name="pageNumber" onkeyup="value=value.replace(/[\W]/g,'')" onblur="value=value.replace(/[\W]/g,'')">
        	<div class="top">
            	<p class="condition">管理员账号：<input id="query_account" value="${queryAccount}" type="text" class="query" placeholder="请输入管理员账号"
            		name="queryAccount" onkeyup="value=value.replace(/[\W]/g,'')" onblur="value=value.replace(/[\W]/g,'')" maxlength="16"/></p>
            	<input type="text" value="查&nbsp;询" class="ask" onfocus="this.blur()" onclick="queryHandle()" />
        	</div>
        </form>
        

        <table class="table" cellspacing="0" cellpadding="0">
            <tr class="tableTitle">
                <td class="account">管理员账号</td>
                <td class="role">管理员角色</td>
                <td class="time">添加时间</td>
                <td class="operate">操作</td>
            </tr>
            <c:forEach items="${backerList}" var="backer">
            	<tr class="tableInfo">
                <td class="account">${backer.backerAccount}</td>
                <td class="role">${backer.roleName}</td>
                <td class="time"><fmt:formatDate value="${backer.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td class="operate">
                	<c:forEach items="${backer_rolePowerList}" var="power">
                	    <c:if test="${power == 171103}">
                	        <input type="text" value="修改角色" class="change" onfocus="this.blur()" onclick="roleModify('${backer.roleId}','${backer.backerId}')" />
                	    </c:if>
                	</c:forEach>
                	<c:if test="${backer_backerAccount != backer.backerAccount}">
                    	<c:forEach items="${backer_rolePowerList}" var="power">
                        	<c:if test="${power == 171105}">
                	        	<input type="text" value="重置密码" class="reset" onfocus="this.blur()" onclick="resetPassword('${backer.backerId}')" />
                	    	</c:if>
                		</c:forEach>
                	</c:if>
                	<c:if test="${backer_backerAccount == backer.backerAccount}">
                		<input type="text" value="修改密码" class="changePassword" onfocus="this.blur()" onclick="modifyPassword('${backer.backerId}')" />
                	</c:if>
                    <c:forEach items="${backer_rolePowerList}" var="power">
                	    <c:if test="${power == 171104}">
                	        <input type="text" value="删&nbsp;除" class="delete" onfocus="this.blur()" onclick="deleteBacker('${backer.backerId}')" />
                	    </c:if>
                	</c:forEach>
                </td>
            </tr>
            </c:forEach> 
        </table>

        <div class="changePage">
            <p class="total">共${totalNumber}条</p>
            <p class="jump">
                <input type="text" id="goPageNumber" value="${pageNumber+1}" onkeyup="value=value.replace(/[^\d]/g,'')" onblur="value=value.replace(/[^\d]/g,'')"/>
                <input type="text" value="跳&nbsp;转" class="jumpButton" onfocus="this.blur()" onclick="goPage()"/>
            </p>
            <p class="page">
                <input type="text" value="<上一页" onfocus="this.blur()" onclick="prePage()"/>
                <span class="pageNumber"><span>${pageNumber+1}</span>/<span>${totalPageNumber+1}</span></span>
                <input type="text" value="下一页>" onfocus="this.blur()" onclick="nextPage()"/>
            </p>
        </div>
    </div>
</div>


<div class="mask">
    <div class="mask_content">
        <div class="add_pop">
            <form id="addForm" action="<%=path %>/backerWeb/backerManage/addBacker.htm" method="post">
            	<span class="rt"><img src="<%=path %>/resource/image/back/location_img.png">添加管理员</span>
            	<p class="entry_tips">
                	<label class="sign">登录账号<span>*</span>：</label>
                	<input type="text" class="box" name="addBackerAccount" id="addBackerAccount" placeholder="请输入管理员登录账号，6-16个字符，a-z、0-9" maxlength="16"
                		onkeyup="value=value.replace(/[\W]/g,'')" onblur="value=value.replace(/[\W]/g,'')" autocomplete="off" />
            	</p>
            	<p class="entry_tips">
                	<label class="sign">选择角色<span>*</span>：</label>
                	<select class="choice" name="addRole" id="addRole">
                    	<option value="0">请选择管理员角色</option>
                    	<c:forEach items="${backRoleList}" var="roleList">
                    		<option value="${roleList.roleId}">${roleList.roleName}</option>
                    	</c:forEach>
                	</select>
            	</p>
            	<p class="entry_tips">
                	<label class="sign">登录密码<span>*</span>：</label>
                	<input type="password" class="box" name="addPassword" id="addPasswordOne" placeholder="请输入管理员登录密码，6-16个字符，a-z、0-9" maxlength="16"
                		onkeyup="value=value.replace(/[\W]/g,'')" onblur="value=value.replace(/[\W]/g,'')" autocomplete="off"/>
            	</p>
            	<p class="entry_tips">
                	<label class="sign">重复密码<span>*</span>：</label>
                	<input type="password" class="box" id="addPasswordTwo" placeholder="请输入管理员登录账号，6-16个字符，a-z、0-9" maxlength="16"
                	onkeyup="value=value.replace(/[\W]/g,'')" onblur="value=value.replace(/[\W]/g,'')" autocomplete="off"/>
            	</p>

            	<div class="Button">
                	<input type="text" class="cancel" value="取&nbsp;&nbsp;消" onfocus="this.blur()" onclick="cancleHandle()" />
                	<input type="text" class="yes" value="确&nbsp;&nbsp;定" onfocus="this.blur()"  onclick="addBackerHandle()" />
            	</div>
            </form>
            
        </div>
		<form id="modifyForm" action="<%=path %>/backerWeb/backerManage/roleModify.htm" method="post">
    		<input type="hidden" name="modify_backerId" id="modify_backerId">
    		<input type="hidden" name="backerAccount" value="${backerAccount}">
    		<input type="hidden" id="pageNumber" value="${pageNumber}" name="pageNumber">
    		
    		<div class="change_pop">
            	<span class="rt"><img src="<%=path %>/resource/image/back/location_img.png">修改角色</span>
            	<p class="entry_tips">
                	<label class="sign">选择角色<span>*</span>：</label>
                 	<select class="choice" id="modify_selectedRoleId" name="modify_roleId">
                    	<c:forEach items="${backRoleList}" var="roleList">
                    		<option value="${roleList.roleId}">${roleList.roleName}</option>
                    	</c:forEach>
                	</select>
            	</p>

            	<div class="Button">
                	<input type="text" class="cancel" value="取&nbsp;&nbsp;消" onfocus="this.blur()" onclick="cancleHandle()" />
                	<input type="text" class="yes" value="确&nbsp;&nbsp;定" onfocus="this.blur()" onclick="roleModifyHandle()"  />
            	</div>
        	</div>
		</form>
        
        <div class="changePassword_pop">
            <span class="rt"><img src="<%=path %>/resource/image/back/location_img.png">修改密码</span>
            <form id="modifyPassword" action="<%=path %>/backerWeb/backerManage/modifyPassword.htm" method="post">
                <input type="hidden" name="pageNumber" value="${pageNumber}">
                <input type="hidden" name="backerAccount" value="${backer_backerAccount}">
                <input type="hidden" name="modifyPassword_backerId" id="modifyPassword_backerId">
                <p class="entry_tips">
                	<label class="sign">原密码<span>*</span>：</label>
                	<input type="password" id="oldPassword_input" name="oldPassword_input" class="box" placeholder="请输入原登录密码，6-16个字符，a-z、0-9" maxlength="16"
                	    onkeyup="value=value.replace(/[\W]/g,'')" onblur="value=value.replace(/[\W]/g,'')" autocomplete="off"/>
            	</p>
            	<p class="entry_tips">
                    <label class="sign">新密码<span>*</span>：</label>
                	<input type="password" id="passwordOne" name="password" class="box" placeholder="请输入管理员登录密码，6-16个字符，a-z、0-9" maxlength="16"
                	    onkeyup="value=value.replace(/[\W]/g,'')" onblur="value=value.replace(/[\W]/g,'')" autocomplete="off"/>
                </p>
                <p class="entry_tips">
                	<label class="sign">重复密码<span>*</span>：</label>
                	<input type="password" id="passwordTwo" class="box" placeholder="请再次输入管理员登录密码" maxlength="16"
                	    onkeyup="value=value.replace(/[\W]/g,'')" onblur="value=value.replace(/[\W]/g,'')" autocomplete="off"/>
                </p>
            </form>
            <div class="Button">
                <input type="text" class="cancel" value="取&nbsp;&nbsp;消" onfocus="this.blur()" onclick="cancleHandle()" />
                <input type="text" class="yes" value="确&nbsp;&nbsp;定" onfocus="this.blur()" onclick="modifyPasswordHandle()" />
            </div>
        </div>

		<form id="resetForm" action="<%=path %>/backerWeb/backerManage/resetPassword.htm" method="post">
    		<input type="hidden" name="reset_backerId" id="reset_backerId">
    		<input type="hidden" name="queryAccount" value="${queryAccount}">
    		<input type="hidden" id="pageNumber" value="${pageNumber}" name="pageNumber">
    		
    		<div class="reset_pop">
            	<span class="rt"><img src="<%=path %>/resource/image/back/location_img.png">重置密码</span>
            	<p class="mask_tips">确定重置该管理员的登录密码？重置后的登录密码是：123456</p>

            	<div class="Button">
                	<input type="text" class="cancel" value="取&nbsp;&nbsp;消" onfocus="this.blur()" onclick="cancleHandle()" />
                	<input type="text" class="yes" value="确&nbsp;&nbsp;定" onfocus="this.blur()" onclick="resetPasswordHandle()"  />
            	</div>
        	</div>
		</form>
        
		<form id="deleteForm" action="<%=path %>/backerWeb/backerManage/deleteBacker.htm" method="post">
    		<input type="hidden" name="queryAccount" value="${queryAccount}">
    		<input type="hidden" name="deleteBackerId" id="delete_backerId">
    		<input type="hidden" id="pageNumber" value="${pageNumber}" name="pageNumber">
    		
    		<div class="delete_pop">
            	<span class="rt"><img src="<%=path %>/resource/image/back/location_img.png">删除操作</span>
            	<p class="mask_tips">确定删除该管理员？</p>
            	<div class="Button">
                	<input type="text" class="cancel" value="取&nbsp;&nbsp;消" onfocus="this.blur()" onclick="cancleHandle()" />
                	<input type="text" class="yes" value="确&nbsp;&nbsp;定" onfocus="this.blur()" onclick="deleteBackerHandle()" />
            	</div>
        	</div>
		</form>
    </div>
</div>

<script type="text/javascript" src="<%=path %>/resource/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/loadBackPage.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/simpleTips.js"></script>
<script type="text/javascript">
    var popObj;
    $(function(){
        $(".add").click(function(){
            $(".mask").css("display" , "block");
            $(".add_pop").css("display" , "block");
            popObj = ".add_pop";
        });
        $(".change").click(function(){
            $(".mask").css("display" , "block");
            $(".change_pop").css("display" , "block");
            popObj = ".change_pop";
        });
        $(".changePassword").click(function(){
            $(".mask").css("display" , "block");
            $(".changePassword_pop").css("display" , "block");
            popObj = ".changePassword_pop";
        });
        $(".reset").click(function(){
            $(".mask").css("display" , "block");
            $(".reset_pop").css("display" , "block");
            popObj = ".reset_pop";
        });
        $(".delete").click(function(){
            $(".mask").css("display" , "block");
            $(".delete_pop").css("display" , "block");
            popObj = ".delete_pop";
        });
        $(".cancel").click(function(){
            $(".mask").hide();
            $(popObj).hide();
        });
        $(".yes").click(function(){
            //$(".mask").hide();
            //$(popObj).hide();
        });
    });
</script>


<script type="text/javascript">
	window.onload = function(){
	var code = '${code}';
	var message = '${message}';
		if(code != 1 && message != ''){
		    openTips(message);
		    return false;
		}
	}
	function cancleHandle(){
	    document.getElementById('addBackerAccount').value = "";
	    document.getElementById('addPasswordOne').value = "";
	    document.getElementById('addPasswordTwo').value = "";
	    var select = document.getElementById('addRole');
	    select[0].selected = true;
	    
	    document.getElementById('oldPassword_input').value = "";
	    document.getElementById('passwordOne').value = "";
	    document.getElementById('passwordTwo').value = "";
	}
	function queryHandle(){
	    //点击查询时传给控制器的页面值应人为设置为0，不直接传0是因为还有上一页下一页
		document.getElementById('pageNumber').value = "0";
		$('#queryForm').submit(); 
	}
	//添加管理员
	function addBackerHandle(){
	    var backerAccount = document.getElementById('addBackerAccount').value;
	    var passwordOne = document.getElementById('addPasswordOne').value;
	    var passwordTwo = document.getElementById('addPasswordTwo').value;
	    var role = document.getElementById('addRole').value;
	    if(backerAccount == null || backerAccount == "" || backerAccount.length < 6 ){
	        openTips("帐号非法，请输入6-16个字符，a-z、0-9");
	        return false;
	    }
	    if(passwordOne == null || passwordOne == "" || passwordOne.length < 6 ){
	        openTips("密码非法，请输入6-16个字符，a-z、0-9")
	        return false;
	    }
	    if(passwordOne != passwordTwo){
	        openTips("两次密码输入不一致，请检查");
	        return false;
	    }
	    if(role == null || role == "" || role == 0){
	        openTips("请选择管理员角色");
	        return false;
	    }
	    
	    $("#addForm").submit();
	}
	//修改角色
	function roleModify(oldRoleId, backerId){
	    if(oldRoleId == null || oldRoleId == "" || backerId == null || backerId == ""){
	        openTips("该管理员不存在");
	        return;
	    }
	     
        //$("#modify_selectedRoleId").get(0).options[oldRoleId].selected = true;
		document.getElementById('modify_backerId').value = backerId;
    	var temp = $("#modify_selectedRoleId").val(oldRoleId);
    	temp[0].selected = true;
    	/* var modifySelectedRole = document.getElementById('modify_selectedRoleId');
    	modifySelectedRole[oldRoleId].selected = true; */
	    
	}
	function roleModifyHandle(){
	    var newRoleId = document.getElementById('modify_selectedRoleId').value;
	    var backerId = document.getElementById('modify_backerId').value;
	    
	    if(backerId == null || backerId == ""){
	        openTips("参数错误");
	        return false;
	    }
	    if(newRoleId == null || newRoleId == ""){
	    	openTips("请选择管理员角色");
	    	return false;
	    }
	    
        $('#modifyForm').submit(); 
	}
	//重置密码
	function resetPassword(backerId){
	    if(backerId == null || backerId == ""){
	        openTips("该管理员不存在");
	    }else{
	        document.getElementById('reset_backerId').value = backerId;
	    }
	}
	function resetPasswordHandle(){
	    var backerId = document.getElementById('reset_backerId').value;
	    if(backerId == null || backerId == ""){
	        openTips("参数错误");
	    }else{
	        $("#resetForm").submit();
	    }
	}
	//修改密码
	function modifyPassword(backerId){
	    if(backerId == null || backerId == ""){
	        openTips("参数错误")
	    }else{
	        document.getElementById('modifyPassword_backerId').value = backerId;
	    }
	}
	function modifyPasswordHandle(){
		var oldPassword_input = document.getElementById('oldPassword_input').value;
	    var passwordOne = document.getElementById('passwordOne').value;
	    var passwordTwo = document.getElementById('passwordTwo').value;
	    var backerId = document.getElementById('modifyPassword_backerId').value;
	    if(backerId == null || backerId == ""){
	        openTips("参数错误");
	        return false;
	    }
	    if(oldPassword_input == null || oldPassword_input == "" || oldPassword_input.length < 6){
	        openTips("原密码错误");
	        return false;
	    }
	    if(passwordOne == null || passwordOne.length < 6 || passwordOne == ""){
	        openTips("新密码填写不正确，请填写6~16字母与数组组合");
	    }else if(passwordOne != passwordTwo){
	        openTips("两次密码填写不一致");
	    }else{
	        $('#modifyPassword').submit();
	    }
	}
	//删除
	function deleteBacker(backerId){
	    if(backerId == null || backerId == ""){
	        openTips("参数错误");
	        return;
	    }
	        
        document.getElementById('delete_backerId').value = backerId;
	}
	function deleteBackerHandle(){
	    var backerId = document.getElementById('delete_backerId').value;
	    if(backerId == null || backerId == ""){
	        openTips("参数错误");
	        return false;
	    }
	    
	    $("#deleteForm").submit();
	}
	//上一页
	function prePage(){
	    var pageNumber = ${pageNumber};
	    if(pageNumber > 0){
	        pageNumber = pageNumber - 1;
	        
	        document.getElementById("pageNumber").value = pageNumber;
	        $('#queryForm').submit();
	    }else{
	    	openTips("当前已是第一页");
	    }
	}
	//下一页
	function nextPage(){
		var pageNumber = ${pageNumber};
		var totalPageNumber = ${totalPageNumber};
		if(pageNumber < totalPageNumber){
		    pageNumber++;
		    document.getElementById("pageNumber").value = pageNumber;
		    $('#queryForm').submit();
		}else{
			openTips("当前已是最后一页");
		}
	}
	//跳转到指定页
	function goPage(){
	    var goPageNumber = document.getElementById('goPageNumber').value;
	    var totalPageNumber = ${totalPageNumber};
	    var pageNumber = ${pageNumber};
	    
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
	    document.getElementById('pageNumber').value = pageNumber;
	    $('#queryForm').submit();
	}
</script>
</body>
</html>