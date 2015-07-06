//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.03 at 04:40:57 PM EEST 
//


package tr.com.agem.arya.metadata.xul.impl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for separatorOrientAttributeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="separatorOrientAttributeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="horizontal"/>
 *     &lt;enumeration value="vertical"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "separatorOrientAttributeType")
@XmlEnum
public enum SeparatorOrientAttributeType {


    /**
     * horizontal: The separator separates vertically placed elements
     * 
     */
    @XmlEnumValue("horizontal")
    HORIZONTAL("horizontal"),

    /**
     * vertical: The separator separates horizontally placed elements.
     * 
     */
    @XmlEnumValue("vertical")
    VERTICAL("vertical");
    private final String value;

    SeparatorOrientAttributeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SeparatorOrientAttributeType fromValue(String v) {
        for (SeparatorOrientAttributeType c: SeparatorOrientAttributeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
