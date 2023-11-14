<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script>
    	window.onload = function(){
    		
    		const agree1 = document.getElementsByName('agree1')[0];
    		const agree2 = document.getElementsByName('agree2')[0];
    		const agree3 = document.getElementsByName('agree3')[0];
    		const type = document.getElementById('type').value;
    		
    		const agree = document.querySelector('.agree'); 
    		agree.addEventListener('click', function(e){
    			e.preventDefault();
    			
    			if(!agree1.checked){
    				if(type == 'normal'){
    					alert('케이마켓 이용약관에 동의하셔야 합니다.');	
    				}else {
    					alert('판매자 이용약관에 동의하셔야 합니다.');
    				}
    				return;
    			}else if(!agree2.checked){
    				alert('전자금융거래 이용약관에 동의하셔야 합니다.');
    				return;
    			}else if(!agree3.checked){
    				alert('개인정보 수집동의에 동의하셔야 합니다.');
    				return;
    			}else {
    				if(type == 'normal'){
    					location.href = '/Kmarket/member/register.do?agree1='+agree1.value+'&agree2='+agree2.value+'&agree3='+agree3.value+'&type='+type;	
    				}else {
    					location.href = '/Kmarket/member/registerSeller.do?agree1='+agree1.value+'&agree2='+agree2.value+'&agree3='+agree3.value+'&type='+type;    					
    				}
    			}
    		});
    	}
</script>
        <main id="member">
            <div class="signup">
            	<form action="/Kmarket/member/SignUpController.do" method="post">
            	<input type="hidden" id="type" name="type" value="${type}"/>
				<nav>
					<h1>약관동의</h1>
				</nav>
				
				<c:choose>
				<c:when test="${type eq 'normal'}">
				<section>
					<h3><span class="essential">(필수)</span>케이마켓 이용약관</h3>
					<textarea class="terms" readonly>${dto.terms}</textarea>
					<label><input type="checkbox" name="agree1" />동의합니다.</label>

					<h3><span class="essential">(필수)</span>전자금융거래 이용약관</h3>
					<textarea class="financial" readonly>${dto.finance}</textarea>
					<label><input type="checkbox" name="agree2" />동의합니다.</label>

					<h3><span class="essential">(필수)</span>개인정보 수집동의</h3>
					<textarea class="privacy" readonly>${dto.privacy}</textarea>
					<label><input type="checkbox" name="agree3" />동의합니다.</label>
				</section>
				<section>
					<h3><span class="optional">(선택)</span>위치정보 이용약관</h3>
					<textarea class="location" readonly>${dto.location}</textarea>
					<label><input type="checkbox" name="agree4" />동의합니다.</label>
				</section>
				</c:when>
				<c:when test="${type eq 'seller'}">
					<section>
					<h3><span class="essential">(필수)</span>판매자 이용약관</h3>
					<textarea class="terms" readonly>${dto.tax}</textarea>
					<label><input type="checkbox" name="agree1" />동의합니다.</label>

					<h3><span class="essential">(필수)</span>전자금융거래 이용약관</h3>
					<textarea class="financial" readonly>${dto.finance}</textarea>
					<label><input type="checkbox" name="agree2" />동의합니다.</label>

					<h3><span class="essential">(필수)</span>개인정보 수집동의</h3>
					<textarea class="privacy" readonly>${dto.privacy}</textarea>
					<label><input type="checkbox" name="agree3" />동의합니다.</label>
				</section>
				</c:when>
				</c:choose>
				
				
				<div>
					
					<input type="button" class="agree" value="동의하기" />
					
				</div>
				</form>
            </div>
        </main>        
<%@ include file="./_footer.jsp" %>