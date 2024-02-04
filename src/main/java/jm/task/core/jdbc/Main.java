package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import jm.task.core.jdbc.model.User;

import java.lang.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // implement algorithm here
        UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();
        //UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();

        // Creating a table for Users
        userDao.createUsersTable();

        // Adding 4 Users to the table with data of your choice
        userDao.saveUser("Shatlyk", "Gurdov", (byte) 33);
        System.out.println("User with name - Shatlyk added to the database");

        userDao.saveUser("Zaha", "Badi", (byte) 25);
        System.out.println("User with name - Zaha added to the database");

        userDao.saveUser("Maksat", "Atayev", (byte) 35);
        System.out.println("User with name - Maksat added to the database");

        userDao.saveUser("Nury", "Idogdy", (byte) 28);
        System.out.println("User with name - Nury added to the database");

        // Getting all Users from the database and printing them to the console
        List<User> users = userDao.getAllUsers();
        {
            for (User user : users) {
                System.out.println(user);
            }
        }

        // Removing users by ID from the Users table
        userDao.removeUserById(1L);

        // Clearing the Users table
        userDao.cleanUsersTable();

        // Dropping the Users table
        userDao.dropUsersTable();
    }
}
