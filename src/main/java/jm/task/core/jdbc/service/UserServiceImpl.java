package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;


public class UserServiceImpl implements UserService {
    //UserDaoJ imp = new UserDaoJDBCImpl();
    UserDao imp = new UserDaoHibernateImpl();
    public void createUsersTable() {
        try{
            imp.createUsersTable();}
        catch (Exception w){
            System.out.println(w.toString());
        }
    }

    public void dropUsersTable() {
        imp.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        imp.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        imp.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return imp.getAllUsers();
    }

    public void cleanUsersTable() {
        imp.cleanUsersTable();
    }   
}
