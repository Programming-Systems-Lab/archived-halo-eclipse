
package edu.columbia.cs.psl.halo.server.stubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for user complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="user">
 *   &lt;complexContent>
 *     &lt;extension base="{http://server.halo.psl.cs.columbia.edu/}lazyCycleBreaker">
 *       &lt;sequence>
 *         &lt;element name="achievementPoints" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="activeTitle" type="{http://server.halo.psl.cs.columbia.edu/}title" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="level" type="{http://server.halo.psl.cs.columbia.edu/}level" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://server.halo.psl.cs.columbia.edu/}userStatus" minOccurs="0"/>
 *         &lt;element name="xp" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user", propOrder = {
    "achievementPoints",
    "activeTitle",
    "email",
    "firstName",
    "id",
    "lastName",
    "level",
    "password",
    "status",
    "xp"
})
public class User
    extends LazyCycleBreaker
{

    protected int achievementPoints;
    protected Title activeTitle;
    protected String email;
    protected String firstName;
    protected int id;
    protected String lastName;
    protected Level level;
    protected String password;
    protected UserStatus status;
    protected int xp;

    /**
     * Gets the value of the achievementPoints property.
     * 
     */
    public int getAchievementPoints() {
        return achievementPoints;
    }

    /**
     * Sets the value of the achievementPoints property.
     * 
     */
    public void setAchievementPoints(int value) {
        this.achievementPoints = value;
    }

    /**
     * Gets the value of the activeTitle property.
     * 
     * @return
     *     possible object is
     *     {@link Title }
     *     
     */
    public Title getActiveTitle() {
        return activeTitle;
    }

    /**
     * Sets the value of the activeTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Title }
     *     
     */
    public void setActiveTitle(Title value) {
        this.activeTitle = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
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
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the level property.
     * 
     * @return
     *     possible object is
     *     {@link Level }
     *     
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Sets the value of the level property.
     * 
     * @param value
     *     allowed object is
     *     {@link Level }
     *     
     */
    public void setLevel(Level value) {
        this.level = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link UserStatus }
     *     
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserStatus }
     *     
     */
    public void setStatus(UserStatus value) {
        this.status = value;
    }

    /**
     * Gets the value of the xp property.
     * 
     */
    public int getXp() {
        return xp;
    }

    /**
     * Sets the value of the xp property.
     * 
     */
    public void setXp(int value) {
        this.xp = value;
    }

}
