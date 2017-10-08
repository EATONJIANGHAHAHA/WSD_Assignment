package service.rest;

import dao.BookingDAO;
import dao.BookingDAOImpl;
import jaxblist.Bookings;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import static dao.BookingDAOImpl.BOOKING_SERVICE;
import static dao.BookingDAOImpl.WEB_INF_BOOKINGS_XML;


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
