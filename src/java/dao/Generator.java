package dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Generator extends Connector {

	private final String PACKAGE_NAME = "entity";
	private final String FILE_EXTENSION = ".java";

	private final String IMPORT = """
			import lombok.*;""";
	private final String ANOTATION = """
			@NoArgsConstructor
			@AllArgsConstructor
			@Getter
			@Setter
			@ToString""";

	public static void main(String[] args) {
		Generator generator = new Generator();
		try {
			generator.generateAllEntitieClasses();
		} catch (IOException | SQLException ex) {
			Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void generateAllEntitieClasses() throws IOException, SQLException {
		DatabaseMetaData metaData = getConnection().getMetaData();
		ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
		while (tables.next()) {
			String tableName = tables.getString("TABLE_NAME");
			String schemaName = tables.getString("TABLE_SCHEM");
			if ("sys".equalsIgnoreCase(schemaName) || "INFORMATION_SCHEMA".equalsIgnoreCase(schemaName) || tableName.equalsIgnoreCase("sysdiagrams")) {
				continue;
			}
			ResultSet columns = metaData.getColumns(null, null, tableName, "%");
			String entityCode = generateEntityCode(tableName, columns);
			generateEntityClass(tableName, entityCode);
		}
	}

	private void generateEntityClass(String tableName, String entityCode) throws IOException {
		File file = new File("src/java/" + PACKAGE_NAME.replace('.', '/') + "/" + tableName + FILE_EXTENSION);
		file.getParentFile().mkdirs();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(entityCode);
		}
	}

	private String generateEntityCode(String tableName, ResultSet columns) throws SQLException {
		StringBuilder code = new StringBuilder();
		String className = tableName.replaceAll(" ", "");
		code.append("package ").append(PACKAGE_NAME).append(";\n\n");
		code.append(IMPORT).append("\n\n");
		code.append(ANOTATION).append("\n");
		code.append("public class ").append(className).append(" {\n");
		while (columns.next()) {
			String fieldName = columns.getString("COLUMN_NAME");
			String fieldType = convertDataType(columns.getString("TYPE_NAME"));
			code.append("    private ").append(fieldType).append(" ").append(fieldName).append(";\n");
		}
		code.append("}\n");
		return code.toString();
	}

	private String convertDataType(String sqlType) {
		sqlType = sqlType.replace("identity", "").trim().toUpperCase();
		switch (sqlType.toUpperCase()) {
			case "BIT": return "boolean";
			case "SMALLINT": return "short";
			case "INT": return "int";
			case "BIGINT": return "long";
			case "REAL", "FLOAT": return "float";
			case "MONEY", "DECIMAL": return "double";
			case "VARCHAR", "NVARCHAR", "TEXT", "NTEXT", "NCHAR": return "String";
			case "DATETIME", "DATE": return "Date";
			case "IMAGE": return "byte[]";
			default: return sqlType;
		}
	}
}
