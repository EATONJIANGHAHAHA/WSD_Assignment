package application;

import jaxblist.Users;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class UserApplication extends BaseApplication<Users> {
    public static final String WEB_INF_STUDENTS_XML = "/WEB-INF/students.xml";
    public static final String WEB_INF_TUTORS_XML = "/WEB-INF/tutors.xml";

    public UserApplication(){
        super();
    }

    public UserApplication(String filePath) throws JAXBException, IOException {
        super(filePath);
    }

    public UserApplication(String filePath, Users tutors){
        super(filePath, tutors);
    }
}
