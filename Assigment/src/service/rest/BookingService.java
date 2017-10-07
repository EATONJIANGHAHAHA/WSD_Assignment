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
     * @param status status of booking
     * @return
     */
    @Path("bookings")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Bookings getBookings(@QueryParam("id") Integer id, @QueryParam("subject") String subject, @QueryParam("email")
            String email, @QueryParam("status") String status){
        return getBookingDAO().getBookings(id, subject, email, status);
    }
}
