<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Visitor"%>
<%@ page import="domain.ComputerStatus"%>
<%@ page import="domain.Computer"%>
<%@ page import="domain.Visit"%>
<%
Visitor visitor1 = new Visitor(1l, "Иванов", "Иван", "Иванович", "Паспорт", "ул. Каширская, д. 8/4, кв. 140", "+7(900)123-45-67");
Visitor visitor2 = new Visitor(2l, "Петров", "Петр", "Петрович", "Паспорт", "ул. Малиновского, д. 10, кв. 19", "+7(901)234-56-78");
Visitor visitor3= new Visitor(3l, "Семенова", "Светлана", "Семеновна", "Паспорт", "ул. 2-ая Краснодарская, д. 149/7, кв. 3", "+7(902)345-67-89");
Visitor[] visitors = new Visitor[]{visitor1, visitor2, visitor3};
pageContext.setAttribute("visitors", visitors);

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

Visit visit1 = new Visit(1l, "10.04.2023", "2 часа", 180, 1l, visitor1, 1l, computer1);
Visit visit2 = new Visit(2l, "10.04.2023", "5 часов", 450, 2l, visitor2, 5l, computer5);
Visit visit3 = new Visit(3l, "13.04.2023", "30 минут", 45, 3l, visitor3, 4l, computer4);
Visit[] visits = new Visit[]{visit1, visit2, visit3};
pageContext.setAttribute("visits", visits);
%>

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
						<c:forEach var="visit" items="${visits}">
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
								<c:forEach var="visitor" items="${visitors}">
									<option value="${visitor}">${visitor.getSurname()} ${visitor.getName()} ${visitor.getPatronymic()}></option>
								</c:forEach>
							</select>
						</p>
						<p><label for="computer">Компьютер</label>
							<select>
								<option disabled>Выберите компьютер</option>
								<c:forEach var="computer" items="${computers}">
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