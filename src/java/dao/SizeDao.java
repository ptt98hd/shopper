package dao;

import entity.Size;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SizeDao extends Connector {

//	READ
//	===================================================================================================================
	public List<Size> getSizes() {
		List<Size> sizes = new ArrayList<>();
		String sql = "SELECT * FROM [Size];";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int sizeId = resultSet.getInt("sizeId");
				short sizeEu = resultSet.getShort("sizeEu");
				Size size = new Size(sizeId, sizeEu);
				sizes.add(size);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return sizes;
	}

	public Size getSize(int sizeId) {
		Size size = null;
		String sql = "SELECT * FROM [Size] WHERE [sizeId] = ?;";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, sizeId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				short sizeEu = resultSet.getShort("sizeEu");
				size = new Size(sizeId, sizeEu);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return size;
	}

//	CREATE
//	===================================================================================================================
	public int insertSize(Size newSize) {
		int rowAffected = 0;
		String sql = """
               INSERT INTO [Size](sizeEu)
               VALUES (?);""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setShort(1, newSize.getSizeEu());
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rowAffected;
	}

//	UPDATE
//	===================================================================================================================
	public int updateSize(int sizeId, Size newSize) {
		int rowAffected = 0;
		String sql = """
               UPDATE [Size]
               SET [sizeEu] = ?
               WHERE sizeId = ?;""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setShort(1, newSize.getSizeEu());
			preparedStatement.setInt(2, sizeId);
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rowAffected;
	}

//	DELETE
//	===================================================================================================================
	public int deleteSize(int sizeId) {
		int rowAffected = 0;
		String sql = "DELETE FROM [Size] WHERE sizeId = ?;";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, sizeId);
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rowAffected;
	}
}
