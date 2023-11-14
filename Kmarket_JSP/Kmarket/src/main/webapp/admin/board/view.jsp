<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<%@ include file="../_aside.jsp"%>
<script>
	window.onload = function(){
			
		const commentURL = "/Kmarket/admin/board/write.do";	
		const formComment = document.getElementById('formComment');
		const commentList = document.getElementsByClassName('commentList')[0];
		
		////////////////////////////////////////////////////////////////////////
		// 댓글입력(최신 Javascript(ES6) 문법 적용)
		////////////////////////////////////////////////////////////////////////
		formComment.onsubmit = async (e) => {
			e.preventDefault();
			
			// 개별 데이터 취합
			const aNo  = formComment.aNo.value;
			const writer  = formComment.writer.value;
			const content = formComment.content.value;
			

			const paramsData = new URLSearchParams({
				'aNo': aNo,
				'writer': writer,
				'content': content
			});
			
			// 데이터 서버 전송
			const response = await fetch(commentURL, {
				method: 'POST',
				body: paramsData 
			});
						
			// 서버 응답 데이터 수신
			const data = await response.json();
			console.log('data : ' + JSON.stringify(data));
			
			if(data != null){
				// 댓글목록 동적 처리
				let article = "<article>";
				    article += "<span class='nick'>"+data.Writer+"</span>";
					article += "<span class='date'>"+data.Rdate+"</span>";
					article += "<textarea readonly class='content' data-value='"+data.content+"'>"+data.content+"</textarea>";
					article += "<div>";
					article += "<a href='#' class='remove' data-no='"+data.aNo+"'>삭제</a>";
					article += "<a href='#' class='cancel' data-no='"+data.aNo+"'>취소</a>";
					article += "<a href='#' class='modify' data-no='"+data.aNo+"'>수정</a>";
					article += "</div>";
					article += "</article>";
				
				commentList.insertAdjacentHTML('beforeend', article);
				alert('댓글이 등록 되었습니다.');
				
			}else{
				alert('댓글이 등록이 실패 했습니다.');
			}
		}; // 댓글 입력 끝
		
		////////////////////////////////////////////////////////////////////////
		// 댓글삭제(동적 이벤트 바인딩 처리 -> 동적 생성되는 새로운 댓글목록 삭제링크가 동작함)
		////////////////////////////////////////////////////////////////////////
		document.addEventListener('click', function(e){
			if(e.target && e.target.classList.value == 'remove'){
				e.preventDefault();
				
				if(!confirm('정말 삭제 하시겠습니까?')){
					return;
				}
				
				const no = e.target.dataset['no'];
				//console.log('no : ' + no);
				
				const params = new URLSearchParams({
					'type': 'REMOVE',
					'no': no,
				});
				
				fetch(commentURL+'?'+params, {
					method: 'GET'
				})
				.then(res => res.json())
				.then(data => {
					console.log('data : ' + data);
					
					if(data.result > 0){
						
						alert('댓글을 삭제했습니다.');
						
						// 댓글 동적 삭제
						const article = e.target.parentNode.closest('article');
						commentList.removeChild(article);
					}else{
						alert('댓글 삭제가 실패 했습니다.');
					}
				});
			}
		});
		
		////////////////////////////////////////////////////////////////////////
		// 댓글수정(동적 이벤트 바인딩 처리 -> 동적 생성되는 새로운 댓글목록 삭제링크가 동작함)
		////////////////////////////////////////////////////////////////////////
		let isModifying = false;
				
		document.addEventListener('click', async function(e){
			
			const article  = e.target.parentNode.closest('article');
			const textarea = article.getElementsByTagName('textarea')[0];
			const remove   = article.getElementsByClassName('remove')[0];
			const cancel   = article.getElementsByClassName('cancel')[0];
			const modify   = article.getElementsByClassName('modify')[0];
			
			// 수정&수정완료
			if(e.target && e.target.classList.value == 'modify'){
				e.preventDefault();

				// 다른 수정 중인 상태(수정모드)의 댓글 Article 문서객체 생성  
				const modifying = document.getElementsByClassName('modifying')[0];
				
				const txt = e.target.innerText;
				
				if(txt == '수정'){
					
					// 수정할려는 해당 댓글의 부모 Article 태그에 클래스값 modifying 설정 
					article.classList.add('modifying');

					// 수정모드 전환
					textarea.style.border = '1px solid #e4eaec';
					textarea.style.background = '#fff';
					textarea.readOnly = false;
					textarea.focus();
					
					remove.style.display = 'none';
					cancel.style.display = 'inline';
					modify.innerText = '수정완료';
					
					// 수정 중인 댓글이 존재할 경우 해당 댓글 수정모드 해제
					if(modifying != null){						 
						const modifyingTextarea = modifying.getElementsByTagName('textarea')[0];
						const modifyingRemove = modifying.getElementsByClassName('remove')[0];
						const modifyingCancel = modifying.getElementsByClassName('cancel')[0];
						const modifyingModify = modifying.getElementsByClassName('modify')[0];
						
						modifyingTextarea.style.border = 'none';
						modifyingTextarea.style.background = 'none';
						modifyingTextarea.readOnly = true;
						
						modifyingRemove.style.display = 'inline';
						modifyingCancel.style.display = 'none';
						modifyingModify.innerText = '수정';
						
						modifying.classList.remove('modifying');
					}
					
				}else if(txt == '수정완료'){
					
					if(!confirm('정말 수정 하시겠습니까?')){
						return;
					}
										
					const no = e.target.dataset['no'];
					const content = textarea.value;
					
					const params = new URLSearchParams({
						'type': 'MODIFY',
						'no': no,
						'content': content
					});
					
					// 데이터 서버 전송
					const response = await fetch(commentURL+"?"+params, {
						method: 'GET'
					});
								
					// 서버 응답 데이터 수신
					const data = await response.json();
					console.log('data : ' + JSON.stringify(data));
					
					if(data.result > 0){
						alert('수정완료 했습니다.');
						
						// 수정모드 해제
						textarea.style.border = 'none';
						textarea.style.background = 'none';
						textarea.readOnly = true;
						
						remove.style.display = 'inline';
						cancel.style.display = 'none';
						modify.innerText = '수정';
						
					}else{
						alert('수정실패 했습니다.');
					}
				}
			}// 수정&수정완료 끝
			
			// 수정취소
			if(e.target && e.target.classList.value == 'cancel'){
				e.preventDefault();
				const value = textarea.dataset['value'];
				
				// 수정모드 해제
				textarea.style.border = 'none';
				textarea.style.background = 'none';
				textarea.readOnly = true;
				textarea.value = value;
				
				remove.style.display = 'inline';
				cancel.style.display = 'none';
				modify.innerText = '수정';
			
			} // 수정취소 끝
			
		});// 댓글수정 addEventListener end
		
	};
