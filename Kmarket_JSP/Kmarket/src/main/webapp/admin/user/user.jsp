<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%@ include file="../_aside.jsp" %>
            <section id="admin-user-user">
                <nav>
                    <h3>회원정보관리</h3>
                    <p>
                        HOME > 회원관리 > <strong>회원정보관리</strong>
                    </p>
                </nav>
                <!-- 상품목록 컨텐츠 시작 -->                                
                <section>
                    <div>
                        <form id="formSearch" action="/Kmarket/admin/user/user.do?" method="get">
				      		<input type="hidden" name="type" value="1"/>
				      		<input type="hidden" name="level" value="0"/>
					       	<select name="search">
					              <option value="search1">아이디</option>
					              <option value="search2">이름</option>                               
					       	</select>
					       	<input type="text" name="search_text">
					       	<button id="btnSearch">검색</button>
		   				</form>
                    </div>
                    <p class="sort">
		                <a href="/Kmarket/admin/user/user.do?type=1&level=0" class="${level eq '0'?'on':''}">전체&nbsp;|</a>
		                <a href="/Kmarket/admin/user/user.do?type=1&level=1" class="${level eq '1'?'on':''}">일반&nbsp;|</a>
		                <a href="/Kmarket/admin/user/user.do?type=1&level=2" class="${level eq '2'?'on':''}">실버&nbsp;|</a>
		                <a href="/Kmarket/admin/user/user.do?type=1&level=3" class="${level eq '3'?'on':''}">골드&nbsp;</a>
		            </p>
                    <table>
                        <tr>
                            <th>아이디</th>
                            <th>이름</th>
                            <th>등급</th>
                            <th>포인트</th>
                            <th>휴대폰</th>
                            <th>이메일</th>
                            <th>우편번호</th>
                            <th>주소</th>
                            <th>관리</th>
                        </tr>
						<c:forEach var="user" items="${users}">
							<tr>
	                            <td>${user.uid}</td>
	                            <td>${user.name}</td>
	                            <td>${user.level}</td>
	                            <td>${user.point}</td>
	                            <td>${user.hp}</td>
	                            <td>${user.email}</td>
	                            <td>${user.zip}</td>
	                            <td>${user.addr1}</td>
	                            <td>
	                                <a href="#">[활동중지]</a>
	                            </td>
	                        </tr>
						</c:forEach>
                    </table>
                    <div class="paging">
			           	<c:if test="${pageGroupStart > 1}">
			           		<span class="prev">
			           			<a href="/Kmarket/admin/user/user.do?pg=${pageGroupStart - 1}&level=${level}&search=${search}&search_text=${search_text}&type=3">
			           			<&nbsp;이전
			           			</a>
			            	</span>
			           	</c:if>
			               <span class="num">
			            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
			            	<a href="/Kmarket/admin/user/user.do?pg=${i}&level=${level}&search=${search}&search_text=${search_text}&type=3" class="${currentPage == i? 'on':'off'}">${i}</a>
			            </c:forEach>
			               </span>
			            <c:if test="${pageGroupEnd < lastPageNum}">
				            <span class="next">
			                   <a href="/Kmarket/admin/user/user.do?pg=${pageGroupEnd + 1}&level=${level}&search=${search}&search_text=${search_text}&type=3">
			                   다음&nbsp;>
			                   </a>
				            </span>
			            </c:if>
			           </div>

                </section>                

                
                <p class="ico info">
                    <strong>Tip!</strong>
                    
                </p>

                

                <!-- 상품목록 컨텐츠 끝 -->
            </section>
        </main>
<%@ include file="../_footer.jsp" %>
