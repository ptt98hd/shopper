package dao;

import entity.Brand;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandDao extends Connector {

	public List<Brand> getBrands() {
		List<Brand> brands = new ArrayList<>();
		String sql = "SELECT * FROM [Brand];";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int brandId = resultSet.getInt("brandId");
				String brandName = resultSet.getNString("brandName");
				String brandImg = resultSet.getNString("brandImg");
				Brand brand = new Brand(brandId, brandName, brandImg);
				brands.add(brand);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return brands;
	}

	public Brand getBrand(int brandId) {
		Brand brand = null;
		String sql = "SELECT * FROM [Brand] WHERE [brandId] = ?;";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, brandId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String brandName = resultSet.getNString("brandName");
				String brandImg = resultSet.getNString("brandImg");
				brand = new Brand(brandId, brandName, brandImg);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return brand;
	}

	public int insertBrand(Brand newBrand) {
		int rowAffected = 0;
		String sql = """
               INSERT INTO [Brand](BrandName, brandImg)
               VALUES (?, ?);""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setNString(1, newBrand.getBrandName());
			preparedStatement.setNString(2, newBrand.getBrandImg());
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rowAffected;
	}

	public int updateBrand(int brandId, Brand newBrand) {
		int rowAffected = 0;
		String sql = """
               UPDATE [Brand]
               SET [BrandName] = ?, [brandImg] = ?
               WHERE [brandId] = ?;""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setNString(1, newBrand.getBrandName());
			preparedStatement.setNString(2, newBrand.getBrandImg());
			preparedStatement.setInt(3, brandId);
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rowAffected;
	}

	public int deleteBrand(int brandId) {
		int rowAffected = 0;
		String sql = """
               UPDATE [Brand]
               SET [disable] = 1
               WHERE [brandId] = ?""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, brandId);
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rowAffected;
	}
}
