
package jaxblist;

import model.Student;
import util.DigestUtil;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Students extends BaseJAXBList<Student> implements Users<Student>{

    public Students(){}
    public Students(List<Student> list){
        super(list);
    }

    @Override
    public Student findById(Integer id) {
        for(Student student: getList()){
            if(student.getId() == id) return student;
        }
        return null;
    }

    @Override
    @XmlElement(name ="student")
    public List<Student> getList() {
        return this.list;
    }

    @Override
    public void setList(List<Student> list) {
        this.list = list;
    }


    @Override
    public boolean isRegistered(String email) {
        for(Student student: getList()){
            if(student.getEmail().equals(email)) return true;
        }
            return false;

    }

    @Override
    public Student login(String email, String password) {
        for(Student student: getList()){
            if(student.getEmail().equals(email) && student.getPassword().equals(DigestUtil
                    .encryptPWD(password))) return student;
        }
        return null;

    }
}
