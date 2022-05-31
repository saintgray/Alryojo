<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<script>
$(document).ready(function(){
		// 단계별 작성을 위한 이전/다음 버튼을 눌렀을 때의 스위칭 기능
		$('#linearea .prev').on('click',function(){
			history.go(-1);
		})
		$('#linearea .next').on('click',function(){
			
			$('#linearea').addClass('hide');
			$('#calltimearea').removeClass('hide');
			$('.progress-bar').attr({'style':'width: 50%','aria-valuenow':'50'});
			$('html').scrollTop(0);
		})
		
		$('#calltimearea .prev').on('click',function(){
			$('#linearea').removeClass('hide');
			$('#calltimearea').addClass('hide');
			$('.progress-bar').attr({'style':'width: 25%','aria-valuenow':'25'});
			$('html').scrollTop(0);
		})
		$('#calltimearea .next').on('click',function(){
			console.log('clicked');
			$('#calltimearea').addClass('hide');
			$('#careerarea').removeClass('hide');
			$('.progress-bar').attr({'style':'width: 75%','aria-valuenow':'75'});
			$('html').scrollTop(0);
		})
		
		$('#careerarea .prev').on('click',function(){
			$('#calltimearea').removeClass('hide');
			$('#careerarea').addClass('hide');
			$('.progress-bar').attr({'style':'width: 50%','aria-valuenow':'50'});
			$('html').scrollTop(0);
		})
		$('#careerarea .next').on('click',function(){
			
			$('#careerarea').addClass('hide');
			$('#qnaarea').removeClass('hide');
			$('.progress-bar').attr({'style':'width: 100%','aria-valuenow':'100'});
			$('html').scrollTop(0);
		})
		
		$('#qnaarea .prev').on('click',function(){
			$('#careerarea').removeClass('hide');
			$('#qnaarea').addClass('hide');
			$('.progress-bar').attr({'style':'width: 75%','aria-valuenow':'75'});
			$('html').scrollTop(0);
		})
		
		
		
	
	
		
		// 회원의 첨부파일 (File 객체) 을 보관하고 있다가 프로필 등록을 눌렀을때 서버에게 data 로 보내주기 위한 배열 전역 변수를 선언.
		var myfiles=[];
		
		
		
		$('#registerbtn').on('click',function(){
			
			var formData = new FormData();
			
			formData.append('line',$('input[name=line]').val());
			formData.append('calltime',$('input[name=calltime_prefix]').val()+' ~ '+$('input[name=calltime_suffix]').val());
			formData.append('career',$('.summernote_career').summernote('code'));
			formData.append('qna',$('.summernote_qna').summernote('code'));
			
			
			
			
			$(myfiles).each(function(index,item){
				// 서버에서 받을때 커맨드 객체 내의  List<MultipartFile> 의 변수명을 files 로 맞추어 주면 자동으로 바인딩 하여 들어갈 것이다.
				formData.append('files['+index+']', item);
			})
			
			$.ajax({
				url: 'register',
				type:'post',
				enctype:'multipart/form-data',
				processData:false,
				cash:false,
				contentType:false,
				data:formData,
				success:function(data){
					console.log('통신성공');
					alert('정상적으로 등록되었습니다.');
					location.href="${pageContext.request.contextPath}/member/profile/main";
				},
				error:function(data){
					
					alert('통신신패!');
					console.log(data);
				}
				
			})
			
			
		})
		
		
		$('#myfiles').on('change',function(e){
			// #myfiles 요소의 내용이 변했을 때 >> 파일을 선택했을 때
			// 이벤트를 받아와 target 속성의 files 를 콘솔로 출력해본다. 
			console.log(e.target.files);
			// 결과는 FileList{0:File, 1:File........ N:File length : N+1}
			var fileList=e.target.files;
			// 이 파일리스트 배열을 돌면서 파일 미리보기에 파일을 추가해보자.
			// 자바스크립트의 FileReader 객체가 파일들을 읽어올 수 있는 메소드를 제공해준다.
			
			// 미리 보기에서 삭제 , 추가 기능 구현을 위해 전역 변수에 저장된 myfiles의 길이를 먼저 구한다.
			var myfileslength=myfiles.length;
			
			
			$(fileList).each(function(index, item){
				
				
			 	/* var fileObj={
						
						file: item,
						// MB 기준
						file_size: item.size/(1024*1024),
						file_exet: item.name.substring(item.name.lastIndexOf('.')),
						file_originnm: item.name.replaceAll(' ','_')
				}  */
			 
				// List를 돌면서 그 안의 File 들을  myfiles 배열에도  추가한다
				console.log(item);
				myfiles.push(item);
				///////////////////////////////////////////////////////////////////////////////////////
				
				
				
			
				// myfiles 배열에 5개의 파일이 미리 들어가 있었다면 새로 추가되는 첫번째 파일의 인덱스는 5가 될것이다.
				// 그렇다면 삭제기능을 만들기 위해서는 myfiles 에 든 이 파일의 인덱스를 기준으로 삭제를 해야 하기 때문에
				//  myfileslength+index(5+0, 5+1, 5+2....)
				var html='<div class="select d-flex flex-wrap ">';
					html+='<span style="color:red" class="thisindex">'+(myfileslength+index)+'</span>';
					html+='<span class="mx-2">'+item.name+'</span>';
					html+='<img src="${pageContext.request.contextPath}/resources/files/server/icons/icon_x.png" class="delRow me-3">';
					html+="</div>"
					
				$('#selected').append($(html));
		
			
			})
			console.log(myfiles);
			
			
			
		})
		
	// 추가한 첨부파일의 삭제 
	$('#selected').on('click','.delRow',function(){
		console.log($(this).siblings('.thisindex').text());
		// myfiles 배열에서 해당 인덱스의 파일을 삭제한다.
		myfiles.splice($(this).siblings('.thisinndex').text(),1);
		
		
		
		
		// 미리보기 부분에서 삭제한 파일 뒤에 있는 모든 요소들의 thisindex 값을 1만큼 줄인다.
		var indexchoosen=$(this).siblings('.thisindex').text();
		$('.thisindex').each(function(index,item){
			
			
			if($(item).text() > indexchoosen){
				
				$(item).text($(item).text()-1);
			}
			
		})
		// 마지막으로 삭제하려는 파일을 미리보기에서 삭제한다.
		$(this).parent('.select').remove();
		console.log(myfiles);
	})
		
	// Jquery 의 timepicker 사용
		$('.timepicker').timepicker({
	    timeFormat: 'HH:mm p',
	    interval: 60,
	    minTime: '01',
	    maxTime: '23:00pm',
	    defaultTime: '00',
	    startTime: '00',
	    dynamic: false,
	    dropdown: true,
	    scrollbar: true
	});
	
	
	
	
	
	 $('.summernote_career').summernote({
		toolbar: [
		    // [groupName, [list of button]]
		    ['style', ['bold', 'italic', 'underline', 'clear']],
		    ['font', ['strikethrough', 'superscript', 'subscript']],
		    ['fontsize', ['fontsize']],
		    /* ['color', ['color']], */
		    ['para', ['ul', 'ol', 'paragraph']],
		    ['height', ['height']],
		    ['insert', ['link', 'picture', 'video']],
		  ],
		 height: 600,
         codemirror: {
        	 theme: 'monokai'
         },
         lang: 'ko-KR',
         placeholder: '화려한 나의 경력을 작성해주세요'
         //minHeight: 600,
         //maxHeight: 600,
         //width: 900
		
	});
	 
	 $('.summernote_qna').summernote({
			toolbar: [
			    // [groupName, [list of button]]
			    ['style', ['bold', 'italic', 'underline', 'clear']],
			    ['font', ['strikethrough', 'superscript', 'subscript']],
			    ['fontsize', ['fontsize']],
			    /* ['color', ['color']], */
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']],
			    ['insert', ['link', 'picture', 'video']],
			  ],
			 height: 600,
	         codemirror: {
	        	 theme: 'monokai'
	         },
	         lang: 'ko-KR',
	         placeholder: 'QNA 작성'
	         //minHeight: 600,
	         //maxHeight: 600,
	         //width: 900
			
		})
	 
	/* $('.dropdown-toggle').dropdown(); */
		
	
})

</script>