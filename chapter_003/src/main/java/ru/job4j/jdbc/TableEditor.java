package ru.job4j.jdbc;

import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private final Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password")
        );
    }

    public void createTable(String tableName) throws SQLException {
        String sql = "create table if not exists " + tableName + "()";
        execute(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = "DROP TABLE IF EXISTS " + tableName;
        execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = "ALTER TABLE " + tableName + " ADD COLUMN " + columnName + " " + type;
        execute(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = "ALTER TABLE " + tableName + " DROP COLUMN " + columnName;
        execute(sql);
    }

    public void renameColumn(
            String tableName,
            String columnName,
            String newColumnName
    ) throws SQLException {
        String sql = "ALTER TABLE " + tableName
                + " RENAME COLUMN " + columnName
                + " TO " + newColumnName;
        execute(sql);
    }

    private void execute(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    public String getScheme(String tableName) throws SQLException {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}