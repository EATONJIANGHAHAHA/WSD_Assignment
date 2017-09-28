package jaxblist;

import model.BaseModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@XmlSeeAlso({Bookings.class, Users.class})
@XmlAccessorType(XmlAccessType.PROPERTY)
public abstract class BaseJAXBList<T extends BaseModel> implements Serializable{

    @XmlTransient
    private List<T> list;

    public BaseJAXBList(){}

    public BaseJAXBList(List<T> list) {
        this.list = list;
    }

    /**
     * Find the object by its id.
     * @param id
     * @return
     */
    public T  findById(Integer id){
        for(T t: list){
            if(t.getId() == id) return t;
        }
        return null;
    }

    @XmlTransient
    public List<T> getList(){
        return this.list;
    }

    public  void setList(List<T> list){
        this.list = list;
    }

    public void add( T item ){
        list.add( item );
    }

    public void remove(T item){
        list.remove(item);
    }

    public void removeLast(){
        list.remove(list.get(list.size()-1));
    }
}
