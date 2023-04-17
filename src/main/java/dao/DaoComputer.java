package main.java.dao;

import main.java.domain.Computer;
import main.java.domain.ComputerStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoComputer extends ConnectionProperty {
	private static final String SELECT_ALL_COMPUTERSTATUSES = "SELECT id, computerStatus FROM computerStatus";
	private static final String SELECT_ALL_COMPUTERS = "SELECT id, computerStatusId, computerName, computerDescription FROM computer";

	public DaoComputer() {

	}

	// Поиск статуса компьютера по id
	private ComputerStatus findById(Long id, ArrayList<ComputerStatus> computerStatuses) {
		if(computerStatuses != null) {
			for(ComputerStatus r: computerStatuses) {
				if((r.getStatusId()).equals(id)) {
					return r;
				}
			}
		} else {
			return null;
		}
		return null;
	}

	// Загрузка всех компьютеров
	public List<Computer> selectAllComputers() {
		List<ComputerStatus> computerStatuses = new ArrayList<>();
		List<Computer> computers = new ArrayList<>();
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
						findById(computerStatusId, (ArrayList<ComputerStatus>) computerStatuses)
				));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return computers;
	}
}
