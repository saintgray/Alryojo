<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	$(document).ready(function(){
		
		
		
		
		
		$('#findMe').on('click',function(){
			console.log($('#email').val().indexOf('@'));
			if($('#email').val().indexOf(' ')>=0 || $('#email').val().indexOf('@')==-1){
				showWarning($('#email'),'*올바른 이메일 주소를 입력하세요');
			}else if($('#email').val().trim().length==0){
				showWarning($('#email'),'*이메일을 입력해주세요');
			}else{
				var data={
					m_email_prefix:$('#email').val().substring(0,$('#email').val().indexOf('@')),
					m_email_suffix:$('#email').val().substring($('#email').val().indexOf('@')+1)
				}
				
				console.log(data);
					$.ajax({
						url:'${pageContext.request.contextPath}/chk_email',
						type:'POST',
						data:data,
						success:function(data){
							console.log(data);
							if(data=='ABLEREGISTER'){
								showWarning($('#email'),'*계정 정보가 없습니다 이메일을 다시 확인하세요');
							}else{
								
								
								$('#findaccountarea').append('<div class="text-center"><img src="${pageContext.request.contextPath}/resources/files/server/icons/loading/loading.svg" id="loading"></div>');
								
								
								$.ajax({
									url:'${pageContext.request.contextPath}/findaccount',
									type:'POST',
									data:{email:$('#email').val().trim()},
									success:function(data){
										$('#loading').parent().remove();
										if(data==1){
											alert('등록하신 계정으로 이메일을 발송했습니다');
										}else{
											alert('오류가 발생하였습니다 잠시 후 다시 시도하세요');
										}
										
									},
									error:function(data){
										console.log(data);
										alert('오류가 발생하였습니다 잠시 후 다시 시도하세요');
									}
								})
								
								
							}
						},
						error:function(data){
							alert('일시적인 오류가 발생하였습니다 잠시 후 다시 시도하세요');
							console.log(data);
						}
						
					})
					
			}
			
		})
		
		$('#email').on('focus',function(){
			$(this).removeClass('is-invalid');
			$('#fb').text(' ');
		})
		
	})
	
	
	function showWarning(dom, msg){
		$(dom).addClass('is-invalid');
		$('#fb').text(msg);
	}

</script>