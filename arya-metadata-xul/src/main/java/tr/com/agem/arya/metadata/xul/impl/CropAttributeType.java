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
 * <p>Java class for cropAttributeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="cropAttributeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="start"/>
 *     &lt;enumeration value="end"/>
 *     &lt;enumeration value="left"/>
 *     &lt;enumeration value="right"/>
 *     &lt;enumeration value="center"/>
 *     &lt;enumeration value="none"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "cropAttributeType")
@XmlEnum
public enum CropAttributeType {


    /**
     * 
     *             start: The text will be cropped on its left side.
     *           
     * 
     */
    @XmlEnumValue("start")
    START("start"),

    /**
     * 
     *             end: The text will be cropped on its right side.
     *           
     * 
     */
    @XmlEnumValue("end")
    END("end"),

    /**
     * 
     *             left: Deprecated The text will be cropped on its left side.
     *           
     * 
     */
    @XmlEnumValue("left")
    LEFT("left"),

    /**
     * 
     *             right: Deprecated The text will be cropped on its right side.
     *           
     * 
     */
    @XmlEnumValue("right")
    RIGHT("right"),

    /**
     * 
     *             center: The text will be cropped in the middle, showing both the start and end of the text normally.
     *           
     * 
     */
    @XmlEnumValue("center")
    CENTER("center"),

    /**
     * 
     *             none: The text will be not be cropped using an ellipsis. However, the text will simply be cut off if it is too large. The side depends on the CSS text alignment.
     *           
     * 
     */
    @XmlEnumValue("none")
    NONE("none");
    private final String value;

    CropAttributeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CropAttributeType fromValue(String v) {
        for (CropAttributeType c: CropAttributeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
