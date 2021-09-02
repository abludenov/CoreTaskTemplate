package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("nameUser1", "lastNameUser1", (byte) 1);
        userService.saveUser("nameUser2", "lastNameUser2", (byte) 2);
        userService.saveUser("nameUser3", "lastNameUser3", (byte) 3);
        userService.saveUser("nameUser4", "lastNameUser4", (byte) 4);

        List<User> userList = userService.getAllUsers();

        for (User user : userList) {
            System.out.println(user);
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
