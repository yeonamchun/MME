<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${!empty uDTO}">
 	<table class="userinfo">
 		<tr>
			<td><a href="">알람</a></td>
			<td><a href="MemberInfoUi">마이페이지</a></td>
			<td><a href="">견적요청</a></td>
			<td><a href="">커뮤니티</a></td>
 		</tr>
 	</table>
</c:if>  

<c:if test="${empty uDTO}">
 	<table class="userinfo">
 		<tr>
 			<td><a href="">커뮤니티</a></td>
 		</tr>
 	</table>
</c:if>  
