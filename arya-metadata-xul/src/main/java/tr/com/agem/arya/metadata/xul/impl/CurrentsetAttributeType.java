//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.03 at 11:33:56 AM EEST 
//


package tr.com.agem.arya.metadata.xul.impl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for currentsetAttributeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="currentsetAttributeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="separator"/>
 *     &lt;enumeration value="spring"/>
 *     &lt;enumeration value="spacer"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "currentsetAttributeType")
@XmlEnum
public enum CurrentsetAttributeType {

    @XmlEnumValue("separator")
    SEPARATOR("separator"),
    @XmlEnumValue("spring")
    SPRING("spring"),
    @XmlEnumValue("spacer")
    SPACER("spacer");
    private final String value;

    CurrentsetAttributeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CurrentsetAttributeType fromValue(String v) {
        for (CurrentsetAttributeType c: CurrentsetAttributeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
