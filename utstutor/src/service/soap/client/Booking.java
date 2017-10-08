
package service.soap.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for booking complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="booking">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.uts.edu.au/31284/wsd}baseModel">
 *       &lt;sequence>
 *         &lt;element name="student_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="student_email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tutor_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tutor_email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "booking", namespace = "http://www.uts.edu.au/31284/wsd", propOrder = {
    "studentName",
    "studentEmail",
    "tutorName",
    "tutorEmail",
    "subject",
    "status"
})
public class Booking
    extends BaseModel
{

    @XmlElement(name = "student_name", namespace = "http://www.uts.edu.au/31284/wsd")
    protected String studentName;
    @XmlElement(name = "student_email", namespace = "http://www.uts.edu.au/31284/wsd")
    protected String studentEmail;
    @XmlElement(name = "tutor_name", namespace = "http://www.uts.edu.au/31284/wsd")
    protected String tutorName;
    @XmlElement(name = "tutor_email", namespace = "http://www.uts.edu.au/31284/wsd")
    protected String tutorEmail;
    @XmlElement(namespace = "http://www.uts.edu.au/31284/wsd")
    protected String subject;
    @XmlElement(namespace = "http://www.uts.edu.au/31284/wsd")
    protected String status;

    /**
     * Gets the value of the studentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Sets the value of the studentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentName(String value) {
        this.studentName = value;
    }

    /**
     * Gets the value of the studentEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentEmail() {
        return studentEmail;
    }

    /**
     * Sets the value of the studentEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentEmail(String value) {
        this.studentEmail = value;
    }

    /**
     * Gets the value of the tutorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTutorName() {
        return tutorName;
    }

    /**
     * Sets the value of the tutorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTutorName(String value) {
        this.tutorName = value;
    }

    /**
     * Gets the value of the tutorEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTutorEmail() {
        return tutorEmail;
    }

    /**
     * Sets the value of the tutorEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTutorEmail(String value) {
        this.tutorEmail = value;
    }

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

}
