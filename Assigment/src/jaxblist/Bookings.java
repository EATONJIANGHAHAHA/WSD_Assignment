package jaxblist;

import model.Booking;

/**
 * This class represents a list of bookings
 */
public class Bookings extends BaseJAXBList<Booking>{

    @Override
    public Booking findById( Integer id) {
        for(Booking booking: this.getAll()){
            if(booking.getId() == id) return booking;
        }
        return null;
    }

    /**
     * Find the booking record according to the student email
     * @param email
     * @return
     */
    public Booking findByStudentEmail( String email ){
        for(Booking booking: this.getAll()){
            if(booking.getStudentEmail().equals(email)) return booking;
        }
        return null;
    }

    /**
     * Find the booking record according to the tutor email.
     * @param email
     * @return
     */
    public Booking findByTutorId( String email ){
        for(Booking booking: getAll()){
            if(booking.getTutorEmail().equals(email)) return booking;
        }
        return null;
    }
}
