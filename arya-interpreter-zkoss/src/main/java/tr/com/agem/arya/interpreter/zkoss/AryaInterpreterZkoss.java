package tr.com.agem.arya.interpreter.zkoss;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.zkoss.zk.ui.Component;

import tr.com.agem.arya.interpreter.zkoss.component.ZkossButton;
import tr.com.agem.arya.interpreter.zkoss.component.ZkossCheckbox;
import tr.com.agem.arya.interpreter.zkoss.component.ZkossCombobox;
import tr.com.agem.arya.interpreter.zkoss.component.ZkossDatebox;
import tr.com.agem.arya.interpreter.zkoss.component.ZkossLabel;
import tr.com.agem.arya.interpreter.zkoss.component.ZkossListbox;
import tr.com.agem.arya.interpreter.zkoss.component.ZkossTextbox;
import tr.com.agem.arya.metadata.zul.impl.ButtonType;
import tr.com.agem.arya.metadata.zul.impl.CheckboxType;
import tr.com.agem.arya.metadata.zul.impl.ComboboxType;
import tr.com.agem.arya.metadata.zul.impl.DateboxType;
import tr.com.agem.arya.metadata.zul.impl.LabelType;
import tr.com.agem.arya.metadata.zul.impl.ListboxType;
import tr.com.agem.arya.metadata.zul.impl.TextboxType;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.interpreter.IAryaInterpreter;

public class AryaInterpreterZkoss implements IAryaInterpreter {

	private List<IAryaComponent> components;

	public List<IAryaComponent> getComponents() {
		return components;
	}

	public void setComponents(List<IAryaComponent> components) {
		this.components = components;
	}

	private Component parent;

	@Override
	public void createComponents(Object parentObject,
			List<Object> componentsList) {
		this.parent = (Component) parentObject;
		components = new ArrayList<IAryaComponent>();
		for (Object oo : componentsList) {
			if (oo instanceof JAXBElement<?>) {
				JAXBElement<?> j = (JAXBElement<?>) oo;
				System.out.println(j.getDeclaredType());
				String compStr = ((JAXBElement<?>) j).getName().getLocalPart();
				if (compStr.equalsIgnoreCase("label")) {
					components.add(new ZkossLabel().create(
							(LabelType) j.getValue(), parent));
				} else if (compStr.equalsIgnoreCase("textbox")) {
					components.add(new ZkossTextbox().create(
							(TextboxType) j.getValue(), parent));
				} else if (compStr.equalsIgnoreCase("button")) {
					components.add(new ZkossButton().create(
							(ButtonType) j.getValue(), parent));
				} else if (compStr.equalsIgnoreCase("checkbox")) {
					components.add(new ZkossCheckbox().create(
							(CheckboxType) j.getValue(), parent));
				} else if (compStr.equalsIgnoreCase("datebox")) {
					components.add(new ZkossDatebox().create(
							(DateboxType) j.getValue(), parent));
				} else if (compStr.equalsIgnoreCase("listbox")) {
					components.add(new ZkossListbox().create(
							(ListboxType) j.getValue(), parent));
				} else if (compStr.equalsIgnoreCase("combobox")) {
					components.add(new ZkossCombobox().create(
							(ComboboxType) j.getValue(), parent));
				}
			} else
				System.out.println("---> " + oo);
		}

	}

	public Component findComponentById(String id) {
		String compId;
		Component retVal = null;
		for (IAryaComponent comp : components) {
			compId = ((Component) comp.getComponent()).getId();
			if (compId.equalsIgnoreCase(id)) {
				retVal = (Component) comp.getComponent();
			}
		}
		return retVal;
	}

}