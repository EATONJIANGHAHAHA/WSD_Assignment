package service.soap.client;

import util.StringUtil;

import java.util.List;
import java.util.Scanner;

public class UtsTutorClient {

    public static final String AUTHENTICATION_ALERT = "Please log in first.";

    public static void main(String[] args){
        UtsTutorService service = new UtsTutorService();
        UtsTutorSoap utsTutorSoap = service.getUtsTutorSoapPort();
        User user = null;;
        Integer option;
        do {
            displayMenu(user);
            option = getNextInteger("Your option:");
            if(option == null) option = -2;
            switch (option) {
                case -1:
                    break;
                case 0: // login.
                    user = utsTutorSoap.login(getNextString("Email: "), getNextString("Password: ")
                            , getNextString("User type (tutor/student): "));
                    if (user == null) System.out.println("Incorrect email or password");
                    break;
                case 1: //View bookings
                    List<Booking> bookings = utsTutorSoap.getBookings(getNextInteger("Booking id: "),
                            getNextString("Subject: "), getNextString("Student email: "),
                            getNextString("Status: "));
                    if (bookings != null && bookings.size() != 0) {
                        for (Booking booking : bookings) {
                            readBooking(booking);
                        }
                    } else System.out.print("No such record. ");
                    break;
                case 2: //Cancel a booking
                    if (user != null) {
                        Object result = null;
                        try {
                            result = utsTutorSoap.cancelBooking(getNextInteger("Booking id: "), user);
                            Booking booking = (Booking) result;
                            readBooking(booking);
                            System.out.println("This booking is cancelled.");
                        } catch (ClassCastException e) {
                            String s = (String) result;
                            System.out.println(s);
                        }

                    } else System.out.println(AUTHENTICATION_ALERT);
                    break;
                case 3://Edit account
                    if (user != null) {
                        Object result = new Object();
                        try {
                            System.out.println("Current account");
                            readUser(user);
                            System.out.println("leave the following blank if you don't want to change its value.");

                            if (user.getSpeciality() == null)
                                result = utsTutorSoap.editAccount(
                                        getNextString("New name: "), getNextString("New password: "),
                                        getNextString("Date of birth(yyyy-mm-dd): "), null, user);
                            else result = utsTutorSoap.editAccount(
                                    getNextString("New name: "), getNextString("New password: "),
                                    getNextString("Date of birth(yyyy-mm-dd): "),
                                    getNextString("New speciality: "), user);
                            user = (User) result;
                        }
                        catch (ClassCastException e){
                            String s = (String)result;
                            System.out.println(s);
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
                        if (user.getSpeciality() == null) {
                            Object result = null;
                            try {
                                result = utsTutorSoap.createBooking(getNextString("Tutor email: "), user);
                                Booking booking = (Booking) result;
                                if (booking != null) {
                                    System.out.print("Successfully created: " );
                                    readBooking(booking);
                                }
                            } catch (ClassCastException e) {
                                String s = (String) result;
                                System.out.println(s);
                            }
                        } else {
                            Object result = null;
                            try {
                                result = utsTutorSoap.completeBooking(getNextInteger("Booking id: "), user);
                                Booking booking = (Booking)result;
                                if (booking != null){
                                    System.out.print("Successfully completed: " );
                                    readBooking(booking);
                                }
                            } catch (ClassCastException e) {
                                String s = (String) result;
                                System.out.println(s);
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
            System.out.println("Please enter a valid number.");
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
     * Display the booking message.
     * @param booking
     */
    private static void readBooking(Booking booking){
        System.out.println("ID: " + booking.getId() + "; Student Name: " + booking.getStudentName()
                + "; Student Email: " + booking.getStudentEmail() + "; Tutor Name: "
                + booking.getTutorEmail() + "; Subject: " + booking.getSubject() + "; Status: "
                + booking.getStatus());
    }

    /**
     * Display the user information.
     * @param user
     */
    private static void readUser(User user){
        String s = "ID: " + user.getId() + "; Email: " + user.getEmail() + "Password: *******; Name: " + user.getName()
                + "; Date Of Birth: " + user.getDateOfBirth();
        if(user.getSpeciality() != null) s += "; Speciality: " + user.getSpeciality();
        System.out.println(s);
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
            if (user.getSpeciality()!= null) System.out.println(
                    "5.Complete a bookings");
            else System.out.println("5.Create a booking");
            System.out.println("6.Log out");
        }
    }
}
