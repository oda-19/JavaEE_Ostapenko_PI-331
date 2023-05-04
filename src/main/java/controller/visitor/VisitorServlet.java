package main.java.controller.visitor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.dao.ConnectionProperty;
import main.java.domain.Visitor;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/visitor")
public class VisitorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	String userPath;

	String SELECT_ALL_VISITORS = "SELECT id, surname, name, patronymic, identityDocument, address, phone FROM visitor";
	String INSERT_VISITOR = "INSERT INTO visitor (surname, name, patronymic, identityDocument, address, phone) VALUES (?, ?, ?, ?, ?, ?)";

	ArrayList<Visitor> visitors = new ArrayList<>();

	public VisitorServlet() {
		prop = new ConnectionProperty();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		ConnectionProperty builder = new ConnectionProperty();

		try (Connection conn = builder.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_VISITORS);
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
		} catch (Exception e) {
			System.out.println(e);
		}

		userPath = request.getServletPath();
		if("/visitor".equals(userPath)){
			request.getRequestDispatcher("/WEB-INF/view/visitor/visitor.jsp")
					.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ConnectionProperty builder = new ConnectionProperty();

		try (Connection conn = builder.getConnection()){
			String surname = request.getParameter("surname");
			String name = request.getParameter("name");
			String patronymic = request.getParameter("patronymic");
			String identityDocument = request.getParameter("identityDocument");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			Visitor newVisitor = new Visitor(surname, name, patronymic, identityDocument, address, phone);

			try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_VISITOR)){
				preparedStatement.setString(1, newVisitor.getSurname());
				preparedStatement.setString(2, newVisitor.getName());
				preparedStatement.setString(3, newVisitor.getPatronymic());
				preparedStatement.setString(4, newVisitor.getIdentityDocument());
				preparedStatement.setString(5, newVisitor.getAddress());
				preparedStatement.setString(6, newVisitor.getPhone());

				int result = preparedStatement.executeUpdate();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
			getServletContext().getRequestDispatcher("/WEB-INF/view/visitor/visitor.jsp")
					.forward(request, response);
		}
		doGet(request, response);
	}
}
