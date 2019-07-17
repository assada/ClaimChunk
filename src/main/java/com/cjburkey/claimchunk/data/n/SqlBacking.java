package com.cjburkey.claimchunk.data.n;

import com.cjburkey.claimchunk.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

final class SqlBacking {

    static Connection connect() throws SQLException, ClassNotFoundException {
        return connect(Config.getString("database", "hostname"),
                Config.getInt("database", "port"),
                Config.getString("database", "database"),
                Config.getString("database", "username"),
                Config.getString("database", "password"));
    }

    private static Connection connect(String hostname, int port, String databaseName, String username, String password) throws ClassNotFoundException, SQLException {
        // Make sure JDBC is loaded
        Class.forName("com.mysql.jdbc.Driver");

        // Create a connection with JDBC
        return DriverManager.getConnection(
                String.format("jdbc:mysql://%s:%s/%s", hostname, port, databaseName),
                username,
                password);
    }

    static boolean tableDoesntExist(Connection connection, String databaseName, String tableName) throws SQLException {
        String sql = "SELECT count(*) FROM information_schema.TABLES WHERE (TABLE_SCHEMA = ?) AND (TABLE_NAME = ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, databaseName);
            statement.setString(2, tableName);
            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) return results.getInt(1) <= 0;
            }
        }
        return true;
    }

}