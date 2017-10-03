package application;

import jaxblist.Bookings;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class BookingApplication extends BaseApplication<Bookings> {
    public static final String WEB_INF_BOOKINGS_XML = "/WEB-INF/bookings.xml";
    public static final String BOOKING_SERVICE = "bookingService";
    public static final String WEB_IF_BOOKINGS_XSD = "/WEB-INF/bookings.xsd";

    public BookingApplication(){
        super();
    }

    public BookingApplication(String filePath) throws JAXBException, IOException {
        super(filePath);
    }

    public BookingApplication(String filePath, String schemaPath) throws JAXBException, IOException {
        super(filePath, schemaPath);
    }

    public BookingApplication(String filePath, String schemaPath, Bookings bookings){
        super(filePath, schemaPath, bookings);
    }

}
