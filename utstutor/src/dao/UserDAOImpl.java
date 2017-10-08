package dao;

import jaxblist.Users;
import model.User;

/**
 * Allows a series of operations amongst the records of users.
 */
public class UserDAOImpl extends BaseDAO_Impl<Users, User> implements UserDAO {
    public static final String WEB_INF_STUDENTS_XML = "/WEB-INF/students.xml";
    public static final String WEB_INF_TUTORS_XML = "/WEB-INF/tutors.xml";
    public static final String WEB_INF_USERS_XSD = "/WEB-INF/users.xsd";
    public static final String TUTOR_SERVICE = "tutorService";

    public UserDAOImpl(){
        super();
    }

    public UserDAOImpl(String filePath){
        super(filePath);
    }

    public UserDAOImpl(String filePath, String schemaPath){
        super(filePath, schemaPath);
    }

    public UserDAOImpl(String filePath, String schemaPath, Users tutors){
        super(filePath, schemaPath, tutors);
    }

    /**
     * Return true if the email address is registered.
     * @param email
     * @return
     */
    @Override
    public boolean isRegistered(String email) {
        return read().isRegistered(email);
    }

    /**
     * Return the user by a specific email.
     * @param email
     * @return
     */
    @Override
    public User searchByEmail(String email) {
        return read().findByEmail(email);
    }

    @Override
    public Users searchUsersByEmail(String email) {
        return read().findUsersByEmail(email);
    }

    /**
     * Return the user jaxb list by status
     * @param status
     * @return
     */
    @Override
    public Users searchByStatus(String status) {
        return read().findTutorByStatus(status);
    }

    /**
     * Return the user, if password and email matches.
     * @param email
     * @param password
     * @return
     */
    @Override
    public User login(String email, String password) {
        return read().login(email, password);
    }

}
