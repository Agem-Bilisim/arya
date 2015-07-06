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
 * <p>Java class for newlinesAttributeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="newlinesAttributeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="pasteintact"/>
 *     &lt;enumeration value="pastetofirst"/>
 *     &lt;enumeration value="replacewithcommas"/>
 *     &lt;enumeration value="replacewithspaces"/>
 *     &lt;enumeration value="strip"/>
 *     &lt;enumeration value="stripsurroundingwhitespace"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "newlinesAttributeType")
@XmlEnum
public enum NewlinesAttributeType {


    /**
     * pasteintact: Paste newlines unchanged.
     * 
     */
    @XmlEnumValue("pasteintact")
    PASTEINTACT("pasteintact"),

    /**
     * pastetofirst: Paste text up to the first newline, dropping the rest of the text.
     * 
     */
    @XmlEnumValue("pastetofirst")
    PASTETOFIRST("pastetofirst"),

    /**
     * replacewithcommas: Pastes the text with the newlines replaced with commas.
     * 
     */
    @XmlEnumValue("replacewithcommas")
    REPLACEWITHCOMMAS("replacewithcommas"),

    /**
     * replacewithspaces: Pastes the text with newlines replaced with spaces.
     * 
     */
    @XmlEnumValue("replacewithspaces")
    REPLACEWITHSPACES("replacewithspaces"),

    /**
     * strip: Pastes the text with the newlines removed.
     * 
     */
    @XmlEnumValue("strip")
    STRIP("strip"),

    /**
     * stripsurroundingwhitespace: Pastes the text with newlines and adjacent whitespace removed.
     * 
     */
    @XmlEnumValue("stripsurroundingwhitespace")
    STRIPSURROUNDINGWHITESPACE("stripsurroundingwhitespace");
    private final String value;

    NewlinesAttributeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NewlinesAttributeType fromValue(String v) {
        for (NewlinesAttributeType c: NewlinesAttributeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
