
package edu.columbia.cs.psl.halo.server.stubs;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for enrollmentType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="enrollmentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="STUDENT"/>
 *     &lt;enumeration value="PROFESSOR"/>
 *     &lt;enumeration value="TA"/>
 *     &lt;enumeration value="ADMIN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "enrollmentType")
@XmlEnum
public enum EnrollmentType {

    STUDENT,
    PROFESSOR,
    TA,
    ADMIN;

    public String value() {
        return name();
    }

    public static EnrollmentType fromValue(String v) {
        return valueOf(v);
    }

}
