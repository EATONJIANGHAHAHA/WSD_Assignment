package dao;

import jaxblist.Users;
import model.User;

public interface UserDAO extends BaseDAO<Users, User>{
    boolean isRegistered(String email);
}
