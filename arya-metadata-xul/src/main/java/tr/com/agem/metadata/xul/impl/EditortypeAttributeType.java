//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.02 at 05:11:41 PM EEST 
//


package tr.com.agem.metadata.xul.impl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for editortypeAttributeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="editortypeAttributeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="html"/>
 *     &lt;enumeration value="text"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "editortypeAttributeType")
@XmlEnum
public enum EditortypeAttributeType {


    /**
     * 
     *             html: An HTML editor.
     *           
     * 
     */
    @XmlEnumValue("html")
    HTML("html"),

    /**
     * 
     *             text: A plaintext editor.
     *           
     * 
     */
    @XmlEnumValue("text")
    TEXT("text");
    private final String value;

    EditortypeAttributeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EditortypeAttributeType fromValue(String v) {
        for (EditortypeAttributeType c: EditortypeAttributeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
