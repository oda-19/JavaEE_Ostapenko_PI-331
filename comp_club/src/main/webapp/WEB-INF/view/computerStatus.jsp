<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.ComputerStatus"%>
<%
ComputerStatus computerStatus1 = new ComputerStatus(1l, "Включен");
ComputerStatus computerStatus2 = new ComputerStatus(2l, "Неисправен");
ComputerStatus computerStatus3= new ComputerStatus(3l, "В ремонте");
ComputerStatus computerStatus4 = new ComputerStatus(4l, "Выключен");
ComputerStatus[] computerStatuses = new ComputerStatus[]{computerStatus1, computerStatus2, computerStatus3, computerStatus4};
int length = computerStatuses.length;
pageContext.setAttribute("computerStatuses", computerStatuses);
%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Статусы компьютеров</title>
<head>
	<meta charset="UTF-8">
	<title>ComputerStatus</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jspf/header.jsp" />
	<div id="main">
		<section>
			<aside class="leftAside">
				<h3>Список статусов компьютеров</h3>
				<table>
					<thead>
						<tr>
							<th scope="col">Код</th>
							<th scope="col">Статус</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="computerStatus" items="${computerStatuses}">
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