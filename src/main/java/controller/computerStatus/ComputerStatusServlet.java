package main.java.controller.computerStatus;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.dao.ConnectionProperty;
import main.java.domain.ComputerStatus;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/computerStatus")
public class ComputerStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	String userPath;

	String SELECT_ALL_COMPUTERSTATUSES = "SELECT id, computerStatus FROM computerStatus";
	String INSERT_COMPUTERSTATUS = "INSERT INTO computerStatus (computerStatus) VALUES (?)";

	ArrayList<ComputerStatus> computerStatuses = new ArrayList<>();

	public ComputerStatusServlet() {
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
		} catch (Exception e) {
			System.out.println(e);
		}

		userPath = request.getServletPath();
		if("/computerStatus".equals(userPath)){
			request.getRequestDispatcher("/WEB-INF/view/computerStatus/computerStatus.jsp")
					.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ConnectionProperty builder = new ConnectionProperty();

		try (Connection conn = builder.getConnection()){
			String computerStatus = request.getParameter("computerStatus");
			ComputerStatus newComputerStatus = new ComputerStatus(computerStatus);

			try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_COMPUTERSTATUS)){
				preparedStatement.setString(1, newComputerStatus.getComputerStatus());

				int result = preparedStatement.executeUpdate();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
			getServletContext().getRequestDispatcher("/WEB-INF/view/computerStatus/computerStatus.jsp")
					.forward(request, response);
		}
		doGet(request, response);
	}
}