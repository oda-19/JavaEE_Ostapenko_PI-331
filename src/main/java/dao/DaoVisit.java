package main.java.dao;

import main.java.domain.Computer;
import main.java.domain.ComputerStatus;
import main.java.domain.Visit;
import main.java.domain.Visitor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoVisit extends ConnectionProperty {
	private static final String SELECT_ALL_COMPUTERSTATUSES = "SELECT id, computerStatus FROM computerStatus";
	private static final String SELECT_ALL_COMPUTERS = "SELECT id, computerStatusId, computerName, computerDescription FROM computer";
	private static final String SELECT_ALL_VISITORS = "SELECT id, surname, name, patronymic, identitydocument, address, phone FROM visitor";
	private static final String SELECT_ALL_VISITS = "SELECT id, visitorId, computerId, visitDate, visitLenth, pay FROM visit";
	public DaoVisit() {

	}

	// Поиск статуса компьютера по id
	private ComputerStatus findByIdComputerStatus(Long id, ArrayList<ComputerStatus> computerStatuses) {
		if (computerStatuses != null) {
			for (ComputerStatus r: computerStatuses) {
				if ((r.getStatusId()).equals(id)) {
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
				if ((r.getComputerId()).equals(id)) {
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
				if ((r.getVisitorId()).equals(id)) {
					return r;
				}
			}
		} else {
			return null;
		}
		return null;
	}

	// Загрузка всех посещений
	public List<Visit> selectAllVisits() {
		List<ComputerStatus> computerStatuses = new ArrayList<>();
		List<Computer> computers = new ArrayList<>();
		List<Visitor> visitors = new ArrayList<>();
		List<Visit> visits = new ArrayList<>();
		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COMPUTERSTATUSES);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				computerStatuses.add(new ComputerStatus(
				rs.getLong("id"),
				rs.getString("computerStatus")));
			}

			long computerStatusId;
			preparedStatement = connection.prepareStatement(SELECT_ALL_COMPUTERS);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				computerStatusId = rs.getLong("computerStatusId");
				computers.add(new Computer(
						rs.getLong("id"),
						rs.getString("computerName"),
						rs.getString("computerDescription"),
						computerStatusId,
						findByIdComputerStatus(computerStatusId, (ArrayList<ComputerStatus>) computerStatuses)
				));
			}

			preparedStatement = connection.prepareStatement(SELECT_ALL_VISITORS);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				visitors.add(new Visitor(
						rs.getLong("id"),
						rs.getString("surname"),
						rs.getString("name"),
						rs.getString("patronymic"),
						rs.getString("identitydocument"),
						rs.getString("address"),
						rs.getString("phone")));
			}

			long visitorId;
			long computerId;
			preparedStatement = connection.prepareStatement(SELECT_ALL_VISITS);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				visitorId = rs.getLong("visitorId");
				computerId = rs.getLong("computerId");
				visits.add(new Visit(
						rs.getLong("id"),
						rs.getDate("visitDate"),
						rs.getString("visitLenth"),
						rs.getInt("pay"),
						visitorId,
						findByIdVisitor(visitorId, (ArrayList<Visitor>) visitors),
						computerId,
						findByIdComputer(computerId, (ArrayList<Computer>) computers)
				));
			}

		} catch (SQLException e) {
			System.out.println(e);
		}
		return visits;
	}
}
