package main.java.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.dao.DaoVisitor;
import main.java.domain.Visitor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/visitor")
public class VisitorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoVisitor daoVisitor;

	public void init() {
		daoVisitor = new DaoVisitor();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
				default:
					listVisitor(request, response);
					break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listVisitor(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Visitor> listVisitor = daoVisitor.selectAllVisitors();
		request.setAttribute("listVisitor", listVisitor);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/visitor.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
