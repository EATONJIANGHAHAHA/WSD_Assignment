package jaxblist;

import model.Tutor;
import util.DigestUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tutors")
public class Tutors extends BaseJAXBList<Tutor> implements Users{
    @Override
    public Tutor findById(Integer id) {
        for(Tutor tutor: getAll()){
            if(tutor.getId() == id) return tutor;
        }
        return null;
    }

    @Override
    @XmlElement(name = "tutor")
    public List<Tutor> getAll() {
        return this.list;
    }

    @Override
    public boolean isRegistered(String email) {
        for(Tutor tutor: getAll()){
            if(tutor.getEmail().equals(email)) return true;
        }
        return false;
    }

    @Override
    public boolean login(String email, String password) {
        for(Tutor tutor: getAll()){
            if(tutor.getEmail().equals(email) && tutor.getPassword().equals(DigestUtil
                    .encryptPWD(password))) return true;
        }
        return false;
    }
}
