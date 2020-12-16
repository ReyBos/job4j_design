package ru.job4j.jdbc;

import java.sql.*;

public class StatementDemo {
    private static Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        Config config = new Config("./chapter_003/src/main/resources/app.properties");
        config.load();
        String url = config.get(Config.URL);
        String login = config.get(Config.LOGIN);
        String password = config.get(Config.PASSWORD);
        return DriverManager.getConnection(url, login, password);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
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

    public static void main(String[] args) throws Exception {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "create table if not exists demo_table(%s, %s);",
                        "id serial primary key",
                        "name varchar(255)"
                );
                statement.execute(sql);
                System.out.println(getTableScheme(connection, "demo_table"));
            }
        }
    }
}
