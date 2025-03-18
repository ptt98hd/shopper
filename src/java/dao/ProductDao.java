package dao;

import entity.Brand;
import entity.Category;
import entity.Color;
import entity.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends Connector {

	private static final int PAGE_SIZE = 12;
	private static final CategoryDao categoryDao = new CategoryDao();
	private static final BrandDao brandDao = new BrandDao();
	private static final ColorDao colorDao = new ColorDao();

//	READ
//	===================================================================================================================
	public List<Product> getProducts(
		boolean discontinue, int categoryId, int brandId,
		int colorId, String productName, String sortBy, int page
	) {
		List<Product> products = new ArrayList<>();
		String sql = sqlBuilder(
			"select * from Product",
			discontinue, categoryId, brandId, colorId, productName, sortBy, page
		);
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			setParameters(preparedStatement, discontinue, categoryId, brandId, colorId, productName, page);
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int productId = resultSet.getInt("productId");
				productName = resultSet.getNString("productName");
				String productImg = resultSet.getNString("productImg");
				double price = resultSet.getDouble("price");
				Category category = categoryDao.getCategory(resultSet.getInt("categoryId"));
				Brand brand = brandDao.getBrand(resultSet.getInt("brandId"));
				Color color = colorDao.getColor(resultSet.getInt("colorId"));
				Product product = new Product(productId, productName, productImg, price, category, brand, color);
				products.add(product);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return products;
	}

	public int countPages(
		boolean discontinue, int categoryId, int brandId,
		int colorId, String productName
	) {
		int pages = 1;
		String sql = sqlBuilder(
			"SELECT COUNT(*) FROM Product",
			discontinue, categoryId, brandId, colorId, productName, null, 0
		);
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			setParameters(preparedStatement, discontinue, categoryId, brandId, colorId, productName, 0);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int countProducts = resultSet.getInt(1);
				pages = (int) countProducts / PAGE_SIZE + 1;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return pages;
	}

	private String sqlBuilder(
		String sql, Boolean discontinue, int categoryId, int brandId,
		int colorId, String productName, String sortBy, int page
	) {
		StringBuilder query = new StringBuilder(sql);
		query.append(" WHERE 1 = 1");

		if (discontinue != null) query.append(" AND discontinue = ?");
		if (categoryId != 0) query.append(" AND categoryId = ?");
		if (brandId != 0) query.append(" AND brandId = ?");
		if (colorId != 0) query.append(" AND colorId = ?");
		if (productName != null && !productName.isBlank()) query.append(" AND productName LIKE ?");
		if (sortBy != null && !sortBy.isBlank()) query.append(" ORDER BY ").append(sortBy);
		if (page > 0) query.append(" OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

		return query.toString();
	}

	private void setParameters(
		PreparedStatement preparedStatement, Boolean discontinue,
		int categoryId, int brandId, int colorId, String productName, int page
	) throws SQLException {
		int index = 1;

		if (discontinue != null) preparedStatement.setBoolean(index++, discontinue);
		if (categoryId != 0) preparedStatement.setInt(index++, categoryId);
		if (brandId != 0) preparedStatement.setInt(index++, brandId);
		if (colorId != 0) preparedStatement.setInt(index++, colorId);
		if (productName != null && !productName.isBlank())
			preparedStatement.setString(index++, "%" + productName.trim() + "%");
		if (page > 0) {
			int offset = (page - 1) * PAGE_SIZE;
			preparedStatement.setInt(index++, offset); // OFFSET
			preparedStatement.setInt(index++, PAGE_SIZE); // FETCH NEXT
		}
	}

	public List<Product> getNewProducts() {
		List<Product> products = new ArrayList<>();
		String sql = """
               SELECT TOP 6 * FROM Product
               WHERE discontinue = 0
               ORDER BY productId DESC""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int productId = resultSet.getInt("productId");
				String productName = resultSet.getNString("productName");
				String productImg = resultSet.getNString("productImg");
				double price = resultSet.getDouble("price");
				Category category = categoryDao.getCategory(resultSet.getInt("categoryId"));
				Brand brand = brandDao.getBrand(resultSet.getInt("brandId"));
				Color color = colorDao.getColor(resultSet.getInt("colorId"));
				Product product = new Product(productId, productName, productImg, price, category, brand, color);
				products.add(product);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return products;
	}

	public List<Product> getSimilarProducts(Product product) {
		List<Product> products = new ArrayList<>();
		String sql = """
               SELECT TOP 6 * FROM Product
               WHERE discontinue = 0
               AND Product.productId <> ?
               AND (
                   (categoryId = ? OR ? IS NULL) OR
                   (brandId = ? OR ? IS NULL) OR
                   (colorId = ? OR ? IS NULL)
               )
               ORDER BY NEWID()""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			int index = 1;
			preparedStatement.setInt(index++, product.getProductId());
			preparedStatement.setInt(index++, product.getCategory().getCategoryId());
			preparedStatement.setInt(index++, product.getCategory().getCategoryId());
			preparedStatement.setInt(index++, product.getBrand().getBrandId());
			preparedStatement.setInt(index++, product.getBrand().getBrandId());
			preparedStatement.setInt(index++, product.getColor().getColorId());
			preparedStatement.setInt(index++, product.getColor().getColorId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int productId = resultSet.getInt("productId");
				String productName = resultSet.getNString("productName");
				String productImg = resultSet.getNString("productImg");
				double price = resultSet.getDouble("price");
				Category category = categoryDao.getCategory(resultSet.getInt("categoryId"));
				Brand brand = brandDao.getBrand(resultSet.getInt("brandId"));
				Color color = colorDao.getColor(resultSet.getInt("colorId"));
				Product similarProduct = new Product(productId, productName, productImg, price, category, brand, color);
				products.add(similarProduct);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return products;
	}

	public Product getProduct(int productId) {
		Product product = null;
		String sql = """
               SELECT * FROM Product
               WHERE discontinue = 0 AND [productId] = ?;""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, productId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String productName = resultSet.getNString("productName");
				String productImg = resultSet.getNString("productImg");
				double price = resultSet.getDouble("price");
				Category category = categoryDao.getCategory(resultSet.getInt("categoryId"));
				Brand brand = brandDao.getBrand(resultSet.getInt("brandId"));
				Color color = colorDao.getColor(resultSet.getInt("colorId"));
				product = new Product(productId, productName, productImg, price, category, brand, color);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return product;
	}

//	CREATE
//	===================================================================================================================
	public int insertProduct(Product newProduct) {
		int productId = 0;
		String sql = """
               INSERT INTO Product
               (productName, productImg, price, categoryId, brandId, colorId)
               VALUES (?, ?, ?, ?, ?, ?);""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setNString(1, newProduct.getProductName());
			preparedStatement.setNString(2, newProduct.getProductImg());
			preparedStatement.setDouble(3, newProduct.getPrice());
			preparedStatement.setInt(4, newProduct.getCategory().getCategoryId());
			preparedStatement.setInt(5, newProduct.getBrand().getBrandId());
			preparedStatement.setInt(6, newProduct.getColor().getColorId());
			if (preparedStatement.executeUpdate() > 0) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					productId = resultSet.getInt(1);
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return productId;
	}

//	UPDATE
//	===================================================================================================================
	public int updateProduct(int productId, Product newProduct) {
		int rowAffected = 0;
		String sql = """
               UPDATE Product
               SET productName = ?, productImg = ?, price = ?,
               categoryId = ?, brandId = ?, colorId = ?
               WHERE productId= ?;""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setNString(1, newProduct.getProductName());
			preparedStatement.setNString(2, newProduct.getProductImg());
			preparedStatement.setDouble(3, newProduct.getPrice());
			preparedStatement.setInt(4, newProduct.getCategory().getCategoryId());
			preparedStatement.setInt(5, newProduct.getBrand().getBrandId());
			preparedStatement.setInt(6, newProduct.getColor().getColorId());
			preparedStatement.setInt(7, productId);
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rowAffected;
	}

//	DELETE
//	===================================================================================================================
	public int deleteProduct(int productId) {
		int rowAffected = 0;
		String sql = "UPDATE Product SET discontinue = 1 WHERE productId = ?";
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
