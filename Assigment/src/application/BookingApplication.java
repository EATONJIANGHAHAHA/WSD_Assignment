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

    public BookingApplication(String filePath, String schemaPath) throws JAXBException, IOException {
        super(filePath, schemaPath);
    }

    public BookingApplication(String filePath, String schemaPath, Bookings bookings){
        super(filePath, schemaPath, bookings);
    }
}
