
package edu.columbia.cs.psl.halo.server.stubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for task complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="task">
 *   &lt;complexContent>
 *     &lt;extension base="{http://server.halo.psl.cs.columbia.edu/}lazyCycleBreaker">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parent" type="{http://server.halo.psl.cs.columbia.edu/}task" minOccurs="0"/>
 *         &lt;element name="quest" type="{http://server.halo.psl.cs.columbia.edu/}quest" minOccurs="0"/>
 *         &lt;element name="type" type="{http://server.halo.psl.cs.columbia.edu/}taskType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "task", propOrder = {
    "description",
    "name",
    "parent",
    "quest",
    "type"
})
public class Task
    extends LazyCycleBreaker
{

    protected String description;
    protected String name;
    protected Task parent;
    protected Quest quest;
    protected TaskType type;

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

    /**
     * Gets the value of the parent property.
     * 
     * @return
     *     possible object is
     *     {@link Task }
     *     
     */
    public Task getParent() {
        return parent;
    }

    /**
     * Sets the value of the parent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Task }
     *     
     */
    public void setParent(Task value) {
        this.parent = value;
    }

    /**
     * Gets the value of the quest property.
     * 
     * @return
     *     possible object is
     *     {@link Quest }
     *     
     */
    public Quest getQuest() {
        return quest;
    }

    /**
     * Sets the value of the quest property.
     * 
     * @param value
     *     allowed object is
     *     {@link Quest }
     *     
     */
    public void setQuest(Quest value) {
        this.quest = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link TaskType }
     *     
     */
    public TaskType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaskType }
     *     
     */
    public void setType(TaskType value) {
        this.type = value;
    }

}
