package dao;

import entity.OrderDetail;
import entity.ProductSize;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDao extends Connector {

//	READ
//	===================================================================================================================
	public List<OrderDetail> getOrderDetails(int orderId) {
		List<OrderDetail> orderDetails = new ArrayList<>();
		String sql = """
               SELECT * FROM OrderDetail
               WHERE orderId = ?""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, orderId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int orderDetailId = resultSet.getInt("orderDetailId");
				ProductSize productSize = new ProductSizeDao().getProductSize(resultSet.getInt("productSizeId"));
				int quantity = resultSet.getInt("quantity");
				double total = resultSet.getDouble("total");
				OrderDetail orderDetail = new OrderDetail(orderDetailId, orderId, productSize, quantity, total);
				orderDetails.add(orderDetail);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return orderDetails;
	}

//	CREATE
//	===================================================================================================================
	public int insertOrderDetail(OrderDetail newOrderDetails) {
		int rowAffected = 0;
		String sql = """
               INSERT INTO OrderDetail
               (orderId, productSizeId, quantity, total)
               VALUES (?, ?, ?, ?)""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, newOrderDetails.getOrderId());
			preparedStatement.setInt(2, newOrderDetails.getProductSize().getProductSizeId());
			preparedStatement.setInt(3, newOrderDetails.getQuantity());
			preparedStatement.setDouble(4, newOrderDetails.getTotal());
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rowAffected;
	}

//	DELETE
//	===================================================================================================================
	public int deleteOrderDetail(int orderDetailId) {
		int rowAffected = 0;
		String sql = "DELETE FROM [Order] WHERE orderDetailId = ?";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, orderDetailId);
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rowAffected;
	}

	public int deleteOrderDetails(int orderId) {
		int rowAffected = 0;
		String sql = "DELETE FROM [OrderDetail] WHERE orderId = ?";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, orderId);
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rowAffected;
	}
}
