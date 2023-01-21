package dao;

import constants.OrderStatus;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class StatusDaoTest {

    @Test
    void getAllStatuses() {
        Map<Integer, OrderStatus> expectedStatuses = new HashMap<>();
        expectedStatuses.put(1, OrderStatus.PENDING_PAYMENT);
        expectedStatuses.put(2, OrderStatus.PAID);
        expectedStatuses.put(3, OrderStatus.IN_PROGRESS);
        expectedStatuses.put(4, OrderStatus.COMPLETE);
        expectedStatuses.put(5, OrderStatus.CANCELED);
        assertEquals(expectedStatuses, StatusDao.getInstance().getAllStatuses());
    }

    @Test
    void getStatusById() {
        assertEquals(OrderStatus.PENDING_PAYMENT, StatusDao.getInstance().getStatusById(1));
        assertEquals(OrderStatus.PAID, StatusDao.getInstance().getStatusById(2));
        assertEquals(OrderStatus.IN_PROGRESS, StatusDao.getInstance().getStatusById(3));
        assertEquals(OrderStatus.COMPLETE, StatusDao.getInstance().getStatusById(4));
        assertEquals(OrderStatus.CANCELED, StatusDao.getInstance().getStatusById(5));
    }

    @Test
    void getStatusIdByStatus() {
        assertEquals(1, StatusDao.getInstance().getStatusIdByStatus(OrderStatus.PENDING_PAYMENT));
        assertEquals(2, StatusDao.getInstance().getStatusIdByStatus(OrderStatus.PAID));
        assertEquals(3, StatusDao.getInstance().getStatusIdByStatus(OrderStatus.IN_PROGRESS));
        assertEquals(4, StatusDao.getInstance().getStatusIdByStatus(OrderStatus.COMPLETE));
        assertEquals(5, StatusDao.getInstance().getStatusIdByStatus(OrderStatus.CANCELED));
    }

    @Test
    void removeStatus() {
        assertTrue(StatusDao.getInstance().removeStatus(OrderStatus.TEST));
    }

    @Test
    void addStatus() {
        assertTrue(StatusDao.getInstance().addStatus(OrderStatus.TEST));
    }
}