
package edu.columbia.cs.psl.halo.server.stubs;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for titlePosition.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="titlePosition">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="infix"/>
 *     &lt;enumeration value="prefix"/>
 *     &lt;enumeration value="sufix"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "titlePosition")
@XmlEnum
public enum TitlePosition {

    @XmlEnumValue("infix")
    INFIX("infix"),
    @XmlEnumValue("prefix")
    PREFIX("prefix"),
    @XmlEnumValue("sufix")
    SUFIX("sufix");
    private final String value;

    TitlePosition(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TitlePosition fromValue(String v) {
        for (TitlePosition c: TitlePosition.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
