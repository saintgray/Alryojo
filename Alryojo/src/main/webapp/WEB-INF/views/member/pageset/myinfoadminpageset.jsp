<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>



function editInfo(data){
	
	$.ajax({
		url:'${pageContext.request.contextPath}/admin/info/edit',
		type:'POST',
		data:data,
		contentType:false,
		processData:false,
		cash:false,
		enctype:'multipart/form-data',
		success:function(data){
			console.log('통신성공');
			if(data>=1){
				alert('정상적으로 수정되었습니다');
				location.href='${pageContext.request.contextPath}/myinfo';		
			}else{
				alert('오류가 발생하였습니다. 잠시 후 다시 시도하세요');
			}

		},
		error:function(data){
			
			console.log('통신실패');
			alert('오류가 발생하였습니다. 잠시 후 다시 시도하세요');
			
		}
	})
	
}



</script>