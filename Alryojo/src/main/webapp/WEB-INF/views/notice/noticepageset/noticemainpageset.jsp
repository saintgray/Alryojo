<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
$(document).ready(function(){
	
	$('#sm_ntc').css('color','#FCA106');
	
	
	$('#searchBtn').on('click',function(){
		var keyword=$('#searchbararea').val();
		location.href='${pageContext.request.contextPath}/notice?selectPage=1&numOfNoticesPerPage=5&keyword='+keyword;
		
	})
	$('.pageNum').on('click',function(){
		var keyword=$('#searchbararea').val();
		location.href='${pageContext.request.contextPath}/notice?selectPage='+$(this).text()+'&numOfNoticesPerPage=5&keyword='+keyword;
	})
	
	
	$('#searchbararea').on('keydown',function(e){
		if(e.keyCode==13){
			
			$('#searchBtn').trigger('click');
		}
		
	})

})
</script>


