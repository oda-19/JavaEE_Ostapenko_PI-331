package main.java.controller.visit;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.dao.ConnectionProperty;
import main.java.domain.Computer;
import main.java.domain.ComputerStatus;
import main.java.domain.Visit;
import main.java.domain.Visitor;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/deleteVisit")
public class DeleteVisitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;

    String SELECT_ALL_COMPUTERSTATUSES = "SELECT id, computerStatus FROM computerStatus";
    String SELECT_ALL_COMPUTERS = "SELECT id, computerStatusId, computerName, computerDescription FROM computer";
    String SELECT_ALL_VISITORS = "SELECT id, surname, name, patronymic, identityDocument, address, phone FROM visitor";
    String SELECT_ALL_VISITS = "SELECT id, visitorId, computerId, visitDate, visitLength, pay FROM visit ORDER BY id ASC";
    String SELECT_VISIT_BY_ID = "SELECT id, visitorId, computerId, visitDate, visitLength, pay FROM visit WHERE id = ?";
    String DELETE_VISIT = "DELETE FROM visit WHERE id = ?";

    ArrayList<ComputerStatus> computerStatuses = new ArrayList<>();
    ArrayList<Computer> computers = new ArrayList<>();
    ArrayList<Visitor> visitors = new ArrayList<>();
    ArrayList<Visit> visits = new ArrayList<>();
    ArrayList<Visit> deleteVisit = new ArrayList<>();

    public DeleteVisitServlet() {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        ConnectionProperty builder = new ConnectionProperty();

        try (Connection conn = builder.getConnection()) {
            String strId = request.getParameter("id");
            Long idVisitSelected = null;
            if (strId != null) {
                idVisitSelected = Long.parseLong(strId);
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
                            findByIdComputerStatus(computerStatusId, computerStatuses)));
                }
                rs.close();
                request.setAttribute("computers", computers);
            } else {
                System.out.println("Ошибка загрузки computer");
            }

            stmt = conn.createStatement();
            rs = stmt.executeQuery(SELECT_ALL_VISITORS);
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

            long visitorId;
            long computerId;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SELECT_ALL_VISITS);
            if (rs != null) {
                visits.clear();
                while (rs.next()) {
                    visitorId = rs.getLong("visitorId");
                    computerId = rs.getLong("computerId");
                    visits.add(new Visit(rs.getLong("id"),
                            rs.getDate("visitDate"),
                            rs.getString("visitLength"),
                            rs.getFloat("pay"),
                            visitorId,
                            findByIdVisitor(visitorId, visitors),
                            computerId,
                            findByIdComputer(computerId, computers)));
                }
                rs.close();
                request.setAttribute("visits", visits);
            } else {
                System.out.println("Ошибка загрузки visit");
            }

            try (PreparedStatement preparedStatement = conn.prepareStatement(SELECT_VISIT_BY_ID)) {
                preparedStatement.setLong(1, idVisitSelected);
                rs = preparedStatement.executeQuery();
                if (rs != null) {
                    deleteVisit.clear();
                    Long idVisitor = null;
                    Long idComputer = null;
                    while (rs.next()) {
                        deleteVisit.add(new Visit(rs.getLong("id"),
                                rs.getDate("visitDate"),
                                rs.getString("visitLength"),
                                rs.getFloat("pay"),
                                idVisitor,
                                findByIdVisitor(idVisitor, visitors),
                                idComputer,
                                findByIdComputer(idComputer, computers)));
                    }
                    rs.close();
                    request.setAttribute("visitDelete", deleteVisit);
                } else {
                    System.out.println("Ошибка загрузки visit");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        String userPath = request.getServletPath();
        if("/deleteVisit".equals(userPath)){
            request.getRequestDispatcher("/WEB-INF/view/visit/deleteVisit.jsp")
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

            try (PreparedStatement preparedStatement = conn.prepareStatement(DELETE_VISIT)) {
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

    // Поиск статуса компьютера по id
    private ComputerStatus findByIdComputerStatus(Long id, ArrayList<ComputerStatus> computerStatuses) {
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

    // Поиск компьютера по id
    private Computer findByIdComputer(Long id, ArrayList<Computer> computers) {
        if (computers != null) {
            for (Computer r: computers) {
                if ((r.getId()).equals(id)) {
                    return r;
                }
            }
        } else {
            return null;
        }
        return null;
    }

    // Поиск посетителя по id
    private Visitor findByIdVisitor(Long id, ArrayList<Visitor> visitors) {
        if (visitors != null) {
            for (Visitor r: visitors) {
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
