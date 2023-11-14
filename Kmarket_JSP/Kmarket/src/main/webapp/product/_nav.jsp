<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 제목, 페이지 네비게이션 -->
      <nav>
        <h1>상품목록</h1>
        <p>HOME >
	        <c:forEach var="cate2s" items="${categories}"> 
			    <c:if test="${prodCate1 == cate2s.get(0).cate1}">
			        <span>${cate2s.get(0).c1Name}></span>
			    </c:if>
			    <c:forEach var="cate2" items="${cate2s}"> 
			        <c:if test="${prodCate1 == cate2s.get(0).cate1 && prodCate2 == cate2.cate2}">
			            <strong>${cate2.c2Name}</strong>
			        </c:if>
			    </c:forEach>
			</c:forEach>
        </p>
      </nav>

      