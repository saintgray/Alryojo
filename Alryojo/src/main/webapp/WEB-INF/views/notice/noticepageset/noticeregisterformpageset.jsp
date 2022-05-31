<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<script>



	//회원의 첨부파일 (File 객체) 을 보관하고 있다가 프로필 등록을 눌렀을때 서버에게 data 로 보내주기 위한 배열 전역 변수를 선언.
	var myfiles=[];



	$(document).ready(function() {

		$('.notice_content').summernote({
			toolbar : [
						['style',[
									'bold',
									'italic',
									'underline',
									'clear' 
								] 
						],
						['font',[
									'strikethrough',
									'superscript',
									'subscript' 
								] 
						],
					[ 'fontsize',
							[ 'fontsize' ] ],
					/* ['color', ['color']], */
					[
							'para',
							[ 'ul', 'ol',
									'paragraph' ] ],
					[ 'height', [ 'height' ] ],
					[
							'insert',
							[ 'link',
									'picture',
									'video' ] ], ],
	
			minHeight : 500,
			maxHeight : 500,
			codemirror : {
				theme : 'monokai'
			},
			lang : 'ko-KR',
			placeholder : '내용'
		})

		$('#registerbtn').on('click',function() {
		
							console.log($('.notice_content').summernote('code'));
							
							
							
							var formData = new FormData();
							
							formData.append('notice_title',$('#notice_title').val());
							formData.append('notice_content',$('.notice_content').summernote('code'));
							
							
							
							
							
							$(myfiles).each(function(index,item){
								// 서버에서 받을때 커맨드 객체 내의  List<MultipartFile> 의 변수명을 files 로 맞추어 주면 자동으로 바인딩 하여 들어갈 것이다.
								formData.append('files['+index+']', item);
							})
							
							$.ajax({
		
										url : '${pageContext.request.contextPath}/admin/notice/register',
										type : 'POST',
										// 보낼 data : form 태그 내에 있는 모든 name 속성 요소의 값을 파라미터로 만들어줌
										data:formData,
										enctype:'multipart/form-data',
										processData:false,
										cash:false,
										contentType:false,
										success : function(data) {
											if (data >= 1) {
												alert('정상적으로 등록되었습니다');
												location.href = "${pageContext.request.contextPath}/notice?selectPage=1&numOfNoticesPerPage=5";
											} else {
												alert('오류가 발생하였습니다. 잠시 후 다시 시도하세요');
											}
										},
										error : function(data) {
											console.log(data);
										}
							})
		})

		$('#cancelbtn').click(function() {
							location.href = "${pageContext.request.contextPath}/notice?selectPage=${param.selectPage}&numOfNoticesPerPage=${param.numOfNoticesPerPage}";
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
		
						
						
						
})
</script>