<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="main.java.domain.Visitor"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">

<title>Посетители</title>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<title>Visitor</title>
</head>
<body>
<jsp:include page="/WEB-INF/jspf/header.jsp" />
<div class="container-fluid">
	<h3>Список посетителей</h3>
	<div class="container">
		<div class="table-responsive card">
			<table class="table table-bordered">
				<thead class="thead-dark">
				<tr>
					<th scope="col">Код</th>
					<th scope="col">Фамилия</th>
					<th scope="col">Имя</th>
					<th scope="col">Отчество</th>
					<th scope="col">Документ, удостоверяющий личность</th>
					<th scope="col">Адрес</th>
					<th scope="col">Телефон</th>
					<th scope="col">Редактировать</th>
					<th scope="col">Удалить</th>
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
						<td width="20" id="td-edit">
							<a href='<c:url value="/editVisitor?id=${visitor.getId()}" />'
							   role="button" class="btn btn-outline-secondary">
								<img alt="Редактировать" src="images/icon-edit.png"></a>
						</td>
						<td width="20" id="td-delete">
							<a href='<c:url value="/deleteVisitor?id=${visitor.getId()}"/>'
							   role="button" class="btn btn-outline-secondary">
								<img alt="Удалить" src="images/icon-delete.png"></a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<h3>Добавить посетителя</h3>
	<div class="container" id="container-form">
		<form method="POST" action="">
			<div class="row mb-3">
				<label for="surname" class="col-sm-2 col-form-label">Фамилия</label>
				<div class="col-sm-10">
					<input type="text" name="surname" class="form-control" id="staticSurname" />
				</div>
			</div>

			<div class="row mb-3">
				<label for="name" class="col-sm-2 col-form-label">Имя</label>
				<div class="col-sm-10">
					<input type="text" name="name" class="form-control" id="staticName" />
				</div>
			</div>

			<div class="row mb-3">
				<label for="patronymic" class="col-sm-2 col-form-label">Отчество</label>
				<div class="col-sm-10">
					<input type="text" name="patronymic" class="form-control" id="staticPatronymic" />
				</div>
			</div>

			<div class="row mb-3">
				<label for="identityDocument" class="col-sm-2 col-form-label">Документ, удостоверяющий личность</label>
				<div class="col-sm-10">
					<input type="text" name="identityDocument" class="form-control" id="staticIdentityDocument" />
				</div>
			</div>

			<div class="row mb-3">
				<label for="address" class="col-sm-2 col-form-label">Адрес</label>
				<div class="col-sm-10">
					<input type="text" name="address" class="form-control" id="staticAddress" />
				</div>
			</div>

			<div class="row mb-3">
				<label for="phone" class="col-sm-2 col-form-label">Телефон</label>
				<div class="col-sm-10">
					<input type="text" name="phone" class="form-control" id="staticPhone" />
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