package dao;

import jaxblist.Bookings;
import model.Booking;

/**
 * Defines the operations for database of booking.
 */
public interface BookingDAO extends BaseDAO<Bookings, Booking>{
    Bookings searchByEmail(String email, boolean isStudent);
    Bookings getBookings(Integer id, String subject, String studentEmail, String status);
}
