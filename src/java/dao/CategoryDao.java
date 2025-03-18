package dao;

import entity.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends Connector {

	public List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<>();
		String sql = "SELECT * FROM [Category];";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int categoryId = resultSet.getInt("categoryId");
				String categoryName = resultSet.getNString("categoryName");
				String categoryImg = resultSet.getNString("categoryImg");
				Category category = new Category(categoryId, categoryName, categoryImg);
				categories.add(category);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return categories;
	}

	public Category getCategory(int categoryId) {
		Category category = null;
		String sql = "SELECT * FROM [Category] WHERE [categoryId] = ?;";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, categoryId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String categoryName = resultSet.getNString("categoryName");
				String categoryImg = resultSet.getNString("categoryImg");
				category = new Category(categoryId, categoryName, categoryImg);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return category;
	}

	public int insertCategory(Category newCategory) {
		int rowAffected = 0;
		String sql = """
               INSERT INTO [Category](categoryName, categoryImg)
               VALUES (?, ?);""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setNString(1, newCategory.getCategoryName());
			preparedStatement.setNString(2, newCategory.getCategoryImg());
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rowAffected;
	}

	public int updateCategory(int categoryId, Category newCategory) {
		int rowAffected = 0;
		String sql = """
               UPDATE [Category]
               SET [categoryName] = ?, [categoryImg] = ?
               WHERE [categoryId] = ?;""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setNString(1, newCategory.getCategoryName());
			preparedStatement.setNString(2, newCategory.getCategoryImg());
			preparedStatement.setInt(3, categoryId);
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rowAffected;
	}

	public int deleteCategory(int categoryId) {
		int rowAffected = 0;
		String sql = "DELETE FROM [Category] WHERE [categoryId] = ?";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, categoryId);
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rowAffected;
	}
}
