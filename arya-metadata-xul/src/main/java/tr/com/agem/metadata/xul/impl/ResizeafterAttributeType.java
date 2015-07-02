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
 * <p>Java class for resizeafterAttributeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="resizeafterAttributeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="closest"/>
 *     &lt;enumeration value="farthest"/>
 *     &lt;enumeration value="grow"/>
 *     &lt;enumeration value="flex"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "resizeafterAttributeType")
@XmlEnum
public enum ResizeafterAttributeType {


    /**
     * 
     *             closest: The element immediately to the right or below the splitter resizes.
     *           
     * 
     */
    @XmlEnumValue("closest")
    CLOSEST("closest"),

    /**
     * 
     *             farthest: The element that is the farthest away from the splitter to the right or below the splitter resizes.
     *           
     * 
     */
    @XmlEnumValue("farthest")
    FARTHEST("farthest"),

    /**
     * 
     *             grow: The elements to the right or below the splitter do not change size (unless they are flexible) when the splitter is dragged, but instead the entire container changes size.
     *           
     * 
     */
    @XmlEnumValue("grow")
    GROW("grow"),

    /**
     * 
     *             flex: The closest flexible element resizes. New in Firefox 3
     *           
     * 
     */
    @XmlEnumValue("flex")
    FLEX("flex");
    private final String value;

    ResizeafterAttributeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ResizeafterAttributeType fromValue(String v) {
        for (ResizeafterAttributeType c: ResizeafterAttributeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
