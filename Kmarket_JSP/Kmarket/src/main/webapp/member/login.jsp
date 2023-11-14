<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script>
/* 비밀번호 보기,숨기기 */
$(document).ready(function(){
    $('i').on('click',function(){
        $('input').toggleClass('active');
        if($('input').hasClass('active')){
            $(this).attr('class',"fa fa-eye-slash fa-lg") //눈모양+슬래시 추가
            .prev('input').attr('type',"text"); //비밀번호 그대로 노출
        }else{
            $(this).attr('class',"fa fa-eye fa-lg")
            .prev('input').attr('type','password');
        }
    });
});

</script>
        <main id="member">
            <div class="login">
                <nav>
                    <h1>로그인</h1>                    
                </nav>
                
				<form action="/Kmarket/member/login.do" method="post">

                    <table border="0">
                        <tr>
                            <td>아이디</td>
                            <td><input type="text" name="uid" placeholder="아이디 입력"></td>
                        </tr>
                        <tr>
                            <td>비밀번호</td>
                            <td>
                            <input type="password" name="pass" placeholder="비밀번호 입력">
                            <i class="fa fa-eye fa-lg"></i>
                            </td>
                        </tr>
                    </table>					
					<input type="submit" value="로그인" />
					<span>
                        <label><input type="checkbox" name="auto" value="1"/>자동 로그인</label>
						<a href="#">아이디찾기</a>
						<a href="#">비밀번호찾기</a>
						<a href="${ctxPath}/member/join.do">회원가입</a>
					</span>

                    <a href="https://safelogin.kr/sauth/regist?site_code=NA&sub_code=0" class="banner"><img src="${ctxPath}/member/img/member_login_banner.jpg" alt="1만원 할인 쿠폰 받기"></a>

				</form>
				<img src="${ctxPath}/member/img/member_certifi_logo.gif" alt="banner">
            </div>
        </main>        
<%@ include file="./_footer.jsp" %>