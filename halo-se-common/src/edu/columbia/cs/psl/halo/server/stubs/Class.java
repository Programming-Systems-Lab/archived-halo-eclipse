
package edu.columbia.cs.psl.halo.server.stubs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for class complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="class">
 *   &lt;complexContent>
 *     &lt;extension base="{http://server.halo.psl.cs.columbia.edu/}lazyCycleBreaker">
 *       &lt;sequence>
 *         &lt;element name="enrollments" type="{http://server.halo.psl.cs.columbia.edu/}enrollment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "class", propOrder = {
    "enrollments",
    "id",
    "name"
})
public class Class
    extends LazyCycleBreaker
{

    @XmlElement(nillable = true)
    protected List<Enrollment> enrollments;
    protected int id;
    protected String name;

    /**
     * Gets the value of the enrollments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the enrollments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEnrollments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Enrollment }
     * 
     * 
     */
    public List<Enrollment> getEnrollments() {
        if (enrollments == null) {
            enrollments = new ArrayList<Enrollment>();
        }
        return this.enrollments;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
