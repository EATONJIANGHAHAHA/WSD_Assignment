package service.rest;

import application.BookingApplication;
import jaxblist.Bookings;
import model.Booking;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBException;

import java.io.IOException;

import static application.BookingApplication.*;


@Path("/bookingApp")
public class BookingService {
    @Context
    private ServletContext application;
    private BookingApplication getBookingApp() throws IOException, JAXBException{
        synchronized (application){
            BookingApplication bookingApp = (BookingApplication)
                    application.getAttribute(BOOKING_APP);
            if(bookingApp == null){
                bookingApp = new BookingApplication();
                bookingApp.setFilePath(BOOKING_FILE_PATH);
                application.setAttribute(BOOKING_APP, bookingApp);
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

    @Path("bookings/{email}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Bookings getByStuEmail(@PathParam("email") String email) throws JAXBException, IOException {
        return getAll().findByStudentEmail(email);
    }

    @Path("bookings/{status}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Bookings getByStatus(@PathParam("status") String status) throws JAXBException, IOException {
        return getAll().findByStatus(status);
    }

    @Path("bookings/{subject}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Bookings getBySubject(@PathParam("subject") String subject) throws JAXBException, IOException {
        return getAll().findBySubject(subject);
    }

    @Path("bookings/{id}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Booking findById(@PathParam("id") Integer id) throws JAXBException, IOException {
        return getAll().findById(id);
    }
}
