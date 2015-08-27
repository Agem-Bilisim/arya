package tr.com.agem.arya.interpreter.component;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;

import tr.com.agem.arya.metadata.arya.impl.ComboboxType;
import tr.com.agem.arya.metadata.arya.impl.ComboitemType;
import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaCombobox extends AryaComponent implements IAryaComponent {

	@Override
	public IAryaComponent create(Object object, Object parent, Object masterWindow) {
		setComponent(new Combobox());
		Combobox component = (Combobox) getComponent();
		component.setId(((ComboboxType) object).getId());
		component.setParent((Component) parent);
		if (((ComboboxType) object).getAttributeOrCustomAttributesOrTemplate() != null) {
			List<Object> componentsList = ((ComboboxType) object).getAttributeOrCustomAttributesOrTemplate();
			for (Object oo : componentsList) {
				if (oo instanceof JAXBElement<?>) {
					JAXBElement<?> j = (JAXBElement<?>) oo;
					System.out.println(j.getDeclaredType());
					String compStr = ((JAXBElement<?>) j).getName().getLocalPart();
					if (compStr.equalsIgnoreCase("comboitem")) {
						Comboitem ci = new Comboitem();
						ci.setLabel(((ComboitemType) j.getValue()).getLabel());
						ci.setValue(((ComboitemType) j.getValue()).getValue());
						ci.setParent(component);
					}
				}
			}
		}
		return this;
	}
}