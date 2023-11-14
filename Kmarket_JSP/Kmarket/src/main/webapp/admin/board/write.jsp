<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<%@ include file="../_aside.jsp"%>

<section id="admin-board-notice">
	<nav>
		<c:choose>
	        <c:when test="${param.group == 'faq'}">
	           <h3>자주묻는 질문 작성</h3>
	        </c:when>
	        <c:when test="${param.group == 'notice'}">
	           <h3>공지사항 작성</h3>
	        </c:when>
		</c:choose>
		
		<p>
			HOME > 게시판관리 > 	
			<c:choose>
	        <c:when test="${param.group == 'faq'}">
	           자주묻는 질문
	        </c:when>
	        <c:when test="${param.group == 'notice'}">
	           공지사항
	        </c:when>
		</c:choose>
		</p>
	</nav>
	<!-- 공지사항 컨텐츠 시작 -->
	<article>
		<form action="${ctxPath}/admin/faq/write.do?group=faq&cateDetail=${param.selectDetailView}&type=20" id="formComment" method="post">
			<input type="hidden" name="writer" value="${sessUser.uid}">  
			<input type="hidden" name="type" value="20">  
			<input type="hidden" name="uLevel" value="${sessUser.type}">  
			<div class="write">
			<!-- write시작 -->
			<table>
			       <tr>
                    <td>문의유형</td>
                    <td>
                      <select id="selectDetailView" name="selectDetailView">
                     	  <option  selected disabled>선택</option>
	                      <c:forEach var="cate" items="${cateDto}">
	                      	<option value="${cate.akName}">${cate.akName}</option>
	                      </c:forEach>
                      </select>
                    </td>
                  </tr>
                  <tr>
                    <td>문의제목</td>                  
                    <td>
                      <input type="text" name="title" placeholder="제목을 입력하세요." style=" width: 100%;   margin-bottom: 6px; font-size: 15px; font-weight: bold;"/>
                    </td>
                  </tr>                
                  <tr>
                    <td>문의내용</td>                  
                    <td>
                      <textarea name="content" placeholder="내용을 입력하세요." style="width: 100%; height: 360px; resize: none; font-size: 16px; padding: 10px; box-sizing: border-box; background-color: white;"></textarea>
                    </td>
                  </tr>	
				</table>
			</div>
			<input type="submit" class="btnSubmit" value="등록하기"/>
			<br>
			<hr>
		</form>
			<a href="./list.html" class="btnList">목록보기</a> <br> <br> <br>
	</article>
	<!-- 공지사항 컨텐츠 끝 -->
</section>
</main>
<%@ include file="../_footer.jsp"%>
