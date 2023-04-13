<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.ComputerStatus"%>
<%@ page import="domain.Computer"%>
<%
ComputerStatus computerStatus1 = new ComputerStatus(1l, "Включен");
ComputerStatus computerStatus2 = new ComputerStatus(2l, "Неисправен");
ComputerStatus computerStatus3= new ComputerStatus(3l, "В ремонте");
ComputerStatus computerStatus4 = new ComputerStatus(4l, "Выключен");
ComputerStatus[] computerStatuses = new ComputerStatus[]{computerStatus1, computerStatus2, computerStatus3, computerStatus4};
pageContext.setAttribute("computerStatuses", computerStatuses);

Computer computer1 = new Computer(1l, "Компьютер 1", "Процессор: Intel Core i5 11400F, 2.6 ГГц (4.4 ГГц, в режиме Turbo); Диски: SSD 1024 ГБ; Оперативная память: 16 ГБ, DDR4, DIMM, 3200 МГц; Графика: NVIDIA GeForce RTX 3060 - 12288 Мб; Операционная система: Windows 10", 1l, computerStatus1);
Computer computer2 = new Computer(2l, "Компьютер 2", "Процессор: Intel Core i5 11400F, 2.6 ГГц (4.4 ГГц, в режиме Turbo); Диски: SSD 1024 ГБ; Оперативная память: 16 ГБ, DDR4, DIMM, 3200 МГц; Графика: NVIDIA GeForce RTX 3060 - 12288 Мб; Операционная система: Windows 10", 3l, computerStatus3);
Computer computer3 = new Computer(3l, "Компьютер 3", "Процессор: Intel Core i5 10400F, 2.9 ГГц (4.3 ГГц, в режиме Turbo); Диски: SSD 500 ГБ; Оперативная память: 16 ГБ, DDR4, DIMM, 2666 МГц; Графика: NVIDIA GeForce RTX 3060 - 12288 Мб; Операционная система: Windows 11", 2l, computerStatus2);
Computer computer4 = new Computer(4l, "Компьютер 4", "Процессор: Intel Core i5 11400F, 2.6 ГГц (4.4 ГГц, в режиме Turbo); Диски: SSD 1024 ГБ; Оперативная память: 16 ГБ, DDR4, DIMM, 3200 МГц; Графика: Intel GeForce RTX 3050 - 8192 Мб; Операционная система: Windows 10", 1l, computerStatus1);
Computer computer5 = new Computer(5l, "Компьютер 5", "Процессор: Intel Core i5 11400F, 2.6 ГГц (4.4 ГГц, в режиме Turbo); Диски: SSD 1024 ГБ; Оперативная память: 16 ГБ, DDR4, DIMM, 3200 МГц; Графика: NVIDIA GeForce RTX 3060 - 12288 Мб; Операционная система: Windows 7", 4l, computerStatus4);
Computer[] computers = new Computer[]{computer1, computer2, computer3, computer4, computer5};
pageContext.setAttribute("computers", computers);
%>

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
						<c:forEach var="computer" items="${computers}">
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
								<c:forEach var="computerStatus" items="${computerStatuses}">
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