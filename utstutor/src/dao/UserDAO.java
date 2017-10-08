package dao;

import jaxblist.Users;
import model.User;

/**
 * Represents a series options of user in database.
 */
public interface UserDAO extends BaseDAO<Users, User>{
    boolean isRegistered(String email);
    User searchByEmail(String email);
    Users searchUsersByEmail(String email);
    Users searchByStatus(String status);
    User login(String email, String password);
}
