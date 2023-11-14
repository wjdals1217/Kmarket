<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
      <section id="cs">
        <div class="notice">
          <nav>
            <div>
              <p>홈<span>></span>공지사항</p>
            </div>
          </nav>
          <section class="list">
            <aside>
              <h2>공지사항</h2>
              <ul>
                <li class="${cateDetail eq 'all'?'on':'off'}"><a href="${ctxPath}/cs/notice.do?group=notice&cate=all&cateDetail=all&type=1">전체 보기</a></li>
                <li class="${cateDetail eq 'all'?'on':'off'}"><a href="${ctxPath}/cs/notice.do?group=notice&cate=all&cateDetail=service&type=1">고객서비스</a></li>
                <li class="${cateDetail eq 'all'?'on':'off'}"><a href="${ctxPath}/cs/notice.do?group=notice&cate=all&cateDetail=safe&type=1">안전거래</a></li>
                <li class="${cateDetail eq 'all'?'on':'off'}"><a href="${ctxPath}/cs/notice.do?group=notice&cate=all&cateDetail=hazard&type=1">위해상품</a></li>
                <li class="${cateDetail eq 'all'?'on':'off'}"><a href="${ctxPath}/cs/notice.do?group=notice&cate=all&cateDetail=event&type=1">이벤트당첨</a></li>

              </ul>
            </aside>
            <article>
              <nav>
              <c:choose>
                <c:when test="${cateDetail == 'all'}">
			            <h1>전체보기</h1>
			            <h2>전체 공지 사항 입니다.</h2>
			        </c:when>
			        <c:when test="${cateDetail == 'service'}">
			            <h1>고객서비스</h1>
			            <h2>고객 서비스 공지사항 입니다.</h2>
			        </c:when>
			        <c:when test="${cateDetail == 'hazard'}">
			            <h1>위해상품</h1>
			            <h2>위해상품 관련 공지 입니다.</h2>
			        </c:when>
			        <c:when test="${cateDetail == 'safe'}">
			            <h1>안전거래</h1>
			            <h2>안전거래 관련 공지사항 입니다.</h2>
			        </c:when>
			        <c:when test="${cateDetail == 'event'}">
			            <h1>이벤트 당첨</h1>
			            <h2>이벤트 당첨 관련 공지사항 입니다.</h2>
			        </c:when>
			        <c:otherwise>
			        </c:otherwise>
			    </c:choose>
              </nav>
				 <c:choose>
              	  <c:when test="${not empty articles }">
		              <table>
		              	<tr>
		              		<td>제목</td>
		              		<td>글쓴이</td>
		              		<td>작성일</td>
		              	</tr>
		              	<c:forEach var="article" items="${articles}">
		                <tr>
		                  <td><a href="${ctxPath}/cs/view.do?aNo=${article.aNo }&">[ ${article.dName}  ] ${article.title} && ${article.aNo } </a></td>
		                  <td>${article.writer}</td>
		                  <td>${article.rdate}</td>
		                </tr>
		               	</c:forEach> 
		              </table>
	              </c:when>
	              <c:when test="${empty articles }">
	              	  <table>
		                <tr>
		                  <td><a href="#"> 게시글이 존재하지 않습니다.  </a></td>
		                  <td> </td>
		                  <td> </td>
		                </tr>
		              </table>
	              </c:when>
			 </c:choose>
		    <!-- 페이지 네비게이션 -->
		        <div class="page">
		        	<c:if test="${pageGroupStart > 1}">
		            	<a href="${ctxPath}/cs/notice.do?group=notice&cateDetail=${cateDetail}&type=20&pg=${pageGroupStart - 1}" class="prev">이전</a>
		            </c:if>
		            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
		            	<a href="${ctxPath}/cs/notice.do?group=notice&cateDetail=${cateDetail}&type=20&pg=${i}&" class="num ${currentPage == i?'current':'off'}">${i}</a>
		            </c:forEach>
		            <c:if test="${pageGroupEnd < lastPageNum}">
		            	<a href="${ctxPath}/cs/notice.do?group=notice&cateDetail=${cateDetail}&type=20&pg=${pageGroupEnd + 1}" class="next">다음</a>
		            </c:if>
		        </div>
            </article>
          </section>
        </div>
      </section>

      <footer>
        <ul>
          <li><a href="#">회사소개</a></li>
          <li><a href="#">서비스이용약관</a></li>
          <li><a href="#">개인정보처리방침</a></li>
          <li><a href="#">전자금융거래약관</a></li>
        </ul>
        <div>
          <p><img src="./images/footer_logo.png" alt="로고" /></p>
          <p>
            <strong>(주)KMARKET</strong><br />
            부산시 강남구 테헤란로 152 (역삼동 강남파이낸스센터)<br />
            대표이사 : 홍길동<br />
            사업자등록번호 : 220-81-83676 사업자정보확인<br />
            통신판매업신고 : 강남 10630호 Fax : 02-589-8842
          </p>
          <p>
            <strong>고객센터</strong><br />
            Tel : 1234-5678 (평일 09:00~18:00)<br />
            스마일클럽/SVIP 전용 : 1522-5700 (365일 09:00~18:00)<br />
            경기도 부천시 원미구 부일로 223(상동) 투나빌딩 6층<br />
            Fax : 051-123-4567 | Mail : kmarket@kmarket.co.kr<br />
          </p>
        </div>
      </footer>
    </div>
  </body>
</html>
