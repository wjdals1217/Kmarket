<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%@ include file="../_aside.jsp" %>
            <section id="admin-store-userSeller">
                <nav>
                    <h3>판매자</h3>
                    <p>
                        HOME > 상점관리 > <strong>판매자관리</strong>
                    </p>
                </nav>
                <!-- 상품목록 컨텐츠 시작 -->                                
                <section>
                    <div>
                        <form id="formSearch" action="/Kmarket/admin/store/userSeller.do?" method="get">
				      		<input type="hidden" name="type" value="2"/>
					       	<select name="search">
					              <option value="search1">아이디</option>
					              <option value="search2">담당자명</option>                               
					              <option value="search3">사업장</option>                               
					       	</select>
					       	<input type="text" name="search_text">
					       	<button id="btnSearch">검색</button>
		   				</form>
                    </div>
                    <table>
                        <tr>
                            <th>아이디</th>
                            <th>회사명</th>
                            <th>사업자등록번호</th>
                            <th>대표</th>
                            <th>담당자</th>
                            <th>휴대폰번호</th>
                            <th>이메일</th>
                            <th>팩스번호</th>
                            <th>관리</th>
                        </tr>
						<c:forEach var="seller" items="${sellers}">
							<tr>
	                            <td>${seller.uid}</td>
	                            <td>${seller.company}</td>
	                            <td>${seller.bizRegNum}</td>
	                            <td>${seller.ceo}</td>
	                            <td>${seller.manager}</td>
	                            <td>${seller.managerHp}</td>
	                            <td>${seller.email}</td>
	                            <td>${seller.fax}</td>
	                            <td>
	                                <a href="#">[활동중지]</a>
	                            </td>
	                        </tr>
						</c:forEach>
                        </table>
                    <div class="paging">
		           	<c:if test="${pageGroupStart > 1}">
		           		<span class="prev">
		           			<a href="/Kmarket/admin/store/userSeller.do?pg=${pageGroupStart - 1}&search=${search}&search_text=${search_text}&type=2">
		           			<&nbsp;이전
		           			</a>
		            	</span>
		           	</c:if>
		               <span class="num">
		            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
		            	<a href="/Kmarket/admin/store/userSeller.do?pg=${i}&search=${search}&search_text=${search_text}&type=2" class="${currentPage == i? 'on':'off'}">${i}</a>
		            </c:forEach>
		               </span>
		            <c:if test="${pageGroupEnd < lastPageNum}">
			            <span class="next">
		                   <a href="/Kmarket/admin/store/userSeller.do?pg=${pageGroupEnd + 1}&search=${search}&search_text=${search_text}&type=2">
		                   다음&nbsp;>
		                   </a>
			            </span>
		            </c:if>
		           </div>

                </section>                

                
                <p class="ico info">
                    <strong>Tip!</strong>
                    판매자 수수료는 없습니다.
                </p>

                

                <!-- 상품목록 컨텐츠 끝 -->
            </section>
        </main>
<%@ include file="../_footer.jsp" %>
