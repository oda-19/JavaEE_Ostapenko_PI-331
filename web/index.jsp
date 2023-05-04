<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">

	<title>Главная страница</title>
</head>
<body>
<jsp:include page="/WEB-INF/jspf/header.jsp" />
<div class="container-fluid">
	<h2>Функции системы</h2>
	<ul>
		<li><a href="/comp_club/computerStatus">Статусы компьютера</a>
		<li><a href="/comp_club/computer">Компьютеры</a>
		<li><a href="/comp_club/visitor">Посетители</a>
		<li><a href="/comp_club/visit">Посещения</a>
	</ul>
</div>
<jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>