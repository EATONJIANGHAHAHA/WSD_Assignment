package dao;

import jaxblist.Bookings;
import model.Booking;

public interface BookingDAO extends BaseDAO<Bookings, Booking>{
    Bookings searchByEmail(String email, boolean isStudent);
    Bookings searchBookingsById(Integer id);
    Bookings searchBySubject(String subject);
}
