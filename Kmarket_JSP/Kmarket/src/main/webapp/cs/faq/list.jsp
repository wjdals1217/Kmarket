<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
      <section id="cs">
        <div class="faq">
          <nav>
            <div>
              <p>홈<span>></span>자주묻는 질문</p>
            </div>
          </nav>
          <section class="list">
            <aside>
              <h2>자주묻는 질문</h2>
              <ul>
      			<li class="${cateDetail eq 'member'?'on':'off'}"><a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=member">회원</a></li>
                <li class="${cateDetail eq 'coupon' || cateDetail eq 'event' ?'on':'off'}"><a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=coupon">쿠폰/이벤트</a></li>
                <li class="${cateDetail eq 'order'?'on':'off'}"><a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=order">주문/결제</a></li>
                <li class="${cateDetail eq 'delivery'?'on':'off'}"><a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=delivery">배송</a></li>
                <li class="${cateDetail eq 'cancle'?'on':'off'}"><a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=cancle">취소/반품/교환</a></li>
                <li class="${cateDetail eq 'travel'?'on':'off'}"><a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=travel">여행/숙박/항공</a></li>
                <li class="${cateDetail eq 'safe'?'on':'off'}"><a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=safe">안전거래</a></li>
              </ul>
            </aside>
			<article>
				<nav>
				<c:choose>
			        <c:when test="${cateDetail == 'all'}">
			            <h1>전체보기</h1>
			            <h2>전체 문의 내용입니다.</h2>
			        </c:when>
			        <c:when test="${cateDetail == 'member'}">
			            <h1>회원</h1>
			            <h2>회원 관련 문의 내용입니다.</h2>
			        </c:when>
			        <c:when test="${cateDetail == 'event' || cateDetail == 'coupon'}">
			            <h1>이벤트/쿠폰</h1>
			            <h2>이벤트/쿠폰 관련 문의 내용입니다.</h2>
			        </c:when>
			        <c:when test="${cateDetail == 'hazard'}">
			            <h1>위해상품</h1>
			            <h2>위해상품 관련 문의 내용입니다.</h2>
			        </c:when>
			        <c:when test="${cateDetail == 'order'}">
			            <h1>주문/결제</h1>
			            <h2>주문/결제 관련 문의 내용입니다.</h2>
			        </c:when>
			        <c:when test="${cateDetail == 'delivery'}">
			            <h1>배송</h1>
			            <h2>배송 관련 문의 내용입니다.</h2>
			        </c:when>
			        <c:when test="${cateDetail == 'cancle'}">
			            <h1>취소/반품/교환</h1>
			            <h2>취소/반품/교환 관련 문의 내용입니다.</h2>
			        </c:when>
			        <c:when test="${cateDetail == 'travel'}">
			            <h1>여행/숙박/항공</h1>
			            <h2>여행/숙박/항공 관련 문의 내용입니다.</h2>
			        </c:when>
			        <c:when test="${cateDetail == 'safe'}">
			            <h1>안전거래</h1>
			            <h2>안전거래 관련 문의 내용입니다.</h2>
			        </c:when>
			        <c:otherwise>
			            <!-- 기본값 설정 또는 처리할 내용을 여기에 추가하세요. -->
			        </c:otherwise>
			    </c:choose>
				</nav>
				<c:choose>
					<c:when test="${!empty cs }">
						<c:forEach var="type" items="${types}">
			                <div>
			                    <h3>${type.dName }</h3>
			                    <ul>
			                    	<!-- varStatus : 상태용 변수(참고 링크 : https://jetalog.net/20) -->
									<c:forEach var="board" items="${type.dto}" varStatus="status">
										<c:if test="${board.type eq type.type}">
					                        <li class="${status.index ge 1 
					                        	&& status.index le 9 ? board.type : '' }">
					                            <a href="${ctxpath}/cs/faqBoard/view.do?group=faq&cateDetail=${cateDetail}&aNo=${board.aNo}">
					                                <span>Q.</span>${board.title }
					                            </a>
					                        </li>
				                        </c:if>
			                        </c:forEach>
			                        <li class="more">
			                            <a class="moreAteg ${type.type }" href="#">더보기</a>
			                            <a class="lessAteg ${type.type }" style="display: none" href="#">간단히보기</a>
			                        </li>
			                    </ul>
			                </div>
	                	</c:forEach>
	                </c:when>
	                <c:otherwise>
		                <ul>
							<li>등록된 게시물이 없습니다.</li>
						</ul>
	                </c:otherwise>
                </c:choose>
            </article>
        </section>
    </div>
</section>
<script src="${ctxPath}/cs/js/csScript.js"></script>
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
  </body>
</html>
