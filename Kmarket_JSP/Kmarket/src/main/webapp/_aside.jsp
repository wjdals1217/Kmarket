<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"></c:set>
	<!-- 
		날짜 : 2023/09/14
		이름 : 김무현
		내용 : include 작업
	 -->
 <!-- 카테고리/베스트 상품 영역 -->
        <aside>
          <!-- 카테고리 -->
          <ul class="category">     
          	<li><i class="fa fa-bars" aria-hidden="true"></i>카테고리</li>
          		<c:forEach var="cate2s" items="${categories}">
                 <li>
                        <a href="${ctxPath}/product/list.do?prodCate1=${cate2s.get(0).cate1}">
                        <c:if test="${cate2s.get(0).cate1 == 10}"><i class="fas fa-tshirt"></i></c:if>                        
                        <c:if test="${cate2s.get(0).cate1 == 11}"><i class="fas fa-socks"></i></c:if>                        
                        <c:if test="${cate2s.get(0).cate1 == 12}"><i class="fas fa-child"></i></c:if>                        
                        <c:if test="${cate2s.get(0).cate1 == 13}"><i class="fas fa-utensils"></i></c:if>                        
                        <c:if test="${cate2s.get(0).cate1 == 14}"><i class="fas fa-home"></i></c:if>                        
                        <c:if test="${cate2s.get(0).cate1 == 15}"><i class="fas fa-laptop"></i></c:if>     	                   
                        <c:if test="${cate2s.get(0).cate1 == 16}"><i class="fas fa-medal"></i></c:if>                        
                        <c:if test="${cate2s.get(0).cate1 == 17}"><i class="fas fa-car"></i></c:if>                        
                        <c:if test="${cate2s.get(0).cate1 == 18}"><i class="fas fa-book"></i></c:if>                        
                        	${cate2s.get(0).c1Name}
                        </a>
                        <ol>
                        	<c:forEach var="cate2" items="${cate2s}">
                            	<li><a href="${ctxPath}/product/list.do?prodCate1=${cate2s.get(0).cate1}&prodCate2=${cate2.cate2}">${cate2.c2Name}</a></li>
                            </c:forEach>
                        </ol>
        		</li>
               </c:forEach>
		</ul>

    <!-- 베스트상품 배너 -->
    <article class="best">
      <h1><i class="fas fa-crown"></i>베스트상품</h1>
      <ol>
      <c:forEach var="productsaside" items="${productsaside}" varStatus="loop">
        <li>
          <a href="${ctxPath}/product/view.do?prodCate1=${productsaside.prodCate1}&prodCate2=${productsaside.prodCate2}&prodNo=${productsaside.prodNo}" >
            <div class="thumb">
              <i>${loop.index + 1}</i>
              <img src="${ctxPath}/thumb/${productsaside.prodCate1}/${productsaside.prodCate2}/${productsaside.newThumb1}" alt="상품 이미지">
            </div>
            <h2>${productsaside.prodName}</h2>
            <div class="org_price">
              <del>${productsaside.priceWithComma}</del>
              <span>${productsaside.discount}%</span>
            </div>
            <div class="dis_price">
           <ins>
            <c:set var="discountedPrice" value="0" />
				<c:choose>
				    <c:when test="${productsaside.discount != 0}">
				        <c:set var="discountedPrice" value="${productsaside.price - (productsaside.price * (productsaside.discount / 100))}"/>
				    </c:when>
				    <c:otherwise>
				        <c:set var="discountedPrice" value="${productsaside.price}" />
				    </c:otherwise>
				</c:choose>
				<fmt:formatNumber value="${discountedPrice}"/>
			</ins>
            </div>
          </a>
        </li>
        </c:forEach>
      </ol>
    </article>
  </aside>
  
  