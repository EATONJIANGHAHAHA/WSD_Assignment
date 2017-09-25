package jaxblist;

import model.Tutor;
import util.DigestUtil;

public class Tutors extends BaseJAXBList<Tutor> implements Users{
    @Override
    public Tutor findById(Integer id) {
        for(Tutor tutor: getAll()){
            if(tutor.getId() == id) return tutor;
        }
        return null;
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
