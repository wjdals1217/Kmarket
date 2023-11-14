<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
      <section id="cs">
        <div class="qna">
          <nav>
            <div>
              <p>홈<span>></span>문의하기</p>
            </div>
          </nav>
          <section class="write">
          <%@ include file="../_aside.jsp" %>
            <article>
              <form action="${ctxPath}/cs/write.do"  method="post" enctype="multipart/form-data">
				  <input type="hidden" name="group" value="${group}"/>
			      <input type="hidden" name="type" value="23"/>
			      <input type="hidden" name="cateDetail" id="cateDetail" value="${cateDetail}"/>
                  <input type="hidden" name="writer" value="${sessUser.uid}"/>
			      <input type="hidden" name="uLevel" value="${sessUser.level}"/>
			      <input type="hidden" name="regip" value="${sessUser.regip}"/> 
                <table>
                  <tr>
                    <td>문의유형</td>
                    <td>
                      <select id="selectDetailView" name="selectDetailView">
                     	  <option  selected disabled>선택</option>
	                      <c:forEach var="cate" items="${cateDto}">
	                      	<option value="${cate.dName}">${cate.dName}</option>
	                      </c:forEach>
                      </select>
                    </td>
                  </tr>
                  <tr>
                    <td>문의제목</td>                  
                    <td>
                      <input type="text" name="title" placeholder="제목을 입력하세요."/>
                    </td>
                  </tr>                
                  <tr>
                    <td>문의내용</td>                  
                    <td>
                      <textarea name="content" placeholder="내용을 입력하세요."></textarea>
                    </td>
                  </tr>
                  <tr>
                    <td>첨부 파일</td>                  
                    <td>
                      <input type="file" name="file" value="파일첨부">
                    </td>
                  </tr>
                </table>
                <div>
                  <a href="${ctxPath}/cs/qna.do?group=qna&type=${type}&cateDeatil=${cateDetail}" class="btnList">취소하기</a>
                  <input type="submit" class="btnSubmit" value="등록하기"/>
                </div>
              </form>
            </article>
          </section>
        </div>
      </section>
<script src="./js/csScript.js"></script>
      <footer>
        <ul>
          <li><a href="#">회사소개</a></li>
          <li><a href="#">서비스이용약관</a></li>
          <li><a href="#">개인정보처리방침</a></li>
          <li><a href="#">전자금융거래약관</a></li>
        </ul>
        <div>
          <p><img src="../images/footer_logo.png" alt="로고" /></p>
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
