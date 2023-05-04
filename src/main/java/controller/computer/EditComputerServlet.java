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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet("/editComputer")
public class EditComputerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;

    String SELECT_ALL_COMPUTERS = "SELECT id, computerStatusId, computerName, computerDescription FROM computer ORDER BY id ASC";
    String SELECT_ALL_COMPUTERSTATUSES = "SELECT id, computerStatus FROM computerStatus";
    String SELECT_COMPUTER_BY_ID = "SELECT id, computerStatusId, computerName, computerDescription FROM computer WHERE id = ?";
    String EDIT_COMPUTER = "UPDATE computer SET computerStatusId = ?, computerName = ?, computerDescription = ? WHERE id = ?";

    ArrayList<ComputerStatus> computerStatuses = new ArrayList<>();
    ArrayList<Computer> computers = new ArrayList<>();
    ArrayList<Computer> editComputer = new ArrayList<>();

    public EditComputerServlet() {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        ConnectionProperty builder = new ConnectionProperty();

        try (Connection conn = builder.getConnection()) {
            String strId = request.getParameter("id");
            Long idComputerSelected = null;
            if (strId != null) {
                idComputerSelected = Long.parseLong(strId);
            }

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
            } else {
                System.out.println("Ошибка загрузки computerStatus");
            }

            stmt = conn.createStatement();
            Long computerStatusId;
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
            } else {
                System.out.println("Ошибка загрузки computer");
            }

            try (PreparedStatement preparedStatement = conn.prepareStatement(SELECT_COMPUTER_BY_ID)) {
                preparedStatement.setLong(1, idComputerSelected);
                rs = preparedStatement.executeQuery();
                if (rs != null) {
                    editComputer.clear();
                    while (rs.next()) {
                        editComputer.add(new Computer(rs.getLong("id"),
                                rs.getString("computerName"),
                                rs.getString("computerDescription"),
                                rs.getLong("computerStatusId")));
                    }
                    rs.close();
                    request.setAttribute("computerEdit", editComputer);
                } else {
                    System.out.println("Ошибка загрузки computer");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        String userPath = request.getServletPath();
        if("/editComputer".equals(userPath)){
            request.getRequestDispatcher("/WEB-INF/view/computer/editComputer.jsp")
                    .forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConnectionProperty builder = new ConnectionProperty();

        try (Connection conn = builder.getConnection()){
            String strId = request.getParameter("id");
            Long id = null;
            if (strId != null) {
                id = Long.parseLong(strId);
            }

            String computerName = request.getParameter("computerName");
            String computerDescription = request.getParameter("computerDescription");

            String computerStatus = request.getParameter("computerStatus");
            int index1 = computerStatus.indexOf('=');
            int index2 = computerStatus.indexOf(",");
            String r1 = computerStatus.substring(index1+1, index2);
            Long computerStatusId = Long.parseLong(r1.trim());

            PreparedStatement preparedStatement = conn.prepareStatement(EDIT_COMPUTER);
            preparedStatement.setLong(1, computerStatusId);
            preparedStatement.setString(2, computerName);
            preparedStatement.setString(3, computerDescription);
            preparedStatement.setLong(4, id);

            int rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
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
