<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
$('.bg_template').each(function(index, item){
	
	

	// DB 에서 해당 회원의 평점을 계산해서 5점 만점에 평균 x점이 나왔다고 가정하자.
	// 별 5개 이미지 길이만큼의 x에 대한 백분율 만큼 #bg_template 길이를 결정해 주면 될것이다.
	// 예를 들어 3.3점이라 하자.

	var avgRank=$(item).children('.rank').text();
	console.log(avgRank);
	// 일의 자리수 추출
	var fullStars=Math.floor(avgRank);
	// 소수점 자리수 추출
	var remainStarSize=(avgRank%1).toFixed(2); 
	console.log('item >> children(.star)');
	console.log($(item).children('.star'));
	console.log('fullstars>>');
	console.log(fullStars);
	console.log('remainSize');
	console.log(remainStarSize);
	
	$(item).children('.star').each(function(index,item){
		if(index<fullStars){
			$(item).css({
				'background':'url(${pageContext.request.contextPath}/resources/files/server/icons/bg_yello.png)',
				'background-repeat':'no-repeat',
				'background-size':($(item).width()-1)+'px'});
				
		}
		if(index==fullStars){
			console.log(($(item).width()*remainStarSize)+'px');
			console.log($(item).height());
			$(item).css({
				'background':'url(${pageContext.request.contextPath}/resources/files/server/icons/bg_yello.png)',
				'background-repeat':'no-repeat',
				'background-size': ($(item).width()*remainStarSize)+'px '+($(item).height())+'px'
				});
			
		}
	})
	
	
	




	
	
	
})

</script>