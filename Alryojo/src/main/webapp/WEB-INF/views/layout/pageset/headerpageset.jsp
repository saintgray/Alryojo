<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>

	$(document).ready(function(){
		
		
		$('body').on('click','#register, .oc-reg',function(){
			location.href="${pageContext.request.contextPath}/termsform";
			
		})
		
		$('body').on('click','#logo',function(){
			location.href="${pageContext.request.contextPath}/";
		})
		$('#loginTab, .oc-login').on('click',function(){
			location.href="${pageContext.request.contextPath}/member/loginform";
		})
		
		 $('.logoutbtn').on('click',function(){
			 	
  	        	  
  	        	  location.href="${pageContext.request.contextPath}/logout";
  	     })
  	   	  
  	   	$('.changeType').on('click',function(){
       	  
       	  location.href="${pageContext.request.contextPath}/member/switchType"
         })
         
        $('#manageprofile').on('click',function(){
        	
        	
        	location.href="${pageContext.request.contextPath}/member/profile/main"
        })
        
        $('#myinfo').on('click',function(){
        	location.href="${pageContext.request.contextPath}/myinfo"
        })
        
        $('#chat, #oc-chat').on('click',function(){
        	location.href="${pageContext.request.contextPath}/chat/chatlist";
        	
        })
        $('#request, #oc-request').on('click',function(){
        	location.href="${pageContext.request.contextPath}/post/requestgroup";
        	
        })
	
		
	})

</script>