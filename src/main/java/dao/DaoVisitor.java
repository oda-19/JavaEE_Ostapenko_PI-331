package main.java.dao;

import main.java.domain.Visitor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoVisitor extends ConnectionProperty {
	private static final String SELECT_ALL_VISITORS = "SELECT id, surname, name, patronymic, identitydocument, address, phone" +
			" FROM visitor";

	public DaoVisitor() {

	}

	// Загрузка всех посетителей
	public List<Visitor> selectAllVisitors() {
		List<Visitor> visitors = new ArrayList<>();
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VISITORS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

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
		} catch (SQLException e) {
			System.out.println(e);
		}
		return visitors;
	}
}
