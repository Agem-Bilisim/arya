//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.18 at 08:16:12 PM EEST 
//


package tr.com.agem.arya.metadata.arya.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

import org.w3c.dom.Element;


/**
 * <p>Java class for groupfootType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="groupfootType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;group ref="{aryaportal.org}anyGroup" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;attGroup ref="{aryaportal.org}xulElementAttrGroup"/>
 *       &lt;attribute name="align" type="{aryaportal.org}gridAlignAttrType" />
 *       &lt;attribute name="nowrap" type="{aryaportal.org}booleanType" />
 *       &lt;attribute name="spans" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="valign" type="{aryaportal.org}valignAttrType" />
 *       &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "groupfootType", propOrder = {
    "content"
})
public class GroupfootType {

    @XmlElementRefs({
        @XmlElementRef(name = "toolbarbutton", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "tabbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "radio", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "columnlayout", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "script", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "vlayout", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "tree", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "grid", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "timebox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "radiogroup", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "navbar", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "imagemap", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "chart", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "custom-attributes", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "colorbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "button", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "label", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "menupopup", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "window", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "absolutelayout", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "textbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "box", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "panel", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "charts", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "doublebox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "attribute", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "audio", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "calendars", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "arya", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "image", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "checkbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "a", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "intbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "styleType", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "template", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "decimalbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "progressmeter", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "vbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "combobutton", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "popup", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "div", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "tablelayout", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "borderlayout", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "separator", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "groupbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "menubar", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "longbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "combobox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "calendar", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "anchorlayout", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "hlayout", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "cell", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "listbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "paging", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "toolbar", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "selectbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "datebox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "hbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false)
    })
    @XmlMixed
    @XmlAnyElement(lax = true)
    protected List<Object> content;
    @XmlAttribute(name = "align")
    protected String align;
    @XmlAttribute(name = "nowrap")
    protected String nowrap;
    @XmlAttribute(name = "spans")
    protected String spans;
    @XmlAttribute(name = "valign")
    protected String valign;
    @XmlAttribute(name = "value")
    protected String value;
    @XmlAttribute(name = "label")
    protected String label;
    @XmlAttribute(name = "context")
    protected String context;
    @XmlAttribute(name = "tooltip")
    protected String tooltip;
    @XmlAttribute(name = "onCtrlKey")
    protected String onCtrlKey;
    @XmlAttribute(name = "ctrlKeys")
    protected String ctrlKeys;
    @XmlAttribute(name = "width")
    protected String width;
    @XmlAttribute(name = "height")
    protected String height;
    @XmlAttribute(name = "sclass")
    protected String sclass;
    @XmlAttribute(name = "zclass")
    protected String zclass;
    @XmlAttribute(name = "style")
    protected String style;
    @XmlAttribute(name = "left")
    protected String left;
    @XmlAttribute(name = "top")
    protected String top;
    @XmlAttribute(name = "draggable")
    protected String draggable;
    @XmlAttribute(name = "droppable")
    protected String droppable;
    @XmlAttribute(name = "focus")
    protected String focus;
    @XmlAttribute(name = "tooltiptext")
    protected String tooltiptext;
    @XmlAttribute(name = "zindex")
    protected String zindex;
    @XmlAttribute(name = "renderdefer")
    protected String renderdefer;
    @XmlAttribute(name = "onCreate")
    protected String onCreate;
    @XmlAttribute(name = "onDrop")
    protected String onDrop;
    @XmlAttribute(name = "action")
    protected String action;
    @XmlAttribute(name = "hflex")
    protected String hflex;
    @XmlAttribute(name = "vflex")
    protected String vflex;
    @XmlAttribute(name = "apply")
    protected String apply;
    @XmlAttribute(name = "auService")
    protected String auService;
    @XmlAttribute(name = "autag")
    protected String autag;
    @XmlAttribute(name = "binder")
    protected String binder;
    @XmlAttribute(name = "children")
    protected String children;
    @XmlAttribute(name = "form")
    protected String form;
    @XmlAttribute(name = "forward")
    protected String forward;
    @XmlAttribute(name = "fulfill")
    protected String fulfill;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "mold")
    protected String mold;
    @XmlAttribute(name = "onFulfill")
    protected String onFulfill;
    @XmlAttribute(name = "stubonly")
    protected String stubonly;
    @XmlAttribute(name = "use")
    protected String use;
    @XmlAttribute(name = "viewModel")
    protected String viewModel;
    @XmlAttribute(name = "visible")
    protected String visible;
    @XmlAttribute(name = "forEach")
    protected String forEach;
    @XmlAttribute(name = "forEachBegin")
    protected String forEachBegin;
    @XmlAttribute(name = "forEachEnd")
    protected String forEachEnd;
    @XmlAttribute(name = "if")
    protected String _if;
    @XmlAttribute(name = "self")
    protected String self;
    @XmlAttribute(name = "unless")
    protected String unless;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link ToolbarbuttonType }{@code >}
     * {@link JAXBElement }{@code <}{@link TabboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link RadioType }{@code >}
     * {@link Element }
     * {@link Object }
     * {@link JAXBElement }{@code <}{@link ColumnlayoutType }{@code >}
     * {@link JAXBElement }{@code <}{@link ScriptType }{@code >}
     * {@link JAXBElement }{@code <}{@link VlayoutType }{@code >}
     * {@link JAXBElement }{@code <}{@link TreeType }{@code >}
     * {@link JAXBElement }{@code <}{@link GridType }{@code >}
     * {@link JAXBElement }{@code <}{@link TimeboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link RadiogroupType }{@code >}
     * {@link JAXBElement }{@code <}{@link NavbarType }{@code >}
     * {@link JAXBElement }{@code <}{@link ImagemapType }{@code >}
     * {@link JAXBElement }{@code <}{@link ChartType }{@code >}
     * {@link JAXBElement }{@code <}{@link CustomAttributesType }{@code >}
     * {@link JAXBElement }{@code <}{@link ColorboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link ButtonType }{@code >}
     * {@link JAXBElement }{@code <}{@link LabelType }{@code >}
     * {@link JAXBElement }{@code <}{@link MenupopupType }{@code >}
     * {@link JAXBElement }{@code <}{@link WindowType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbsolutelayoutType }{@code >}
     * {@link JAXBElement }{@code <}{@link TextboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link BoxType }{@code >}
     * {@link JAXBElement }{@code <}{@link PanelType }{@code >}
     * {@link String }
     * {@link JAXBElement }{@code <}{@link ChartsType }{@code >}
     * {@link JAXBElement }{@code <}{@link DoubleboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link AttributeType }{@code >}
     * {@link JAXBElement }{@code <}{@link AudioType }{@code >}
     * {@link JAXBElement }{@code <}{@link CalendarsType }{@code >}
     * {@link JAXBElement }{@code <}{@link AryaType }{@code >}
     * {@link JAXBElement }{@code <}{@link ImageType }{@code >}
     * {@link JAXBElement }{@code <}{@link CheckboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link AType }{@code >}
     * {@link JAXBElement }{@code <}{@link IntboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     * {@link JAXBElement }{@code <}{@link TemplateType }{@code >}
     * {@link JAXBElement }{@code <}{@link DecimalboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link ProgressmeterType }{@code >}
     * {@link JAXBElement }{@code <}{@link VboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link CombobuttonType }{@code >}
     * {@link JAXBElement }{@code <}{@link PopupType }{@code >}
     * {@link JAXBElement }{@code <}{@link DivType }{@code >}
     * {@link JAXBElement }{@code <}{@link TablelayoutType }{@code >}
     * {@link JAXBElement }{@code <}{@link BorderlayoutType }{@code >}
     * {@link JAXBElement }{@code <}{@link SeparatorType }{@code >}
     * {@link JAXBElement }{@code <}{@link GroupboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link MenubarType }{@code >}
     * {@link JAXBElement }{@code <}{@link LongboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link CalendarType }{@code >}
     * {@link JAXBElement }{@code <}{@link ComboboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link AnchorlayoutType }{@code >}
     * {@link JAXBElement }{@code <}{@link HlayoutType }{@code >}
     * {@link JAXBElement }{@code <}{@link ListboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link CellType }{@code >}
     * {@link JAXBElement }{@code <}{@link PagingType }{@code >}
     * {@link JAXBElement }{@code <}{@link ToolbarType }{@code >}
     * {@link JAXBElement }{@code <}{@link SelectboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link DateboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link HboxType }{@code >}
     * 
     * 
     */
    public List<Object> getContent() {
        if (content == null) {
            content = new ArrayList<Object>();
        }
        return this.content;
    }

    /**
     * Gets the value of the align property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlign() {
        return align;
    }

    /**
     * Sets the value of the align property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlign(String value) {
        this.align = value;
    }

    /**
     * Gets the value of the nowrap property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNowrap() {
        return nowrap;
    }

    /**
     * Sets the value of the nowrap property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNowrap(String value) {
        this.nowrap = value;
    }

    /**
     * Gets the value of the spans property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpans() {
        return spans;
    }

    /**
     * Sets the value of the spans property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpans(String value) {
        this.spans = value;
    }

    /**
     * Gets the value of the valign property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValign() {
        return valign;
    }

    /**
     * Sets the value of the valign property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValign(String value) {
        this.valign = value;
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
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabel(String value) {
        this.label = value;
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
     * Gets the value of the onCtrlKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnCtrlKey() {
        return onCtrlKey;
    }

    /**
     * Sets the value of the onCtrlKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnCtrlKey(String value) {
        this.onCtrlKey = value;
    }

    /**
     * Gets the value of the ctrlKeys property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCtrlKeys() {
        return ctrlKeys;
    }

    /**
     * Sets the value of the ctrlKeys property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCtrlKeys(String value) {
        this.ctrlKeys = value;
    }

    /**
     * Gets the value of the width property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWidth() {
        return width;
    }

    /**
     * Sets the value of the width property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWidth(String value) {
        this.width = value;
    }

    /**
     * Gets the value of the height property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHeight() {
        return height;
    }

    /**
     * Sets the value of the height property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeight(String value) {
        this.height = value;
    }

    /**
     * Gets the value of the sclass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSclass() {
        return sclass;
    }

    /**
     * Sets the value of the sclass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSclass(String value) {
        this.sclass = value;
    }

    /**
     * Gets the value of the zclass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZclass() {
        return zclass;
    }

    /**
     * Sets the value of the zclass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZclass(String value) {
        this.zclass = value;
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
     * Gets the value of the left property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeft() {
        return left;
    }

    /**
     * Sets the value of the left property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeft(String value) {
        this.left = value;
    }

    /**
     * Gets the value of the top property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTop() {
        return top;
    }

    /**
     * Sets the value of the top property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTop(String value) {
        this.top = value;
    }

    /**
     * Gets the value of the draggable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDraggable() {
        return draggable;
    }

    /**
     * Sets the value of the draggable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDraggable(String value) {
        this.draggable = value;
    }

    /**
     * Gets the value of the droppable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDroppable() {
        return droppable;
    }

    /**
     * Sets the value of the droppable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDroppable(String value) {
        this.droppable = value;
    }

    /**
     * Gets the value of the focus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFocus() {
        return focus;
    }

    /**
     * Sets the value of the focus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFocus(String value) {
        this.focus = value;
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
     * Gets the value of the zindex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZindex() {
        return zindex;
    }

    /**
     * Sets the value of the zindex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZindex(String value) {
        this.zindex = value;
    }

    /**
     * Gets the value of the renderdefer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRenderdefer() {
        return renderdefer;
    }

    /**
     * Sets the value of the renderdefer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRenderdefer(String value) {
        this.renderdefer = value;
    }

    /**
     * Gets the value of the onCreate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnCreate() {
        return onCreate;
    }

    /**
     * Sets the value of the onCreate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnCreate(String value) {
        this.onCreate = value;
    }

    /**
     * Gets the value of the onDrop property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnDrop() {
        return onDrop;
    }

    /**
     * Sets the value of the onDrop property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnDrop(String value) {
        this.onDrop = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * Gets the value of the hflex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHflex() {
        return hflex;
    }

    /**
     * Sets the value of the hflex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHflex(String value) {
        this.hflex = value;
    }

    /**
     * Gets the value of the vflex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVflex() {
        return vflex;
    }

    /**
     * Sets the value of the vflex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVflex(String value) {
        this.vflex = value;
    }

    /**
     * Gets the value of the apply property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApply() {
        return apply;
    }

    /**
     * Sets the value of the apply property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApply(String value) {
        this.apply = value;
    }

    /**
     * Gets the value of the auService property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuService() {
        return auService;
    }

    /**
     * Sets the value of the auService property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuService(String value) {
        this.auService = value;
    }

    /**
     * Gets the value of the autag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutag() {
        return autag;
    }

    /**
     * Sets the value of the autag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutag(String value) {
        this.autag = value;
    }

    /**
     * Gets the value of the binder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBinder() {
        return binder;
    }

    /**
     * Sets the value of the binder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBinder(String value) {
        this.binder = value;
    }

    /**
     * Gets the value of the children property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChildren() {
        return children;
    }

    /**
     * Sets the value of the children property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChildren(String value) {
        this.children = value;
    }

    /**
     * Gets the value of the form property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForm() {
        return form;
    }

    /**
     * Sets the value of the form property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForm(String value) {
        this.form = value;
    }

    /**
     * Gets the value of the forward property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForward() {
        return forward;
    }

    /**
     * Sets the value of the forward property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForward(String value) {
        this.forward = value;
    }

    /**
     * Gets the value of the fulfill property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFulfill() {
        return fulfill;
    }

    /**
     * Sets the value of the fulfill property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFulfill(String value) {
        this.fulfill = value;
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
     * Gets the value of the mold property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMold() {
        return mold;
    }

    /**
     * Sets the value of the mold property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMold(String value) {
        this.mold = value;
    }

    /**
     * Gets the value of the onFulfill property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnFulfill() {
        return onFulfill;
    }

    /**
     * Sets the value of the onFulfill property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnFulfill(String value) {
        this.onFulfill = value;
    }

    /**
     * Gets the value of the stubonly property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStubonly() {
        return stubonly;
    }

    /**
     * Sets the value of the stubonly property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStubonly(String value) {
        this.stubonly = value;
    }

    /**
     * Gets the value of the use property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUse() {
        return use;
    }

    /**
     * Sets the value of the use property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUse(String value) {
        this.use = value;
    }

    /**
     * Gets the value of the viewModel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViewModel() {
        return viewModel;
    }

    /**
     * Sets the value of the viewModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViewModel(String value) {
        this.viewModel = value;
    }

    /**
     * Gets the value of the visible property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisible() {
        return visible;
    }

    /**
     * Sets the value of the visible property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisible(String value) {
        this.visible = value;
    }

    /**
     * Gets the value of the forEach property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForEach() {
        return forEach;
    }

    /**
     * Sets the value of the forEach property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForEach(String value) {
        this.forEach = value;
    }

    /**
     * Gets the value of the forEachBegin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForEachBegin() {
        return forEachBegin;
    }

    /**
     * Sets the value of the forEachBegin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForEachBegin(String value) {
        this.forEachBegin = value;
    }

    /**
     * Gets the value of the forEachEnd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForEachEnd() {
        return forEachEnd;
    }

    /**
     * Sets the value of the forEachEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForEachEnd(String value) {
        this.forEachEnd = value;
    }

    /**
     * Gets the value of the if property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIf() {
        return _if;
    }

    /**
     * Sets the value of the if property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIf(String value) {
        this._if = value;
    }

    /**
     * Gets the value of the self property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelf() {
        return self;
    }

    /**
     * Sets the value of the self property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelf(String value) {
        this.self = value;
    }

    /**
     * Gets the value of the unless property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnless() {
        return unless;
    }

    /**
     * Sets the value of the unless property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnless(String value) {
        this.unless = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
