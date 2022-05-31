<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
$(document).ready(function(){

	$('#plus_icon').on('click',function(){
		
		location.href="${pageContext.request.contextPath}/member/profile/register"
	})       
	
	
})
</script>