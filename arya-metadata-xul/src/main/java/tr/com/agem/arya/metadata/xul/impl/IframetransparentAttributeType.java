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
 * <p>Java class for iframetransparentAttributeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="iframetransparentAttributeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="transparent"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "iframetransparentAttributeType")
@XmlEnum
public enum IframetransparentAttributeType {


    /**
     * transparent: This results in the iframe's background being transparent. This can be used to workaround things like bug 540911
     * 
     */
    @XmlEnumValue("transparent")
    TRANSPARENT("transparent");
    private final String value;

    IframetransparentAttributeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IframetransparentAttributeType fromValue(String v) {
        for (IframetransparentAttributeType c: IframetransparentAttributeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
