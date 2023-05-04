package main.java.controller.computerStatus;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.dao.ConnectionProperty;
import main.java.domain.ComputerStatus;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet("/editComputerStatus")
public class EditComputerStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;
    String userPath;

    String SELECT_ALL_COMPUTERSTATUSES = "SELECT id, computerStatus FROM computerStatus ORDER BY id ASC";
    String SELECT_COMPUTERSTATUS_BY_ID = "SELECT id, computerStatus FROM computerStatus WHERE id = ?";
    String EDIT_COMPUTERSTATUS = "UPDATE computerStatus SET computerStatus = ? WHERE id = ?";

    ArrayList<ComputerStatus> computerStatuses = new ArrayList<>();
    ArrayList<ComputerStatus> editComputerStatus = new ArrayList<>();

    public EditComputerStatusServlet() {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        ConnectionProperty builder = new ConnectionProperty();

        try (Connection conn = builder.getConnection()) {
            String strId = request.getParameter("id");
            Long id = null; // id редактируемого статуса компьютера
            if (strId != null) {
                id = Long.parseLong(strId);
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

            try (PreparedStatement preparedStatement = conn.prepareStatement(SELECT_COMPUTERSTATUS_BY_ID)) {
                preparedStatement.setLong(1, id);
                rs = preparedStatement.executeQuery();
                if (rs != null) {
                    editComputerStatus.clear();
                    while (rs.next()) {
                        editComputerStatus.add(new ComputerStatus(rs.getLong("id"),
                                rs.getString("computerStatus")));
                    }
                    rs.close();
                    request.setAttribute("computerStatusEdit", editComputerStatus);
                } else {
                    System.out.println("Ошибка загрузки computerStatus");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        userPath = request.getServletPath();
        if("/editComputerStatus".equals(userPath)){
            request.getRequestDispatcher("/WEB-INF/view/computerStatus/editComputerStatus.jsp")
                    .forward(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConnectionProperty builder = new ConnectionProperty();

        try (Connection conn = builder.getConnection()) {
            String strId = request.getParameter("id");
            Long id = null;
            if (strId != null) {
                id = Long.parseLong(strId);
            }

            String computerStatus = request.getParameter("computerStatus");
            try (PreparedStatement preparedStatement = conn.prepareStatement(EDIT_COMPUTERSTATUS)) {
                preparedStatement.setString(1, computerStatus);
                preparedStatement.setLong(2, id);

                int result = preparedStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        doGet(request, response);
    }
}
