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
    public Bookings getBookings() throws JAXBException, IOException{
        return getBookingApp().getItems();
    }

}
