package dao;

import entity.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao extends Connector {

//	READ
//	===============================================================================================
	public Account userLogin(String email, String password) {
		return login(email, password, false);
	}

	public Account adminLogin(String email, String password) {
		return login(email, password, true);
	}

	private Account login(String email, String password, boolean isAdmin) {
		Account account = null;
		String sql = """
               SELECT * FROM [Account]
               WHERE [email] = ? AND [password] = ? AND isAdmin = ?;""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setNString(1, email);
			preparedStatement.setNString(2, password);
			preparedStatement.setBoolean(3, isAdmin);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int accountId = resultSet.getInt("accountId");
				String fullname = resultSet.getNString("fullname");
				password = null;
				account = new Account(accountId, fullname, email, password, isAdmin);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return account;
	}

	public Account getAccount(int accountId) {
		Account account = null;
		String sql = """
               SELECT * FROM [Account]
               WHERE [accountId] = ?""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, accountId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String fullname = resultSet.getNString("fullname");
				String email = resultSet.getNString("email");
				boolean isAdmin = resultSet.getBoolean("isAdmin");
				account = new Account(accountId, fullname, email, "", isAdmin);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return account;
	}

//	CREATE
//	===============================================================================================
	public int createUser(Account account) {
		account.setAdmin(false);
		return createAccount(account);
	}

	public int createAdmin(Account account) {
		account.setAdmin(true);
		return createAccount(account);
	}

	private int createAccount(Account account) {
		int rowAffected = 0;
		String sql = """
               INSERT INTO [Account]
               ([fullname], [email], [password], [isAdmin])
               VALUES (?, ?, ?, ?)""";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setNString(1, account.getFullname());
			preparedStatement.setNString(2, account.getEmail());
			preparedStatement.setNString(3, account.getPassword());
			preparedStatement.setBoolean(4, account.isAdmin());
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rowAffected;
	}

//	UPDATE
//	===============================================================================================
	public int updateAccount(int accountId, Account account) {
		int rowAffected = 0;
		String sql = """
               UPDATE [Account]
               SET [fullname] = ?, [password] = ?
               WHERE accountId = ?""";
		try {
			int index = 1;
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setNString(index++, account.getFullname());
			preparedStatement.setNString(index++, account.getPassword());
			preparedStatement.setInt(index++, account.getAccountId());
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rowAffected;
	}
}
