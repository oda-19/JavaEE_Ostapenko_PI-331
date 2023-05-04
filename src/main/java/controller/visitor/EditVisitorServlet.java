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

@WebServlet("/editVisitor")
public class EditVisitorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;
    String userPath;

    String SELECT_ALL_VISITORS = "SELECT id, surname, name, patronymic, identityDocument, address, phone FROM visitor ORDER BY id ASC";
    String SELECT_VISITOR_BY_ID = "SELECT id, surname, name, patronymic, identityDocument, address, phone FROM visitor WHERE id = ?";
    String EDIT_VISITOR = "UPDATE visitor SET surname = ?, name = ?, patronymic = ?, identityDocument = ?, address = ?, phone = ? WHERE id = ?";

    ArrayList<Visitor> visitors = new ArrayList<>();
    ArrayList<Visitor> editVisitor = new ArrayList<>();

    public EditVisitorServlet() {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        ConnectionProperty builder = new ConnectionProperty();

        try (Connection conn = builder.getConnection()) {
            String strId = request.getParameter("id");
            Long id = null; // id редактируемого посетителя
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
                    editVisitor.clear();
                    while (rs.next()) {
                        editVisitor.add(new Visitor(rs.getLong("id"),
                                rs.getString("surname"),
                                rs.getString("name"),
                                rs.getString("patronymic"),
                                rs.getString("identityDocument"),
                                rs.getString("address"),
                                rs.getString("phone")));
                    }
                    rs.close();
                    request.setAttribute("visitorEdit",
                            editVisitor);
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
        if("/editVisitor".equals(userPath)){
            request.getRequestDispatcher("/WEB-INF/view/visitor/editVisitor.jsp")
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

            String surname = request.getParameter("surname");
            String name = request.getParameter("name");
            String patronymic = request.getParameter("patronymic");
            String identityDocument = request.getParameter("identityDocument");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");

            try (PreparedStatement preparedStatement = conn.prepareStatement(EDIT_VISITOR)) {
                preparedStatement.setString(1, surname);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, patronymic);
                preparedStatement.setString(4, identityDocument);
                preparedStatement.setString(5, address);
                preparedStatement.setString(6, phone);
                preparedStatement.setLong(7, id);

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
