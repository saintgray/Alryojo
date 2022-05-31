<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>

	$(document).ready(function(){
		
		$('#loginbtn').on('click',function(){
			var invailed=false;
			if($('#m_email').val().length==0){
				$('#w_email').text('*이메일을 입력하세요').css('visibility','visible');
				invailed=true;
				
			}else if($('#m_email').val().indexOf('@')<0){
				$('#w_email').text('*올바른 이메일 형식을 입력하세요').css('visibility','visible');
				invailed=true;
			}
			if($('#m_pw').val().length==0){
				$('#w_pw').text('*비밀번호를 입력하세요').css('visibility','visible');
				invailed=true;
			}
			
			
			
			
			if(!invailed){
				$('#insertarea').append($('<img src="${pageContext.request.contextPath}/resources/files/server/icons/loading/loading.svg" id="loading">'));
				$('form').submit();
			}
		})
		
		
		$('#m_email, #m_pw').on('focus',function(){
			$(this).parent().next().css('visibility','hidden');
			$('#auth_warning').text('');
		})
		
		
		$('input').on('keydown',function(e){
			
			
			if(e.keyCode==13){
				
				
				$('#loginbtn').trigger('click');
			}
		})
		
		// forgetMe 
		///////////////////////////
		$('#forgetMe').on('click',function(){
			location.href="${pageContext.request.contextPath}/findaccount";	
		})
		
	})
</script>