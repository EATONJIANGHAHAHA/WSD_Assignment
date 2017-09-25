package jaxblist;

import model.Student;
import util.DigestUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "students")
public class Students extends BaseJAXBList<Student> implements Users{

    @Override
    public Student findById(Integer id) {
        for(Student student: getAll()){
            if(student.getId() == id) return student;
        }
        return null;
    }

    @Override
    public boolean isRegistered(String email) {
        for(Student student: getAll()){
            if(student.getEmail().equals(email)) return true;
        }
            return false;

    }

    @Override
    public boolean login(String email, String password) {
        for(Student student: getAll()){
            if(student.getEmail().equals(email) && student.getPassword().equals(DigestUtil
                    .encryptPWD(password))) return true;
        }
        return false;

    }
}
