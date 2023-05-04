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

@WebServlet("/deleteComputerStatus")
public class DeleteComputerStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;
    String userPath;

    String SELECT_ALL_COMPUTERSTATUSES = "SELECT id, computerStatus FROM computerStatus ORDER BY id ASC";
    String SELECT_COMPUTERSTATUS_BY_ID = "SELECT id, computerStatus FROM computerStatus WHERE id = ?";
    String DELETE_COMPUTERSTATUS = "DELETE FROM computerStatus WHERE id = ?";

    ArrayList<ComputerStatus> computerStatuses = new ArrayList<>();
    ArrayList<ComputerStatus> deleteComputerStatus = new ArrayList<>();

    public DeleteComputerStatusServlet() {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        ConnectionProperty builder = new ConnectionProperty();

        try (Connection conn = builder.getConnection()) {
            String strId = request.getParameter("id");
            Long id = null;
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
                    deleteComputerStatus.clear();
                    while (rs.next()) {
                        deleteComputerStatus.add(new ComputerStatus(rs.getLong("id"),
                                rs.getString("computerStatus")));
                    }
                    rs.close();
                    request.setAttribute("computerStatusDelete",
                            deleteComputerStatus);
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
        if ("/deleteComputerStatus".equals(userPath)) {
            request.getRequestDispatcher("/WEB-INF/view/computerStatus/deleteComputerStatus.jsp")
                    .forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConnectionProperty builder = new ConnectionProperty();

        try (Connection conn = builder.getConnection()) {
            Long id = Long.parseLong(request.getParameter("id"));
            try (PreparedStatement preparedStatement = conn.prepareStatement(DELETE_COMPUTERSTATUS)) {
                preparedStatement.setLong(1, id);

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
