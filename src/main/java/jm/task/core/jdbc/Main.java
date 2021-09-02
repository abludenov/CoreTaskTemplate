package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userService = new UserDaoJDBCImpl();
        //userService.createUsersTable();
        userService.saveUser("Alexander", "Bliudenov", (byte) 32);
    }
}
