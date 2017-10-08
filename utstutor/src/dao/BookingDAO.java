package dao;

import jaxblist.Bookings;
import model.Booking;

public interface BookingDAO extends BaseDAO<Bookings, Booking>{
    Bookings searchByEmail(String email, boolean isStudent);
    Bookings getBookings(Integer id, String subject, String studentEmail, String status);
}
