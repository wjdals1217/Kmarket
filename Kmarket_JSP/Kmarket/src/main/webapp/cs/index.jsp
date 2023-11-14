<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
      <section id="cs">
        <div class="main">
          <h1 class="title"><strong>케이마켓</strong>이 도와드릴게요!</h1>              
          <section class="notice">
            <h1>공지사항<a href="${ctxPath}/cs/notice.do?group=notice&cate=all&cateDetail=all&type=1">전체보기</a></h1>
             <c:choose>
                <c:when test="${!empty notice }">
	                <c:forEach var="notice" items="${notice}">
	                <ul>
	                    <li>
	                        <a href="${ctxPath }/cs/view.do?group=notice&cate=All&aNo=${notice.aNo}" class="title">
	                        	[${notice.dName}] ${notice.title}
	                        </a>
	                        <span class="date">${notice.rdate}</span>
	                    </li>
	                </ul>
	                </c:forEach>
                </c:when>
                <c:otherwise>
           			<ul>
						<li>등록된 게시물이 없습니다.</li>
					</ul>
        		</c:otherwise>
        		</c:choose>
          </section>
        
          <section class="faq"><!-- aename제거 -->
            <h1>자주 묻는 질문<a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=member">전체보기</a>
            </h1>
            <ol>
              <li>
                <a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=member"><span>회원</span></a>
              </li>
              <li>
                <a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=coupon"><span>쿠폰/이벤트</span></a>
              </li>
              <li>
                <a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=order"><span>주문/결제</span></a>
              </li>
              <li>
                <a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=delivery"><span>배송</span></a>
              </li>
              <li>
                <a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=cancle"><span>취소/반품/교환</span></a>
              </li>
              <li>
                <a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=travel"><span>여행/숙박/항공</span></a>
              </li>
              <li>
                <a href="${ctxPath}/cs/faq.do?group=faq&aeName=all&type=20&cateDetail=safe"><span>안전거래</span></a>
              </li>
            </ol>
          </section>
        
          <section class="qna">
            <h1>
              문의하기
              <a href="${ctxPath}/cs/qna.do?group=qna&cateDetail=all&type=20">전체보기</a>
            </h1>
            <ul>
                       <c:choose>
                <c:when test="${!empty qna }">
	                <c:forEach var="qna" items="${qna}">
	                <ul>
	                    <li>
	                        <a href="${ctxPath }/cs/view.do?group=qna&cate=All&aNo=${qna.aNo}" class="title">
	                        	[${qna.dName}] ${qna.title}
	                        </a>
	                        <span class="date">${qna.rdate}</span>
	                    </li>
	                </ul>
	                </c:forEach>
                </c:when>
                <c:otherwise>
           			<ul>
						<li>등록된 게시물이 없습니다.</li>
					</ul>
        		</c:otherwise>
        		</c:choose>
          </section>

          <section class="tel">
            <h1>
              1:1 상담이 필요하신가요?
            </h1>

            <article>
              <div>
                <h3>고객센터 이용안내</h3>
                <p>
                  <span>일반회원/비회원</span><br>
                  <strong>1566-0001</strong><span>(평일 09:00 ~ 18:00)</span>
                </p>
                <p>
                  <span>스마일클럽 전용</span><br>
                  <strong>1566-0002</strong><span>(365일 09:00 ~ 18:00)</span>
                </p>
              </div>
            </article>
            <article>
              <div>
                <h3>판매상담 이용안내</h3>
                <p>
                  <span>판매고객</span><br>
                  <strong>1566-5700</strong><span>(평일 09:00 ~ 18:00)</span>
                </p>
                <p>
                  <a href="#">판매자 가입 및 서류 접수 안내 〉</a>
                </p>                
              </div>
            </article>

          </section>
        
      </section>

      <footer>
        <ul>
          <li><a href="#">회사소개</a></li>
          <li><a href="#">서비스이용약관</a></li>
          <li><a href="#">개인정보처리방침</a></li>
          <li><a href="#">전자금융거래약관</a></li>
        </ul>
        <div>
          <p><img src="../img/footer_logo.png" alt="로고" /></p>
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
