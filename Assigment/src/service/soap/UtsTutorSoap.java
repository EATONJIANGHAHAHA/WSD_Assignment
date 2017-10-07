package service.soap;

import dao.BookingDAO;
import dao.BookingDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import exception.AuthenticationException;
import exception.DataValidationException;
import jaxblist.Bookings;
import model.Booking;
import model.User;
import util.DigestUtil;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import static dao.BookingDAOImpl.WEB_INF_BOOKINGS_XML;
import static dao.UserDAOImpl.WEB_INF_STUDENTS_XML;
import static dao.UserDAOImpl.WEB_INF_TUTORS_XML;
import static dao.UserDAOImpl.WEB_INF_USERS_XSD;
import static model.Booking.ACTIVE;
import static model.Booking.CANCELLED;
import static model.Booking.COMPLETED;
import static model.User.AVAILABLE;
import static model.User.TUTOR;
import static model.User.UNAVAILABLE;
import static util.StringUtil.isEmpty;

@WebService(serviceName = "utsTutorService")
public class UtsTutorSoap {


    public static final String TUTOR_DAO = "tutorDao";
    public static final String STUDENT_DAO = "studentDao";
    public static final String BOOKING_DAO = "bookingDao";
    private User user;

    @Resource
    private WebServiceContext context;

    /**
     * Return the user dao according to current user type.
     * @return
     */
    private UserDAO getUserDAO(){
        return getUserDAO(user.getType());
    }

    /**
     * return the user dao according to the user type.
     * @param userType
     * @return
     */
    private UserDAO getUserDAO(String userType){
        String tag;
        String xmlPath;
        if(userType.equals(TUTOR)) {
            tag = TUTOR_DAO;
            xmlPath = WEB_INF_TUTORS_XML;
        }
        else {
            tag = STUDENT_DAO;
            xmlPath = WEB_INF_STUDENTS_XML;
        }
        ServletContext application = (ServletContext)context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);;
        synchronized (application) {
            UserDAO userDAO = (UserDAOImpl)application
                    .getAttribute(tag);
            if (userDAO == null) {
                userDAO = new UserDAOImpl(application.getRealPath(xmlPath), application.getRealPath(WEB_INF_USERS_XSD));
                application.setAttribute(tag, userDAO);
            }
            return userDAO;
        }
    }

    private BookingDAO getBookingDAO(){
        ServletContext application = (ServletContext)context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);;
        synchronized (application) {
            BookingDAO bookingDAO = (BookingDAOImpl)application
                    .getAttribute(BOOKING_DAO);
            if (bookingDAO == null) {
               bookingDAO = new BookingDAOImpl(application.getRealPath(WEB_INF_BOOKINGS_XML), application.getRealPath(WEB_INF_BOOKINGS_XML));
                application.setAttribute(BOOKING_DAO, bookingDAO);
            }
            return bookingDAO;
        }
    }


    /**
     * Return true if the user is logged in successfully.
     * @param email
     * @param password
     * @param userType
     * @return
     */
    @WebMethod
    public User login(String email, String password, String userType){
        user = getUserDAO(userType).login(email, DigestUtil.encryptPWD(password));
        return user;
    }

    /**
     * Return the result of creating the booking.
     * @param tutorEmail
     * @return
     */
    @WebMethod
    public Booking createBooking(String tutorEmail) throws AuthenticationException {
        if(user != null && user.isStudent()) {
            UserDAO tutorDAO = getUserDAO(TUTOR);
            User tutor = tutorDAO.searchByEmail(tutorEmail);
            if(tutor.isAvailable()){
                Booking booking = new Booking(user.getName(), user.getEmail(), tutor.getName(), tutor.getEmail(),
                        tutor.getSpeciality());
                tutor.setAvailability(UNAVAILABLE);
                try{
                    getBookingDAO().create(booking);
                    tutorDAO.save();
                } catch (DataValidationException e) {
                    e.printStackTrace();
                }
                return booking;
            }
            return null;
        }
        throw new AuthenticationException();
    }

    /**
     * Return the booking records.
     * @param id
     * @param subject
     * @param studentEmail
     * @param status
     * @return
     */
    @WebMethod
    public Bookings getBookings(Integer id, String subject, String studentEmail, String status){
        return getBookingDAO().getBookings(id, subject, studentEmail, status);
    }

    /**
     * Cancel booking according to the booking id.
     * @param bookingId
     * @return
     */
    public Booking cancelBooking(Integer bookingId) throws AuthenticationException {
        if(user != null){
            BookingDAO bookingDAO = getBookingDAO();
            Booking booking = bookingDAO.searchById(bookingId);
            if(booking.getStatus().equals(ACTIVE)) {
                booking.setStatus(CANCELLED);
                UserDAO tutorDAO = getUserDAO(TUTOR);
                tutorDAO.searchByEmail(booking.getTutorEmail()).setAvailability(AVAILABLE);
                try{
                    bookingDAO.save();
                    tutorDAO.save();
                    return booking;
                } catch (DataValidationException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        throw new AuthenticationException();
    }

    /**
     * Complete the booking by id if the user is a tutor.
     * @param bookingId
     * @return
     * @throws AuthenticationException
     */
    @WebMethod
    public Booking completeBooking(Integer bookingId) throws AuthenticationException {
        if(user == null || user.isStudent()) {
            throw new AuthenticationException();
        }
        else {
            BookingDAO bookingDAO = getBookingDAO();
            Booking booking = bookingDAO.searchById(bookingId);
            if(booking.getStatus().equals(ACTIVE)) {
                booking.setStatus(COMPLETED);
                user.setAvailability(AVAILABLE);
                try{
                    bookingDAO.save();
                    getUserDAO().save();
                    return booking;
                } catch (DataValidationException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    /**
     * Allows an user to cancel his account.
     * @return
     * @throws AuthenticationException
     */
    @WebMethod
    public boolean cancelAccount() throws AuthenticationException {
        if(user != null){
            try {
                BookingDAO bookingDAO = getBookingDAO();
                Bookings bookings = bookingDAO.searchByEmail(user.getEmail(), user.isStudent());
                if(bookings.getList() != null && bookings.getList().size() != 0){
                    for(Booking booking: bookings.findByStatus(ACTIVE).getList()) booking.setStatus(CANCELLED);
                    bookingDAO.save();
                }
                getUserDAO().delete(user);
                user = null;
                return true;
            } catch (DataValidationException e) {
                e.printStackTrace();
                return false;
            }
        }
        throw new AuthenticationException();
    }

    /**
     * Get the current user.
     * @return
     */
    @WebMethod
    public User fetchUser(){
        return user;
    }

    /**
     * Allows the user to edit their account.
     * @return
     */
    @WebMethod
    public User editAccount(String name, String password, String dateOfBirth, String speciality) throws DataValidationException, AuthenticationException {
        if(user == null) throw new AuthenticationException();
        User oldUser = new User(user);
        if(!isEmpty(name)) user.setName(name);
        if(!isEmpty(password)) user.setPassword(DigestUtil.encryptPWD(password));
        if(!isEmpty(dateOfBirth)) user.setDateOfBirth(dateOfBirth);
        if(!isEmpty(speciality)) user.setSpeciality(speciality);
        UserDAO userDAO = getUserDAO();
        userDAO.update(oldUser, user);
        return user;
    }

}
