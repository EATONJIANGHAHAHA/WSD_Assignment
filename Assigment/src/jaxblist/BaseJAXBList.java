package jaxblist;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@XmlSeeAlso({Bookings.class, Users.class})
public abstract class BaseJAXBList<T> implements Serializable{

    @XmlTransient
    protected List<T> list;

    public BaseJAXBList(){}

    public BaseJAXBList(List<T> list) {
        this.list = list;
    }

    public abstract T findById(Integer id);

    public abstract List<T> getList();

    public abstract void setList(List<T> list);//{this.list = list;}

    public void add( T item ){
        list.add( item );
    }

    public void remove(T item){
        list.remove(item);
    }
}
