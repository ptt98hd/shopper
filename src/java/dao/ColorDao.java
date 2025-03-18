package dao;

import entity.Color;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColorDao extends Connector {

	public List<Color> getAllColors() {
		List<Color> colors = new ArrayList<>();
		String query = "SELECT colorId, colorName, hex FROM Color";
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int colorId = rs.getInt("colorId");
				String colorName = rs.getString("colorName");
				String hex = rs.getString("hex");
				Color color = new Color(colorId, colorName, hex);
				colors.add(color);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return colors;
	}

	public Color getColor(int colorId) {
		Color color = null;
		String query = "SELECT colorId, colorName, hex FROM Color WHERE colorId = ?";
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, colorId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String colorName = rs.getString("colorName");
				String hex = rs.getString("hex");
				color = new Color(colorId, colorName, hex);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return color;
	}

	public int insertColor(Color newColor) {
		int result = 0;
		String query = "INSERT INTO Color (colorName, hex) VALUES (?, ?)";
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newColor.getColorName());
			pstmt.setString(2, newColor.getHex());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return result;
	}

	public int updateColor(int colorId, Color newColor) {
		int result = 0;
		String query = "UPDATE Color SET colorName = ?, hex = ? WHERE colorId = ?";
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newColor.getColorName());
			pstmt.setString(2, newColor.getHex());
			pstmt.setInt(3, colorId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return result;
	}

	public int deleteColor(int colorId) {
		int result = 0;
		String query = "DELETE FROM Color WHERE colorId = ?";
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, colorId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return result;
	}
}
