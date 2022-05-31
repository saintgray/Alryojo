<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
	$(document).ready(function(){
		
		$('.rqMember').on('click',function(){
			location.href='${pageContext.request.contextPath}/post/requestpost?postidx='+$(this).prev().text();
		})
		
	})
</script>