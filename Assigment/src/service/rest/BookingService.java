package service.rest;

import application.BookingApplication;
import static application.BookingApplication.*;
import jaxblist.Bookings;
import model.Booking;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBElement;
import java.io.IOException;


/**
 * A restful web service for bookings.
 */
@Path("/bookingService")
public class BookingService {
    @Context
    private ServletContext application;

    /**
     * Get booking application.
     * @return
     * @throws IOException
     * @throws JAXBException
     */
    private BookingApplication getBookingApp() throws IOException, JAXBException{
        synchronized (application){
            BookingApplication bookingApp = (BookingApplication)
                    application.getAttribute(BOOKING_SERVICE);
            if(bookingApp == null){
                bookingApp = new BookingApplication(application.getRealPath(WEB_INF_BOOKINGS_XML));
                application.setAttribute(BOOKING_SERVICE, bookingApp);
            }
            return bookingApp;
        }
    }

    /**
     * Returns all the booking records.
     * @return
     * @throws JAXBException
     * @throws IOException
     */
    @Path("bookings")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Bookings getAll() throws JAXBException, IOException{
        return getBookingApp().getItems();
    }

    /**
     * Return the booking records by the student's email.
     * @param email
     * @return
     * @throws JAXBException
     * @throws IOException
     */
    @Path("bookings/searchByEmail")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Bookings getByStuEmail(@QueryParam("email") String email) throws JAXBException, IOException {
        return getAll().findByStudentEmail(email);
    }

    /**
     * Return all the booking records by a specific subject.
     * @param subject
     * @return
     * @throws JAXBException
     * @throws IOException
     */
    @Path("/bookings/{subject}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Bookings getBySubject(@PathParam("subject") String subject) throws JAXBException, IOException {
        return getAll().findBySubject(subject);
    }

    /**
     * Return a booking record by a booking id.
     * @param id
     * @return
     * @throws JAXBException
     * @throws IOException
     */
    @Path("bookings/searchById")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Booking findById(@QueryParam("id") Integer id) throws JAXBException, IOException {
        return getAll().findById(id);
    }
}
