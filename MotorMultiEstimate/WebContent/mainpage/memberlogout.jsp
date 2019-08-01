<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OrderCon</title>


<style>  
.mytable 
{ 
	border-collapse:collapse; 
	width:978px;
	height:100px;
}  
.mytable th, .mytable td { border:0px solid black; }

.userinfo
{
	border-collapse:collapse; 
}
.userinfo th, .userinfo td { border:0px solid black; }
</style>


</head>
<body>
<table class="mytable">
	<tr>
		<td style="width:678px;text-align:left;"><jsp:include page="../common/userinfo.jsp" flush="true" /></td>
		<td style="width:300px;text-align:right;"><jsp:include page="../common/userlink.jsp" flush="true" /></td>
	</tr>
</table>
<hr>
<jsp:include page="../member/memberlogout.jsp" flush="true" />
</body>
</html>