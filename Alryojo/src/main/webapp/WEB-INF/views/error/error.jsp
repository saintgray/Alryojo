<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com" >
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet" type="text/css">
<style>
	#wrap{
		position:fixed;
		display:flex;
		flex-direction: column;
		justify-content:center;
		top: 50%;
		left: 50%;
		transform: translate(-50%,-50%);
		text-align: center;
	}
	#wrap img{
		width: 300px;
		height:300px;
	}
	#warning{
		display:inline-block;
		margin-top: 20px;
		margin-bottom: 20px;
		color:red;
		font-size:30px;
		font-family: 'Noto Sans KR', sans-serif;
		cursor: none;
		font-weight: 700;
	}
</style>
<title>404</title>
</head>
<body>

	<div id="wrap">
		<img src="${pageContext.request.contextPath}/resources/files/server/icons/rejected.png">
		<span id="warning">접근 권한이 없습니다</span>
	</div>
</body>
</html>