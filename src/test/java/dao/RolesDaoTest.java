package dao;

import constants.Role;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RolesDaoTest {

    @Test
    void getAllRoles() {
        Map<Integer, Role> expectedRoles = new HashMap<>();
        expectedRoles.put(5,Role.USER);
        expectedRoles.put(2,Role.MANAGER);
        expectedRoles.put(3,Role.CRAFTSMAN);
        Map<Integer, Role> actualRoles = RolesDao.getInstance().getAllRoles();
        assertEquals(expectedRoles, actualRoles);
    }

    @Test
    void getRoleIdByRole() {
        int expected = 1;
        int actual = RolesDao.getInstance().getRoleIdByRole(Role.USER);
        assertEquals(expected, actual);
        expected = 2;
        actual = RolesDao.getInstance().getRoleIdByRole(Role.MANAGER);
        assertEquals(expected, actual);
        expected = 3;
        actual = RolesDao.getInstance().getRoleIdByRole(Role.CRAFTSMAN);
        assertEquals(expected, actual);
    }

    @Test
    void removeRole() {
        assertTrue(RolesDao.getInstance().removeRole(Role.TEST));
    }

    @Test
    void addRole() {
        assertTrue(RolesDao.getInstance().addRole(Role.TEST));
    }

    @Test
    void updateRole() {
        assertTrue(RolesDao.getInstance().updateRole(Role.TEST));
    }
}