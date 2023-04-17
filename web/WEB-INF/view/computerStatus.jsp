<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="main.java.domain.ComputerStatus"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Статусы компьютера</title>
<head>
	<meta charset="UTF-8">
	<title>ComputerStatus</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jspf/header.jsp" />
	<div id="main">
		<section>
			<aside class="leftAside">
				<h3>Список статусов компьютера</h3>
				<table>
					<thead>
						<tr>
							<th scope="col">Код</th>
							<th scope="col">Статус</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="computerStatus" items="${listComputerStatus}">
							<tr>
								<td>${computerStatus.getStatusId()}</td>
								<td>${computerStatus.getComputerStatus()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</aside>
		</section>
		<section>
			<article>
				<h3>Добавить статус компьютера</h3>
				<div class="text-article">
					<form method="POST" action="">
						<p><label for="computerStatus">Статус</label> <input type="text" name="computerStatus" /></p>
					</form>
					<p><button type="submit">Добавить</button></p>
				</div>
			</article>
		</section>
	</div>
	<jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>