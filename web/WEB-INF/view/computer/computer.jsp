<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="main.java.domain.ComputerStatus"%>
<%@ page import="main.java.domain.Computer"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">

<title>Компьютеры</title>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<title>Computer</title>
</head>
<body>
<jsp:include page="/WEB-INF/jspf/header.jsp" />
<div class="container-fluid">
	<h3>Список компьютеров</h3>
	<div class="container">
		<div class="table-responsive card">
			<table class="table table-bordered">
				<thead class="thead-dark">
				<tr>
					<th scope="col">Код</th>
					<th scope="col">Наименование</th>
					<th scope="col">Статус компьютера</th>
					<th scope="col">Описание характеристик</th>
					<th scope="col">Редактировать</th>
					<th scope="col">Удалить</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="computer" items="${computers}">
					<tr>
						<td>${computer.getId()}</td>
						<td>${computer.getComputerName()}</td>
						<td>${computer.getComputerStatus()}</td>
						<td>${computer.getComputerDescription()}</td>
						<td width="20" id="td-edit">
							<a href='<c:url value="/editComputer?id=${computer.getId()}" />'
							   role="button" class="btn btn-outline-secondary">
								<img alt="Редактировать" src="images/icon-edit.png"></a>
						</td>
						<td width="20" id="td-delete">
							<a href='<c:url value="/deleteComputer?id=${computer.getId()}" />'
							   role="button" class="btn btn-outline-secondary">
								<img alt="Удалить" src="images/icon-delete.png"></a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<h3>Добавить компьютер</h3>
	<div class="container" id="container-form">
		<form method="POST" action="">
			<div class="row mb-3">
				<label for="computerName" class="col-sm-2 col-form-label">Наименование</label>
				<div class="col-sm-10">
					<input type="text" name="computerName" id="staticComputerName" class="form-control" />
				</div>
			</div>

			<div class="row mb-3">
				<label for="computerStatus" class="col-sm-2 col-form-label">Статус компьютера</label>
				<div class="col-sm-10">
					<select name = "computerStatus" id="staticComputerStatus" class="form-control">
						<option>Выберите статус компьютера</option>
						<c:forEach var="computerStatus" items="${computerStatuses}">
							<option value="${computerStatus}">
								<c:out value="${computerStatus.getComputerStatus()}"></c:out>
							</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="row mb-3">
				<label for="computerDescription" class="col-sm-2 col-form-label">Описание характеристик</label>
				<div class="col-sm-10">
					<input type="text" name="computerDescription" id="staticComputerDescription" class="form-control" />
				</div>
			</div>

			<div class="col text-center">
				<button type="submit" class="btn btn-primary" id="btn-1">Добавить</button>
			</div>
		</form>
	</div>
</div>
<jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>