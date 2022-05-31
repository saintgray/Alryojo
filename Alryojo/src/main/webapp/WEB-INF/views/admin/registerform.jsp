<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<%@ include file="/WEB-INF/views/defaultpageset.jsp" %>
<style>
	#wrap{
	
		margin-top:150px;
		width: 420px;
		padding: 10px;
	}
	
	.input-group-text{
		background-color: #FFF;
	}

</style>
<title>관리자 등록</title>
</head>
<body>




	<div class="gw">
	
	<%@include file="/WEB-INF/views/layout/header.jsp" %>
	
		<div id="wrap" class="container">
				
				
				<h1 class="banner">관리자 등록</h1>
				
				<div class="input-group my-3">
					<i class="bi bi-person-circle input-group-text"></i>
					<input type="text" class="form-control" name="m_email_prefix" placeholder="manager" id="prefix">
					<span class="input-group-text">@</span>
					<input type="text" class="form-control" name="m_email_suffix" placeholder="aljdream.com" id="suffix">
				</div>
				
				<div class="invalid-feedback"></div>
				
				
				<div class="input-group my-3">
					<i class="bi bi-key input-group-text"></i>
					<input type="password" class="form-control" placeholder="password" id="pw">
					
				</div>
				
				<div class="invalid-feedback"></div>
				
				
				<div class="input-group my-3">
					<i class="bi bi-key input-group-text"></i>
					<input type="password" class="form-control" placeholder="confirm-password" id="repw">
					
				</div>
				
				<div class="invalid-feedback"></div>
				
				
				<div class="text-center">
					<button type="button" class="btn btn-orange" id="regBtn">등록</button>
				</div>		
		
		</div>
	
	
	</div>
	
	<%@include file="/WEB-INF/views/layout/footer.jsp" %>
	
	
</body>

<script>

$(document).ready(function(){
	
	$('input').on('focus',function(){
		if($(this).attr('id')=='prefix' || $(this).attr('id')=='suffix'){
			$('#prefix,#suffix').removeClass('is-invalid');
			$(this).parent().next().css('display','none');
		}else{
			$(this).removeClass('is-invalid');
			$(this).parent().next().css('display','none');	
		}
		
		
	})
	
	$('#regBtn').on('click',function(){
		if($('#prefix').val().trim().length==0 || $('#suffix').val().trim().length==0){
			warning($('#prefix,#suffix'),'* 필수 입력사항입니다');
		}else if(!vailedDomain($('#suffix').val().trim())){
			warning($('#suffix'),'* 관리자는 aljdream.com 도메인명을 가져야 합니다');		
		}else if($('#pw').val().trim().length==0){
			warning($('#pw'),'* 필수 입력사항입니다');
		}else if($('#pw').val().indexOf(' ')>=0){
			warning($('#pw'),'* 공백은 사용할 수 없습니다');
		}else if($('#pw').val().length<8){
			warning($('#pw'),'* 비밀번호는 8자리 이상이어야 합니다.')
		}else if($('#repw').val().trim()!=$('#pw').val().trim()){
			warning($('#repw'),'* 비밀번호가 일치하지 않습니다');
		}else{
			
			if(confirm('등록하시겠습니까?')){

				insertdata={
						m_email_prefix: $('#prefix').val(),
						m_email_suffix: $('#suffix').val(),
						admin_password:$('#pw').val()
				}
				
				$.ajax({
					url:'${pageContext.request.contextPath}/superadmin/admin/register',
					type:'POST',
					data:insertdata,
					success:function(data){
						if(data==1){
							alert('등록완료');
							location.href="${pageContext.request.contextPath}/superadmin/admin/register";
						}else{
							
							alert('오류가 발생했습니다 잠시 후 다시 시도하세요');
						}
					},
					error:function(data){
						console.log(data);
						alert('오류가 발생했습니다 잠시 후 다시 시도하세요');
					}
					
					
				})
				
				
				
			}
			
		}
	})
	
})


function vailedDomain(email){
	
	return email=='aljdream.com';
	
}

function warning(dom, msg){
	$(dom).addClass('is-invalid');
	$(dom).parent().next().text(msg).css('display','block');
}

</script>
</html>