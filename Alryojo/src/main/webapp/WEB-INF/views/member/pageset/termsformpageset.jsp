<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>

// 회원가입 폼에서 뒤로가기를 눌렀을 시 체크박스 자동해제
window.onpageshow = function(event) {
    if ( event.persisted || (window.performance && window.performance.navigation.type == 2)) {
		
		if($("input[type=checkbox]").is(":checked")) {
			$("input[type=checkbox]").prop("checked", false);
		}
    }
};

$(document).ready(function() {
	console.log('로딩끝');
	
	
	$('input[type=checkbox]').each(function(index,item){
		$(item).checked=false;
	})
	
	
	$('#cancel').on('click', function() {
		history.go(-1);
	})

	$('#gotoregister').on('click', function() {
		var allcheck = true;
		$('.required').each(function(index, item) {

			if ($(item).prop('checked') == false) {
				allcheck = false;
				return false;
			}
		})
		console.log(allcheck);
		if (!allcheck) {

			$('.warning').attr('style', "display: block");
		} else {
			$('#termsform').submit();
		}
		return allcheck;
	})

	/*
	 * $('#allselect').change(function() { if (this.checked) {
	 * $(this).siblings().prop('checked', true); } else {
	 * $(this).siblings().prop('checked', false); } })
	 */

	$('#allselect').change(function() {
		if (this.checked) {
			$(this).siblings().prop('checked', true);
			$('label').children().attr('src','${pageContext.request.contextPath}/resources/files/server/icons/check_on.svg');

		} else {
			$(this).siblings().prop('checked', false);
			$('label').children().attr('src','${pageContext.request.contextPath}/resources/files/server/icons/check_off.svg');
		}

	})

	$('input[type=checkbox]').change(function() {
		$('.warning').attr('style', 'display: none');
	})

	$('.required, .canchoice').change(function() {
		console.log($(this).prop('checked'));
		if ($(this).prop('checked') == false) {
			$('#allselect').prop('checked', false);
			$('label[for=allselect]').children().attr('src','${pageContext.request.contextPath}/resources/files/server/icons/check_off.svg');
			$('label[for='+$(this).attr('id')+']').children().attr('src','${pageContext.request.contextPath}/resources/files/server/icons/check_off.svg');
			
		}else{
			$('label[for='+$(this).attr('id')+']').children().attr('src','${pageContext.request.contextPath}/resources/files/server/icons/check_on.svg');
		}
	})

})
</script>

