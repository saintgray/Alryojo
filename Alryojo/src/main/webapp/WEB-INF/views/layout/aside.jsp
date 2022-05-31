<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
#sidemenu{
	padding-top: 30px;
	min-width:200px;
    border-right: 1px solid rgb(197,197,197);
}
#sidemenulist{
	color: rgb(195,195,195);
	
}
#sidemenu a{
	cursor: pointer;
	text-decoration: none;
	
}


@media ( max-width : 720px) {
	#sidemenu {
		display: none;
	}
}

</style>
<div id="sidemenu" class="pe-5 ms-3">

	<div class="fw-bold text-center"
		style="background-color: rgb(197, 197, 197); border: 1px solid rgb(150, 150, 150)">
		
		<a href="${pageContext.request.contextPath}/customerCenter">고객센터</a>
	</div>

	<ul class="pe-2 mt-5  text-center" id="sidemenulist">


		<li class="my-4"><a id="sm_ntc" href="${pageContext.request.contextPath}/notice">공지사항</a></li>
		<li class="my-4"><a id="sm_qna" href="${pageContext.request.contextPath}/qna/list">1:1 문의</a></li>
		<li class="my-4"><a id="sm_faq">FAQ</a></li>


	</ul>

</div>