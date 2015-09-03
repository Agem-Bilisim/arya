//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.03 at 11:57:53 AM EEST 
//


package tr.com.agem.arya.metadata.zul.impl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * <p>Java class for templateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="templateType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;group ref="{aryaportal.org}anyGroup"/>
 *         &lt;element ref="{aryaportal.org}area"/>
 *         &lt;element ref="{aryaportal.org}caption"/>
 *         &lt;element ref="{aryaportal.org}center"/>
 *         &lt;element ref="{aryaportal.org}columns"/>
 *         &lt;element ref="{aryaportal.org}column"/>
 *         &lt;element ref="{aryaportal.org}comboitem"/>
 *         &lt;element ref="{aryaportal.org}detail"/>
 *         &lt;element ref="{aryaportal.org}east"/>
 *         &lt;element ref="{aryaportal.org}foot"/>
 *         &lt;element ref="{aryaportal.org}footer"/>
 *         &lt;element ref="{aryaportal.org}group"/>
 *         &lt;element ref="{aryaportal.org}groupfoot"/>
 *         &lt;element ref="{aryaportal.org}listcell"/>
 *         &lt;element ref="{aryaportal.org}listfoot"/>
 *         &lt;element ref="{aryaportal.org}listfooter"/>
 *         &lt;element ref="{aryaportal.org}listgroup"/>
 *         &lt;element ref="{aryaportal.org}listgroupfoot"/>
 *         &lt;element ref="{aryaportal.org}listhead"/>
 *         &lt;element ref="{aryaportal.org}listheader"/>
 *         &lt;element ref="{aryaportal.org}listitem"/>
 *         &lt;element ref="{aryaportal.org}mcomboitem"/>
 *         &lt;element ref="{aryaportal.org}menu"/>
 *         &lt;element ref="{aryaportal.org}menuitem"/>
 *         &lt;element ref="{aryaportal.org}menuseparator"/>
 *         &lt;element ref="{aryaportal.org}nav"/>
 *         &lt;element ref="{aryaportal.org}navitem"/>
 *         &lt;element ref="{aryaportal.org}navseparator"/>
 *         &lt;element ref="{aryaportal.org}north"/>
 *         &lt;element ref="{aryaportal.org}rows"/>
 *         &lt;element ref="{aryaportal.org}row"/>
 *         &lt;element ref="{aryaportal.org}south"/>
 *         &lt;element ref="{aryaportal.org}tab"/>
 *         &lt;element ref="{aryaportal.org}tabpanel"/>
 *         &lt;element ref="{aryaportal.org}tabpanels"/>
 *         &lt;element ref="{aryaportal.org}tabs"/>
 *         &lt;element ref="{aryaportal.org}treecell"/>
 *         &lt;element ref="{aryaportal.org}treechildren"/>
 *         &lt;element ref="{aryaportal.org}treecol"/>
 *         &lt;element ref="{aryaportal.org}treecols"/>
 *         &lt;element ref="{aryaportal.org}treefoot"/>
 *         &lt;element ref="{aryaportal.org}treefooter"/>
 *         &lt;element ref="{aryaportal.org}treeitem"/>
 *         &lt;element ref="{aryaportal.org}treerow"/>
 *         &lt;element ref="{aryaportal.org}west"/>
 *       &lt;/choice>
 *       &lt;attGroup ref="{aryaportal.org}rootAttrGroup"/>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="src" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="if" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="var" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="status" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "templateType", propOrder = {
    "attributeOrCustomAttributesOrTemplate"
})
public class TemplateType {

