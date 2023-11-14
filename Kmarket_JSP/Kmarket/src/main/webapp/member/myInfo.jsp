<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> 
<script src="/Kmarket/js/zipcode.js"></script>
<script src="/Kmarket/js/validation.js"></script> 
<script src="/Kmarket/js/checkUser.js"></script> 
<script src="/Kmarket/js/authEmail.js"></script>
<script>
	//checkUser.js에서 window.onload 사용했기 때문에 실행안돼(한 번만 onload함수가 실행됨) 둘 다 제이쿼리로 바꿔줌 
	$(function(){
		
		const inputUid = document.getElementsByName('uid')[0]; 
		const inputPass = document.getElementsByName('pass2')[0]; 
		
		//비밀번호 변경
		const btnUpdatePass = document.getElementById('btnUpdatePass');
		btnUpdatePass.addEventListener('click', function(){
			
			if(isPassOk && confirm('정말 비밀번호를 수정하시겠습니까?')){

				//fetch에서 post데이터 전송을 위해 URLSearchParams 사용
				const params = new URLSearchParams();
				params.append('kind', 'PASSWORD');
				params.append('uid', inputUid.value);
				params.append('pass', inputPass.value);
				
				//fetch함수로 AJAX통신
				fetch('/Kmarket/member/myInfo.do', {
					method: 'POST',
					body: params
				})
				.then((response)=>response.json())
				.then((data)=>{
					concole.log('data : ' + data);
					if(data.result > 0){
						alert('비밀번호가 수정되었습니다. 다시 로그인하십시오.');
						location.href = '/Kmarket/member/logout.do';
					}
				});
			}else {
				alert('변경 비밀번호가 유효하지 않거나 일치하지 않습니다.');
			}
		});
		
		//탈퇴하기
		const btnWithdraw = document.getElementById('btnWithdraw');
		btnWithdraw.addEventListener('click', function(){

			if(confirm('정말 탈퇴하시겠습니까?')){
			
			const jsonData = {
					"kind": "WITHDRAW",
					"uid": inputUid.value
			};
			
			$.ajax({
				url: '/Kmarket/member/myInfo.do',
				type: 'POST',
				data: jsonData,
				dataType: 'json',
				success: function(data){
					console.log('data : ' + data);
					if(data.result > 0){
						alert('회원탈퇴 처리가 완료되었습니다. 이용해 주셔서 감사합니다.');
						location.href = '/Kmarket/member/logout.do';
					}
				}
			});
			}
			
		});
	
	});

</script>
        <main id="member">
            <section class="myInfo">
                <form action="/Kmarket/member/myInfo.do" method="post">
                <input type="hidden" name="kind" value="MODIFY" />
                <input type="hidden" name="type" value="MODIFY" />
                <input type="hidden" name="uid" value="${sessUser.uid} " />
                    <table border="1">
                        <caption>회원정보 설정</caption>
                        <tr>
                            <td>아이디</td>
                            <td>${sessionScope.sessUser.uid}</td> <!--로그인 하고 마이페이지를 들어오기 때문에 session의 사용자 정보 이용 -->
                        </tr>
                        <tr>
                            <td>비밀번호</td>
                            <td>
                                <input type="password" name="pass1" placeholder="비밀번호 입력"/>
                                <span class="passResult"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>비밀번호 확인</td>
                            <td>
                                <input type="password" name="pass2" placeholder="비밀번호 입력 확인"/>
                                <button type="button" id="btnUpdatePass" class="btnUpdatePass">비밀번호 수정</button>
                            </td>
                        </tr>
                        <tr>
                            <td>회원가입날짜</td>
                            <td>${sessionScope.sessUser.regDate}</td>
                        </tr>
                    </table>
        
                    <table border="1">
                        <caption>개인정보 수정</caption>
                        <tr>
                            <td>이름</td>
                            <td>
                                <input type="text" name="name" value="${sessionScope.sessUser.name}"/>
                                <span class="nameResult"></span>                     
                            </td>
                        </tr>
                        <tr>
                            <td>별명</td>
                            <td>
                                <p class="nickInfo">공백없는 한글, 영문, 숫자 입력</p>
                                <input type="text" name="nick" value="${sessionScope.sessUser.nick}" placeholder="별명 입력"/>
                                <button type="button" id="btnCheckNick"><img src="../img/chk_id.gif" alt="중복확인"/></button>
                                <span class="nickResult"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>이메일</td>
                            <td>
                                
                                <input type="email" name="email" value="${sessionScope.sessUser.email}" placeholder="이메일 입력"/>
                                <span class="resultEmail"></span>
                                <button type="button" id="btnEmailCode"><img src="../img/chk_auth.gif" alt="인증번호 받기"/></button>
                                <div class="auth">
                                    <input type="text" name="auth" placeholder="인증번호 입력"/>
                                    <button type="button" id="btnEmailAuth"><img src="../img/chk_confirm.gif" alt="확인"/></button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>휴대폰</td>
                            <td>
                                <input type="text" name="hp" value="${sessionScope.sessUser.hp}" placeholder="휴대폰 입력"/>
                                <span class="resultHp"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>주소</td>
                            <td>
                                <input type="text" name="zip" id="zip" readonly="readonly" value="${sessionScope.sessUser.zip}" placeholder="우편번호"/>
                                <button type="button" onclick="zipcode()"><img src="../img/chk_post.gif" alt="우편번호찾기"/></button>
                                <input type="text" name="addr1" id="addr1" value="${sessionScope.sessUser.addr1}" placeholder="주소 검색"/>
                                <input type="text" name="addr2" id="addr2" value="${sessionScope.sessUser.addr2}" placeholder="상세주소 입력"/>
                            </td>
                        </tr>
                        <tr>
                            <td>회원탈퇴</td>
                            <td>
                                <button type="button" id="btnWithdraw" class="btnWithdraw">탈퇴하기</button>
                            </td>
                        </tr>
                    </table>
        
                    <div>
                        <a href="/Kmarket/member/login.do" class="btn btnCancel">취소</a>
                        <input type="submit" value="회원수정" class="btn btnRegister"/>
                    </div>
        
                </form>
        
            </section>
        </main>
<%@ include file="./_footer.jsp" %>