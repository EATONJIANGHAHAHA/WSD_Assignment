package dao;

import jaxblist.Users;
import model.User;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Allows a series of operations amongst the records of users.
 */
public class UserDAOImpl extends BaseDAO_Impl<Users, User> implements UserDAO {
    public static final String WEB_INF_STUDENTS_XML = "/WEB-INF/students.xml";
    public static final String WEB_INF_TUTORS_XML = "/WEB-INF/tutors.xml";
    public static final String WEB_INF_USERS_XSD = "/WEB-INF/users.xsd";

    public UserDAOImpl(){
        super();
    }

    public UserDAOImpl(String filePath) throws JAXBException, IOException {
        super(filePath);
    }

    public UserDAOImpl(String filePath, String schemaPath) throws JAXBException, IOException {
        super(filePath, schemaPath);
    }

    public UserDAOImpl(String filePath, String schemaPath, Users tutors){
        super(filePath, schemaPath, tutors);
    }

    @Override
    public boolean isRegistered(String email) {
        return read().isRegistered(email);
    }

    @Override
    public User searchByEmail(String email) {
        return read().findByEmail(email);
    }

}
