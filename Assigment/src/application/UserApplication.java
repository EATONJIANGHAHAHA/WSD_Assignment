package application;

import jaxblist.Users;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Allows a series of operations amongst the records of users.
 */
public class UserApplication extends BaseApplication<Users> {
    public static final String WEB_INF_STUDENTS_XML = "/WEB-INF/students.xml";
    public static final String WEB_INF_TUTORS_XML = "/WEB-INF/tutors.xml";
    public static final String WEB_INF_USERS_XSD = "/WEB-INF/users.xsd";

    public UserApplication(){
        super();
    }

    public UserApplication(String filePath) throws JAXBException, IOException {
        super(filePath);
    }

    public UserApplication(String filePath, String schemaPath) throws JAXBException, IOException {
        super(filePath, schemaPath);
    }

    public UserApplication(String filePath, String schemaPath, Users tutors){
        super(filePath, schemaPath, tutors);
    }
}
