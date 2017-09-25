package jaxblist;

import model.Student;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.util.List;

@XmlSeeAlso({Bookings.class, Student.class})
public abstract class BaseJAXBList<T> implements Serializable{

    @XmlElement(name = "item")
    private List<T> list;

    public abstract T findById(Integer id);

    public List<T> getAll(){
        return list;
    }

    public void set( List<T> list){
        this.list = list;
    }

    public void add( T item ){
        list.add( item );
    }

    public void remove(T item){
        list.remove(item);
    }
}
