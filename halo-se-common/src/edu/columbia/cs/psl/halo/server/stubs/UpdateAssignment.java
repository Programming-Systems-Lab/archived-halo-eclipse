
package edu.columbia.cs.psl.halo.server.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import edu.columbia.cs.psl.halo.server.wrapper.EqualsHashCodeProvider;


/**
 * <p>Java class for updateAssignment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateAssignment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://server.halo.psl.cs.columbia.edu/}assignment" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateAssignment", propOrder = {
    "arg0"
})
public class UpdateAssignment
    extends EqualsHashCodeProvider
    implements Serializable
{

    private final static long serialVersionUID = 100L;
    protected Assignment arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link Assignment }
     *     
     */
    public Assignment getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Assignment }
     *     
     */
    public void setArg0(Assignment value) {
        this.arg0 = value;
    }

}
