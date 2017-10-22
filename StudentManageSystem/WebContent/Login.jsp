<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="stu.common.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/login_normalize.css" />
<link rel="stylesheet" type="text/css" href="css/login_demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="css/login_component.css" />
</head>
<!--生成随机数保存在session中，以便后台也可以拿到-->
<%  	 
	request.getSession().setAttribute("md5RandomKey", StringUtils.getRandomNum(10));//产生随机数，和密码一起生成MD5  
%>  
<%    
          if(session.getAttribute("name") == null) {      
%>    
          <script type="text/javascript" language="javascript">        
            alert("您还没有登录，请登录...");    
           </script>   
<%    
       }    
%>    
<body>
<div class="container demo-1">
	<div class="content">
		<div id="large-header" class="large-header">
			<canvas id="demo-canvas"></canvas>
			<div class="logo_box">
				<h3>欢迎你</h3>
				<form action="userLoginDo.userManage" method="post" onsubmit="verifyResult">
					<div class="input_outer">
						<span class="u_user"></span>
						<input name="logname" id="logname" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入账户">
					</div>
					<div class="input_outer">
						<span class="us_uer"></span>
						<input name="logpass" id="logpass" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="请输入密码">
					</div>
					<div class="mb2"><input class="act-but submit" type="submit" style="color: #FFFFFF" value="登录" onclick="encryptionPassword()"/></div>
				</form>
			</div>
		</div>
	</div>
</div><!-- /container -->
 <%
    Object message = request.getAttribute("message");
    if(message!=null && !"".equals(message)){

 %>
     <script type="text/javascript">
         alert("<%=message%>");
     </script>
 <%} %>
<script src="js/jquery.min.js"></script>
<script src="js/login_TweenLite.min.js"></script>
<script src="js/login_EasePack.min.js"></script>
<script src="js/login_rAF.js"></script>
<script src="js/login_demo-1.js"></script>
<script src="js/MD5.js"></script>

<script type="text/javascript">
function encryptionPassword(){   
	var hash = MD5(document.getElementById("logpass").value)+"${md5RandomKey}";  
    $("#logpass").val(hash);    
    alert(document.getElementById("logpass").value);
   }   
</script>

</body>
</html>