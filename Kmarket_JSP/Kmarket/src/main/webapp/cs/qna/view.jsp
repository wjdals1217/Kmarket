<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>

<section id="cs">
	<div class="qna">
		<nav>
			<div>
				<p>
					홈<span>></span>문의하기
				</p>
			</div>
		</nav>
		<section class="view">
			<%@include file="../_aside.jsp"%>
			<article>
				<nav>
					<h2 class="title">${dto.title}</h2>
					<p>
						<span>${dto.writer}</span> <span>${dto.rdate}</span> <br> <br>
						<br> <br> <a
							href="${ctxPath}/cs/delete.do?aNo=${dto.aNo}" class="btnWrite">글
							삭제</a>
						<!-- 버튼사이즈 줄이기 -->
					</p>
				</nav>

				<div class="content">
					<p>
						${dto.content}<br> <br> <br>
					</p>
					<p>
						※ 피싱 관련 피해신고<br />
						<br /> ▶ 경찰청 사이버수사국 (국번없이)182 : http://cyberbureau.police.go.kr<br />
						▶ KISA 인터넷침해대응센터 (국번없이)118 : http://www.krcert.or.kr<br /> 감사합니다.<br />
					</p>
					<br> <br> <br> <br>
					<nav>
						<c:if test="${dto.file > 0}">
							<tr>
								<td>첨부파일</td>
								<td><a href="#">${dto.file}</a>&nbsp; <span>${article.fileDto.download}</span>회
									다운로드</td>
							</tr>
						</c:if>
					</nav>
				</div>
			<!-- 	<br>
				<hr> -->
				<a href="./list.html" class="btnList">목록보기</a>
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
		<p>
			<img src="../images/footer_logo.png" alt="로고" />
		</p>
		<p>
			<strong>(주)KMARKET</strong><br /> 부산시 강남구 테헤란로 152 (역삼동 강남파이낸스센터)<br />
			대표이사 : 홍길동<br /> 사업자등록번호 : 220-81-83676 사업자정보확인<br /> 통신판매업신고 : 강남
			10630호 Fax : 02-589-8842
		</p>
		<p>
			<strong>고객센터</strong><br /> Tel : 1234-5678 (평일 09:00~18:00)<br />
			스마일클럽/SVIP 전용 : 1522-5700 (365일 09:00~18:00)<br /> 경기도 부천시 원미구 부일로
			223(상동) 투나빌딩 6층<br /> Fax : 051-123-4567 | Mail :
			kmarket@kmarket.co.kr<br />
		</p>
	</div>
</footer>
</div>
</body>
</html>
