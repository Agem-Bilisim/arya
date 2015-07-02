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
 * <p>Java class for paneltypeAttributeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="paneltypeAttributeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="autocomplete"/>
 *     &lt;enumeration value="autocomplete-richlistbox"/>
 *     &lt;enumeration value="arrow"/>
 *     &lt;enumeration value="drag"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "paneltypeAttributeType")
@XmlEnum
public enum PaneltypeAttributeType {


    /**
     * autocomplete: Specify this for a panel that provides a tree for an autocomplete element.
     * 
     */
    @XmlEnumValue("autocomplete")
    AUTOCOMPLETE("autocomplete"),

    /**
     * autocomplete-richlistbox: (New in Firefox 3.0)  Specify this for a panel that provides a richlistbox for an autocomplete element.
     * 
     */
    @XmlEnumValue("autocomplete-richlistbox")
    AUTOCOMPLETE_RICHLISTBOX("autocomplete-richlistbox"),

    /**
     * arrow: (Requires Gecko 2.0) Specify this for a panel that provides an arrow pointing at its anchor.
     * 
     */
    @XmlEnumValue("arrow")
    ARROW("arrow"),

    /**
     * drag: Specify this for a panel that's being used as a drag image for a drag and drop operation.
     * 
     */
    @XmlEnumValue("drag")
    DRAG("drag");
    private final String value;

    PaneltypeAttributeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PaneltypeAttributeType fromValue(String v) {
        for (PaneltypeAttributeType c: PaneltypeAttributeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
