<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="main.java.domain.Visitor"%>
<%@ page import="main.java.domain.ComputerStatus"%>
<%@ page import="main.java.domain.Computer"%>
<%@ page import="main.java.domain.Visit"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Посещения</title>
<head>
	<meta charset="UTF-8">
	<title>Visit</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jspf/header.jsp" />
	<div id="main">
		<section>
			<aside class="leftAside">
				<h3>Список посещений</h3>
				<table>
					<thead>
						<tr>
							<th scope="col">Код</th>
							<th scope="col">Дата посещения</th>
							<th scope="col">Продолжительность посещения</th>
							<th scope="col">Сумма к оплате за посещение</th>
							<th scope="col">Посетитель</th>
							<th scope="col">Компьютер</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="visit" items="${listVisit}">
							<tr>
								<td>${visit.getVisitId()}</td>
								<td>${visit.getVisitDate()}</td>
								<td>${visit.getVisitLenth()}</td>
								<td>${visit.getPay()}</td>
								<td>${visit.getVisitor()}</td>
								<td>${visit.getComputer()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</aside>
		</section>
		<section>
			<article>
				<h3>Добавить посещение</h3>
				<div class="text-article">
					<form method="POST" action="">
						<p><label for="visitDate">Дата посещения</label> <input type="text" name="visitDate" /></p>
						<p><label for="visitLenth">Продолжительность посещения</label> <input type="text" name="visitLenth" /></p>
						<p><label for="pay">Сумма к оплате за посещение</label> <input type="text" name="pay" /></p>
						<p><label for="visitor">Посетитель</label>
							<select>
								<option disabled>Выберите посетителя</option>
								<c:forEach var="visitor" items="${listVisitor}">
									<option value="${visitor}">${visitor.getSurname()} ${visitor.getName()} ${visitor.getPatronymic()}></option>
								</c:forEach>
							</select>
						</p>
						<p><label for="computer">Компьютер</label>
							<select>
								<option disabled>Выберите компьютер</option>
								<c:forEach var="computer" items="${listComputer}">
									<option value="${computer}">${computer.getComputerName()}></option>
								</c:forEach>
							</select>
						</p>
					</form>
					<p><button type="submit">Добавить</button></p>
				</div>
			</article>
		</section>
	</div>
	<jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>