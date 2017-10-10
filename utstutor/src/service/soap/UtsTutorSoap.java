package service.soap;

import dao.BookingDAO;
import dao.BookingDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import exception.DataValidationException;
import jaxblist.Bookings;
import model.Booking;
import model.User;
import util.EncryptUtil;
import util.StringUtil;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.List;

import static dao.BookingDAOImpl.WEB_IF_BOOKINGS_XSD;
import static dao.BookingDAOImpl.WEB_INF_BOOKINGS_XML;
import static dao.UserDAOImpl.*;
import static model.Booking.*;
import static model.User.*;
import static util.StringUtil.isEmpty;

@WebService(serviceName = "utsTutorService")
public class UtsTutorSoap {


    public static final String TUTOR_DAO = "tutorDao";
    public static final String STUDENT_DAO = "studentDao";
    public static final String BOOKING_DAO = "bookingDao";

    @Resource
    private WebServiceContext context;

    /**
     * return the user dao according to the user type.
     *
     * @param userType
     * @return
     */
    private UserDAO getUserDAO(String userType) {
        String tag;
        String xmlPath;
        if (userType.equals(TUTOR)) {
            tag = TUTOR_DAO;
            xmlPath = WEB_INF_TUTORS_XML;
        } else {
            tag = STUDENT_DAO;
            xmlPath = WEB_INF_STUDENTS_XML;
        }
        ServletContext application = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);

        synchronized (application) {
            UserDAO userDAO = (UserDAOImpl) application
                    .getAttribute(tag);
            if (userDAO == null) {
                userDAO = new UserDAOImpl(application.getRealPath(xmlPath), application.getRealPath(WEB_INF_USERS_XSD));
                application.setAttribute(tag, userDAO);
            }
            return userDAO;
        }
    }

    /**
     * Return the booking dao.
     * @return
     */
    private BookingDAO getBookingDAO() {
        ServletContext application = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);

        synchronized (application) {
            BookingDAO bookingDAO = (BookingDAOImpl) application
                    .getAttribute(BOOKING_DAO);
            if (bookingDAO == null) {
                bookingDAO = new BookingDAOImpl(application.getRealPath(WEB_INF_BOOKINGS_XML), application.getRealPath(
                        WEB_IF_BOOKINGS_XSD));
                application.setAttribute(BOOKING_DAO, bookingDAO);
            }
            return bookingDAO;
        }
    }


    /**
     * Return true if the user is logged in successfully.
     *
     * @param email
     * @param password
     * @param userType
     * @return
     */
    @WebMethod
    public User login(String email, String password, String userType) {
        User user = getUserDAO(userType).login(email, password);
        return user;
    }

    /**
     * Return the result of creating the booking.
     *
     * @param tutorEmail
     * @return
     */
    @WebMethod
    public Object createBooking(String tutorEmail, User user) {
        UserDAO tutorDAO = getUserDAO(TUTOR);
        User tutor = tutorDAO.searchByEmail(tutorEmail);
        if(tutor == null)return "Creation failed: no user with such email.";
        if (tutor.isAvailable()) {
            Booking booking = new Booking(user.getName(), user.getEmail(), tutor.getName(), tutor.getEmail(),
                    tutor.getSpeciality());
            tutor.setAvailability(UNAVAILABLE);
            try {
                getBookingDAO().create(booking);
                tutorDAO.save();
            } catch (DataValidationException e) {
                e.printStackTrace();
            }
            return getBookingDAO().read().getLast();
        }
        return "This tutor is currently unavailable.";

    }

    /**
     * Return the booking records.
     *
     * @param id
     * @param subject
     * @param studentEmail
     * @param status
     * @return
     */
    @WebMethod
    public List<Booking> getBookings(Integer id, String subject, String studentEmail, String status) {

        return getBookingDAO().getBookings(id, subject, studentEmail, status).getList();
    }

    /**
     * Cancel booking according to the booking id.
     *
     * @param bookingId
     * @return
     */
    public Object cancelBooking(Integer bookingId, User user) {
        BookingDAO bookingDAO = getBookingDAO();
        Booking booking = bookingDAO.searchById(bookingId);
        if(booking == null) return "Edition failed: No such booking.";
        if (booking.getTutorEmail().equals(user.getEmail()) || booking.getStudentEmail().equals(user.getEmail())) {
            if (booking.getStatus().equals(ACTIVE)) {
                booking.setStatus(CANCELLED);
                UserDAO tutorDAO = getUserDAO(TUTOR);
                tutorDAO.searchByEmail(booking.getTutorEmail()).setAvailability(AVAILABLE);
                try {
                    bookingDAO.save();
                    tutorDAO.save();
                    return booking;
                } catch (DataValidationException e) {
                }
            }
            else return "Edition failed: this booking is already " + booking.getStatus() + ".";
        }
        return "Edition failed: You have no access to this booking.";
    }


    /**
     * Complete the booking by id if the user is a tutor.
     *
     * @param bookingId
     * @return
     */
    @WebMethod
    public Object completeBooking(Integer bookingId, User user) {
        BookingDAO bookingDAO = getBookingDAO();
        Booking booking = bookingDAO.searchById(bookingId);
        if(booking == null) return "No such booking";
        if (booking.getStatus().equals(ACTIVE)) {
            if(booking.getTutorEmail().equals(user.getEmail())){
            booking.setStatus(COMPLETED);
            User oldUser = new User(user);
            user.setAvailability(AVAILABLE);
            try {
                bookingDAO.save();
                getUserDAO(user.getType()).update(oldUser, user);
                return booking;
            } catch (DataValidationException e) {
                e.printStackTrace();
            }}
            else return "Completion failed: you do not have access to this booking.";
        }
        return "Completion failed: The booking has already been " + booking.getStatus() + ".";
    }

    /**
     * Allows an user to cancel his account.
     *
     * @return
     */
    @WebMethod
    public boolean cancelAccount(User user) {

        try {
            BookingDAO bookingDAO = getBookingDAO();
            Bookings bookings = bookingDAO.searchByEmail(user.getEmail(), user.isStudent());
            if (bookings.getList() != null && bookings.getList().size() != 0) {
                for (Booking booking : bookings.findByStatus(ACTIVE).getList()) booking.setStatus(CANCELLED);
                bookingDAO.save();
            }
            getUserDAO(user.getType()).delete(user);
            return true;
        } catch (DataValidationException e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * Allows the user to edit their account.
     *
     * @return
     */
    @WebMethod
    public Object editAccount(String name, String password, String dateOfBirth, String speciality, User user){
        User oldUser = new User(user);
        try{
            if (!isEmpty(name)) user.setName(name);
            if (!isEmpty(password)) user.setPassword(EncryptUtil.encryptPWD(password));
            if (!isEmpty(dateOfBirth)) user.setDateOfBirth(dateOfBirth);
            if (!isEmpty(speciality)) user.setSpeciality(speciality);
            UserDAO userDAO = getUserDAO(user.getType());
            userDAO.update(oldUser, user);
            return user;
        }
        catch (DataValidationException e){
           return "Edition failed: you entered invalid " + StringUtil.readExceptionCause(e.getMessage());
        }

    }

}