</script>

<section id="admin-board-notice">
	<nav>
		<h3>고객문의</h3>
		<p>
			HOME > 게시판관리 > <strong>고객문의</strong>
		</p>
	</nav>
	<!-- 공지사항 컨텐츠 시작 -->
	<article>
		<form action="#" id="formComment" method="post">
			<input type="hidden" name="aNo" value="${dto.aNo}"> 
			<input type="hidden" name="writer" value="${sessUser.name}"> 
			<nav>
				<h2 class="title">${dto.title}</h2>
				<p>
					<span>${dto.writer}</span> <span>${dto.rdate}</span> <br> <br>
					<br> <br>
					<!-- 버튼사이즈 줄이기 -->
				</p>
			</nav>

			<div class="content">
				<p>
					${dto.content} <br> <br> <br>
				</p>
				<p>
					※ 피싱 관련 피해신고<br /> <br /> ▶ 경찰청 사이버수사국 (국번없이)182 :
					http://cyberbureau.police.go.kr<br /> ▶ KISA 인터넷침해대응센터 (국번없이)118 :
					http://www.krcert.or.kr<br /> 감사합니다.<br />
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
			<a href="./list.html" class="btnList">목록보기</a> <br> <br> <br>
			<br>
			<hr>
			<h3>댓글 작성</h3>
			<br> <br>
			<textarea name="content" placeholder="내용을 입력하세요."></textarea>
			<br> <br> <a href=""><button>글 등록</button></a>
			<!-- 버튼사이즈 줄이기 -->
			
		</form>
		<hr>
		<!-- 댓글리스트 -->
		<section class="commentList">
			<h3>댓글목록</h3>
				<article >
					<!-- style.css 829라인 margin-right: 10px 추가 -->
					<span class="nick">${Cdto.rdate}</span> <span class="date">${Cdto.writer}</span>
					<textarea readonly class="Ccontent" data-value="${Cdto.content}">${Cdto.content}</textarea>
					<div>
						<a href="#" class="remove" data-no="${Cdto.rNo}">삭제</a> 
						<a href="#" class="cancel" data-no="${Cdto.rNo}">취소</a>
						<!-- style.css 858라인 display:none 처리 -->
						<a href="#" class="modify" data-no="${Cdto.rNo}">수정</a>
					</div>
				</article>
			<c:if test="${empty comments}">
				<p class="empty">등록된 댓글이 없습니다.</p>
			</c:if>
		</section>
	</article>
	<!-- 공지사항 컨텐츠 끝 -->
</section>
</main>
<%@ include file="../_footer.jsp"%>
