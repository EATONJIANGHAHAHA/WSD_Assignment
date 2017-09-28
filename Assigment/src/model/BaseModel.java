package model;

import adapter.IDAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

@XmlSeeAlso({Booking.class, User.class})
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BaseModel implements Serializable{
    private Integer id = 0;

    @XmlElement(name = "id")
    @XmlJavaTypeAdapter(IDAdapter.class)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
