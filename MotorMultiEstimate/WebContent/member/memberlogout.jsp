<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="js/YeoUtil.js"></script>
<script type="text/javascript">
	var util = new YeoUtil();
	
	$(document).ready(function()
	{
		$("#userLogOut").on("click", function(){
			util.getHttp("post","MemberUtil", "opt=200", "text", function (data){
			
				if(data == "true")
				{
					alert("로그 아웃");
					location.href = "MainPage";
				}
				else
				{
					alert("로그 아웃 실패");
					location.href = "MainPage";
				}
			});
		});
	});

</script>

</head>
<body>
<table>
	<tr>
		<td align="right" width="100">접속 시간&nbsp;:&nbsp;</td>
		<td>${sessionStime}</td>
	</tr>
	<tr>
		<td align="right" width="100">현재 시간&nbsp;:&nbsp;</td>
		<td>${sessionEtime}</td>
	</tr>
	<tr>
		<td colspan="2"><button id="userLogOut">로그 아웃</button></td>
	</tr>
</table>
</body>
</html>