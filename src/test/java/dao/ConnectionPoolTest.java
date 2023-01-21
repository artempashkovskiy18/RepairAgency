package dao;

import com.mysql.cj.jdbc.ConnectionImpl;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionPoolTest {

    @Test
    void getInstance() {
        ConnectionPool cp = ConnectionPool.getInstance();
        ConnectionPool cpe = ConnectionPool.getInstance();
        assertNotNull(cp);
        assertNotNull(cpe);
        assertEquals(cp, cpe);
    }

    @Test
    void getConnection() {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection connection = cp.getConnection();
        assertNotNull(connection);
    }

    @Test
    void releaseConnection() throws SQLException {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection connectionTrue = cp.getConnection();
        Connection connectionFalse = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/repair_agency", "root", "Qwertyu2_");

        assertTrue(cp.releaseConnection(connectionTrue));
        assertFalse(cp.releaseConnection(connectionFalse));
    }
}