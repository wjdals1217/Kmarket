/**
 * 
 */
// 이메일 인증
$(function(){
	
	let preventDoubleClick = false;
	
	$('#btnEmailCode').click(function(){
		
		const division  = $('input[name=division]').val();
		const uid   = $('input[name=uid]').val();
		const name  = $('input[name=name]').val();
		const email = $('input[name=km_email], input[name=kms_email]').val() + '@' +$('input[name=km_email2]').val();
		
		console.log('division : ' + division);
		console.log('uid : ' + uid);
		console.log('name : ' + name);
		console.log('email : ' + email);
		
		const jsonData = {
			"division": division,
			"uid": uid,
			"name": name,
			"email": email
		};
		 
		console.log('jsonData : ' + jsonData);
		
		if(preventDoubleClick){
			return;
		}
		
		preventDoubleClick = true;
		$('.resultEmail').text('인증코드 전송 중 입니다. 잠시만 기다리세요...');
		$('.resultEmailForId').text('인증코드 전송 중 입니다. 잠시만 기다리세요...');
		$('.resultEmailForPass').text('인증코드 전송 중 입니다. 잠시만 기다리세요...');
		
		setTimeout(function(){
			
			$.ajax({
				url:'/Kmarket/member/authEmail.do',
				type: 'GET',
				data: jsonData,
				dataType: 'json',
				success: function(data){
					console.log(data);
					console.log(data.status);
					
					if(data.result > 0){						
						$('.resultEmail').css('color', 'red').text('이미 사용중인 이메일 입니다.');
						isEmailOk = false;
						
						if(data.status > 0){
							$('.resultEmailForId').css('color', 'green').text('이메일을 확인 후 인증코드를 입력하세요.');
							$('.resultEmailForPass').css('color', 'green').text('이메일을 확인 후 인증코드를 입력하세요.');
							$('input[name=auth]').prop('disabled', false);
						}else{
							$('.resultEmailForId').css('color', 'red').text('인증코드 전송이 실패했습니다. 잠시후 다시 시도하십시요.');
							$('.resultEmailForPass').css('color', 'red').text('인증코드 전송이 실패했습니다. 잠시후 다시 시도하십시요.');
						}
						
					}else{
						if(data.status > 0){
							$('.resultEmail').css('color', 'green').text('이메일을 확인 후 인증코드를 입력하세요.');
							$('.auth').show();
							$('input[name=km_email], input[name=kms_email]').attr('readonly', true);
						}else{
							$('.resultEmail').css('color', 'red').text('인증코드 전송이 실패했습니다. 잠시후 다시 시도하십시요.');
							$('.resultEmailForId').css('color', 'red').text('해당하는 사용자, 이메일이 일치하지 않습니다.');
							$('.resultEmailForPass').css('color', 'red').text('해당하는 아이디, 이메일이 일치하지 않습니다.');
						}
						
					}
					
					preventDoubleClick = false;
				}				
			});
		}, 1000);
	});
	
	$('#btnEmailAuth').click(function(){
		const code = $('input[name=auth]').val();
		const jsonData = {
			"code": code
		};
				
		$.ajax({
			url: '/Kmarket/member/authEmail.do',
			type: 'POST',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				
				console.log(data);
				
				if(data.result > 0 ){
					$('.resultEmail').css('color', 'green').text('이메일 인증이 완료 되었습니다.');
					$('.resultEmailForId').css('color', 'green').text('이메일 인증이 완료 되었습니다.');
					$('.resultEmailForPass').css('color', 'green').text('이메일 인증이 완료 되었습니다.');
					isEmailOk = true;
				}else{
					$('.resultEmail').css('color', 'red').text('이메일 인증이 실패 했습니다.다시 시도하십시요.');
					$('.resultEmailForId').css('color', 'red').text('이메일 인증이 실패 했습니다.다시 시도하십시요.');
					$('.resultEmailForPass').css('color', 'red').text('이메일 인증이 실패 했습니다.다시 시도하십시요.');
					isEmailOk = false;
				}
				
			}
		});
	});
});