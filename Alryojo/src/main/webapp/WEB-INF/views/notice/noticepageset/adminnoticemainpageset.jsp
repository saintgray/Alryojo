<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>

$(document).ready(function(){
	
	$('#regnoticebtn').on('click',function() {
		location.href = "${pageContext.request.contextPath}/admin/notice/register?selectPage=${param.selectPage}&numOfNoticesPerPage=${param.numOfNoticesPerPage}";
	})
	
})

</script>