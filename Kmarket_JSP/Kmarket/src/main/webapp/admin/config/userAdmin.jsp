<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%@ include file="../_aside.jsp" %>
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
    <section id="admin-config-userAdmin">
        <nav>
            <h3>관리자</h3>
            <p>
                HOME > 환경설정 > <strong>관리자</strong>
            </p>
        </nav>
        <!-- 상품목록 컨텐츠 시작 -->                                
        <section>
            <div>
                <form id="formSearch" action="/Kmarket/admin/config/userAdmin.do?" method="get">
		      		<input type="hidden" name="type" value="3"/>
			       	<select name="search">
			              <option value="search1">아이디</option>
			              <option value="search2">이름</option>                               
			       	</select>
			       	<input type="text" name="search_text">
			       	<button id="btnSearch">검색</button>
   				</form>
            </div>
            <form id="formCheck" action="/Kmarket/admin/config/delete.do" method="post">
            	<input type="hidden" name="type" value="3"/>
	            <table>
	                <tr>
	                    <th><input type="checkbox" name="all"/></th>
	                    <th>아이디</th>
	                    <th>이름</th>
	                    <th>이메일</th>
	                    <th>휴대폰</th>
	                    <th>관리</th>
	                </tr>
	                <c:forEach var="admin" items="${admins}">
		                <tr>
		                    <td><input type="checkbox" name="chk"/></td>
		                    <td>${admin.uid}</td>
		                    <td>${admin.name}</td>
		                    <td>${admin.email}</td>
		                    <td>${admin.hp}</td>
		                    <td>
		                        <a href="#">[삭제]</a>
		                        <a href="#">[수정]</a>
		                    </td>
		                </tr>
	                </c:forEach>
	            </table>
			</form>
            
            <input class="btnDelete" type="button" value="선택삭제" />                          
            <input class="btnRegister" type="button" value="관리자 등록" />                          

            <div class="paging">
           	<c:if test="${pageGroupStart > 1}">
           		<span class="prev">
           			<a href="/Kmarket/admin/config/userAdmin.do?pg=${pageGroupStart - 1}&search=${search}&search_text=${search_text}&type=3">
           			<&nbsp;이전
           			</a>
            	</span>
           	</c:if>
               <span class="num">
            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
            	<a href="/Kmarket/admin/config/userAdmin.do?pg=${i}&search=${search}&search_text=${search_text}&type=3" class="${currentPage == i? 'on':'off'}">${i}</a>
            </c:forEach>
               </span>
            <c:if test="${pageGroupEnd < lastPageNum}">
	            <span class="next">
                   <a href="/Kmarket/admin/config/userAdmin.do?pg=${pageGroupEnd + 1}&search=${search}&search_text=${search_text}&type=3">
                   다음&nbsp;>
                   </a>
	            </span>
            </c:if>
           </div>

        </section>                

        
        <p class="ico info">
            <strong>Tip!</strong>
            입퇴사 시 수정, 삭제 반영 당일에 해주세요.
        </p>

        

        <!-- 상품목록 컨텐츠 끝 -->
    </section>
</main>
<%@ include file="../_footer.jsp" %>
