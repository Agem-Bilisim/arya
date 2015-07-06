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
 * <p>Java class for stateAttributeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="stateAttributeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="open"/>
 *     &lt;enumeration value="collapsed"/>
 *     &lt;enumeration value="dragging"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "stateAttributeType")
@XmlEnum
public enum StateAttributeType {


    /**
     * 
     *             open: The content either before or after the splitter, depending on the value of the collapsed attribute, is currently displayed.
     *           
     * 
     */
    @XmlEnumValue("open")
    OPEN("open"),

    /**
     * 
     *             collapsed: The content either before or after the splitter is collapsed and is not visible.
     *           
     * 
     */
    @XmlEnumValue("collapsed")
    COLLAPSED("collapsed"),

    /**
     * 
     *             dragging: The user is current adjusting the position of the splitter, typically by dragging it with the mouse.
     *           
     * 
     */
    @XmlEnumValue("dragging")
    DRAGGING("dragging");
    private final String value;

    StateAttributeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StateAttributeType fromValue(String v) {
        for (StateAttributeType c: StateAttributeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
