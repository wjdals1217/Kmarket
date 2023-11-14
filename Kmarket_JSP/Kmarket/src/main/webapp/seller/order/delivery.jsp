<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%@ include file="../_aside.jsp"%>
    <section id="seller-order-delivery">
        <nav>
            <h3>취소/반품/교환</h3>
            <p>
                HOME > 주문관리 > <strong>취소/반품/교환</strong>
            </p>
        </nav>
        <!-- 취소/반품/교환 컨텐츠 시작 --> 
        <!-- ordNo, prodNo, ordUid, ordDate, ordStatus, ordComplete(수정불가) -->                               
        <!-- -->                               
        <section>
            <div>
                <form id="formSearch" action="/Kmarket/seller/order/delivery.do" method="get">
			      <input type="hidden" name="seller" value="${sessUser.company}"/>
			      <input type="hidden" name="sort" value="0"/>
			       <select name="search">
			              <option value="search1">상품명</option>
			              <option value="search2">상품코드</option>                               
			       </select>
			       <input type="text" name="search_text">
			       <button id="btnSearch">검색</button>
   				</form>
            </div>
            <p class="sort">
                <a href="/Kmarket/seller/order/delivery.do?seller=${sessUser.company}&sort=0" class="${sort eq '0'?'on':''}">전체&nbsp;|</a>
                <a href="/Kmarket/seller/order/delivery.do?seller=${sessUser.company}&sort=3" class="${sort eq '3'?'on':''}">취소&nbsp;|</a>
                <a href="/Kmarket/seller/order/delivery.do?seller=${sessUser.company}&sort=4" class="${sort eq '4'?'on':''}">반품&nbsp;|</a>
                <a href="/Kmarket/seller/order/delivery.do?seller=${sessUser.company}&sort=5" class="${sort eq '5'?'on':''}">교환&nbsp;</a>
            </p>
            <c:choose>
            	<c:when test="${not empty orders}">
            		<table>
		                <tr>
		                    <th>주문번호</th>
		                    <th>상품번호</th>
		                    <th>주문자</th>
		                    <th>주문일자</th>
		                    <th>주문상태</th>
		                    <th>배송상태</th>
		                    <th>입금상태</th>
		                </tr>
		                <c:forEach var="order" items="${orders}">
		                <tr>
		                    <td>${order.ordNo}</td>
		                    <td>${order.orderItemDTO.prodNo}</td>
		                    <td>${order.ordUid}</td>
		                    <td>${order.ordDate}</td>
		                    <td>
		                    <select name="ordStatus">
		                      <option value="success">구매확정</option>
		                      <option value="cancel">취소</option>
		                      <option value="return">반품</option>
		                      <option value="exchange">교환</option>
		                    </select>
		                    </td>
		                    <td>
		                        <c:choose>
				                  	<c:when test="${order.deliveryStatus ==  null || order.deliveryStatus eq 'yet'}">
				                  		<span>배송전</span>
				                  	</c:when>
				                  	<c:when test="${order.deliveryStatus eq 'ing'}">
				                  		<span>배송중</span>
				                  	</c:when>
				                  	<c:when test="${order.deliveryStatus eq 'success'}">
				                  		<span>배송완료</span>
				                  	</c:when>
				                  </c:choose>
		                    </td>
		                    <c:choose>
		                    	<c:when test="${order.ordComplete eq '2'}">
		                    		<td>입금완료</td>
		                    	</c:when>
		                    	<c:otherwise>
		                    		<td>입금대기</td>
		                    	</c:otherwise>
		                    </c:choose>
		                </tr>
		                </c:forEach>
		            </table>
		
		            <div class="paging">
		           	<c:if test="${pageGroupStart > 1}">
		           		<span class="prev">
		           			<a href="/Kmarket/seller/order/delivery.do?pg=${pageGroupStart - 1}&search=${search}&search_text=${search_text}&seller=${sessUser.company}&sort=${sort}">
		           			<&nbsp;이전
		           			</a>
		            	</span>
		           	</c:if>
		               <span class="num">
		            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
		            	<a href="/Kmarket/seller/order/delivery.do?pg=${i}&search=${search}&search_text=${search_text}&seller=${sessUser.company}&sort=${sort}" class="${currentPage == i? 'on':'off'}">${i}</a>
		            </c:forEach>
		               </span>
		            <c:if test="${pageGroupEnd < lastPageNum}">
			            <span class="next">
			                   <a href="/Kmarket/seller/order/delivery.do?pg=${pageGroupEnd + 1}&search=${search}&search_text=${search_text}&seller=${sessUser.company}&sort=${sort}">
			                   다음&nbsp;>
			                   </a>
			            </span>
		            </c:if>
		               
		           </div>	
            	</c:when>
            	<c:when test="${empty orders }">
	         	  <table>
	            	<tr>
	              		<td> 주문이 존재하지 않습니다.  </td>
	            	</tr>
	          	  </table>
	           </c:when>
            </c:choose>
            

        </section>                

        
        <p class="ico info">
            <strong>Tip!</strong>
            배송관리 페이지 입니다. 배송상태 항목 클릭 시 수정이 가능합니다.
        </p>

        

        <!-- 배송관리 컨텐츠 끝 -->
    </section>
</main>
<%@ include file="../_footer.jsp" %>
