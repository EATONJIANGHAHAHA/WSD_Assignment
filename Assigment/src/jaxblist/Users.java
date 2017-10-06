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
     * Return the tutors by whether they are available
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
     * Return the tutors by their availability.
     * @param availability
     * @return
     */
    public Users findTutorByStatus(String availability){
        Users tutors = new Users(new ArrayList<User>());
        for(User tutor: getList()){
            if((tutor.getAvailability().toLowerCase()).equals(availability.toLowerCase())) tutors.add(tutor);
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
            if((tutor.getName().toLowerCase()).equals(name.toLowerCase())) tutors.add(tutor);
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
            if((tutor.getSpeciality().toLowerCase()).equals(subject.toLowerCase())) tutors.add(tutor);
        }
        return tutors;
    }

    /**
     * Inherited from the super class to assign a specific tag for user in xml.
     * @return
     */
    @Override
    @XmlElement(name = "user")
    public List<User> getList() {
        return super.getList();
    }

    /**
     * Inherited to ensure the correct unmarshal.
     * @param list
     */
    @Override
    public void setList(List<User> list) {
        super.setList(list);
    }


    /**
     * Check whether the user is registered.
     * @param email
     * @return
     */
    public boolean isRegistered(String email) {
        return findByEmail(email) != null;
    }

    /**
     * Find the user by the email address.
     * @param email
     * @return
     */
    public User findByEmail(String email){
        for(User user: getList()){
            if(user.getEmail().equals(email)) return user;
        }
        return null;
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
