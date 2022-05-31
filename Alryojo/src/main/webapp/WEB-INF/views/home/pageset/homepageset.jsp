<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
    <!--  메인 페이지에서 사용할 css와 script  -->
    
    
    <!--  예시
     
    <script src="~~~"></script>
    
    <link rel="stylesheet" href="~~~~"/>
    
    -->
    
    
    
    <script>
    
    $(document).ready(function(){
    	
    	  setInterval(() => {
  	    	
  	        rolling();
  	
  	
  	               setTimeout(function () {
  	            	  $('#bannerarea img').css('left', '0px');
  	                  $('#bannerarea img').first().clone().appendTo('#bannerarea');
  	                  $('#bannerarea img').first().remove();
  	                  
  	              }, 700) 
  	
  	
  	
  	         }, 5000);
  	
  			$.ajax({
  				
  				url:'${pageContext.request.contextPath}/home/infos',
  				type:'POST',
  				contentType:'json',
  				success:function(data){
  					//console.log(data);
  					//console.log(data.memberCount);
  					
  					html='';
  					html+='<tr>\r\n';
  					html+='<td class="mx-3"><h1 class="count counter">'+data.memberCount+'</h1></td>\r\n';
  					html+='<td class="mx-3"><h1 class="count counter">'+data.postCount+'</h1></td>\r\n';
  					html+='<td class="mx-3"><h1 class="count counter">'+data.matchCount+'</h1></td>\r\n'
  					html+='</tr>\r\n';
  					$('#maininfo').prepend(html);
  					
  					
  
  				}
  			})
  			
  			
  			
  			
  			$.ajax({
				url:"${pageContext.request.contextPath}/loc/list",
				type:"POST",
				dataType:"json",
				success:function(data){
					
					
					
					$(data).each(function(index, item){
						var html='<div class="mx-3 my-2">\r\n';
							html+='<span class="d-none">'+item.loc_idx+'</span>\r\n';
							html+='<span class="locName btn btn-orange rounded-pill">'+item.loc_nm+'</span>\r\n';
							html+='</div>\r\n';
						
						
						$('#locsearcharea').append(html);
						
		 
					})
					
					
					
				},
				error:function(data){
					console.log('통신실패');
					console.log(data);
				}
			})
  	
  	
  	          var rolling = function (bannerarea_width) {
  	        	  var bannerarea_width=$('#bannerarea').first('img').width();
  	          	console.log(bannerarea_width);
  	              $('#bannerarea img').animate({ left: -bannerarea_width });
  	            
  	              
  	
  	          }  
    	
  	          
  	          
  	          $('#findbtn').on('click',function(){  	        	 
  	        	 location.href="${pageContext.request.contextPath}/post/search"
  	          })
  	          
  	          
  	          $('#catarea').on('click', '.cat-card', function(){
  	        	  console.log($(this).children('span').text());
  	        	  
				  $.ajax({
					url:'${pageContext.request.contextPath}/category/idx',
					type:'GET',
					data:{name:$(this).children('span').text()},
					context:this,
					success:function(data){
						
						var searchParams={
								cat : data,
								loc: 0,
								p : 1
						};
							
						console.log(searchParams);
						
						const queryString = new URLSearchParams(searchParams).toString();
							
						location.href='${pageContext.request.contextPath}/post/search?'+ queryString;
	
					} 
					
  	        	})
  	        	 
  	        	
  	        	  	  
  	          })
  	          
  	          
  	          $('#locsearcharea').on('click','.locName',function(){
  	        	  console.log($(this).prev().text());
  	        	  
  	        	$.ajax({
					url:'${pageContext.request.contextPath}/category/idx',
					type:'GET',
					data:{name:'취미'},
					context:this,
					success:function(data){
						
						var searchParams={
								cat : data,
								loc: $(this).prev().text(),
								p : 1
						};
							
						console.log(searchParams);
						
						const queryString = new URLSearchParams(searchParams).toString();
							
						location.href='${pageContext.request.contextPath}/post/search?'+ queryString;
	
					}
					
  	        	})	  
  	        	 
  	        	
  	        	  	  
  	          })
  	          
  	          
  	          
  	          
  	          
  	          
  	          
  	          <sec:authorize access="isAuthenticated()">
	  	          $('#writepostbtn').on('click',function(){
	  	        	  
	  	        	  //var type=$('input[name=type]').val();
	  	        	  //var idx=$('input[name=idx]').val();
	  	        	  
	  	        	  //console.log(type);
	  	        	  //console.log(idx);
	  	        	  // 타입이 멘티라면 여기서 바로 writeform 으로 보내고
	  	        	  // 멘토라면 ajax로 프로필이 있는지 한번 확인 후 보냄
	  	        	  <c:set var="type">
	  	        	  	<sec:authentication property="principal.m_type"/>
	  	        	  </c:set>
	  	        	  if(${type eq 'mentee'}){
	  	        		  location.href="${pageContext.request.contextPath}/post/write";
	  	        	  }else{
	  	        		  
	  	        	  
		  	        	  $.ajax({
		  	        		url: "${pageContext.request.contextPath}/post/checkProfile",
		  	        		type: 'post',
		  	        		
							success: function(data){
								
								
								if(data==''){
									
									if(confirm('프로필을 먼저 작성해야 합니다. 작성하시겠습니까?')){
											location.href="${pageContext.request.contextPath}/member/profile/register";	
									}
								
								}else{
									location.href="${pageContext.request.contextPath}/post/write";	
								}
								
								
							},
							error: function(data){
								console.log('통신오류');
								console.log(data);
							}
							
		  	        	  })
		  	        	  
		  	        	  
	  	              }
	  	        	  
	  	          })
  	          </sec:authorize>
  	          
  	          
  	         
  	          
  	          
  	         
    });
    
	  

    
    </script>