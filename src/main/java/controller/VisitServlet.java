package main.java.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.dao.DaoComputer;
import main.java.dao.DaoVisit;
import main.java.dao.DaoVisitor;
import main.java.domain.Computer;
import main.java.domain.Visit;
import main.java.domain.Visitor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/visit")
public class VisitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoComputer daoComputer;
	private DaoVisitor daoVisitor;
	private DaoVisit daoVisit;

	public void init() {
		daoComputer = new DaoComputer();
		daoVisitor = new DaoVisitor();
		daoVisit = new DaoVisit();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
				default:
					listVisit(request, response);
					break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listVisit(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Computer> listComputer = daoComputer.selectAllComputers();
		List<Visitor> listVisitor = daoVisitor.selectAllVisitors();
		List<Visit> listVisit = daoVisit.selectAllVisits();
		request.setAttribute("listComputer", listComputer);
		request.setAttribute("listVisitor", listVisitor);
		request.setAttribute("listVisit", listVisit);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/visit.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
