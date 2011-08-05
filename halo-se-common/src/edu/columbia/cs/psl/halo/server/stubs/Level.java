
package edu.columbia.cs.psl.halo.server.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import edu.columbia.cs.psl.halo.server.wrapper.EqualsHashCodeProvider;


/**
 * <p>Java class for level complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="level">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="level" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="xpMax" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="xpRequired" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "level", propOrder = {
    "id",
    "level",
    "xpMax",
    "xpRequired"
})
public class Level
    extends EqualsHashCodeProvider
    implements Serializable
{

    private final static long serialVersionUID = 100L;
    protected int id;
    protected int level;
    protected int xpMax;
    protected int xpRequired;

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
     * Gets the value of the level property.
     * 
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the value of the level property.
     * 
     */
    public void setLevel(int value) {
        this.level = value;
    }

    /**
     * Gets the value of the xpMax property.
     * 
     */
    public int getXpMax() {
        return xpMax;
    }

    /**
     * Sets the value of the xpMax property.
     * 
     */
    public void setXpMax(int value) {
        this.xpMax = value;
    }

    /**
     * Gets the value of the xpRequired property.
     * 
     */
    public int getXpRequired() {
        return xpRequired;
    }

    /**
     * Sets the value of the xpRequired property.
     * 
     */
    public void setXpRequired(int value) {
        this.xpRequired = value;
    }

}
