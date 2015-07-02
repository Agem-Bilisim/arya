//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.02 at 05:11:41 PM EEST 
//


package tr.com.agem.metadata.xul.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for timepickerElementType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="timepickerElementType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attGroup ref="{http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul}timepickerElementAttributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "timepickerElementType")
public class TimepickerElementType {

    @XmlAttribute(name = "disabled")
    protected Boolean disabled;
    @XmlAttribute(name = "hideseconds")
    protected Boolean hideseconds;
    @XmlAttribute(name = "readonly")
    protected Boolean readonly;
    @XmlAttribute(name = "increment")
    protected BigInteger increment;
    @XmlAttribute(name = "tabindex")
    protected BigInteger tabindex;
    @XmlAttribute(name = "value")
    protected String value;
    @XmlAttribute(name = "align")
    protected AlignAttributeType align;
    @XmlAttribute(name = "allowevents")
    protected Boolean allowevents;
    @XmlAttribute(name = "allownegativeassertions")
    protected Boolean allownegativeassertions;
    @XmlAttribute(name = "coalesceduplicatearcs")
    protected Boolean coalesceduplicatearcs;
    @XmlAttribute(name = "collapsed")
    protected Boolean collapsed;
    @XmlAttribute(name = "containment")
    protected List<String> containment;
    @XmlAttribute(name = "context")
    protected String context;
    @XmlAttribute(name = "contextmenu")
    protected String contextmenu;
    @XmlAttribute(name = "datasources")
    protected List<String> datasources;
    @XmlAttribute(name = "equalsize")
    protected EqualsizeAttributeType equalsize;
    @XmlAttribute(name = "flags")
    protected List<FlagsAttributeType> flags;
    @XmlAttribute(name = "flex")
    protected BigInteger flex;
    @XmlAttribute(name = "height")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger height;
    @XmlAttribute(name = "hidden")
    protected Boolean hidden;
    @XmlAttribute(name = "insertafter")
    @XmlIDREF
    @XmlSchemaType(name = "IDREFS")
    protected List<Object> insertafter;
    @XmlAttribute(name = "insertbefore")
    @XmlIDREF
    @XmlSchemaType(name = "IDREFS")
    protected List<Object> insertbefore;
    @XmlAttribute(name = "maxheight")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger maxheight;
    @XmlAttribute(name = "maxwidth")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger maxwidth;
    @XmlAttribute(name = "menuNotOverriden")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object menuNotOverriden;
    @XmlAttribute(name = "minheight")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger minheight;
    @XmlAttribute(name = "minwidth")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger minwidth;
    @XmlAttribute(name = "mousethrough")
    protected MousethroughAttributeType mousethrough;
    @XmlAttribute(name = "observes")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object observes;
    @XmlAttribute(name = "ordinal")
    protected BigInteger ordinal;
    @XmlAttribute(name = "pack")
    protected PackAttributeType pack;
    @XmlAttribute(name = "persist")
    protected List<String> persist;
    @XmlAttribute(name = "popup")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object popup;
    @XmlAttribute(name = "preference-editable")
    protected Boolean preferenceEditable;
    @XmlAttribute(name = "querytype")
    protected String querytype;
    @XmlAttribute(name = "removeelement")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object removeelement;
    @XmlAttribute(name = "sortDirection")
    protected SortDirectionAttributeType sortDirection;
    @XmlAttribute(name = "sortResource")
    @XmlSchemaType(name = "anyURI")
    protected String sortResource;
    @XmlAttribute(name = "sortResource2")
    @XmlSchemaType(name = "anyURI")
    protected String sortResource2;
    @XmlAttribute(name = "statustext")
    protected String statustext;
    @XmlAttribute(name = "style")
    protected String style;
    @XmlAttribute(name = "template")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object template;
    @XmlAttribute(name = "tooltip")
    protected String tooltip;
    @XmlAttribute(name = "tooltiptext")
    protected String tooltiptext;
    @XmlAttribute(name = "wait-cursor")
    protected Boolean waitCursor;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "width")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger width;
    @XmlAttribute(name = "uri")
    protected String uri;
    @XmlAttribute(name = "orient")
    protected OrientAttributeType orient;
    @XmlAttribute(name = "top")
    protected BigInteger top;
    @XmlAttribute(name = "left")
    protected BigInteger left;
    @XmlAttribute(name = "ref")
    @XmlSchemaType(name = "anyURI")
    protected String ref;
    @XmlAttribute(name = "empty")
    protected Boolean empty;
    @XmlAttribute(name = "dir")
    protected DirAttributeType dir;
    @XmlAttribute(name = "right")
    protected BigInteger right;
    @XmlAttribute(name = "class")
    protected List<String> clazz;
    @XmlAttribute(name = "bottom")
    protected BigInteger bottom;
    @XmlAttribute(name = "position")
    protected BigInteger position;
    @XmlAttribute(name = "container")
    protected Boolean container;

    /**
     * Gets the value of the disabled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDisabled() {
        return disabled;
    }

    /**
     * Sets the value of the disabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDisabled(Boolean value) {
        this.disabled = value;
    }

    /**
     * Gets the value of the hideseconds property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHideseconds() {
        return hideseconds;
    }

    /**
     * Sets the value of the hideseconds property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHideseconds(Boolean value) {
        this.hideseconds = value;
    }

    /**
     * Gets the value of the readonly property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReadonly() {
        return readonly;
    }

    /**
     * Sets the value of the readonly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReadonly(Boolean value) {
        this.readonly = value;
    }

    /**
     * Gets the value of the increment property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIncrement() {
        return increment;
    }

    /**
     * Sets the value of the increment property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIncrement(BigInteger value) {
        this.increment = value;
    }

    /**
     * Gets the value of the tabindex property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTabindex() {
        return tabindex;
    }

    /**
     * Sets the value of the tabindex property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTabindex(BigInteger value) {
        this.tabindex = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the align property.
     * 
     * @return
     *     possible object is
     *     {@link AlignAttributeType }
     *     
     */
    public AlignAttributeType getAlign() {
        return align;
    }

    /**
     * Sets the value of the align property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlignAttributeType }
     *     
     */
    public void setAlign(AlignAttributeType value) {
        this.align = value;
    }

    /**
     * Gets the value of the allowevents property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowevents() {
        return allowevents;
    }

    /**
     * Sets the value of the allowevents property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowevents(Boolean value) {
        this.allowevents = value;
    }

    /**
     * Gets the value of the allownegativeassertions property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllownegativeassertions() {
        return allownegativeassertions;
    }

    /**
     * Sets the value of the allownegativeassertions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllownegativeassertions(Boolean value) {
        this.allownegativeassertions = value;
    }

    /**
     * Gets the value of the coalesceduplicatearcs property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCoalesceduplicatearcs() {
        return coalesceduplicatearcs;
    }

    /**
     * Sets the value of the coalesceduplicatearcs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCoalesceduplicatearcs(Boolean value) {
        this.coalesceduplicatearcs = value;
    }

    /**
     * Gets the value of the collapsed property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCollapsed() {
        return collapsed;
    }

    /**
     * Sets the value of the collapsed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCollapsed(Boolean value) {
        this.collapsed = value;
    }

    /**
     * Gets the value of the containment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the containment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContainment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getContainment() {
        if (containment == null) {
            containment = new ArrayList<String>();
        }
        return this.containment;
    }

    /**
     * Gets the value of the context property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContext() {
        return context;
    }

    /**
     * Sets the value of the context property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContext(String value) {
        this.context = value;
    }

    /**
     * Gets the value of the contextmenu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContextmenu() {
        return contextmenu;
    }

    /**
     * Sets the value of the contextmenu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContextmenu(String value) {
        this.contextmenu = value;
    }

    /**
     * Gets the value of the datasources property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the datasources property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDatasources().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDatasources() {
        if (datasources == null) {
            datasources = new ArrayList<String>();
        }
        return this.datasources;
    }

    /**
     * Gets the value of the equalsize property.
     * 
     * @return
     *     possible object is
     *     {@link EqualsizeAttributeType }
     *     
     */
    public EqualsizeAttributeType getEqualsize() {
        return equalsize;
    }

    /**
     * Sets the value of the equalsize property.
     * 
     * @param value
     *     allowed object is
     *     {@link EqualsizeAttributeType }
     *     
     */
    public void setEqualsize(EqualsizeAttributeType value) {
        this.equalsize = value;
    }

    /**
     * Gets the value of the flags property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flags property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlags().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlagsAttributeType }
     * 
     * 
     */
    public List<FlagsAttributeType> getFlags() {
        if (flags == null) {
            flags = new ArrayList<FlagsAttributeType>();
        }
        return this.flags;
    }

    /**
     * Gets the value of the flex property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFlex() {
        if (flex == null) {
            return new BigInteger("0");
        } else {
            return flex;
        }
    }

    /**
     * Sets the value of the flex property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFlex(BigInteger value) {
        this.flex = value;
    }

    /**
     * Gets the value of the height property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getHeight() {
        return height;
    }

    /**
     * Sets the value of the height property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setHeight(BigInteger value) {
        this.height = value;
    }

    /**
     * Gets the value of the hidden property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHidden() {
        return hidden;
    }

    /**
     * Sets the value of the hidden property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHidden(Boolean value) {
        this.hidden = value;
    }

    /**
     * Gets the value of the insertafter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the insertafter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInsertafter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getInsertafter() {
        if (insertafter == null) {
            insertafter = new ArrayList<Object>();
        }
        return this.insertafter;
    }

    /**
     * Gets the value of the insertbefore property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the insertbefore property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInsertbefore().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getInsertbefore() {
        if (insertbefore == null) {
            insertbefore = new ArrayList<Object>();
        }
        return this.insertbefore;
    }

    /**
     * Gets the value of the maxheight property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxheight() {
        return maxheight;
    }

    /**
     * Sets the value of the maxheight property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxheight(BigInteger value) {
        this.maxheight = value;
    }

    /**
     * Gets the value of the maxwidth property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxwidth() {
        return maxwidth;
    }

    /**
     * Sets the value of the maxwidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxwidth(BigInteger value) {
        this.maxwidth = value;
    }

    /**
     * Gets the value of the menuNotOverriden property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getMenuNotOverriden() {
        return menuNotOverriden;
    }

    /**
     * Sets the value of the menuNotOverriden property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setMenuNotOverriden(Object value) {
        this.menuNotOverriden = value;
    }

    /**
     * Gets the value of the minheight property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinheight() {
        return minheight;
    }

    /**
     * Sets the value of the minheight property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinheight(BigInteger value) {
        this.minheight = value;
    }

    /**
     * Gets the value of the minwidth property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinwidth() {
        return minwidth;
    }

    /**
     * Sets the value of the minwidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinwidth(BigInteger value) {
        this.minwidth = value;
    }

    /**
     * Gets the value of the mousethrough property.
     * 
     * @return
     *     possible object is
     *     {@link MousethroughAttributeType }
     *     
     */
    public MousethroughAttributeType getMousethrough() {
        return mousethrough;
    }

    /**
     * Sets the value of the mousethrough property.
     * 
     * @param value
     *     allowed object is
     *     {@link MousethroughAttributeType }
     *     
     */
    public void setMousethrough(MousethroughAttributeType value) {
        this.mousethrough = value;
    }

    /**
     * Gets the value of the observes property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getObserves() {
        return observes;
    }

    /**
     * Sets the value of the observes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setObserves(Object value) {
        this.observes = value;
    }

    /**
     * Gets the value of the ordinal property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOrdinal() {
        return ordinal;
    }

    /**
     * Sets the value of the ordinal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOrdinal(BigInteger value) {
        this.ordinal = value;
    }

    /**
     * Gets the value of the pack property.
     * 
     * @return
     *     possible object is
     *     {@link PackAttributeType }
     *     
     */
    public PackAttributeType getPack() {
        return pack;
    }

    /**
     * Sets the value of the pack property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackAttributeType }
     *     
     */
    public void setPack(PackAttributeType value) {
        this.pack = value;
    }

    /**
     * Gets the value of the persist property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the persist property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPersist().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPersist() {
        if (persist == null) {
            persist = new ArrayList<String>();
        }
        return this.persist;
    }

    /**
     * Gets the value of the popup property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getPopup() {
        return popup;
    }

    /**
     * Sets the value of the popup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setPopup(Object value) {
        this.popup = value;
    }

    /**
     * Gets the value of the preferenceEditable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPreferenceEditable() {
        return preferenceEditable;
    }

    /**
     * Sets the value of the preferenceEditable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPreferenceEditable(Boolean value) {
        this.preferenceEditable = value;
    }

    /**
     * Gets the value of the querytype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuerytype() {
        return querytype;
    }

    /**
     * Sets the value of the querytype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuerytype(String value) {
        this.querytype = value;
    }

    /**
     * Gets the value of the removeelement property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getRemoveelement() {
        return removeelement;
    }

    /**
     * Sets the value of the removeelement property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setRemoveelement(Object value) {
        this.removeelement = value;
    }

    /**
     * Gets the value of the sortDirection property.
     * 
     * @return
     *     possible object is
     *     {@link SortDirectionAttributeType }
     *     
     */
    public SortDirectionAttributeType getSortDirection() {
        return sortDirection;
    }

    /**
     * Sets the value of the sortDirection property.
     * 
     * @param value
     *     allowed object is
     *     {@link SortDirectionAttributeType }
     *     
     */
    public void setSortDirection(SortDirectionAttributeType value) {
        this.sortDirection = value;
    }

    /**
     * Gets the value of the sortResource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortResource() {
        return sortResource;
    }

    /**
     * Sets the value of the sortResource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortResource(String value) {
        this.sortResource = value;
    }

    /**
     * Gets the value of the sortResource2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortResource2() {
        return sortResource2;
    }

    /**
     * Sets the value of the sortResource2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortResource2(String value) {
        this.sortResource2 = value;
    }

    /**
     * Gets the value of the statustext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatustext() {
        return statustext;
    }

    /**
     * Sets the value of the statustext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatustext(String value) {
        this.statustext = value;
    }

    /**
     * Gets the value of the style property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStyle() {
        return style;
    }

    /**
     * Sets the value of the style property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStyle(String value) {
        this.style = value;
    }

    /**
     * Gets the value of the template property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getTemplate() {
        return template;
    }

    /**
     * Sets the value of the template property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setTemplate(Object value) {
        this.template = value;
    }

    /**
     * Gets the value of the tooltip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTooltip() {
        return tooltip;
    }

    /**
     * Sets the value of the tooltip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTooltip(String value) {
        this.tooltip = value;
    }

    /**
     * Gets the value of the tooltiptext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTooltiptext() {
        return tooltiptext;
    }

    /**
     * Sets the value of the tooltiptext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTooltiptext(String value) {
        this.tooltiptext = value;
    }

    /**
     * Gets the value of the waitCursor property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWaitCursor() {
        return waitCursor;
    }

    /**
     * Sets the value of the waitCursor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWaitCursor(Boolean value) {
        this.waitCursor = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the width property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWidth() {
        return width;
    }

    /**
     * Sets the value of the width property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWidth(BigInteger value) {
        this.width = value;
    }

    /**
     * Gets the value of the uri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets the value of the uri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUri(String value) {
        this.uri = value;
    }

    /**
     * Gets the value of the orient property.
     * 
     * @return
     *     possible object is
     *     {@link OrientAttributeType }
     *     
     */
    public OrientAttributeType getOrient() {
        return orient;
    }

    /**
     * Sets the value of the orient property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrientAttributeType }
     *     
     */
    public void setOrient(OrientAttributeType value) {
        this.orient = value;
    }

    /**
     * Gets the value of the top property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTop() {
        return top;
    }

    /**
     * Sets the value of the top property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTop(BigInteger value) {
        this.top = value;
    }

    /**
     * Gets the value of the left property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLeft() {
        return left;
    }

    /**
     * Sets the value of the left property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLeft(BigInteger value) {
        this.left = value;
    }

    /**
     * Gets the value of the ref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRef() {
        return ref;
    }

    /**
     * Sets the value of the ref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRef(String value) {
        this.ref = value;
    }

    /**
     * Gets the value of the empty property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEmpty() {
        return empty;
    }

    /**
     * Sets the value of the empty property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEmpty(Boolean value) {
        this.empty = value;
    }

    /**
     * Gets the value of the dir property.
     * 
     * @return
     *     possible object is
     *     {@link DirAttributeType }
     *     
     */
    public DirAttributeType getDir() {
        return dir;
    }

    /**
     * Sets the value of the dir property.
     * 
     * @param value
     *     allowed object is
     *     {@link DirAttributeType }
     *     
     */
    public void setDir(DirAttributeType value) {
        this.dir = value;
    }

    /**
     * Gets the value of the right property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRight() {
        return right;
    }

    /**
     * Sets the value of the right property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRight(BigInteger value) {
        this.right = value;
    }

    /**
     * Gets the value of the clazz property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clazz property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClazz().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getClazz() {
        if (clazz == null) {
            clazz = new ArrayList<String>();
        }
        return this.clazz;
    }

    /**
     * Gets the value of the bottom property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBottom() {
        return bottom;
    }

    /**
     * Sets the value of the bottom property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBottom(BigInteger value) {
        this.bottom = value;
    }

    /**
     * Gets the value of the position property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPosition(BigInteger value) {
        this.position = value;
    }

    /**
     * Gets the value of the container property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isContainer() {
        return container;
    }

    /**
     * Sets the value of the container property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setContainer(Boolean value) {
        this.container = value;
    }

}
