<%@page import="vo.MemberVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	*{
		margin : 0; padding : 0;
		text-decoration : none;
		box-sizing : border-box;
	}
	#menu {
		margin : 0 auto;
		padding : 0 20px;
		margin-bottom : 30px;
		height : 100px;
		background-color : #eee;
	}
	#menu a{
		display : inline-block;
		width : 100px;
		line-height : 100px
		text-align : center;
		font-weight : bold;
	}
	.container{
		width : 100%
		margin : 0 auto;
		text-align : center;
	}
	.container p{
		color : green;
		font-size : 30px;
		font-weight : bold;
		margin-bottom : 30px;
	}
	.container table{
		margin : 50px auto;
	}
	footer{
		position : absolute;
		left : 0; bottom : 0;
		width : 100%
		height : 50px;
		padding : 10px 0;
	}
	footer p{
		font-size : 15px;
		text-alogn : center;
	}
</style>
</head>
<body>
	<div id="menu">
		<a href="/BookProject/index.jsp">처음화면</a>
<%
	MemberVO vo = (MemberVO)session.getAttribute("loginOK");
	if(vo == null){
%>
		<a href="/BookProject/login/login.jsp">로그인화면</a>
<%
	}else{
%>
		<a href="/book/bookInsert.jsp">도서 등록</a>
		<a href="/BookProject/listBook">도서 목록</a>
		<a href="/BookProject/logout">로그아웃</a>
		<b>로그인 사용자 : <%= vo.getUserId() %></b>
<%
	}
%>
	</div>
	<div class = "container">
