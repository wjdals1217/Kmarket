<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
	<!-- 
		날짜 : 2023/09/14
		이름 : 김무현
		내용 : include 작업
	 -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kmarket</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <script src="https://kit.fontawesome.com/20962f3e4b.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${ctxPath}/product/css/product.css">
    <link rel="stylesheet" href="${ctxPath}/css/common.css">
    <script src="${ctxPath}/js/index.js"></script>
</head>
<body>
    <div id="wrapper">
        <header>
            <div class="top">
                <div>
                   <c:choose>
	            <c:when test="${empty sessUser}">
	            	<a href="${ctxPath}/member/login.do">로그인</a>
		            <a href="${ctxPath}/member/join.do">회원가입</a>
	            </c:when>
	            <c:otherwise>
		            <c:choose>
		            	<c:when test="${sessUser.type eq 2}">
		            		<span>${sessUser.manager}님 환영합니다.</span>
		            		<a href="#">마이페이지</a>
		            		<a href="${ctxPath}/seller/index.do">판매자</a>
		            	</c:when>
		            	<c:when test="${sessUser.type eq 3}">
		            		<span>${sessUser.uid}님 환영합니다.</span>
		            		<a href="#">마이페이지</a>
		            		<a href="${ctxPath}/admin/index.do">관리자</a>
		            	</c:when>
		            	<c:otherwise>
		            		<span>${sessUser.name}님 환영합니다.</span>
		            		<a href="#">마이페이지</a>
		            		<a href="#">
		            		<i class="fa fa-shopping-cart" aria-hidden="true">
		            		</i>&nbsp;장바구니</a>
		            	</c:otherwise>
	            	</c:choose>
	            	<a href="${ctxPath}/member/logout.do">로그아웃</a>
	            </c:otherwise>
            </c:choose>
                </div>
            </div>
            <div class="logo">
                <div>
                    <a href="/Kmarket/index.do"><img src="../img/header_logo.png" alt="로고"/></a>
                    <form action="#">
                        <input type="text" name="keyword"/>
                        <button><i class="fa fa-search"></i></button>
                    </form>                
                </div>
                
            </div>
            <div class="menu">
                <div>
                    <ul>
                        <li><a href="${ctxPath}">히트상품</a></li>
			            <li><a href="${ctxPath}">추천상품</a></li>
			            <li><a href="${ctxPath}">최신상품</a></li>
			            <li><a href="${ctxPath}">할인상품</a></li>
                    </ul>
                    <ul>
                        <li><a href="#">쿠폰존</a></li>
                        <li><a href="#">사용후기</a></li>
                        <li><a href="#">개인결제</a></li>
                        <li><a href="${ctxPath}/cs/index.do">고객센터</a></li>
                        <li><a href="${ctxPath}/cs/faq.do">FAQ</a></li>
                    </ul>
                </div>
            </div>
        </header>