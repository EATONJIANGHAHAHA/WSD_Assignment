package jaxblist;

import model.Booking;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a list of bookings
 */
@XmlRootElement(name = "bookings")
public class Bookings extends BaseJAXBList<Booking>{

    public Bookings(){}

    public Bookings(List<Booking> list){
        super(list);
    }

    @Override
    public Booking findById( Integer id) {
        for(Booking booking: this.getAll()){
            if(booking.getId() == id) return booking;
        }
        return null;
    }

    @Override
    @XmlElement(name = "booking")
    public List<Booking> getAll() {
        return this.list;
    }

    /**
     * Find the booking record according to the student email
     * @param email
     * @return
     */
    public Bookings findByStudentEmail( String email ){
        Bookings results = new Bookings(new ArrayList<Booking>());
        for(Booking booking: this.getAll()){
            if(booking.getStudentEmail().equals(email)) results.add(booking);
        }
        return results;
    }

    /**
     * Find the booking records according to the booking status.
     * @param status
     * @return
     */
    public Bookings findByStatus( String status ){
        Bookings results = new Bookings(new ArrayList<Booking>());
        for(Booking booking: getAll()){
            if(booking.getStatus().equals(status)) results.add(booking);
        }
        return results;
    }

    /**
     * Find the booking records that matches a specific subject.
     * @param subject
     * @return
     */
    public Bookings findBySubject(String subject){
        Bookings results = new Bookings(new ArrayList<Booking>());
        for(Booking booking: getAll()){
            if(booking.getSubject().equals(subject)) results.add(booking);
        }
        return results;
    }
}
