<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>케이마켓 고객센터</title>
<link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico" />
<link rel="stylesheet" href="./css/style.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<div id="wrapper">
		<header>
			<div class="top">
				<div>
					<p>
						<a href="../member/login.do">로그인</a> <a href="#">회원가입</a> <a
							href="#">마이페이지</a> <a href="#"><i
							class="fa fa-shopping-cart" aria-hidden="true"></i>&nbsp;장바구니</a>
					</p>
				</div>
			</div>
			<div class="logo">
				<div>
					<a href="${ctxPath}/cs/index.do?group=qna&cateDetail=all&type=20"><img src="./images/logo.png" alt="로고" />고객센터</a>
				</div>
			</div>
		</header>