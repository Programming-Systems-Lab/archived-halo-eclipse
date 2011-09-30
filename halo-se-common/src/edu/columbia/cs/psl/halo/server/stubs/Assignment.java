
package edu.columbia.cs.psl.halo.server.stubs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for assignment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="assignment">
 *   &lt;complexContent>
 *     &lt;extension base="{http://server.halo.psl.cs.columbia.edu/}lazyCycleBreaker">
 *       &lt;sequence>
 *         &lt;element name="assignedOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element ref="{http://server.halo.psl.cs.columbia.edu/}course" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dueOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="quests" type="{http://server.halo.psl.cs.columbia.edu/}quest" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "assignment", propOrder = {
    "assignedOn",
    "course",
    "description",
    "dueOn",
    "id",
    "quests",
    "title"
})
public class Assignment
    extends LazyCycleBreaker
    implements Serializable
{

    private final static long serialVersionUID = 100L;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar assignedOn;
    @XmlElement(namespace = "http://server.halo.psl.cs.columbia.edu/")
    protected Course course;
    protected String description;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dueOn;
    protected int id;
    @XmlElement(nillable = true)
    protected List<Quest> quests;
    protected String title;

    /**
     * Gets the value of the assignedOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAssignedOn() {
        return assignedOn;
    }

    /**
     * Sets the value of the assignedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAssignedOn(XMLGregorianCalendar value) {
        this.assignedOn = value;
    }

    /**
     * Gets the value of the course property.
     * 
     * @return
     *     possible object is
     *     {@link Course }
     *     
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Sets the value of the course property.
     * 
     * @param value
     *     allowed object is
     *     {@link Course }
     *     
     */
    public void setCourse(Course value) {
        this.course = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the dueOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDueOn() {
        return dueOn;
    }

    /**
     * Sets the value of the dueOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDueOn(XMLGregorianCalendar value) {
        this.dueOn = value;
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
     * Gets the value of the quests property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the quests property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuests().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Quest }
     * 
     * 
     */
    public List<Quest> getQuests() {
        if (quests == null) {
            quests = new ArrayList<Quest>();
        }
        return this.quests;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

}
