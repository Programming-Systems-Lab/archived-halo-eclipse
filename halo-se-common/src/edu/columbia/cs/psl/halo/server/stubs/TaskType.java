
package edu.columbia.cs.psl.halo.server.stubs;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for taskType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="taskType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SOMETHING"/>
 *     &lt;enumeration value="SOMETHINGELSE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "taskType")
@XmlEnum
public enum TaskType {

    SOMETHING,
    SOMETHINGELSE;

    public String value() {
        return name();
    }

    public static TaskType fromValue(String v) {
        return valueOf(v);
    }

}
