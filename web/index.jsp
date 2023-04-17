<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<title>Главная страница</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jspf/header.jsp" />
	<div id="main">
		<h2>Функции системы</h2>
		<ul>
			<li><a href="/comp_club/visitor">Посетители</a>
			<li><a href="/comp_club/computer">Компьютеры</a>
			<li><a href="/comp_club/visit">Посещения</a>
			<li><a href="/comp_club/computerStatus">Статусы компьютера</a>
		</ul>
	</div>
	<jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>