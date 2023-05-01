<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="main.java.domain.Visitor"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Посетители</title>
<head>
	<meta charset="UTF-8">
	<title>Visitor</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jspf/header.jsp" />
	<div id="main">
		<section>
			<aside class="leftAside">
				<h3>Список посетителей</h3>
				<table>
					<thead>
						<tr>
							<th scope="col">Код</th>
							<th scope="col">Фамилия</th>
							<th scope="col">Имя</th>
							<th scope="col">Отчество</th>
							<th scope="col">Документ, удостоверяющий личность</th>
							<th scope="col">Адрес</th>
							<th scope="col">Телефон</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="visitor" items="${visitors}">
							<tr>
								<td>${visitor.getId()}</td>
								<td>${visitor.getSurname()}</td>
								<td>${visitor.getName()}</td>
								<td>${visitor.getPatronymic()}</td>
								<td>${visitor.getIdentityDocument()}</td>
								<td>${visitor.getAddress()}</td>
								<td>${visitor.getPhone()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</aside>
		</section>
		<section>
			<article>
				<h3>Добавить посетителя</h3>
				<div class="text-article">
					<form method="POST" action="">
						<p><label for="surname">Фамилия</label> <input type="text" name="surname" /></p>
						<p><label for="name">Имя</label> <input type="text" name="name" /></p>
						<p><label for="patronymic">Отчество</label> <input type="text" name="patronymic" /></p>
						<p><label for="identityDocument">Документ, удостоверяющий личность</label> <input type="text" name="identityDocument" /></p>
						<p><label for="address">Адрес</label> <input type="text" name="address" /></p>
						<p><label for="phone">Телефон</label> <input type="text" name="phone" /></p>
						<p><button type="submit">Добавить</button></p>
					</form>

				</div>
			</article>
		</section>
	</div>
	<jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>