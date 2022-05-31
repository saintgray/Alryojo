<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/defaultpageset.jsp" %>
<script src="https://unpkg.com/gojs/release/go-debug.js"></script>
<title>분야관리</title>
<style>
	canvas{
		 position: relative !important;
		
	}
	
	#wrap{
		margin-top: 150px;
	}
	.parcat, .addCat, #manage, #toggle_overview, #catmanagemain{
		cursor: pointer;
	}
	#modifname{
		width:160px;
	}
	
	@media(min-width:576px){
	
		.modal-dialog-centered{
			max-width: 1500px;  
		    margin: 1.75rem auto;
	    }
	    
	    
	}
	
	
	@media(min-width:576px){
	
		#modal-dialog-category{
			max-width: 620px;  
		    margin: 1.75rem auto;
	    }
	    
	    
	}
	#overviewBody{
		padding:0;
	}

</style>
</head>
<body>

<div class="gw">



		
		
		
		<%@include file="/WEB-INF/views/layout/header.jsp" %>
				
				<div id="wrap">
				
				<div class="d-flex flex-wrap banner">
					<span class="ms-5 fw-bold fs-3" id="catmanagemain">분야 관리</span>
					<i class="bi bi-diagram-3-fill mx-2" data-bs-toggle="modal" data-bs-target="#overview" id="toggle_overview"></i>
				</div>
				
				
				<div id="catContainer">
				
				
				</div>
				<span id="depth" class="mx-2 fw-bold d-none" style="color:red">0</span>
				<!-- modal area -->
				<div class="modal fade" id="overview" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="staticBackdropLabel">조직도</h5>
						        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						      </div>
						      <div class="modal-body" id="overviewBody" style="width:100%; height:1500px; margin:20px auto">
						      
						      	<!-- 조직도 영역 -->
						       		
						      </div>
						     
						    </div>
						</div>	
					</div>
				
				<input type="text" id="thiscat" class="d-none">
		
		
		</div>
		
</div>	
		
		
		<!-- register modal -->
			<div class="modal fade" id="modal-regcat" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="registerLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered" id="modal-dialog-category">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="registerLabel"></h5>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">

				      	<div class="input-group">

				      			<i class="bi bi-layers input-group-text" style="background-color: #fff"></i>
				      			<input type="text" class="form-control" name="cat_nm" id="new-cat-name" placeholder="분야이름"/>

				      			<div class="invalid-feedback" id="w-msg">

				      			</div>



				      	</div>

				      </div>
				      <div class="modal-footer d-flex flex-wrap justify-content-around">
				        <button type="button" class="btn btn-grey" data-bs-dismiss="modal" id="close-modal">취소</button>
				        <button type="button" class="btn btn-general" id="confirmreg">등록</button>
				      </div>
				    </div>
				</div>	
			</div>
		
		
		



<%@include file="/WEB-INF/views/layout/footer.jsp" %>
</body>



<script>



