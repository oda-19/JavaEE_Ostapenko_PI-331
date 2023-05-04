<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="main.java.domain.Visitor"%>
<%@ page import="main.java.domain.ComputerStatus"%>
<%@ page import="main.java.domain.Computer"%>
<%@ page import="main.java.domain.Visit"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">

<title>Посещения</title>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<title>Visit</title>
</head>
<body>
<jsp:include page="/WEB-INF/jspf/header.jsp" />
<div class="container-fluid">
	<h3>Список посещений</h3>
	<div class="container">
		<div class="table-responsive card">
			<table class="table table-bordered">
				<thead class="thead-dark">
				<tr>
					<th scope="col">Код</th>
					<th scope="col">Дата посещения</th>
					<th scope="col">Продолжительность посещения</th>
					<th scope="col">Сумма к оплате за посещение</th>
					<th scope="col">Посетитель</th>
					<th scope="col">Компьютер</th>
					<th scope="col">Редактировать</th>
					<th scope="col">Удалить</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="visit" items="${visits}">
					<tr>
						<td>${visit.getId()}</td>
						<td>${visit.getVisitDate()}</td>
						<td>${visit.getVisitLength()}</td>
						<td>${visit.getPay()}</td>
						<td>${visit.getVisitor()}</td>
						<td>${visit.getComputer()}</td>
						<td width="20" id="td-edit">
							<a href='<c:url value="/editVisit?id=${visit.getId()}" />'
							   role="button" class="btn btn-outline-secondary">
								<img alt="Редактировать" src="images/icon-edit.png"></a>
						</td>
						<td width="20" id="td-delete">
							<a href='<c:url value="/deleteVisit?id=${visit.getId()}"/>'
							   role="button" class="btn btn-outline-secondary">
								<img alt="Удалить" src="images/icon-delete.png"></a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<h3>Добавить посещение</h3>
	<div class="container" id="container-form">
		<form method="POST" action="">
			<div class="row mb-3">
				<label for="visitDate" class="col-sm-2 col-form-label">Дата посещения</label>
				<div class="col-sm-10">
					<input type="text" name="visitDate" class="form-control" id="staticVisitDate" />
				</div>
			</div>

			<div class="row mb-3">
				<label for="visitLength" class="col-sm-2 col-form-label">Продолжительность посещения</label>
				<div class="col-sm-10">
					<input type="text" name="visitLength" class="form-control" id="staticVisitLength" />
				</div>
			</div>

			<div class="row mb-3">
				<label for="pay" class="col-sm-2 col-form-label">Сумма к оплате за посещение</label>
				<div class="col-sm-10">
					<input type="text" name="pay" class="form-control" id="staticPay" />
				</div>
			</div>

			<div class="row mb-3">
				<label for="visitor" class="col-sm-2 col-form-label">Посетитель</label>
				<div class="col-sm-10">
					<select name = "visitor" class="form-control" id="staticVisitor" >
						<option>Выберите посетителя</option>
						<c:forEach var="visitor" items="${visitors}">
							<option value="${visitor}">
								<c:out value="${visitor.getSurname()} ${visitor.getName()} ${visitor.getPatronymic()}"></c:out>
							</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="row mb-3">
				<label for="computer" class="col-sm-2 col-form-label">Компьютер</label>
				<div class="col-sm-10">
					<select name = "computer" class="form-control" id="staticComputer">
						<option>Выберите компьютер</option>
						<c:forEach var="computer" items="${computers}">
							<option value="${computer}">
								<c:out value="${computer.getComputerName()}"></c:out>
							</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="col text-center">
				<button type="submit" class="btn btn-primary" id="btn-1">Добавить</button></p>
			</div>
		</form>
	</div>
</div>
<jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>