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
 * <p>Java class for eventnodeAttributeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="eventnodeAttributeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="parent"/>
 *     &lt;enumeration value="window"/>
 *     &lt;enumeration value="document"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "eventnodeAttributeType")
@XmlEnum
public enum EventnodeAttributeType {


    /**
     * 
     *             parent: Keyboard navigation is captured at the parent of the tabbox
     *             .
     *           
     * 
     */
    @XmlEnumValue("parent")
    PARENT("parent"),

    /**
     * window: Keyboard navigation is captured at the window level. Tab navigation will occur as long as any element in the window is focused.
     * 
     */
    @XmlEnumValue("window")
    WINDOW("window"),

    /**
     * document: Keyboard navigation is captured at the document level. Tab navigation will occur as long as any element in the document is focused.
     * 
     */
    @XmlEnumValue("document")
    DOCUMENT("document");
    private final String value;

    EventnodeAttributeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EventnodeAttributeType fromValue(String v) {
        for (EventnodeAttributeType c: EventnodeAttributeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
