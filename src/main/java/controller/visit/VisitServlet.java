package main.java.controller.visit;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.dao.ConnectionProperty;
import main.java.domain.Computer;
import main.java.domain.ComputerStatus;
import main.java.domain.Visit;
import main.java.domain.Visitor;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/visit")
public class VisitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;

	String SELECT_ALL_COMPUTERSTATUSES = "SELECT id, computerStatus FROM computerStatus";
	String SELECT_ALL_COMPUTERS = "SELECT id, computerStatusId, computerName, computerDescription FROM computer";
	String SELECT_ALL_VISITORS = "SELECT id, surname, name, patronymic, identityDocument, address, phone FROM visitor";
	String SELECT_ALL_VISITS = "SELECT id, visitorId, computerId, visitDate, visitLength, pay FROM visit";
	String INSERT_VISIT = "INSERT INTO visit (visitorId, computerId, visitDate, visitLength, pay) VALUES (?, ?, ?, ?, ?)";

	ArrayList<ComputerStatus> computerStatuses = new ArrayList<>();
	ArrayList<Computer> computers = new ArrayList<>();
	ArrayList<Visitor> visitors = new ArrayList<>();
	ArrayList<Visit> visits = new ArrayList<>();

	public VisitServlet() {
		prop = new ConnectionProperty();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		ConnectionProperty builder = new ConnectionProperty();

		try (Connection conn = builder.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_COMPUTERSTATUSES);
			if (rs != null) {
				computerStatuses.clear();
				while (rs.next()) {
					computerStatuses.add(new ComputerStatus(rs.getLong("id"),
							rs.getString("computerStatus")));
				}
				rs.close();
				request.setAttribute("computerStatuses", computerStatuses);
			}

			long computerStatusId;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_ALL_COMPUTERS);
			if (rs != null) {
				computers.clear();
				while (rs.next()) {
					computerStatusId = rs.getLong("computerStatusId");
					computers.add(new Computer(rs.getLong("id"),
							rs.getString("computerName"),
							rs.getString("computerDescription"),
							computerStatusId,
							findByIdComputerStatus(computerStatusId, computerStatuses)));
				}
				rs.close();
				request.setAttribute("computers", computers);
			}

			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_ALL_VISITORS);
			if (rs != null) {
				visitors.clear();
				while (rs.next()) {
					visitors.add(new Visitor(rs.getLong("id"),
							rs.getString("surname"),
							rs.getString("name"),
							rs.getString("patronymic"),
							rs.getString("identityDocument"),
							rs.getString("address"),
							rs.getString("phone")));
				}
				rs.close();
				request.setAttribute("visitors", visitors);
			}

			long visitorId;
			long computerId;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_ALL_VISITS);
			if (rs != null) {
				visits.clear();
				while (rs.next()) {
					visitorId = rs.getLong("visitorId");
					computerId = rs.getLong("computerId");
					visits.add(new Visit(rs.getLong("id"),
							rs.getDate("visitDate"),
							rs.getString("visitLength"),
							rs.getFloat("pay"),
							visitorId,
							findByIdVisitor(visitorId, visitors),
							computerId,
							findByIdComputer(computerId, computers)));
				}
				rs.close();
				request.setAttribute("visits", visits);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		String userPath = request.getServletPath();
		if("/visit".equals(userPath)){
			request.getRequestDispatcher("/WEB-INF/view/visit/visit.jsp")
					.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ConnectionProperty builder = new ConnectionProperty();

		try (Connection conn = builder.getConnection()){
			String visitDate = request.getParameter("visitDate");
			String visitLength = request.getParameter("visitLength");
			String pay = request.getParameter("pay");

			String visitor = request.getParameter("visitor");
			int index1 = visitor.indexOf('=');
			int index2 = visitor.indexOf(",");
			String r1 = visitor.substring(index1+1, index2);
			Long visitorId = Long.parseLong(r1.trim());

			String computer = request.getParameter("computer");
			int index3 = computer.indexOf('=');
			int index4 = computer.indexOf(",");
			String r2 = computer.substring(index3+1, index4);
			Long computerId = Long.parseLong(r2.trim());

			try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_VISIT)){
				preparedStatement.setLong(1, visitorId);
				preparedStatement.setLong(2, computerId);
				preparedStatement.setDate(3, Date.valueOf(visitDate));
				preparedStatement.setString(4, visitLength);
				preparedStatement.setFloat(5, Float.parseFloat(pay));

				int rows = preparedStatement.executeUpdate();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
			getServletContext().getRequestDispatcher("/WEB-INF/view/visit/visit.jsp")
					.forward(request, response);
		}
		doGet(request, response);
	}

	// Поиск статуса компьютера по id
	private ComputerStatus findByIdComputerStatus(Long id, ArrayList<ComputerStatus> computerStatuses) {
		if (computerStatuses != null) {
			for (ComputerStatus r: computerStatuses) {
				if ((r.getId()).equals(id)) {
					return r;
				}
			}
		} else {
			return null;
		}
		return null;
	}

	// Поиск компьютера по id
	private Computer findByIdComputer(Long id, ArrayList<Computer> computers) {
		if (computers != null) {
			for (Computer r: computers) {
				if ((r.getId()).equals(id)) {
					return r;
				}
			}
		} else {
			return null;
		}
		return null;
	}

	// Поиск посетителя по id
	private Visitor findByIdVisitor(Long id, ArrayList<Visitor> visitors) {
		if (visitors != null) {
			for (Visitor r: visitors) {
				if ((r.getId()).equals(id)) {
					return r;
				}
			}
		} else {
			return null;
		}
		return null;
	}
}
