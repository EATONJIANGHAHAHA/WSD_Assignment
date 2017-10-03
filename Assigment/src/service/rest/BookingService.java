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



@Path("/bookingService")
public class BookingService {
    @Context
    private ServletContext application;


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

    @Path("bookings")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Bookings getAll() throws JAXBException, IOException{
        return getBookingApp().getItems();
    }

    @Path("bookings/searchByEmail")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Bookings getByStuEmail(@QueryParam("email") String email) throws JAXBException, IOException {
        return getAll().findByStudentEmail(email);
    }

    @Path("/bookings/{subject}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Bookings getBySubject(@PathParam("subject") String subject) throws JAXBException, IOException {
        return getAll().findBySubject(subject);
    }

    @Path("bookings/searchById")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Booking findById(@QueryParam("id") Integer id) throws JAXBException, IOException {
        return getAll().findById(id);
    }
}
