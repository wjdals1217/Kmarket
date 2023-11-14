<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> 
<script src="${ctxPath}/js/zipcode.js"></script>
<script src="${ctxPath}/js/validation.js"></script>
<script src="${ctxPath}/js/checkMember.js"></script>
<script src="${ctxPath}/js/authEmail.js"></script>
<script>
const BizNumAutoHyphen = (target) => {
	 target.value = target.value
	   .replace(/[^0-9]/, '') //숫자가 아닌 문자를 모두 제거
	  .replace(/^(\d{0,3})(\d{0,2})(\d{0,5})$/g, "$1-$2-$3") //자동(000-00-00000) 하이픈 형식으로 변환
	  .replace(/(\-{1,2})$/g, ""); //번호입력 시 자동 하이픈 반복에서 연속된 하이픈을 모두 제거 
	}

const TelAutoHyphen = (target) => {
	 target.value = target.value
	   .replace(/[^0-9]/g, '') //숫자가 아닌 문자를 모두 제거
	   .replace(/^(\d{2,3})(\d{3})(\d{4})$/, `$1-$2-$3`); //자동(00(0)-000-0000) 하이픈 형식으로 변환
	   //모두 입력 후 하이픈 생성
	}
	
const FaxAutoHyphen = (target) => {
	 target.value = target.value
	   .replace(/[^0-9]/g, '') //숫자가 아닌 문자를 모두 제거
	   .replace(/^(\d{2,3})(\d{3})(\d{4})$/, `$1-$2-$3`); //자동(00(0)-000-0000) 하이픈 형식으로 변환
	   //모두 입력 후 하이픈 생성
	}
	
const HpAutoHyphen = (target) => {
	 target.value = target.value
	   .replace(/[^0-9]/g, '') //숫자가 아닌 문자를 모두 제거
	  .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3") //자동(000-0000-0000) 하이픈 형식으로 변환
	  .replace(/(\-{1,2})$/g, ""); //번호입력 시 자동 하이픈 반복에서 연속된 하이픈을 모두 제거 
	}
</script>
        <main id="member">
            <div class="registerSeller">
				<nav>
					<h1>판매자 회원가입</h1>
				</nav>
                
				<form id="formUser" action="/Kmarket/member/registerSeller.do?type=seller" method="POST">
				<input type="hidden" name="division" value="REGISTER"/> <!-- 이메일 인증 - 회원가입, 아이디 찾기, 비밀번호 찾기 구분짓기 위해서 -->
				<input type="hidden" name="type" value="${type}"/>
					<section>
						<table>
							<caption>필수 정보입력</caption>
							<tr>
								<th><span class="essential">*</span>아이디</th>
								<td>
								<input type="text" name="km_uid" placeholder="아이디를 입력" required /> 
								<button type="button" id="btnCheckUid"><img src="./img/chk_id.gif" alt="중복확인"/></button>
								<span class="msgId">&nbsp;영문, 숫자로 4~12자까지 설정해 주세요.</span>
								</td>
							</tr>
							<tr>
								<th><span class="essential">*</span>비밀번호</th>
								<td><input type="password" name="km_pass1"
									placeholder="비밀번호를 입력" required /> <span class="msgPass">&nbsp;영문,
										숫자, 특수문자를 조합하여 8~12자까지 설정해 주세요.</span></td>
							</tr>
							<tr>
								<th><span class="essential">*</span>비밀번호확인</th>
								<td><input type="password" name="km_pass2"
									placeholder="비밀번호를 확인" required />&nbsp; <span class="msgPass2">&nbsp;비밀번호
										재입력</span></td>
							</tr>
						</table>
					</section>

					<section>
						<table>
							<caption>판매자 정보입력</caption>
							<tr>
								<th><span class="essential">*</span>회사명</th>
								<td><input type="text" oninput="ComAutoChange(this)" name="kms_company"
									placeholder="회사명 입력" required />&nbsp; <span class="msgCompany">&nbsp;(주)포함
										입력, 예) (주)케이마켓</span></td>
							</tr>
							<tr>
								<th><span class="essential">*</span>대표자</th>
								<td>
								<input type="text" name="kms_ceo" placeholder="대표자 입력" required />&nbsp;
								<span class="msgName">&nbsp;</span>
								</td>
							</tr>
							<tr>
								<th><span class="essential">*</span>사업자등록번호</th>
								<td><input type="text" oninput="BizNumAutoHyphen(this)" name="kms_corp_reg" maxlength="12"
									placeholder="사업자등록번호 입력" required />&nbsp; <span class="msgCorp">&nbsp;</span></td>

							</tr>
							<tr>
								<th><span class="essential">*</span>통신판매업신고 번호</th>
								<td><input type="text" name="kms_online_reg"
									placeholder="통신판매업신고 입력" required /> <span class="msgOnline">&nbsp;-
										표시 포함, 예) 강남-12345, 제 1-01-23-4567호, 2017-경기성남-0011</span></td>
							</tr>
							<tr>
								<th><span class="essential">*</span>전화번호</th>
								<td><input type="text" oninput="TelAutoHyphen(this)" name="kms_tel" placeholder="전화번호 입력" maxlength="12"
									required />&nbsp; <span class="msgTel">&nbsp;
										지역번호 포함</span></td>
							</tr>
							<tr>
								<th><span class="essential">*</span>팩스번호</th>
								<td><input type="text" oninput="FaxAutoHyphen(this)" name="kms_fax" placeholder="팩스번호 입력" maxlength="12"
									required />&nbsp; <span class="msgFax">&nbsp;
										지역번호 포함</span></td>
							</tr>
							<tr>
								<th><span class="essential">*</span>EMAIL</th>
								<td>
								<input type="email" name="kms_email" placeholder="이메일 입력" required />
								<button type="button" id="btnEmailCode"><img src="./img/chk_auth.gif" alt="인증번호 받기"/></button>
								<span class="resultEmail"></span>
								<div class="auth">
                                    <input type="text" name="auth" placeholder="인증번호 입력"/>
                                    <button type="button" id="btnEmailAuth"><img src="./img/chk_confirm.gif" alt="확인"/></button>
                                </div>
								</td>
							</tr>
							<tr class="addr">
								<th>회사주소</th>
								<td>
									<div>
										<input type="text" name="zip" id="kms_zip" placeholder="우편번호" readonly />
										<button type="button" onclick="zipcode()"><img src="./img/chk_post.gif" alt="우편번호찾기"/></button>
									</div>
									<div>
										<input type="text" name="addr1" id="kms_addr1" size="50"
											placeholder="주소를 검색하세요." readonly />
									</div>
									<div>
										<input type="text" name="addr2" id="kms_addr2" size="50"
											placeholder="상세주소를 입력하세요." />
									</div>
								</td>
							</tr>
						</table>
					</section>
					<section>
						<table>
							<caption>담당자 정보입력</caption>
							<tr>
								<th><span class="essential">*</span>이름</th>
								<td><input type="text" name="km_name" placeholder="이름을 입력" required />&nbsp;
								<span class="msgName">&nbsp;</span>
								</td>
							</tr>
							<tr>
								<th><span class="essential">*</span>휴대폰</th>
								<td><input type="text" oninput="HpAutoHyphen(this)" name="km_hp" maxlength="13"
									placeholder="휴대폰번호 입력" required />&nbsp; <span class="msgHp">&nbsp;</span></td>
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