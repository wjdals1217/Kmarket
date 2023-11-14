<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%@ include file="../_aside.jsp"%>
<script>
$(function(){
	$('input[name=all]').change(function(){
		const isChecked = $(this).is(':checked');
		
		if(isChecked){
			// 전체선택
			$('input[name=chk]').prop('checked', true);
		}else{
			// 전체해제
			$('input[name=chk]').prop('checked', false);
		}
	});
	$('input[name=chk]').change(function(){
		const isChecked = $(this).is(':checked');
		
		if(!isChecked){
			// 전체선택
			$('input[name=all]').prop('checked', false);
		}
	});
	
	$('.btnDelete').click(function(e){
		e.preventDefault();
		const isChecked = $('input[name=chk]').is(':checked');
		if(isChecked){
			if(confirm('정말 삭제하시겠습니까?')) {
			$('#formCheck').submit();
			}	
		}
	});
});
	$('#btnSearch').click(function(){
		$('#formSearch').submit();
	});
</script>
   <section id="seller-product-list">
       <nav>
           <h3>상품현황</h3>
           <p>
               HOME > 상품관리 > <strong>상품현황</strong>
           </p>
       </nav>
       <!-- 상품현황 컨텐츠 시작 -->                                
       <section>
           <div>
           <form id="formSearch" action="/Kmarket/seller/product/list.do" method="get">
           		<input type="hidden" name="seller" value="${sessUser.company}"/>
	           <select name="search">
	                  <option value="search1">상품명</option>
	                  <option value="search2">상품코드</option>                               
	                  <option value="search3">담당자명</option>                               
	           </select>
	           <input type="text" name="search_text">
	           <button id="btnSearch">검색</button>
           </form>
           
           </div>
           <c:choose>
           	<c:when test="${not empty products}">
           		<form id="formCheck" action="/Kmarket/seller/product/delete.do" method="post">
		           <table>
		               <tr>
		                   <th><input type="checkbox" name="all"/></th>
		                   <th>이미지</th>
		                   <th>상품코드</th>
		                   <th>상품명</th>
		                   <th>판매가격</th>
		                   <th>할인율</th>
		                   <th>포인트</th>
		                   <th>재고</th>
		                   <th>판매자</th>
		                   <th>조회</th>
		                   <th>관리</th>
		               </tr>
		               <c:forEach var="product" items="${products}">
		               <tr>
		               	<td><input type="checkbox" name="chk" value="${product.prodNo}"/></td>
		                <td><img src="/Kmarket/thumb/${product.prodCate1}/${product.prodCate2}/${product.newThumb1}" class="thumb"></td>
		                <td>${product.prodNo}</td>
		                <td><a href="/Kmarket/product/view.do?prodNo=${product.prodNo}">${product.prodName}</a></td>
		                <td>${product.getPriceComma()}</td>
		                <td>${product.discount}</td>
		                <td>${product.point}</td>
		                <td>${product.stock}</td>
		                <td>${product.seller}</td>
		                <td>${product.hit}</td>
		                <td>
		                    <a href="/Kmarket/seller/product/delete.do?prodNo=${product.prodNo}">[삭제]</a>
		                    <a href="/Kmarket/seller/product/modify.do?prodNo=${product.prodNo}&cate1=${product.prodCate1}&cate2=${product.prodCate2}">[수정]</a>
		                </td>
		               </tr>
					</c:forEach>
		           </table>
		           </form>
		
		           <input class="btnDelete" type="button" value="선택삭제" />                          
		
		           <div class="paging">
		           	<c:if test="${pageGroupStart > 1}">
		           		<span class="prev">
		           			<a href="/Kmarket/seller/product/list.do?pg=${pageGroupStart - 1}&search=${search}&search_text=${search_text}&seller=${sessUser.company}">
		           			<&nbsp;이전
		           			</a>
		            	</span>
		           	</c:if>
		               <span class="num">
		            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
		            	<a href="/Kmarket/seller/product/list.do?pg=${i}&search=${search}&search_text=${search_text}&seller=${sessUser.company}" class="${currentPage == i? 'on':'off'}">${i}</a>
		            </c:forEach>
		               </span>
		            <c:if test="${pageGroupEnd < lastPageNum}">
			            <span class="next">
			                   <a href="/Kmarket/seller/product/list.do?pg=${pageGroupEnd + 1}&search=${search}&search_text=${search_text}&seller=${sessUser.company}">
			                   다음&nbsp;>
			                   </a>
			            </span>
		            </c:if>
		               
		           </div>
           	</c:when>
           	<c:when test="${empty products }">
         	  <table>
            	<tr>
              		<td> 상품이 존재하지 않습니다. </td>
            	</tr>
          	  </table>
           </c:when>
           </c:choose>
           

       </section>                

       <p class="ico info">
           <strong>Tip!</strong>
           전자상거래 등에서의 상품 등의 정보제공에 관한 고시에 따라 총 35개 상품군에 대해 상품 특성 등을 양식에 따라 입력할 수 있습니다.
       </p>

       

       <!-- 상품현황 컨텐츠 끝 -->
    </section>
</main>
<%@ include file="../_footer.jsp" %>
