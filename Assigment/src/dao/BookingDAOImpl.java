package dao;

import jaxblist.Bookings;
import model.Booking;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Allows some data base operations of booking records.
 */
public class BookingDAOImpl extends BaseDAO_Impl<Bookings, Booking> implements BookingDAO{
    public static final String WEB_INF_BOOKINGS_XML = "/WEB-INF/bookings.xml";
    public static final String BOOKING_SERVICE = "bookingService";
    public static final String WEB_IF_BOOKINGS_XSD = "/WEB-INF/bookings.xsd";

    public BookingDAOImpl(){
        super();
    }

    public BookingDAOImpl(String filePath){
        super(filePath);
    }

    public BookingDAOImpl(String filePath, String schemaPath){
        super(filePath, schemaPath);
    }

    public BookingDAOImpl(String filePath, String schemaPath, Bookings bookings){
        super(filePath, schemaPath, bookings);
    }

    @Override
    public Bookings searchByEmail(String email, boolean isStudent) {
        if(isStudent) return getItems().findByStudentEmail(email);
        return getItems().findByTutorEmail(email);
    }
}
