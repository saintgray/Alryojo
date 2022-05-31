<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>

	$(document).ready(function(){
		
		$('#chkquit').on('change',function(){
			
			
			if($('#chkquit').is(':checked')){
				
				$('#chkicon').attr('src','${pageContext.request.contextPath}/resources/files/server/icons/check_on.svg')
			}else{
				
				$('#chkicon').attr('src','${pageContext.request.contextPath}/resources/files/server/icons/check_off.svg')
			}
			
			
			
		})
		
		
		$('#quitbtn').on('click',function(){
			
			if($('#chkquit').is(':checked')){
				
				if(confirm('정말로 탈퇴하시겠습니까?')){
					
					$.ajax({
						url:'${pageContext.request.contextPath}/member/quit',
						type:'POST',
						success:function(data){
							if(data!=0){
								alert('탈퇴되었습니다');
								location.href='${pageContext.request.contextPath}/logout'
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
			}else{
				
				alert('탈퇴 안내를 확인 후 동의해주세요');
			}
		})
		
		
		
	})

</script>