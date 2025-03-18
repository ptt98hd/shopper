package dao;

import entity.Account;
import entity.Order;
import entity.OrderStatus;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends Connector {

	public static void main(String[] args) {
		OrderDao orderDao = new OrderDao();

		for (Order order : orderDao.getOrders()) {
			System.out.println(order);
		}
	}

	public List<Order> getOrders() {
		List<Order> orders = new ArrayList<>();
		String sql = "SELECT * FROM [Order]";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int orderId = resultSet.getInt("orderId");
				String consignee = resultSet.getNString("consignee");
				String phone = resultSet.getNString("phone");
				String address = resultSet.getNString("address");
				Account account = new AccountDao().getAccount(resultSet.getInt("accountId"));
				double total = resultSet.getDouble("total");
				String status = resultSet.getNString("status");
				Order order = new Order(orderId, consignee, phone, address, total, account, OrderStatus.getOrderStatus(status));
				orders.add(order);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return orders;
	}

	public List<Order> getOrders(int accountId) {
		List<Order> orders = new ArrayList<>();
		String sql = "SELECT * FROM [Order] WHERE accountId = ?";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, accountId);
			ResultSet resultSet = preparedStatement.executeQuery();
			Account account = new AccountDao().getAccount(accountId);
			while (resultSet.next()) {
				int orderId = resultSet.getInt("orderId");
				String consignee = resultSet.getNString("consignee");
				String phone = resultSet.getNString("phone");
				String address = resultSet.getNString("address");
				double total = resultSet.getDouble("total");
				String status = resultSet.getNString("status");
				Order order = new Order(orderId, consignee, phone, address, total, account, OrderStatus.getOrderStatus(status));
				orders.add(order);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return orders;
	}

	public Order getOrder(int orderId) {
		Order order = null;
		String sql = "Select * FROM [Order] WHERE orderId = ?";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, orderId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String consignee = resultSet.getNString("consignee");
				String phone = resultSet.getNString("phone");
				String address = resultSet.getNString("address");
				Account account = new AccountDao().getAccount(resultSet.getInt("accountId"));
				double total = resultSet.getDouble("total");
				String status = resultSet.getNString("status");
				order = new Order(orderId, consignee, phone, address, total, account, OrderStatus.getOrderStatus(status));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return order;
	}

	public int insertOrder(Order newOrder) {
		int orderId = 0;
		String sql = """
               INSERT INTO [Order]
               (consignee, phone, address, total, accountId)
               VALUES (?, ?, ?, ?, ?)""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setNString(1, newOrder.getConsignee());
			preparedStatement.setNString(2, newOrder.getPhone());
			preparedStatement.setNString(3, newOrder.getAddress());
			preparedStatement.setDouble(4, newOrder.getTotal());
			preparedStatement.setInt(5, newOrder.getAccount().getAccountId());

			if (preparedStatement.executeUpdate() > 0) {
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				if (generatedKeys.next()) {
					orderId = generatedKeys.getInt(1);
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return orderId;
	}

	public int updateOrder(int orderID, OrderStatus status) {
		int rowAffected = 0;
		String sql = """
               UPDATE [dbo].[Order]
               SET [status] = ?
               WHERE [orderId] = ?""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setNString(1, status.getName());
			preparedStatement.setInt(2, orderID);

			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rowAffected;
	}

	public int deleteOrder(int orderId) {
		int rowAffected = 0;
		String sql = "DELETE FROM [Order] WHERE orderId = ?";
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
