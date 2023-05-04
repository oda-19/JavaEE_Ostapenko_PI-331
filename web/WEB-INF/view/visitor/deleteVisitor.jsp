<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="main.java.domain.Visitor"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">

<title>Удаление посетителя</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>Visitor Delete</title>
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
        </div>
    </div>

    <h3>Удаление посетителя</h3>
    <div class="container" id="container-form">
        <form method="POST" action="">
            <div class="row mb-3">
                <label for="id" class="col-sm-2 col-form-label">Код посетителя</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" readonly
                           value="${visitorDelete[0].getId()}" />
                </div>
            </div>

            <div class="row mb-3">
                <label for="surname" class="col-sm-2 col-form-label">Фамилия</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="surname" id="staticSurname" readonly
                           value="${visitorDelete[0].getSurname()}" />
                </div>
            </div>

            <div class="row mb-3">
                <label for="name" class="col-sm-2 col-form-label">Имя</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="name" id="staticName" readonly
                           value="${visitorDelete[0].getName()}" />
                </div>
            </div>

            <div class="row mb-3">
                <label for="patronymic" class="col-sm-2 col-form-label">Отчество</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="patronymic" id="staticPatronymic" readonly
                           value="${visitorDelete[0].getPatronymic()}" />
                </div>
            </div>

            <div class="row mb-3">
                <label for="identityDocument" class="col-sm-2 col-form-label">Документ, удостоверяющий личность</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="identityDocument" id="staticIdentityDocument" readonly
                           value="${visitorDelete[0].getIdentityDocument()}" />
                </div>
            </div>

            <div class="row mb-3">
                <label for="address" class="col-sm-2 col-form-label">Адрес</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="address" id="staticAddress" readonly
                           value="${visitorDelete[0].getAddress()}" />
                </div>
            </div>

            <div class="row mb-3">
                <label for="phone" class="col-sm-2 col-form-label">Телефон</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="phone" id="staticPhone" readonly
                           value="${visitorDelete[0].getPhone()}" />
                </div>
            </div>

            <div class="col text-center">
                <button type="submit" class="btn btn-primary" id="btn-1">Удалить</button>
                <a href='<c:url value="/visitor" />' role="button" id="btn-2" class="btn btn-secondary">Отменить/Возврат</a>
            </div>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>
