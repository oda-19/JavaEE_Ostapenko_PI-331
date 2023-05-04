package main.java.controller.visitor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.dao.ConnectionProperty;
import main.java.domain.Visitor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet("/deleteVisitor")
public class DeleteVisitorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;
    String userPath;

    String SELECT_ALL_VISITORS = "SELECT id, surname, name, patronymic, identityDocument, address, phone FROM visitor ORDER BY id ASC";
    String SELECT_VISITOR_BY_ID = "SELECT id, surname, name, patronymic, identityDocument, address, phone FROM visitor WHERE id = ?";
    String DELETE_VISITOR = "DELETE FROM visitor WHERE id = ?";

    ArrayList<Visitor> visitors = new ArrayList<>();
    ArrayList<Visitor> deleteVisitor = new ArrayList<>();

    public DeleteVisitorServlet() {
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
            } else {
                System.out.println("Ошибка загрузки visitor");
            }

            try (PreparedStatement preparedStatement = conn.prepareStatement(SELECT_VISITOR_BY_ID)) {
                preparedStatement.setLong(1, id);
                rs = preparedStatement.executeQuery();
                if (rs != null) {
                    deleteVisitor.clear();
                    while (rs.next()) {
                        deleteVisitor.add(new Visitor(rs.getLong("id"),
                                rs.getString("surname"),
                                rs.getString("name"),
                                rs.getString("patronymic"),
                                rs.getString("identityDocument"),
                                rs.getString("address"),
                                rs.getString("phone")));
                    }
                    rs.close();
                    request.setAttribute("visitorDelete",
                            deleteVisitor);
                } else {
                    System.out.println("Ошибка загрузки visitor");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        userPath = request.getServletPath();
        if ("/deleteVisitor".equals(userPath)) {
            request.getRequestDispatcher("/WEB-INF/view/visitor/deleteVisitor.jsp")
                    .forward(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConnectionProperty builder = new ConnectionProperty();

        try (Connection conn = builder.getConnection()) {
            Long id = Long.parseLong(request.getParameter("id"));
            try (PreparedStatement preparedStatement = conn.prepareStatement(DELETE_VISITOR)) {
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
