package service.soap.client;

import util.StringUtil;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

import java.util.Scanner;

public class UtsTutorClient {

    public static final String AUTHENTICATION_ALERT = "Please log in first.";

    public static void main(String[] args) throws ServiceException, RemoteException {
        UtsTutorSoapService service = new UtsTutorSoapServiceLocator();
        UtsTutorSoap_PortType utsTutorSoap = service.getUtsTutorSoap();
        User user = null;
        Scanner in = new Scanner(System.in);
        int option;
        do {
            displayMenu(user);
            option = in.nextInt();
            switch (option) {
                case -1:
                    break;
                case 0: // login.
                    user = utsTutorSoap.login(getNextString("Email: "), getNextString("password")
                            , getNextString("User type (tutor/student): "));
                    if (user == null) System.out.println("Incorrect email or password");
                    break;
                case 1: //View bookings
                    Booking[] bookings = (Booking[]) utsTutorSoap.getBookings(getNextInteger("Booking id: "),
                            getNextString("Subject: "), getNextString("Student email: "),
                            getNextString("Status: "));
                    if (bookings != null && bookings.length != 0) {
                        for (Booking booking : bookings) {
                            System.out.print("id: " + booking.getId() + " student name: " + booking.getStudentName()
                                    + " student email: " + booking.getStudentEmail() + " tutor name: "
                                    + booking.getTutorEmail() + " subject: " + booking.getSubject() + " status: "
                                    + booking.getStatus());
                        }
                    } else System.out.print("No such record. ");
                    break;
                case 2: //Cancel a booking
                    if (user != null) {
                        try {
                            Booking booking = utsTutorSoap.cancelBooking(getNextInteger("Booking id: "), user);
                            if (booking != null) System.out.println(booking.toString() + " is cancelled.");
                            else
                                System.out.println("The booking can not be cancelled (already cancelled or completed)" +
                                        " or it is not your booking.");
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                            System.out.println("No such booking record.");
                        }

                    } else System.out.println(AUTHENTICATION_ALERT);
                    break;
                case 3://Edit account
                    if (user != null) {
                        try {
                            System.out.println("Current account");
                            System.out.println(user.toString());
                            System.out.println("leave the following blank if you don't want to change its value.");
                            if (user.isStudent())
                                utsTutorSoap.editAccount(
                                        getNextString("New name: "), getNextString("New password: "),
                                        getNextString("Date of birth(yyyy-mm-dd): "), null, user);
                            else utsTutorSoap.editAccount(
                                    getNextString("New name: "), getNextString("New password: "),
                                    getNextString("Date of birth(yyyy-mm-dd): "),
                                    getNextString("New speciality: "), user);
                        } catch (DataValidationException e) {
                            e.printStackTrace();
                            System.out.println("Edition failed: you have entered invalid " +
                                    StringUtil.readExceptionCause(e.getMessage()) + ".");
                        }
                    } else System.out.print(AUTHENTICATION_ALERT);
                    break;
                case 4://Cancel account
                    if (user != null) {
                        if (utsTutorSoap.cancelAccount(user))
                            System.out.println("Your account is cancelled successfully");
                        user = null;
                    } else System.out.println(AUTHENTICATION_ALERT);
                    break;
                case 5://Create a booking(Student); Complete a booking(Tutor)
                    if (user != null) {
                        if (user.isStudent()) {
                            try {
                                Booking booking = utsTutorSoap.createBooking(getNextString("Tutor email: "), user);
                                if (booking != null) System.out.println("Successfully created: " + booking.toString());
                                else System.out.println("The tutor is currently unavailable");
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                                System.out.println("Cannot find the tutor with this email address");
                            }
                        } else {
                            try {
                                Booking booking = utsTutorSoap.completeBooking(getNextInteger("Booking id: "), user);
                                if (booking != null)
                                    System.out.println("Successfully completed: " + booking.toString());
                                else
                                    System.out.println("It is not your booking or the booking is already competed or " +
                                            "cancelled.");
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                                System.out.println("No such booking record");
                            }

                        }
                    } else System.out.println(AUTHENTICATION_ALERT);
                    break;
                case 6:
                    user = null;
                    break;

            }
        } while (option != -1);


    }

    /**
     * Get an integer.
     *
     * @param msg
     * @return
     */
    public static Integer getNextInteger(String msg) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(msg);
        String s = scanner.nextLine();
        if(StringUtil.isEmpty(s)) return null;
        try{
            return Integer.parseInt(s);
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Get a string.
     *
     * @param msg
     * @return
     */
    public static String getNextString(String msg) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(msg);
        return scanner.nextLine();
    }

    /**
     * Display a menu.
     *
     * @param user
     * @return
     */
    private static void displayMenu(User user) {
        System.out.println("Menu");
        if (user == null) {
            System.out.println("0.Log in");
            System.out.println("1.View all bookings");
            System.out.println("-1.exit");
        } else {
            System.out.println("You now log in as " + user.getName());
            System.out.println("1.View all bookings");
            System.out.println("2.Cancel a booking");
            System.out.println("3.Edit account");
            System.out.println("4.Cancel account");
            if (user.getSpeciality() != null && user.getAvailability() != null) System.out.println(
                    "5.Complete a bookings");
            else System.out.println("5.Create a booking");
            System.out.println("6.Log out");
        }
    }
}
