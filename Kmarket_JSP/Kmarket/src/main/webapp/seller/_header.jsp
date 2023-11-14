<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>케이마켓::판매자</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://kit.fontawesome.com/20962f3e4b.js" crossorigin="anonymous"></script>    
    <script src="/Kmarket/seller/js/gnb.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/Kmarket/seller/css/seller.css">
    <script>
    	const success = ${success}
    	if(success == 200){
    		alert('상품등록이 완료되었습니다.');
    	}
    	
    </script>
</head>
<body>
    <div id="seller-wrapper">
        <header>
            <div>
                <a href="/Kmarket/seller" class="logo"><img src="/Kmarket/seller/img/seller.png" alt="admin_logo"/></a>
                <p>
                    <span>${sessUser.manager}님 반갑습니다.</span>
                    <a href="/Kmarket">HOME |</a>
                    <a href="/Kmarket/member/logout.do">로그아웃 |</a>
                    <a href="/Kmarket/cs">고객센터</a>
                </p>
            </div>
        </header>
	<main>
