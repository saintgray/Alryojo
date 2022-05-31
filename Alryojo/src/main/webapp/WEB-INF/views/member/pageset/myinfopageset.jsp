<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>




$(document).ready(function(){
		
		
		$('#editphoto').on('change',function(e){
			var reader = new FileReader();
			var photo=e.target.files[0];

			
			
			if(!(photo.type.match("image/*"))){
				alert('이미지만 등록하실 수 있습니다.');
			}else{
				reader.readAsDataURL(e.target.files[0]);	
			

				$(reader).on('load',function(e){
					
					
					
					$('#myprofile').attr('src',e.target.result);
					
					setTimeout(function(){
						if(confirm('프로필 사진을 수정하시겠습니까?')){
							// aJax codes
							var formData=new FormData();
							formData.append('photo',$('#editphoto')[0].files[0]);
							editInfo(formData);
							/////////////
						}else{
								$('#myprofile').attr('src','https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/member${info.m_photo}');
								$('#editprofile').val('');
						}
					},200);
					
					
				})
			
			
			}		
			
		})
		
		
		$('#namearea').on('click','#editnameicon',function(){
			var html='';
			html+='<input type="text" class="form-control" placeholder="별명" id="nameinput" value="${info.m_nm}">\r\n';
			html+='<button type="button" class="btn btn-grey ms-1" id="editiconbtn">수정</button>\r\n';
			html+='<button type="button" class="btn btn-danger" id="canceleditnamebtn">취소</button>\r\n';
			$('#namearea').html(html);	
		})
		
		$('#namearea').on('click','#editiconbtn',function(){
			// 별명 수정 ajax 
			
			if(confirm('정말로 수정하시겠습니까?')){
				console.log($('#nameinput').val());
				var formData= new FormData();
				formData.append('m_nm',$('#nameinput').val());
				editInfo(formData);
			}
			
		})
		
		$('#namearea').on('click','#canceleditnamebtn',function(){
			$('#namearea').html('<h3 class="fw-normal fs-4">${info.m_nm}<i class="bi bi-wrench mx-1" id="editnameicon"></i></h3>\r\n');
			
		})
		
		//editpw
		$('#editPwBtn').on('click',function(){
			
			var valid=true;
			
			$('#securityinfos input[type=password]').each(function(index, item){
				
				if($(item).val().indexOf(' ')>=0){
					$(item).next('.warning').text('* 공백은 사용할 수 없습니다').addClass('visible').removeClass('invisible');
					valid=false;
					return false;
				}else if($(item).val().trim().length==0){
					$(item).next('.warning').text('* 비밀번호를 입력해주세요').addClass('visible').removeClass('invisible');
					valid=false;
					return false;
				}else if($('#newPw').val() != $('#repw').val()){
					$('#repw').next().text('*비밀번호가 일치하지 않습니다').addClass('visible').removeClass('invisible');
					valid=false;
				}
			})
			
			if(valid){
				
				if(confirm('비밀번호를 수정하시겠습니까?')){
					
					var formData=new FormData();
					formData.append('m_password',$('#newPw').val());
					editInfo(formData);
				}
				
			}
			
			
			
		})
		
		$('#securityinfos input[type=password]').on('focus',function(){
			$('.warning').removeClass('visible').addClass('invisible');
		})
		
		
		// 회원 정보 (프로필사진, 닉네임, 관심분야) <> 보안 설정(비밀번호) 스위칭 기능
		$('#switchSettings').on('change',function(){
			if($(this).is(':checked')){
				// 보안 설정 옵션 보여줌
				$('#securityinfos').removeClass('d-none');
				$('#infos').addClass('d-none');
				// 텍스트 하이라이팅
				$(this).parent().next().addClass('u_txt_chk');
				$(this).parent().prev().removeClass('u_txt_chk');
				
			}else{
				$('#infos').removeClass('d-none');
				$('#securityinfos').addClass('d-none');
				$(this).parent().prev().addClass('u_txt_chk');
				$(this).parent().next().removeClass('u_txt_chk');
			}
			
			$('html').scrollTop(0);
		})
	
		
	
})







</script>