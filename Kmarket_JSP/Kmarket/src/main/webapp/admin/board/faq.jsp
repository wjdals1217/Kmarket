<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%@ include file="../_aside.jsp" %>
            <section id="admin-board-notice">
                <nav>
                    <h3>자주묻는 질문</h3>
                    <p>
                        HOME > 게시판관리 > <strong>자주묻는 질문</strong>
                    </p>
                </nav>
                <!-- 공지사항 컨텐츠 시작 -->                                
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
                    <p class="sort">
                        <a href="#" class="on">전체&nbsp;|</a>
                        <a href="#" class="off">판매자&nbsp;|</a>
                        <a href="#" class="off">구매자&nbsp;|</a>
                    </p>
                  <c:choose>
              	  <c:when test="${not empty articles }">
                    <table>
                        <tr>
                            <th><input type="checkbox" name="all"/></th>
                            <th>글번호</th>
                            <th>글제목</th>
                            <th>작성자</th>
                            <th>작성일자</th>
                            <th>관리</th>
                        </tr>
						<c:forEach var="article" items="${articles}">
		                <tr>
		                  <th><input type="checkbox" name="all"/></th>
		                  <td>${article.aNo}</td>
		                  <td><a href="${ctxPath}/admin/board/view.do?aNo=${article.aNo }&"> ${article.title}  </a></td>
		                  <td>${article.writer}</td>
		                  <td>${article.rdate}</td>
		                  <td>
                                <a href="#">[삭제]</a>
                                <a href="#">[수정]</a>
                            </td>
		                </tr>
		               	</c:forEach> 
                    </table>
                     <input class="btnDelete" type="button" value="선택삭제" />
	                  </c:when>
		              <c:when test="${empty articles }">
		              	  <table>
			                <tr>
			                  <td> 게시글이 존재하지 않습니다. </td>
			                </tr>
			              </table>
	                    <input class="btnDelete" type="button" value="선택삭제" />
	                    <input class="btnWrite" type="button" value="글등록" />
	               </c:when>
				 	</c:choose>
                    <a href="${ctxPath}/admin/faq/write.do?group=faq"><input class="btnWrite" type="button" value="글등록" /></a>
                	    <!-- 페이지 네비게이션 -->
			        <div class="page">
			        	<c:if test="${pageGroupStart > 1}">
			            	<a href="${ctxPath}/admin/board/faq.do?group=faq&cateDetail=${cateDetail}&type=20&pg=${pageGroupStart - 1}" class="prev">이전</a>
			            </c:if>
			            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
			            	<a href="${ctxPath}/admin/board/faq.do?group=faq&cateDetail=${cateDetail}&type=20&pg=${i}&" class="num ${currentPage == i?'current':'off'}">${i}</a>
			            </c:forEach>
			            <c:if test="${pageGroupEnd < lastPageNum}">
			            	<a href="${ctxPath}/admin/board/faq.do?group=faq&cateDetail=${cateDetail}&type=20&pg=${pageGroupEnd + 1}" class="next">다음</a>
			            </c:if>
			        </div>
                </section>                
                <!-- 공지사항 컨텐츠 끝 -->
            </section>
        </main>
<%@ include file="../_footer.jsp" %>
