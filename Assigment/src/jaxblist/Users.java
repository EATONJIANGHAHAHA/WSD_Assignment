package jaxblist;

import model.User;
import util.DigestUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a list users, allows some basic operation of the users.
 */
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Users extends BaseJAXBList<User>{

    public Users(){}

    public Users(List<User> list){
        super(list);
    }

    /**
     * Return the user by id.
     * @param id
     * @return
     */
    @Override
    public User findById(Integer id) {
        for(User user: getList()){
            if(user.getId() == id) return user;
        }
        return null;
    }

    /**
     * Return the tutors by their status
     * @param status
     * @return
     */
    public Users findTutorByStatus(Boolean status){
        Users tutors = new Users(new ArrayList<User>());
        for(User tutor: getList()){
            if(tutor.isAvailable() == status) tutors.add(tutor);
        }
        return tutors;
    }

    /**
     * Return the tutors by their names
     * @param name
     * @return
     */
    public Users findTutorByName(String name){
        Users tutors = new Users(new ArrayList<User>());
        for(User tutor: getList()){
            if(tutor.getName().equals(name)) tutors.add(tutor);
        }
        return tutors;
    }

    /**
     * Return the tutors by their subjects
     * @param subject
     * @return
     */
    public Users findTutorBySubject(String subject){
        Users tutors = new Users(new ArrayList<User>());
        for(User tutor: getList()){
            if(tutor.getSpeciality().equals(subject)) tutors.add(tutor);
        }
        return tutors;
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


    /**
     * Check whether the user is registered.
     * @param email
     * @return
     */
    public boolean isRegistered(String email) {
        for(User user: getList()){
            if(user.getEmail().equals(email)) return true;
        }
        return false;
    }


    /**
     * Return the user if there is a user stored in xml that matches the email and password.
     * @param email
     * @param password
     * @return
     */
    public User login(String email, String password) {
        for(User user: getList()){
            if(user.getEmail().equals(email) && user.getPassword().equals(DigestUtil
                    .encryptPWD(password))) return user;
        }
        return null;
    }
}
