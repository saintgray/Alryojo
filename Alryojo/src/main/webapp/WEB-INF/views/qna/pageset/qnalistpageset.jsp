<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
$(document).ready(function(){
	
	$('.reply').on('click',function(){
		
		var replyIdx=$(this).prev().text();
		
		location.href='${pageContext.request.contextPath}/reply/content?reply_idx='+replyIdx;
		
		
	})
	
	$('#sm_qna').css('color','#FCA106');
	
	
	$('#prev').on('click',function(){
		
		if(${pageView.curPageIndex}>0){
			let selectPage=${pageView.numOfPagePerPage*(pageView.curPageIndex-1)+1};
			
			location.href='${pageContext.request.contextPath}/qna/list?selectPage='+selectPage+'&numOfQnaPerPage=5';	
		}
			
	})
	
	
	$('#next').on('click',function(){
		
		//console.log(Math.floor(${pageView.totalPage/10}));
		//console.log(${pageView.curPageIndex});
		if(Math.floor(${pageView.totalPage/10}) > ${pageView.curPageIndex}){
			let selectPage=${pageView.numOfPagePerPage*(pageView.curPageIndex+1)+1};
			location.href='${pageContext.request.contextPath}/qna/list?selectPage='+selectPage+'&numOfQnaPerPage=5';	
		}
		
		
	})
	
	
	
	$('#qnaRegBtn').on('click',function(){
		
		location.href='${pageContext.request.contextPath}/member/qna/register';
		
	})
	
	
})




</script>
