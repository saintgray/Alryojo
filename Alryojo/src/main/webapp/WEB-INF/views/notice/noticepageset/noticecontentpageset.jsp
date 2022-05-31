<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>

    $(
  		function(){
  			
  			$('#listbtn').click(
		      	function(){
		      	
		      	
		      	history.go(-1);
		      	})
  			
  			$('#editbtn').click(function(){
  				// 수정하는 폼을 보여줌
  				// 공지사항의 수정은 관리자만 할 수 있다
  				location.href="${pageContext.request.contextPath}/admin/notice/edit?notice_idx=${content.notice_idx}";
  				
  			})
  			
  			$('#delbtn').click(function(){
  				
  				if(!confirm("삭제 하시겠습니까?")){
  					return false;
  				}else{
  					
  					var data={
  							notice_idx:${content.notice_idx}
  					}
  					
  					$.ajax({
  						
  						url: '${pageContext.request.contextPath}/admin/notice/delete',
  						type: 'POST',
  						data: data,
  						success:function(data){
  							if(data==1){
  								alert('정상적으로 삭제되었습니다.');
  								
  								history.go(-1);
  							}else if(data==0){
  								alert('오류가 발생했습니다. 잠시 후 다시 시도하세요');
  							}
  						},
  						error:function(data){
  							console.log(data);
  						}
  						
  					})
  				
  				}

  			})
  		})

</script>