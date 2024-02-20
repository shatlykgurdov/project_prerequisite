package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Shatlyk", "Gurdov", (byte) 33);
        System.out.println("User with name - Shatlyk added to the database");

        userDao.saveUser("Zaha", "Badi", (byte) 25);
        System.out.println("User with name - Zaha added to the database");

        userDao.saveUser("Maksat", "Atayev", (byte) 35);
        System.out.println("User with name - Maksat added to the database");

        userDao.saveUser("Nury", "Idogdy", (byte) 28);
        System.out.println("User with name - Nury added to the database");

        List<User> users = userDao.getAllUsers();
        {
            for (User user : users) {
                System.out.println(user);
            }
        }

        userDao.removeUserById(1L);

        userDao.cleanUsersTable();

        userDao.dropUsersTable();
    }
}
