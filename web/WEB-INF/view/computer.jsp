<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="main.java.domain.ComputerStatus"%>
<%@ page import="main.java.domain.Computer"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Компьютеры</title>
<head>
	<meta charset="UTF-8">
	<title>Computer</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jspf/header.jsp" />
	<div id="main">
		<section>
			<aside class="leftAside">
				<h3>Список компьютеров</h3>
				<table>
					<thead>
						<tr>
							<th scope="col">Код</th>
							<th scope="col">Наименование</th>
							<th scope="col">Статус компьютера</th>
							<th scope="col">Описание характеристик</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="computer" items="${listComputer}">
							<tr>
								<td>${computer.getComputerId()}</td>
								<td>${computer.getComputerName()}</td>
								<td>${computer.getComputerStatus()}</td>
								<td>${computer.getComputerDescription()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</aside>
		</section>
		<section>
			<article>
				<h3>Добавить компьютер</h3>
				<div class="text-article">
					<form method="POST" action="">
						<p><label for="computerName">Наименование</label> <input type="text" name="computerName" /></p>
						<p><label for="computerStatus">Статус компьютера</label>
							<select>
								<option disabled>Выберите статус компьютера</option>
								<c:forEach var="computerStatus" items="${listComputerStatus}">
									<option value="${computerStatus}">${computerStatus.getComputerStatus()}></option>
								</c:forEach>
							</select>
						</p>
						<p><label for="computerDescription">Описание характеристик</label> <input type="text" name="computerDescription" /></p>
					</form>
					<p><button type="submit">Добавить</button></p>
				</div>
			</article>
		</section>
	</div>
	<jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>