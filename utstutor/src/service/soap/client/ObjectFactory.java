
package service.soap.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the service.soap.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EditAccount_QNAME = new QName("http://soap.service/", "editAccount");
    private final static QName _GetBookings_QNAME = new QName("http://soap.service/", "getBookings");
    private final static QName _User_QNAME = new QName("http://www.uts.edu.au/31284/wsd", "user");
    private final static QName _CreateBooking_QNAME = new QName("http://soap.service/", "createBooking");
    private final static QName _LoginResponse_QNAME = new QName("http://soap.service/", "loginResponse");
    private final static QName _CancelAccount_QNAME = new QName("http://soap.service/", "cancelAccount");
    private final static QName _CreateBookingResponse_QNAME = new QName("http://soap.service/", "createBookingResponse");
    private final static QName _CancelBooking_QNAME = new QName("http://soap.service/", "cancelBooking");
    private final static QName _EditAccountResponse_QNAME = new QName("http://soap.service/", "editAccountResponse");
    private final static QName _Booking_QNAME = new QName("http://www.uts.edu.au/31284/wsd", "booking");
    private final static QName _CancelAccountResponse_QNAME = new QName("http://soap.service/", "cancelAccountResponse");
    private final static QName _CancelBookingResponse_QNAME = new QName("http://soap.service/", "cancelBookingResponse");
    private final static QName _CompleteBooking_QNAME = new QName("http://soap.service/", "completeBooking");
    private final static QName _CompleteBookingResponse_QNAME = new QName("http://soap.service/", "completeBookingResponse");
    private final static QName _GetBookingsResponse_QNAME = new QName("http://soap.service/", "getBookingsResponse");
    private final static QName _Login_QNAME = new QName("http://soap.service/", "login");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: service.soap.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Booking }
     * 
     */
    public Booking createBooking() {
        return new Booking();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link BaseModel }
     * 
     */
    public BaseModel createBaseModel() {
        return new BaseModel();
    }

    /**
     * Create an instance of {@link CreateBookingResponse }
     * 
     */
    public CreateBookingResponse createCreateBookingResponse() {
        return new CreateBookingResponse();
    }

    /**
     * Create an instance of {@link CancelAccount }
     * 
     */
    public CancelAccount createCancelAccount() {
        return new CancelAccount();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link CancelBooking }
     * 
     */
    public CancelBooking createCancelBooking() {
        return new CancelBooking();
    }

    /**
     * Create an instance of {@link CompleteBooking }
     * 
     */
    public CompleteBooking createCompleteBooking() {
        return new CompleteBooking();
    }

    /**
     * Create an instance of {@link CancelAccountResponse }
     * 
     */
    public CancelAccountResponse createCancelAccountResponse() {
        return new CancelAccountResponse();
    }

    /**
     * Create an instance of {@link CancelBookingResponse }
     * 
     */
    public CancelBookingResponse createCancelBookingResponse() {
        return new CancelBookingResponse();
    }

    /**
     * Create an instance of {@link EditAccountResponse }
     * 
     */
    public EditAccountResponse createEditAccountResponse() {
        return new EditAccountResponse();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link CompleteBookingResponse }
     * 
     */
    public CompleteBookingResponse createCompleteBookingResponse() {
        return new CompleteBookingResponse();
    }

    /**
     * Create an instance of {@link GetBookingsResponse }
     * 
     */
    public GetBookingsResponse createGetBookingsResponse() {
        return new GetBookingsResponse();
    }

    /**
     * Create an instance of {@link GetBookings }
     * 
     */
    public GetBookings createGetBookings() {
        return new GetBookings();
    }

    /**
     * Create an instance of {@link EditAccount }
     * 
     */
    public EditAccount createEditAccount() {
        return new EditAccount();
    }

    /**
     * Create an instance of {@link CreateBooking }
     * 
     */
    public CreateBooking createCreateBooking() {
        return new CreateBooking();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditAccount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service/", name = "editAccount")
    public JAXBElement<EditAccount> createEditAccount(EditAccount value) {
        return new JAXBElement<EditAccount>(_EditAccount_QNAME, EditAccount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBookings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service/", name = "getBookings")
    public JAXBElement<GetBookings> createGetBookings(GetBookings value) {
        return new JAXBElement<GetBookings>(_GetBookings_QNAME, GetBookings.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.uts.edu.au/31284/wsd", name = "user")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateBooking }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service/", name = "createBooking")
    public JAXBElement<CreateBooking> createCreateBooking(CreateBooking value) {
        return new JAXBElement<CreateBooking>(_CreateBooking_QNAME, CreateBooking.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelAccount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service/", name = "cancelAccount")
    public JAXBElement<CancelAccount> createCancelAccount(CancelAccount value) {
        return new JAXBElement<CancelAccount>(_CancelAccount_QNAME, CancelAccount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateBookingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service/", name = "createBookingResponse")
    public JAXBElement<CreateBookingResponse> createCreateBookingResponse(CreateBookingResponse value) {
        return new JAXBElement<CreateBookingResponse>(_CreateBookingResponse_QNAME, CreateBookingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelBooking }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service/", name = "cancelBooking")
    public JAXBElement<CancelBooking> createCancelBooking(CancelBooking value) {
        return new JAXBElement<CancelBooking>(_CancelBooking_QNAME, CancelBooking.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditAccountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service/", name = "editAccountResponse")
    public JAXBElement<EditAccountResponse> createEditAccountResponse(EditAccountResponse value) {
        return new JAXBElement<EditAccountResponse>(_EditAccountResponse_QNAME, EditAccountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Booking }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.uts.edu.au/31284/wsd", name = "booking")
    public JAXBElement<Booking> createBooking(Booking value) {
        return new JAXBElement<Booking>(_Booking_QNAME, Booking.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelAccountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service/", name = "cancelAccountResponse")
    public JAXBElement<CancelAccountResponse> createCancelAccountResponse(CancelAccountResponse value) {
        return new JAXBElement<CancelAccountResponse>(_CancelAccountResponse_QNAME, CancelAccountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelBookingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service/", name = "cancelBookingResponse")
    public JAXBElement<CancelBookingResponse> createCancelBookingResponse(CancelBookingResponse value) {
        return new JAXBElement<CancelBookingResponse>(_CancelBookingResponse_QNAME, CancelBookingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompleteBooking }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service/", name = "completeBooking")
    public JAXBElement<CompleteBooking> createCompleteBooking(CompleteBooking value) {
        return new JAXBElement<CompleteBooking>(_CompleteBooking_QNAME, CompleteBooking.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompleteBookingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service/", name = "completeBookingResponse")
    public JAXBElement<CompleteBookingResponse> createCompleteBookingResponse(CompleteBookingResponse value) {
        return new JAXBElement<CompleteBookingResponse>(_CompleteBookingResponse_QNAME, CompleteBookingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBookingsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service/", name = "getBookingsResponse")
    public JAXBElement<GetBookingsResponse> createGetBookingsResponse(GetBookingsResponse value) {
        return new JAXBElement<GetBookingsResponse>(_GetBookingsResponse_QNAME, GetBookingsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

}
