package application;

import jaxblist.Bookings;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class BookingApplication extends BaseApplication<Bookings> {
    public static final String BOOKING_FILE_PATH = "/WEB-INF/bookings.xml";
    public static final String BOOKING_APP = "bookingApp";

    public BookingApplication(){
        super();
    }

    public BookingApplication(String filePath) throws JAXBException, IOException {
        super(filePath);
    }

    public BookingApplication(String filePath, Bookings bookings){
        super(filePath, bookings);
    }
}
