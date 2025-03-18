package dao;

import entity.ProductSize;
import entity.Product;
import entity.Size;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSizeDao extends Connector {

//	READ
//	===================================================================================================================
	public List<ProductSize> getProductSizes(int productId) {
		List<ProductSize> productSizes = new ArrayList<>();
		String sql = """
               SELECT * FROM ProductSize
               JOIN Product ON Product.productId = ProductSize.productId
               JOIN Size ON Size.sizeId = ProductSize.sizeId
               WHERE ProductSize.productId  = ?""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, productId);
			ResultSet resultSet = preparedStatement.executeQuery();
			Product product = new ProductDao().getProduct(productId);
			while (resultSet.next()) {
				int productSizeId = resultSet.getInt("productSizeId");
				Size size = new Size(
					resultSet.getInt("sizeId"),
					resultSet.getShort("sizeEu")
				);
				ProductSize productSize = new ProductSize(productSizeId, product, size);
				productSizes.add(productSize);
			}
		} catch (SQLException e) {
		}
		return productSizes;
	}

	public ProductSize getProductSize(int productSizeId) {
		ProductSize productSize = null;
		String sql = """
               SELECT * FROM ProductSize
               JOIN Product ON Product.productId = ProductSize.productId
               JOIN Size ON Size.sizeId = ProductSize.sizeId
               WHERE [productSizeId] = ?""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, productSizeId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Product product = new ProductDao().getProduct(resultSet.getInt("productId"));
				Size size = new Size(
					resultSet.getInt("sizeId"),
					resultSet.getShort("sizeEu")
				);
				productSize = new ProductSize(productSizeId, product, size);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return productSize;
	}

//	CREATE
//	===================================================================================================================
	public int insertProductSize(ProductSize productSize) {
		int rowAffected = 0;
		String sql = """
               INSERT INTO ProductSize
               (productId, sizeId)
               VALUES (?, ?)""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, productSize.getProduct().getProductId());
			preparedStatement.setInt(2, productSize.getSize().getSizeId());
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rowAffected;
	}

	public int insertProductSizes(List<ProductSize> productSizes) {
		int rowAffected = 0;
		for (ProductSize productSize : productSizes) {
			rowAffected += insertProductSize(productSize);
		}
		return rowAffected;
	}

//	DELETE
//	===================================================================================================================
	public int deleteProductSize(int productSizeId) {
		int rowAffected = 0;
		String sql = "DELETE FROM [ProductSize] WHERE [productSizeId] = ?;";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, productSizeId);
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rowAffected;
	}

	public int deleteProductSizes(int productId) {
		int rowAffected = 0;
		String sql = "DELETE FROM [ProductSize] WHERE [productId] = ?;";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, productId);
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rowAffected;
	}
}
