package main.java.dao;

import main.java.domain.ComputerStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoComputerStatus extends ConnectionProperty {
	private static final String SELECT_ALL_COMPUTERSTATUSES = "SELECT id, computerStatus FROM computerStatus";

	public DaoComputerStatus() {

	}

	// Загрузка всех компьютерных статусов
	public List<ComputerStatus> selectAllComputerStatuses() {
		List<ComputerStatus> computerStatuses = new ArrayList<>();
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COMPUTERSTATUSES);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				computerStatuses.add(new ComputerStatus(
						rs.getLong("id"),
						rs.getString("computerStatus")));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return computerStatuses;
	}
}
