package jaxblist;

import model.User;
import util.DigestUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
public class Users extends BaseJAXBList<User>{

    public Users(){}

    public Users(List<User> list){
        super(list);
    }

    @Override
    public User findById(Integer id) {
        for(User tutor: getList()){
            if(tutor.getId() == id) return tutor;
        }
        return null;
    }

    @Override
    @XmlElement(name = "user")
    public List<User> getList() {
        return this.list;
    }

    @Override
    public void setList(List<User> list) {
        this.list = list;
    }


    public boolean isRegistered(String email) {
        for(User user: getList()){
            if(user.getEmail().equals(email)) return true;
        }
        return false;
    }


    public User login(String email, String password) {
        for(User tutor: getList()){
            if(tutor.getEmail().equals(email) && tutor.getPassword().equals(DigestUtil
                    .encryptPWD(password))) return tutor;
        }
        return null;
    }
}