$(document).ready(function(){
	
$('#catmanagemain').on('click',function(){
		
		location.href='${pageContext.request.contextPath}/admin/categorymanage';
	})
	
	$('#new-cat-name').on('focus',function(){
		$('#new-cat-name').val('').removeClass('is-invalid');	
	})
	
	$('#catContainer').on('click','.addCat',function(){
		console.log($('#mainNm').text());
		$('#registerLabel').text($('#mainNm').text());
	})
	
	$('.modal-footer').on('click','#close-modal',function(){
		$('#new-cat-name').val('');
		$('.btn-close').trigger('click');
	})
	$('.btn-close').on('click',function(){
		$('#new-cat-name').val('');
	})
	
	$('#new-cat-name').on('keydown',function(e){
		if(e.keyCode==13){
			$('#confirmreg').trigger('click');
		}
	})
	
	$('#confirmreg').on('click',function(){
		var parIdx=$('.mainIdx').text();
		var cat_nm=$('#new-cat-name').val().trim();
		
		if(cat_nm.length==0){
			$('#w-msg').text('*분야 이름을 입력해주세요');
			$('#new-cat-name').addClass('is-invalid');
		}else if(cat_nm.length<2){
			$('#w-msg').text('*분야이름은 최소 2글자여야 합니다');
			$('#new-cat-name').addClass('is-invalid');
		}else{
			
			if(confirm($('#new-cat-name').val()+' 을 '+$('#mainNm').text()+' 의 분야로 등록하시겠습니까?' )){
				console.log('테스트완료');
				
				data={
						cat_nm: cat_nm,
						parent_idx:parIdx
					};
				//console.log(parIdx);
				//console.log(cat_nm);
				$.ajax({
					url:'${pageContext.request.contextPath}/category/register',
					type:'POST',
					data:JSON.stringify(data),
					contentType: 'application/json; charset=utf-8',
					success:function(data){
						console.log(data.cat_idx);
						if(data.cat_idx==0){
							alert('오류가 발생했습니다 잠시 후 다시 시도하세요');
						}else{
							
							alert('정상적으로 등록되었습니다');
							$('.btn-close').trigger('click');
							
							/* html+='<div class="d-flex flex-row justify-content-around" id="catItems">\r\n'; */
							/* $(data.list).each(function(index,item){ */
								var html='';
								html+='<div class="d-flex">\r\n';
									html+='<span class="mx-2 itemIdx d-none">'+data.cat_idx+'</span>\r\n';
									
									
									
									
									html+='<div class="parcatarea">\r\n';
									
										html+='<span class="mx-2 parcat">'+data.cat_nm+'</span>\r\n';
										
									html+='</div>\r\n';
									
									html+='<span class="mx-2 d-none">'+0+'</span>\r\n';
									
									
									
									html+='<i class="bi bi-three-dots-vertical" id="manage" data-bs-toggle="dropdown" aria-expanded="false"></i>\r\n';
									html+='<ul class="dropdown-menu" aria-labelledby="manage">\r\n';
										html+='<li><button class="dropdown-item modif" type="button">수정</button></li>\r\n';
										html+='<li><button class="dropdown-item del" type="button">삭제</button></li>\r\n';
									html+='</ul>\r\n';
								
								html+='</div>\r\n';
								
								
							/* }) */
							/* html+='</div>'; */
							
							$('#catItems').append(html);
							
							
							
							
							
							
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
	
	
	

	
	
	
	var myDiagram = new go.Diagram("overviewBody",{
		"undoManager.isEnabled": true,
		"allowVerticalScroll": false,
		layout: new go.TreeLayout({ angle: 90, layerSpacing: 100 })
	})
	
		
	myDiagram.nodeTemplate =
		  new go.Node("Horizontal",{ background: "#142B6F" })
		    	.add(new go.TextBlock(
				      "Default Text", { margin: 12, stroke: "white", font: "bold 16px sans-serif" })
		      			.bind("text", "name"));
		      
		      
     myDiagram.linkTemplate =
  	  new go.Link({ routing: go.Link.Orthogonal, corner: 5 }).add( new go.Shape({ strokeWidth: 2, stroke: "#000000" }))
    
    
	var categories=[]
	
	
	$.ajax({
		
		url:'${pageContext.request.contextPath}/category/all',
		type:'GET',
		success:function(data){
			
			
			
			
			
			
			
			$(data).each(function(index,item){
				if(item.parent_idx==0){
					categories.push({key:item.child_idx, name:item.cat_nm});
				}else{
					categories.push({key:item.child_idx, parent:item.parent_idx, name:item.cat_nm});
				}
				
			})
			
			console.log(categories);
			
			myDiagram.model = new go.TreeModel(categories);
		},
		error:function(data){
			console.log(data);
		}
		
	})
	
	
	getChildCategory(null,1,'취미');
	
	
	
	
	
	
	$('#catContainer').on('click','#prev',function(){
		
		getSiblings($('#thiscat').val());
	})
	
	$('#catContainer').on('click','.parcat',function(){
		
		
		
		getChildCategory($(this).parent().prev().text(), $(this).parent().next().text(), $(this).text());
		
	})
	
	
	$('#catContainer').on('click','.modif',function(){
		
		var targetIndex=$(this).parent().parent().siblings('.parcatarea').prev().text();
		var originName=$(this).parent().parent().siblings('.parcatarea').children('.parcat').text();
		
		
		var html='';
		html+='<div class="d-flex">\r\n';
		html+='<input type="text" class="form-control" id="modifname" value="'+$(this).parent().parent().siblings('.parcatarea').children('.parcat').text()+'">\r\n';
		html+='<button type="button" class="btn btn-grey mx-1 cancelmodif">취소</button>\r\n';
		html+='<button type="button" class="btn btn-general mx-2 confirmmodif">수정</button>\r\n';
		html+='<span id="originname" class="d-none">'+originName+'</span>\r\n';
		html+='<span id="targetIdx"style="color:red" class="d-none">'+targetIndex+'</span>\r\n';
		html+='</div>\r\n';
		
		$(this).parent().parent().siblings('#manage').addClass('d-none');
		
		$(this).parent().parent().siblings('.parcatarea').html(html);
		
		console.log($(this).parent().parent().siblings('.parcatarea').children('.parcat'));
	})
	
	
	
	
	$('#catContainer').on('click','.confirmmodif',function(){
		
		if(confirm('정말로 수정하시겠습니까?')){
			
			
			$.ajax({
				
				url:'${pageContext.request.contextPath}/category/edit',
				type:'POST',
				data:{idx:$(this).siblings('#targetIdx').text(),
					  name:$(this).siblings('#modifname').val()},
				context:this,
				success:function(data){
					console.log(data);
					if(data==1){
						alert('정상적으로 수정되었습니다');
						
						var html='';
						html+='<span class="mx-2 parcat">'+$(this).siblings('#modifname').val()+'</span>\r\n';
						console.log($(this).parent().parent());
						$(this).parent().parent().siblings('#manage').removeClass('d-none');
						
						$(this).parent().parent().html(html);
						
						
						
						
					}else{
						alert('오류가 발생하였습니다 잠시 후 다시 시도하세요');
					}
				},
				error:function(data){
					console.log(data);
					alert('오류가 발생하였습니다 잠시 후 다시 시도하세요');
				}
					
				
			})
			
			
			
		}
		
	})
	
	
	
	
	$('#catContainer').on('click','.cancelmodif',function(){
		var html='';
		html+='<span class="mx-2 parcat">'+$(this).siblings('#originname').text()+'</span>\r\n';
		
		$(this).parent().parent().siblings('#manage').removeClass('d-none');
		
		$(this).parent().parent('.parcatarea').html(html);
	})
	
	
	
	// DELETE
	
	$('#catContainer').on('click','.del',function(){
		
		var targetIdx=$(this).parent().parent().siblings('.itemIdx').text();
		
		
		
		if(confirm('정말로 이 분야를 삭제하시겠습니까?')){
			
			$.ajax({
				url:'${pageContext.request.contextPath}/category/delete',
				type:'POST',
				data:{idx:targetIdx},
				context:this,
				success:function(data){
					console.log(data);
					if(data>=1){
						alert('정상적으로 삭제되었습니다');
						$(this).parent().parent().parent().remove();
						
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
		
	})
	
})




function getChildCategory(idx,childNode, name){
	
	
		
			var thisIdx=idx;
	
			if(idx==null){
				$.ajax({
					url:'${pageContext.request.contextPath}/category/idx',
					type:'GET',
					data:{name: '취미'},
					success:function(data){
						thisIdx=data;
						
						
						getList(data,name);
					}
					
				})
			}else{
				getList(idx,name);
			}
		
}

function getSiblings(idx){
	
	$.ajax({
		
		
		
		url:'${pageContext.request.contextPath}/category/siblings',
		type:'GET',
		data:{idx: idx},
		success:function(data){
			
			$('#depth').text(parseInt($('#depth').text())-1);
			
			
			console.log('통신성공');
			html='';
			
			
			html+='<h1 class="banner text-center">\r\n';
			html+='<span id="mainNm">'+data.parentInfo.cat_nm+'</span>\r\n';
			html+='<span class="mainIdx d-none">'+data.parentInfo.cat_idx+'</span>\r\n';
			
			html+='<i class="bi bi-plus-circle addCat" data-bs-toggle="modal" data-bs-target="#modal-regcat"></i>\r\n';
			html+='</h1>\r\n';
			
			
			html+='</h1>\r\n';
			html+='<div class="d-flex flex-row justify-content-around" id="catItems">\r\n';
			$(data.list).each(function(index,item){
				html+='<div class="d-flex">\r\n';
					html+='<span class="mx-2 itemIdx d-none">'+item.cat_idx+'</span>\r\n';
					
					
					
					
					html+='<div class="parcatarea">\r\n';
					
						html+='<span class="mx-2 parcat">'+item.cat_nm+'</span>\r\n';
						
					html+='</div>\r\n';
					
					html+='<span class="mx-2 d-none">'+item.childNodeCount+'</span>\r\n';
					
					
					
					html+='<i class="bi bi-three-dots-vertical" id="manage" data-bs-toggle="dropdown" aria-expanded="false"></i>\r\n';
					html+='<ul class="dropdown-menu" aria-labelledby="manage">\r\n';
						html+='<li><button class="dropdown-item modif" type="button">수정</button></li>\r\n';
						html+='<li><button class="dropdown-item del" type="button">삭제</button></li>\r\n';
					html+='</ul>\r\n';
				
				html+='</div>\r\n';
				
				
			})
			html+='</div>';
			
			if(parseInt($('#depth').text())>1){
				html+='<div class="d-flex flex-row justify-content-center">\r\n';
				html+='<button type="button" id="prev" class="btn btn-grey my-5">이전</button>\r\n';
				html+='</div>\r\n';
			}
			
			html+='</div>\r\n';
			
			$('#catContainer').html(html);
			$('#thiscat').val(data.parentInfo.cat_idx);
			
			
		},
		error:function(data){
			console.log('통신실패');
			console.log(data);
			
		}
	})	
	
	
	
	
}


function getList(idx,name){
	
	

	$.ajax({
			
			
			
			url:'${pageContext.request.contextPath}/category/list',
			type:'GET',
			data:{interest: idx},
			success:function(data){
				console.log('통신성공');
				
				
				$('#depth').text(parseInt($('#depth').text())+1);
				
				
				
				html='';
				
				
				
				html+='<h1 class="banner text-center">\r\n';
				html+='<span id="mainNm">'+name+'</span>\r\n';
				html+='<span class="mainIdx d-none">'+idx+'</span>\r\n';
				
				html+='<i class="bi bi-plus-circle addCat" data-bs-toggle="modal" data-bs-target="#modal-regcat"></i>\r\n';
				
				
				html+='</h1>\r\n';
				html+='<div class="d-flex flex-row justify-content-around" id="catItems">\r\n';
				$(data).each(function(index,item){
					html+='<div class="d-flex">\r\n';
						html+='<span class="mx-2 itemIdx d-none">'+item.cat_idx+'</span>\r\n';
						
						
						
						html+='<div class="parcatarea">\r\n';
						
							html+='<span class="mx-2 parcat">'+item.cat_nm+'</span>\r\n';
						
						html+='</div>\r\n';
						
						
						
						html+='<span class="mx-2 d-none">'+item.childNodeCount+'</span>\r\n';
						
						
						html+='<i class="bi bi-three-dots-vertical" id="manage" data-bs-toggle="dropdown" aria-expanded="false"></i>\r\n';
						html+='<ul class="dropdown-menu" aria-labelledby="manage">\r\n';
							html+='<li><button class="dropdown-item modif" type="button">수정</button></li>\r\n';
							html+='<li><button class="dropdown-item del" type="button">삭제</button></li>\r\n';
						html+='</ul>\r\n';
					
					
					
					html+='</div>\r\n';
				})
				html+='</div>';
				
				
				if(parseInt($('#depth').text())>1){
					html+='<div class="d-flex flex-row justify-content-center">\r\n';
					html+='<button type="button" id="prev" class="btn btn-grey my-5">이전</button>\r\n';
					html+='</div>\r\n';
				}
				
				
				
				$('#catContainer').html(html);
				$('#thiscat').val(idx);
				
			},
			error:function(data){
				
			}
		})	
	
}
</script>





</html>