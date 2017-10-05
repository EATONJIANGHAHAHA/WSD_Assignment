package jaxblist;

import model.BaseModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 * Represents a list of jaxb objects, allowing a series of operations of the object list.
 * @param <V>
 */
@XmlSeeAlso({Bookings.class, Users.class})
@XmlAccessorType(XmlAccessType.PROPERTY)
public abstract class BaseJAXBList<V extends BaseModel> implements Serializable{

    @XmlTransient
    private List<V> list;
    @XmlTransient
    /**
     * To preserve the old data when edit a current existing item.
     */
    private V oldItem = null;

    public BaseJAXBList(){}

    public BaseJAXBList(List<V> list) {
        this.list = list;
    }

    /**
     * Find the object by its id.
     * @param id
     * @return
     */
    public V findById(Integer id){
        for(V v : list){
            if(v.getId() == id) return v;
        }
        return null;
    }

    @XmlTransient
    public List<V> getList(){
        return this.list;
    }

    public  void setList(List<V> list){
        this.list = list;
    }

    public void add( V item ){
        list.add( item );
    }

    public void update(V newItem){
        list.set(newItem.getId()-1, newItem);
    }

    public void remove(V item){
        list.remove(item);
    }

    public void removeLast(){
        list.remove(list.get(list.size()-1));
    }

    public void setOldItem(V oldItem) {
        this.oldItem = oldItem;
    }

    /**
     * Reset the changed item into old ones to ensure the marshal of xml.
     */
    public void recoverList(){
        if (oldItem == null)
            removeLast();
        else {
            list.set(oldItem.getId()-1, oldItem);
            oldItem = null;
        }
    }
}
