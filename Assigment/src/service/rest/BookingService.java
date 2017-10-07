package service.rest;

import dao.BookingDAO;
import dao.BookingDAOImpl;
import static dao.BookingDAOImpl.*;
import jaxblist.Bookings;
import model.Booking;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBException;
import java.io.IOException;


/**
 * A restful web service for bookings.
 */
@Path("/bookingService")
public class BookingService {
    @Context
    private ServletContext application;

    /**
     * Get booking dao.
     * @return
     */
    private BookingDAO getBookingDAO(){
        synchronized (application){
            BookingDAO bookingDAO = (BookingDAOImpl)
                    application.getAttribute(BOOKING_SERVICE);
            if(bookingDAO == null){
                bookingDAO = new BookingDAOImpl(application.getRealPath(WEB_INF_BOOKINGS_XML));
                application.setAttribute(BOOKING_SERVICE, bookingDAO);
            }
            return bookingDAO;
        }
    }



    /**
     *  Return the booking records according to the query param
     * @param id booking id
     * @param subject subject
     * @param email student's email
     * @return
     */
    @Path("bookings")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Bookings getBookings(@QueryParam("id") Integer id, @QueryParam("subject") String subject, @QueryParam("email")
            String email){
        if(id == null && subject == null && email == null) return getBookingDAO().read();
        if(id != null && subject == null && email == null) return getBookingDAO().searchBookingsById(id);
        if(id == null && subject != null && email == null) return getBookingDAO().searchBySubject(subject);
        if(id == null && subject == null && email != null) return getBookingDAO().searchByEmail(email, true);
        if(id != null && subject != null && email == null) return getBookingDAO().searchBookingsById(id).findBySubject(subject);
        if(id != null && subject == null && email != null) return getBookingDAO().searchBookingsById(id).findByStudentEmail(email);
        if(id == null && subject != null && email != null) return getBookingDAO().searchBySubject(subject).findByStudentEmail(email);
        return getBookingDAO().searchBookingsById(id).findBySubject(subject).findByStudentEmail(email);
    }
}
