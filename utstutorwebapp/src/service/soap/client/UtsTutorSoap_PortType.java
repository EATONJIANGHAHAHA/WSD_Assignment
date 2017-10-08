/**
 * UtsTutorSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package service.soap.client;

public interface UtsTutorSoap_PortType extends java.rmi.Remote {
    public service.soap.client.Booking createBooking(java.lang.String tutorEmail, service.soap.client.User user) throws java.rmi.RemoteException;
    public java.lang.Object[] getBookings(int id, java.lang.String subject, java.lang.String studentEmail, java.lang.String status) throws java.rmi.RemoteException;
    public service.soap.client.User login(java.lang.String email, java.lang.String password, java.lang.String userType) throws java.rmi.RemoteException;
    public boolean cancelAccount(service.soap.client.User user) throws java.rmi.RemoteException;
    public service.soap.client.Booking cancelBooking(int bookingId, service.soap.client.User user) throws java.rmi.RemoteException;
    public service.soap.client.User editAccount(java.lang.String name, java.lang.String password, java.lang.String dateOfBirth, java.lang.String speciality, service.soap.client.User user) throws java.rmi.RemoteException, service.soap.client.DataValidationException;
    public service.soap.client.Booking completeBooking(int bookingId, service.soap.client.User user) throws java.rmi.RemoteException;
}
