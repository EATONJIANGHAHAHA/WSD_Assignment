/**
 * UtsTutorSoapService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package service.soap.client;

public interface UtsTutorSoapService extends javax.xml.rpc.Service {
    public java.lang.String getUtsTutorSoapAddress();

    public service.soap.client.UtsTutorSoap_PortType getUtsTutorSoap() throws javax.xml.rpc.ServiceException;

    public service.soap.client.UtsTutorSoap_PortType getUtsTutorSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
