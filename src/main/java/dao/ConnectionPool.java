package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConnectionPool {
    private static final int POOL_SIZE = 10;
    private static final String url = "jdbc:mysql://localhost:3306/repair_agency";
    private static final String user = "root";
    private static final String password = "Qwertyu2_";


    private static ConnectionPool instance;
    private final List<Connection> connections = new ArrayList<>();
    private final List<Connection> usedConnections = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectionPool that = (ConnectionPool) o;
        return connections.equals(that.connections) && usedConnections.equals(that.usedConnections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connections, usedConnections);
    }


    private ConnectionPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            connections.add(createConnection());
        }
    }

    private static Connection createConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = connections
                .remove(connections.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        connections.add(connection);
        return usedConnections.remove(connection);
    }
}
