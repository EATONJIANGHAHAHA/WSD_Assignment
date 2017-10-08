/**
 * Booking.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package service.soap.client;

public class Booking  extends service.soap.client.BaseModel  implements java.io.Serializable {
    private java.lang.String status;

    private java.lang.String studentEmail;

    private java.lang.String studentName;

    private java.lang.String subject;

    private java.lang.String tutorEmail;

    private java.lang.String tutorName;

    public Booking() {
    }

    public Booking(
           java.lang.Integer id,
           java.lang.String status,
           java.lang.String studentEmail,
           java.lang.String studentName,
           java.lang.String subject,
           java.lang.String tutorEmail,
           java.lang.String tutorName) {
        super(
            id);
        this.status = status;
        this.studentEmail = studentEmail;
        this.studentName = studentName;
        this.subject = subject;
        this.tutorEmail = tutorEmail;
        this.tutorName = tutorName;
    }


    /**
     * Gets the status value for this Booking.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this Booking.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the studentEmail value for this Booking.
     * 
     * @return studentEmail
     */
    public java.lang.String getStudentEmail() {
        return studentEmail;
    }


    /**
     * Sets the studentEmail value for this Booking.
     * 
     * @param studentEmail
     */
    public void setStudentEmail(java.lang.String studentEmail) {
        this.studentEmail = studentEmail;
    }


    /**
     * Gets the studentName value for this Booking.
     * 
     * @return studentName
     */
    public java.lang.String getStudentName() {
        return studentName;
    }


    /**
     * Sets the studentName value for this Booking.
     * 
     * @param studentName
     */
    public void setStudentName(java.lang.String studentName) {
        this.studentName = studentName;
    }


    /**
     * Gets the subject value for this Booking.
     * 
     * @return subject
     */
    public java.lang.String getSubject() {
        return subject;
    }


    /**
     * Sets the subject value for this Booking.
     * 
     * @param subject
     */
    public void setSubject(java.lang.String subject) {
        this.subject = subject;
    }


    /**
     * Gets the tutorEmail value for this Booking.
     * 
     * @return tutorEmail
     */
    public java.lang.String getTutorEmail() {
        return tutorEmail;
    }


    /**
     * Sets the tutorEmail value for this Booking.
     * 
     * @param tutorEmail
     */
    public void setTutorEmail(java.lang.String tutorEmail) {
        this.tutorEmail = tutorEmail;
    }


    /**
     * Gets the tutorName value for this Booking.
     * 
     * @return tutorName
     */
    public java.lang.String getTutorName() {
        return tutorName;
    }


    /**
     * Sets the tutorName value for this Booking.
     * 
     * @param tutorName
     */
    public void setTutorName(java.lang.String tutorName) {
        this.tutorName = tutorName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Booking)) return false;
        Booking other = (Booking) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.studentEmail==null && other.getStudentEmail()==null) || 
             (this.studentEmail!=null &&
              this.studentEmail.equals(other.getStudentEmail()))) &&
            ((this.studentName==null && other.getStudentName()==null) || 
             (this.studentName!=null &&
              this.studentName.equals(other.getStudentName()))) &&
            ((this.subject==null && other.getSubject()==null) || 
             (this.subject!=null &&
              this.subject.equals(other.getSubject()))) &&
            ((this.tutorEmail==null && other.getTutorEmail()==null) || 
             (this.tutorEmail!=null &&
              this.tutorEmail.equals(other.getTutorEmail()))) &&
            ((this.tutorName==null && other.getTutorName()==null) || 
             (this.tutorName!=null &&
              this.tutorName.equals(other.getTutorName())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getStudentEmail() != null) {
            _hashCode += getStudentEmail().hashCode();
        }
        if (getStudentName() != null) {
            _hashCode += getStudentName().hashCode();
        }
        if (getSubject() != null) {
            _hashCode += getSubject().hashCode();
        }
        if (getTutorEmail() != null) {
            _hashCode += getTutorEmail().hashCode();
        }
        if (getTutorName() != null) {
            _hashCode += getTutorName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Booking.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model", "Booking"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("studentEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model", "studentEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("studentName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model", "studentName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model", "subject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tutorEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model", "tutorEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tutorName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model", "tutorName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
