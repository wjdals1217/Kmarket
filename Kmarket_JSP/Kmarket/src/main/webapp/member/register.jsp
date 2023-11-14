<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> 
<script src="${ctxPath}/js/zipcode.js"></script>
<script src="${ctxPath}/js/validation.js"></script>
<script src="${ctxPath}/js/checkMember.js"></script>
<script src="${ctxPath}/js/authEmail.js"></script>
<script>
const HpAutoHyphen = (target) => {
	 target.value = target.value
	   .replace(/[^0-9]/g, '') //숫자가 아닌 문자를 모두 제거
	  .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3") //자동(000-0000-0000) 하이픈 형식으로 변환
	  .replace(/(\-{1,2})$/g, ""); //번호입력 시 자동 하이픈 반복에서 연속된 하이픈을 모두 제거 
}
</script>
<script>
$("input[name='km_gender']").change(function(){
	var checked = $("input[name='km_gender']:checked").val();
});	
</script>
<script>
$(document).ready(function() {
    // 이메일 입력방식 선택
    $('#selectEmail').change(function() {
        var selectedOption = $("#selectEmail option:selected").val();

        if (selectedOption === '1') { // 직접입력일 경우
            $("#km_email2").prop("disabled", false).val('');
        } else { // 직접입력이 아닐 경우
            $("#km_email2").prop("disabled", true).val(selectedOption);
        }
    });
});
</script>
        <main id="member">
            <div class="register">
                <nav>
                    <h1>일반 회원가입</h1>
                </nav>
				<form id="formUser" action="/Kmarket/member/register.do?type=normal" method="POST">
				<input type="hidden" name="division" value="REGISTER"/>
				<input type="hidden" name="type" value="${type}"/>
					<section>
						<table>
							<caption>필수 정보입력</caption>
							<tr>
								<th><span class="essential">*</span>아이디</th>
								<td>
								<input type="text" name="km_uid" placeholder="아이디를 입력" required/> 
								<button type="button" id="btnCheckUid"><img src="./img/chk_id.gif" alt="중복확인"/></button>
								<span class="msgId">&nbsp;영문, 숫자로 4~12자까지 설정해 주세요.</span>
								
								</td>
							</tr>
							<tr>
								<th><span class="essential">*</span>비밀번호</th>
								<td><input type="password" name="km_pass1" placeholder="비밀번호를 입력"
									required /> <span class="msgPass">&nbsp;영문, 숫자, 특수문자를 조합하여
										8~12자까지 설정해 주세요.</span></td>
							</tr>
							<tr>
								<th><span class="essential">*</span>비밀번호확인</th>
								<td><input type="password" name="km_pass2" placeholder="비밀번호를 확인"
									required />&nbsp; <span class="msgPass2">&nbsp;비밀번호 재입력</span></td>
							</tr>
						</table>
					</section>
					<section>
						<table>
							<caption>기본 정보입력</caption>
							<tr>
								<th><span class="essential">*</span>이름</th>
								<td>
								<input type="text" name="km_name" placeholder="이름을 입력" required />&nbsp;
								<span class="msgName"></span>
								</td>
							</tr>
							<tr>
								<th><span class="essential">*</span>성별</th>
								<td>
								<label><input type="radio" name="km_gender" value="1" checked="checked">&nbsp;남</label> 
								<label><input type="radio" name="km_gender" value="2">&nbsp;여</label></td>
							</tr>
							<tr>
								<th><span class="essential">*</span>EMAIL</th>
								<td>
								 <input type="text" name="km_email" id="km_email" placeholder="이메일 입력" required />&nbsp;@
                            	 <input type="text" name="km_email2" id="km_email2" placeholder="도메인 입력" required />
								<select name="selectEmail" id="selectEmail">
									<option value="1" selected>직접입력</option>
									<option value="naver.com">naver.com</option>
									<option value="daum.net">daum.net</option>
									<option value="hotmail.com">hotmail.com</option>
									<option value="nate.com">nate.com</option>
									<option value="yahoo.co.kr">yahoo.co.kr</option>
									<option value="empas.com">empas.com</option>
									<option value="dreamwiz.com">dreamwiz.com</option>
									<option value="freechal.com">freechal.com</option>
									<option value="lycos.co.kr">lycos.co.kr</option>
									<option value="korea.com">korea.com</option>
									<option value="gmail.com">gmail.com</option>
									<option value="hanmir.com">hanmir.com</option>
									<option value="paran.com">paran.com</option>
								</select>
								<button type="button" id="btnEmailCode"><img src="./img/chk_auth.gif" alt="인증번호 받기"/></button><br>
								<span class="resultEmail"></span>
								<div class="auth">
                                    <input type="text" name="auth" placeholder="인증번호 입력" required/>
                                    <button type="button" id="btnEmailAuth"><img src="./img/chk_confirm.gif" alt="확인"/></button>
                                </div>
								</td>
							</tr>
							<tr>
								<th><span class="essential">*</span>휴대폰</th>
								<td><input type="text" oninput="HpAutoHyphen(this)" name="km_hp" maxlength="13"
									placeholder="휴대폰번호 입력" required />&nbsp; <span class="msgHp"> 
										13자리를 입력하세요.</span></td>
							</tr>
							<tr class="addr">
								<th>주소</th>
								<td>
									<div>
										<input type="text" name="zip" id="km_zip" placeholder="우편번호" readonly />
										<button type="button" onclick="zipcode()"><img src="./img/chk_post.gif" alt="우편번호찾기"/></button>
									</div>
									<div>
										<input type="text" name="addr1" id="km_addr1" size="50"
											placeholder="주소를 검색하세요." readonly />
									</div>
									<div>
										<input type="text" name="addr2" id="km_addr2" size="50"
											placeholder="상세주소를 입력하세요." />
									</div>
								</td>
							</tr>
						</table>
		
					</section>
					<div>
						<input type="submit" class="join" value="회원가입" />
					</div>
				</form>
            </div>
        </main>        
<%@ include file="./_footer.jsp" %>