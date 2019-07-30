<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 


 <c:if test="${!empty mesg}">
	 <script>alert("${mesg}");</script>
</c:if> 
 	
 <c:if test="${empty login}">
 	<table class="userinfo">
 		<tr>
 			<td><a href="MemberLoginUi">로그인</a></td>
 			<td><a href="MemberJoinUi">회원가입</a> </td>
 			<td><a href="BoardUi">게시판</a></td>
 			<td><span style="font-size:30px;">&nbsp;${title}</span></td>
 		</tr>
 	</table>
</c:if>  
    
 <c:if test="${!empty login}">
 
 	<table class="userinfo">
 		<tr>
 			<td>${login.username}님 어서 오세요</td>
 			<td><a href="">로그아웃</a> </td>
 			<td><a href="">마이페이지</a></td>
 			<td><span style="font-size:30px;">회원가입</span></td>
 		</tr>
 	</table>
 	
</c:if>  
 