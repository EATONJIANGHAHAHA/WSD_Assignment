package jaxblist;

import model.Tutor;
import util.DigestUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tutors")
public class Tutors extends BaseJAXBList<Tutor> implements Users<Tutor>{

    public Tutors(){}

    public Tutors(List<Tutor> list){
        super(list);
    }

    @Override
    public Tutor findById(Integer id) {
        for(Tutor tutor: getList()){
            if(tutor.getId() == id) return tutor;
        }
        return null;
    }

    @Override
    @XmlElement(name = "tutor")
    public List<Tutor> getList() {
        return this.list;
    }

    @Override
    public void setList(List<Tutor> list) {
        this.list = list;
    }

    @Override
    public boolean isRegistered(String email) {
        for(Tutor tutor: getList()){
            if(tutor.getEmail().equals(email)) return true;
        }
        return false;
    }

    @Override
    public Tutor login(String email, String password) {
        for(Tutor tutor: getList()){
            if(tutor.getEmail().equals(email) && tutor.getPassword().equals(DigestUtil
                    .encryptPWD(password))) return tutor;
        }
        return null;
    }
}
