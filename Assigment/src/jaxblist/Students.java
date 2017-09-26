package jaxblist;

import model.Student;
import util.DigestUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "students")
public class Students extends BaseJAXBList<Student> implements Users<Student>{

    public Students(){}
    public Students(List<Student> list){
        super(list);
    }

    @Override
    public Student findById(Integer id) {
        for(Student student: getAll()){
            if(student.getId() == id) return student;
        }
        return null;
    }

    @Override
    @XmlElement(name = "student")
    public List<Student> getAll() {
        return this.list;
    }

    @Override
    public boolean isRegistered(String email) {
        for(Student student: getAll()){
            if(student.getEmail().equals(email)) return true;
        }
            return false;

    }

    @Override
    public Student login(String email, String password) {
        for(Student student: getAll()){
            if(student.getEmail().equals(email) && student.getPassword().equals(DigestUtil
                    .encryptPWD(password))) return student;
        }
        return null;

    }
}
