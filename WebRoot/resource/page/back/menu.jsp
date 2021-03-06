<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
<ul class="menu">
	
	<li class="levelOne" hidden="hidden" id="li_110000" >
        <p class="menuTitle">
            首页
            <img src="<%=path %>/resource/image/back/pullDown.png" class="pullDown" />
            <img src="<%=path %>/resource/image/back/up.png" class="pullUp" />
        </p>

        <ul class="levelTwo">
            <li class="menuInfo" hidden="hidden" id="li_111000" ><a id="a_111000" href="<%=path %>/backerWeb/systemAdsManage/show.htm">首页广告</a></li>
        </ul>
    </li>

    <li class="levelOne" hidden="hidden" id="li_120000" >
        <p class="menuTitle">
            用户管理
            <img src="<%=path %>/resource/image/back/pullDown.png" class="pullDown" />
            <img src="<%=path %>/resource/image/back/up.png" class="pullUp" />
        </p>

        <ul class="levelTwo">
            <li class="menuInfo" hidden="hidden" id="li_121000" ><a id="a_121000" href="<%=path %>/backerWeb/backerUserAccount/show.htm">用户账号</a></li>
        </ul>
    </li>

    <li class="levelOne" hidden="hidden" id="li_130000" >
        <p class="menuTitle">
            订单管理
            <img src="<%=path %>/resource/image/back/pullDown.png" class="pullDown" />
            <img src="<%=path %>/resource/image/back/up.png" class="pullUp" />
        </p>

        <ul class="levelTwo">
            <li class="menuInfo" hidden="hidden" id="li_131000" ><a id="a_131000" href="<%=path %>/backerWeb/backerOrderRecord/show.htm">订单记录</a></li>
        </ul>
    </li>

    <li class="levelOne" hidden="hidden" id="li_140000" >
        <p class="menuTitle">
            提现管理
            <img src="<%=path %>/resource/image/back/pullDown.png" class="pullDown" />
            <img src="<%=path %>/resource/image/back/up.png" class="pullUp" />
        </p>

        <ul class="levelTwo">
            <li class="menuInfo" hidden="hidden" id="li_141000" ><a id="a_141000" href="<%=path %>/backerWeb/backerTakeCashRecord/show.htm">提现记录</a></li>
        </ul>
    </li>

    <li class="levelOne" hidden="hidden" id="li_150000" >
        <p class="menuTitle">
            邀请码管理
            <img src="<%=path %>/resource/image/back/pullDown.png" class="pullDown" />
            <img src="<%=path %>/resource/image/back/up.png" class="pullUp" />
        </p>

        <ul class="levelTwo">
            <li class="menuInfo" hidden="hidden" id="li_151000" ><a id="a_151000" href="<%=path%>/backerWeb/backerInviteCode/show.htm">邀请码库</a></li>
        </ul>
    </li>

    <li class="levelOne" hidden="hidden" id="li_160000" >
        <p class="menuTitle">
            系统管理
            <img src="<%=path %>/resource/image/back/pullDown.png" class="pullDown" />
            <img src="<%=path %>/resource/image/back/up.png" class="pullUp" />
        </p>

        <ul class="levelTwo">
            <li class="menuInfo" hidden="hidden" id="li_161000" ><a id="a_161000" href="<%=path %>/backerWeb/appManage/show.htm">APP版本管理</a></li>
        </ul>
    </li>

    <li class="levelOne" hidden="hidden" id="li_170000" >
        <p class="menuTitle">
            管理员管理
            <img src="<%=path %>/resource/image/back/pullDown.png" class="pullDown" />
            <img src="<%=path %>/resource/image/back/up.png" class="pullUp" />
        </p>

        <ul class="levelTwo">
            <li class="menuInfo" hidden="hidden" id="li_171000" ><a id="a_171000" href="<%=path%>/backerWeb/backerRole/show.htm">角色管理</a></li>
            <li class="menuInfo" hidden="hidden" id="li_171100" ><a id="a_171100" href="<%=path%>/backerWeb/backerManage/show.htm">管理员账号</a></li>
        </ul>
    </li>

    <li class="levelOne" hidden="hidden" id="li_180000" >
        <p class="menuTitle">
            操作记录
            <img src="<%=path %>/resource/image/back/pullDown.png" class="pullDown" />
            <img src="<%=path %>/resource/image/back/up.png" class="pullUp" />
        </p>

        <ul class="levelTwo">
            <li class="menuInfo" hidden="hidden" id="li_181000" ><a id="a_181000" href="<%=path%>/backerWeb/backerManagerLoginRecord/show.htm">管理员登录记录</a></li>
            <li class="menuInfo" hidden="hidden" id="li_181100" ><a id="a_181100" href="<%=path%>/backerWeb/backerUserWalletAmountRecord/show.htm">用户钱包操作记录</a></li>
            <li class="menuInfo" hidden="hidden" id="li_181200" ><a id="a_181200" href="<%=path%>/backerWeb/backerInviteCodeRecord/show.htm">邀请码发放记录</a></li>
        </ul>
    </li>
	
</ul>
</body>

<script type="text/javascript">
    $(function(){
        $(".menuTitle").click(function(){
            if($(this).parent().find(".levelTwo").css("display")=="none"){
                $(this).parent().find(".levelTwo").show();
                $(this).find(".pullUp").show();
                $(this).find(".pullDown").hide();
            }else{
                $(this).parent().find(".pullDown").show();
                $(this).parent().find(".pullUp").hide();
                $(this).parent().find(".levelTwo").css("display","none");
            }
        });
    })
</script>

<script type="text/javascript">
	function showMenu()
	{
		//显示有权限的模块
		var rolePowerList = ${backer_rolePowerList};
		for(var i=0; i<rolePowerList.length; i++)
		{
			var rolePower = rolePowerList[i];
			//一级模块
			var onePower = parseInt(rolePower/10000)*10000;
			var oneLevel = document.getElementById("li_"+onePower);
			if(oneLevel != null && oneLevel != "")
			{
				oneLevel.style.display = "inline-block";
			}
			//二级功能
			var twoPower = parseInt(rolePower/100)*100;
			var twoLevel = document.getElementById("li_"+twoPower);
			if(twoLevel != null && twoLevel != "")
			{
				twoLevel.style.display = "inline-block";
			}
		}
		
		var pagePowerId = ${backer_pagePowerId};
		if(pagePowerId > 0)
		{
			//显示当前模块
			var menuPower = parseInt(pagePowerId/10000)*10000;
			var curMenuLi = document.getElementById("li_"+menuPower);
			if(curMenuLi != null && curMenuLi != "")
			{
				$("#li_"+menuPower).find(".levelTwo").show();
		        $("#li_"+menuPower).find(".pullUp").show();
		        $("#li_"+menuPower).find(".pullDown").hide();
			}
			
			//给当前模块添加选中标记
			var curMenuA = document.getElementById("a_" + pagePowerId);
			if(curMenuA != null && curMenuA != "")
			{
				curMenuA.setAttribute("class", "personal_pitch");
			}
		}
	}
	
	var t1 = setTimeout(showMenu(), 200);
	clearTimeout(t1);
</script>

</html>