    @XmlElementRefs({
        @XmlElementRef(name = "paging", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "listbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "custom-attributes", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "template", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "tree", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "columns", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "listgroup", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "panel", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "calendar", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "menupopup", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "vlayout", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "foot", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "hlayout", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "west", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "area", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "comboitem", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "listhead", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "tab", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "menuitem", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "menuseparator", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "tabpanels", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "radio", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "radiogroup", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "button", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "charts", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "groupfoot", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "listgroupfoot", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "label", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "colorbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "treeitem", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "group", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "mcomboitem", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "nav", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "anchorlayout", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "treefoot", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "longbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "listcell", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "intbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "calendars", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "attribute", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "styleType", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "combobutton", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "absolutelayout", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "chart", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "tabbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "combobox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "treechildren", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "script", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "treecol", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "imagemap", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "audio", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "separator", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "cell", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "textbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "tabpanel", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "center", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "north", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "hbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "navitem", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "rows", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "selectbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "image", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "a", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "listfoot", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "progressmeter", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "treecell", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "row", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "timebox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "listitem", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "decimalbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "checkbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "treecols", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "columnlayout", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "datebox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "toolbar", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "caption", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "menubar", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "doublebox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "detail", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "south", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "footer", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "tablelayout", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "vbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "groupbox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "borderlayout", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "popup", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "menu", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "grid", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "box", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "multiplecombobox", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "column", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "navseparator", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "window", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "treerow", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "east", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "div", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "listheader", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "treefooter", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "navbar", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "toolbarbutton", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "tabs", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "listfooter", namespace = "aryaportal.org", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "arya", namespace = "aryaportal.org", type = JAXBElement.class, required = false)
    })
    @XmlAnyElement(lax = true)
    protected List<Object> attributeOrCustomAttributesOrTemplate;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "src")
    protected String src;
    @XmlAttribute(name = "if")
    protected String _if;
    @XmlAttribute(name = "var")
    protected String var;
    @XmlAttribute(name = "status")
    protected String status;
    @XmlAttribute(name = "onBookmarkChange")
    protected String onBookmarkChange;
    @XmlAttribute(name = "onClientInfo")
    protected String onClientInfo;

    /**
     * Gets the value of the attributeOrCustomAttributesOrTemplate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attributeOrCustomAttributesOrTemplate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributeOrCustomAttributesOrTemplate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link PagingType }{@code >}
     * {@link JAXBElement }{@code <}{@link ListboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link CustomAttributesType }{@code >}
     * {@link JAXBElement }{@code <}{@link TemplateType }{@code >}
     * {@link JAXBElement }{@code <}{@link TreeType }{@code >}
     * {@link JAXBElement }{@code <}{@link ColumnsType }{@code >}
     * {@link JAXBElement }{@code <}{@link ListgroupType }{@code >}
     * {@link JAXBElement }{@code <}{@link PanelType }{@code >}
     * {@link JAXBElement }{@code <}{@link CalendarType }{@code >}
     * {@link JAXBElement }{@code <}{@link MenupopupType }{@code >}
     * {@link JAXBElement }{@code <}{@link VlayoutType }{@code >}
     * {@link JAXBElement }{@code <}{@link FootType }{@code >}
     * {@link JAXBElement }{@code <}{@link HlayoutType }{@code >}
     * {@link JAXBElement }{@code <}{@link WestType }{@code >}
     * {@link JAXBElement }{@code <}{@link AreaType }{@code >}
     * {@link JAXBElement }{@code <}{@link ComboitemType }{@code >}
     * {@link JAXBElement }{@code <}{@link ListheadType }{@code >}
     * {@link JAXBElement }{@code <}{@link TabType }{@code >}
     * {@link JAXBElement }{@code <}{@link MenuitemType }{@code >}
     * {@link JAXBElement }{@code <}{@link MenuseparatorType }{@code >}
     * {@link JAXBElement }{@code <}{@link TabpanelsType }{@code >}
     * {@link JAXBElement }{@code <}{@link RadioType }{@code >}
     * {@link JAXBElement }{@code <}{@link RadiogroupType }{@code >}
     * {@link JAXBElement }{@code <}{@link ButtonType }{@code >}
     * {@link JAXBElement }{@code <}{@link ChartsType }{@code >}
     * {@link JAXBElement }{@code <}{@link GroupfootType }{@code >}
     * {@link JAXBElement }{@code <}{@link ListgroupfootType }{@code >}
     * {@link JAXBElement }{@code <}{@link LabelType }{@code >}
     * {@link JAXBElement }{@code <}{@link ColorboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link TreeitemType }{@code >}
     * {@link JAXBElement }{@code <}{@link GroupType }{@code >}
     * {@link JAXBElement }{@code <}{@link McomboitemType }{@code >}
     * {@link JAXBElement }{@code <}{@link NavType }{@code >}
     * {@link JAXBElement }{@code <}{@link AnchorlayoutType }{@code >}
     * {@link JAXBElement }{@code <}{@link TreefootType }{@code >}
     * {@link JAXBElement }{@code <}{@link LongboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link ListcellType }{@code >}
     * {@link JAXBElement }{@code <}{@link IntboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link CalendarsType }{@code >}
     * {@link JAXBElement }{@code <}{@link AttributeType }{@code >}
     * {@link JAXBElement }{@code <}{@link StyleType }{@code >}
     * {@link JAXBElement }{@code <}{@link CombobuttonType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbsolutelayoutType }{@code >}
     * {@link JAXBElement }{@code <}{@link ChartType }{@code >}
     * {@link JAXBElement }{@code <}{@link TabboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link ComboboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link TreechildrenType }{@code >}
     * {@link JAXBElement }{@code <}{@link ScriptType }{@code >}
     * {@link JAXBElement }{@code <}{@link TreecolType }{@code >}
     * {@link JAXBElement }{@code <}{@link ImagemapType }{@code >}
     * {@link JAXBElement }{@code <}{@link AudioType }{@code >}
     * {@link JAXBElement }{@code <}{@link SeparatorType }{@code >}
     * {@link JAXBElement }{@code <}{@link CellType }{@code >}
     * {@link JAXBElement }{@code <}{@link TextboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link TabpanelType }{@code >}
     * {@link JAXBElement }{@code <}{@link CenterType }{@code >}
     * {@link JAXBElement }{@code <}{@link NorthType }{@code >}
     * {@link JAXBElement }{@code <}{@link HboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link NavitemType }{@code >}
     * {@link JAXBElement }{@code <}{@link RowsType }{@code >}
     * {@link JAXBElement }{@code <}{@link SelectboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link ImageType }{@code >}
     * {@link JAXBElement }{@code <}{@link AType }{@code >}
     * {@link JAXBElement }{@code <}{@link ListfootType }{@code >}
     * {@link JAXBElement }{@code <}{@link ProgressmeterType }{@code >}
     * {@link JAXBElement }{@code <}{@link TreecellType }{@code >}
     * {@link JAXBElement }{@code <}{@link RowType }{@code >}
     * {@link JAXBElement }{@code <}{@link TimeboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link ListitemType }{@code >}
     * {@link JAXBElement }{@code <}{@link DecimalboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link CheckboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link TreecolsType }{@code >}
     * {@link JAXBElement }{@code <}{@link ColumnlayoutType }{@code >}
     * {@link JAXBElement }{@code <}{@link DateboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link ToolbarType }{@code >}
     * {@link JAXBElement }{@code <}{@link CaptionType }{@code >}
     * {@link JAXBElement }{@code <}{@link MenubarType }{@code >}
     * {@link JAXBElement }{@code <}{@link DoubleboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link DetailType }{@code >}
     * {@link JAXBElement }{@code <}{@link SouthType }{@code >}
     * {@link JAXBElement }{@code <}{@link FooterType }{@code >}
     * {@link JAXBElement }{@code <}{@link TablelayoutType }{@code >}
     * {@link JAXBElement }{@code <}{@link VboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link GroupboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link BorderlayoutType }{@code >}
     * {@link JAXBElement }{@code <}{@link PopupType }{@code >}
     * {@link JAXBElement }{@code <}{@link MenuType }{@code >}
     * {@link Object }
     * {@link JAXBElement }{@code <}{@link GridType }{@code >}
     * {@link JAXBElement }{@code <}{@link BoxType }{@code >}
     * {@link JAXBElement }{@code <}{@link MultiplecomboboxType }{@code >}
     * {@link JAXBElement }{@code <}{@link ColumnType }{@code >}
     * {@link JAXBElement }{@code <}{@link NavseparatorType }{@code >}
     * {@link JAXBElement }{@code <}{@link WindowType }{@code >}
     * {@link JAXBElement }{@code <}{@link TreerowType }{@code >}
     * {@link JAXBElement }{@code <}{@link EastType }{@code >}
     * {@link JAXBElement }{@code <}{@link DivType }{@code >}
     * {@link JAXBElement }{@code <}{@link ListheaderType }{@code >}
     * {@link Element }
     * {@link JAXBElement }{@code <}{@link NavbarType }{@code >}
     * {@link JAXBElement }{@code <}{@link TreefooterType }{@code >}
     * {@link JAXBElement }{@code <}{@link TabsType }{@code >}
     * {@link JAXBElement }{@code <}{@link ToolbarbuttonType }{@code >}
     * {@link JAXBElement }{@code <}{@link ListfooterType }{@code >}
     * {@link JAXBElement }{@code <}{@link AryaType }{@code >}
     * 
     * 
     */
    public List<Object> getAttributeOrCustomAttributesOrTemplate() {
        if (attributeOrCustomAttributesOrTemplate == null) {
            attributeOrCustomAttributesOrTemplate = new ArrayList<Object>();
        }
        return this.attributeOrCustomAttributesOrTemplate;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the src property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrc() {
        return src;
    }

    /**
     * Sets the value of the src property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrc(String value) {
        this.src = value;
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
     * Gets the value of the var property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVar() {
        return var;
    }

    /**
     * Sets the value of the var property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVar(String value) {
        this.var = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the onBookmarkChange property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnBookmarkChange() {
        return onBookmarkChange;
    }

    /**
     * Sets the value of the onBookmarkChange property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnBookmarkChange(String value) {
        this.onBookmarkChange = value;
    }

    /**
     * Gets the value of the onClientInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnClientInfo() {
        return onClientInfo;
    }

    /**
     * Sets the value of the onClientInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnClientInfo(String value) {
        this.onClientInfo = value;
    }

}
