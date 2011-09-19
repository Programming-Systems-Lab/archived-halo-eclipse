
package edu.columbia.cs.psl.halo.server.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import edu.columbia.cs.psl.halo.server.wrapper.EqualsHashCodeProvider;


/**
 * <p>Java class for postQuestCopmletionToFacebook complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="postQuestCopmletionToFacebook">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://server.halo.psl.cs.columbia.edu/}quest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "postQuestCopmletionToFacebook", propOrder = {
    "arg0"
})
public class PostQuestCopmletionToFacebook
    extends EqualsHashCodeProvider
    implements Serializable
{

    private final static long serialVersionUID = 100L;
    protected Quest arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link Quest }
     *     
     */
    public Quest getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Quest }
     *     
     */
    public void setArg0(Quest value) {
        this.arg0 = value;
    }

}
