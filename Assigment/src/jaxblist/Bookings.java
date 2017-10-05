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
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Bookings extends BaseJAXBList<Booking>{

    public Bookings(){}

    public Bookings(List<Booking> list){
        super(list);
    }

    @Override
    @XmlElement(name = "booking")
    public List<Booking> getList() {
        return super.getList();
    }

    @Override
    public void setList(List<Booking> list) {
        super.setList(list);
    }

    /**
     * Find the booking record according to the student email
     * @param email
     * @return
     */
    public Bookings findByStudentEmail( String email ){
        Bookings results = new Bookings(new ArrayList<Booking>());
        for(Booking booking: this.getList()){
            if(booking.getStudentEmail().equals(email)) results.add(booking);
        }
        return results;
    }

    /**
     * Update the list from other bookings
     * @param bookings
     */
    public void updateList(Bookings bookings){
        for(Booking newBooking: bookings.getList()){
            for(Booking oldBooking: this.getList()){
                if(newBooking.getId() == oldBooking.getId()) oldBooking = newBooking;
            }
        }
    }

    /**
     * Find the booking record according to the tutor email
     * @param email
     * @return
     */
    public Bookings findByTutorEmail( String email ){
        Bookings results = new Bookings(new ArrayList<Booking>());
        for(Booking booking: this.getList()){
            if(booking.getTutorEmail().equals(email)) results.add(booking);
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
        for(Booking booking: getList()){
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
        for(Booking booking: getList()){
            if(booking.getSubject().equals(subject)) results.add(booking);
        }
        return results;
    }
}
