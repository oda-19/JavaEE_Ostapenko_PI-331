package main.java.controller.computer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.dao.ConnectionProperty;
import main.java.domain.Computer;
import main.java.domain.ComputerStatus;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/computer")
public class ComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;

	String SELECT_ALL_COMPUTERSTATUSES = "SELECT id, computerStatus FROM computerStatus";
	String SELECT_ALL_COMPUTERS = "SELECT id, computerStatusId, computerName, computerDescription FROM computer";
	String INSERT_COMPUTER = "INSERT INTO computer (computerStatusId, computerName, computerDescription) VALUES (?, ?, ?)";

	ArrayList<ComputerStatus> computerStatuses = new ArrayList<>();
	ArrayList<Computer> computers = new ArrayList<>();

	public ComputerServlet() {
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
							findById(computerStatusId, computerStatuses)));
				}
				rs.close();
				request.setAttribute("computers", computers);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		String userPath = request.getServletPath();
		if ("/computer".equals(userPath)){
			request.getRequestDispatcher("/WEB-INF/view/computer/computer.jsp")
					.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ConnectionProperty builder = new ConnectionProperty();
		try (Connection conn = builder.getConnection()){
			String computerName = request.getParameter("computerName");
			String computerDescription = request.getParameter("computerDescription");

			String computerStatus = request.getParameter("computerStatus");
			int index1 = computerStatus.indexOf('=');
			int index2 = computerStatus.indexOf(",");
			String r1 = computerStatus.substring(index1+1, index2);
			Long computerStatusId = Long.parseLong(r1.trim());

			try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_COMPUTER)){
			preparedStatement.setLong(1, computerStatusId);
			preparedStatement.setString(2, computerName);
			preparedStatement.setString(3, computerDescription);

			int rows = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	} catch (Exception e) {
		System.out.println(e);
		getServletContext().getRequestDispatcher("/WEB-INF/view/computer/computer.jsp")
				.forward(request, response);
	}
		doGet(request, response);
	}

	// Поиск статуса компьютера по id
	private ComputerStatus findById(Long id, ArrayList<ComputerStatus> computerStatuses) {
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
}
