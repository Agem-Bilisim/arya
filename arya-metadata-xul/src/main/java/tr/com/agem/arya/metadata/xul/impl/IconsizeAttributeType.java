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
 * <p>Java class for iconsizeAttributeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="iconsizeAttributeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="large"/>
 *     &lt;enumeration value="small"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "iconsizeAttributeType")
@XmlEnum
public enum IconsizeAttributeType {


    /**
     * large: Indicates that large icons should be displayed.
     * 
     */
    @XmlEnumValue("large")
    LARGE("large"),

    /**
     * small: Indicates that small icons should be displayed.
     * 
     */
    @XmlEnumValue("small")
    SMALL("small");
    private final String value;

    IconsizeAttributeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IconsizeAttributeType fromValue(String v) {
        for (IconsizeAttributeType c: IconsizeAttributeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}