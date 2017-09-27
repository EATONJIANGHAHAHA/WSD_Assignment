package application;

import jaxblist.Tutors;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class TutorApplication extends BaseApplication<Tutors> {
    public static final String TUTOR_FILE_PATH = "/WEB-INF/tutors.xml";
    public static final String TUTOR_APP = "tutorApp";

    public TutorApplication(){
        super();
    }

    public TutorApplication(String filePath) throws JAXBException, IOException {
        super(filePath);
    }

    public TutorApplication(String filePath, Tutors tutors){
        super(filePath, tutors);
    }
}
