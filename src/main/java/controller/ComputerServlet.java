package main.java.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.dao.DaoComputer;
import main.java.dao.DaoComputerStatus;
import main.java.domain.Computer;
import main.java.domain.ComputerStatus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/computer")
public class ComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoComputerStatus daoComputerStatus;
	private DaoComputer daoComputer;

	public void init() {
		daoComputerStatus = new DaoComputerStatus();
		daoComputer = new DaoComputer();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
				default:
					listComputer(request, response);
					break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listComputer(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<ComputerStatus> listComputerStatus = daoComputerStatus.selectAllComputerStatuses();
		List<Computer> listComputer = daoComputer.selectAllComputers();
		request.setAttribute("listComputer", listComputer);
		request.setAttribute("listComputerStatus", listComputerStatus);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/computer.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
