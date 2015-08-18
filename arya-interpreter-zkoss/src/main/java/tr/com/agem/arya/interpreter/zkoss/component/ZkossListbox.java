package tr.com.agem.arya.interpreter.zkoss.component;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

import tr.com.agem.arya.metadata.zul.impl.ListboxType;
import tr.com.agem.arya.metadata.zul.impl.ListitemType;
import tr.com.agem.core.interpreter.IAryaComponent;

public class ZkossListbox extends ZkossComponent implements IAryaComponent {

	@Override
	public IAryaComponent create(Object object, Object parent) {
		setComponent(new Listbox());
		Listbox component = (Listbox) getComponent();
		component.setParent((Component) parent);
		if (((ListboxType) object).getContent() != null) {
			List<Object> componentsList = ((ListboxType) object).getContent();
			for (Object oo : componentsList) {
				if (oo instanceof JAXBElement<?>) {
					JAXBElement<?> j = (JAXBElement<?>) oo;
					System.out.println(j.getDeclaredType());
					String compStr = ((JAXBElement<?>) j).getName().getLocalPart();
					if (compStr.equalsIgnoreCase("listitem")) {
						Listitem li = new Listitem();
						li.setLabel(((ListitemType)j.getValue()).getLabel());
						li.setValue(((ListitemType)j.getValue()).getValue());
						li.setParent(component);
					}
				}
			}
		}
		return this;
	}
}