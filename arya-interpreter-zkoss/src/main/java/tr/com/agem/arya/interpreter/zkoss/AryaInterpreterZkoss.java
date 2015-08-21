package tr.com.agem.arya.interpreter.zkoss;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.zkoss.zk.ui.Component;

import tr.com.agem.arya.interpreter.zkoss.component.ZkossButton;
import tr.com.agem.arya.interpreter.zkoss.component.ZkossCheckbox;
import tr.com.agem.arya.interpreter.zkoss.component.ZkossCombobox;
import tr.com.agem.arya.interpreter.zkoss.component.ZkossDatebox;
import tr.com.agem.arya.interpreter.zkoss.component.ZkossLabel;
import tr.com.agem.arya.interpreter.zkoss.component.ZkossListbox;
import tr.com.agem.arya.interpreter.zkoss.component.ZkossTextbox;
import tr.com.agem.arya.metadata.arya.impl.AryaType;
import tr.com.agem.arya.metadata.arya.impl.ButtonType;
import tr.com.agem.arya.metadata.arya.impl.CheckboxType;
import tr.com.agem.arya.metadata.arya.impl.ComboboxType;
import tr.com.agem.arya.metadata.arya.impl.DateboxType;
import tr.com.agem.arya.metadata.arya.impl.LabelType;
import tr.com.agem.arya.metadata.arya.impl.ListboxType;
import tr.com.agem.arya.metadata.arya.impl.TextboxType;
import tr.com.agem.arya.metadata.arya.impl.WindowType;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.interpreter.IAryaInterpreter;

public class AryaInterpreterZkoss implements IAryaInterpreter {

	private List<IAryaComponent> components;
	private JAXBContext jaxbContext;
	private Unmarshaller jaxbUnmarshaller;
	private AryaWindow aryaWindow;

	public List<IAryaComponent> getComponents() {
		return components;
	}

	public void setComponents(List<IAryaComponent> components) {
		this.components = components;
	}

	private Component parent;

	@Override
	public void interpretAryaResponse(AryaResponse response, Object parent,
			Object masterWindow) {
		aryaWindow = (AryaWindow) masterWindow;
		aryaWindow.getIcerik().getChildren().clear();
		try {
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(response.getView());

			@SuppressWarnings("unchecked")
			AryaType arya = ((JAXBElement<AryaType>) ((JAXBElement<AryaType>) jaxbUnmarshaller
					.unmarshal(reader))).getValue();

			List<Object> comp = arya.getContent();

			for (Object o : comp) {
				if (o instanceof JAXBElement) {
					if (((JAXBElement<?>) o).getName().getLocalPart()
							.equalsIgnoreCase("window")) {
						WindowType window = (WindowType) ((JAXBElement<?>) o)
								.getValue();

						createComponents(parent, window.getContent());
					}
				}

			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void interpretAryaResponse(String responseStr, Object parent,
			Object masterWindow) {
		AryaResponse response = new AryaResponse();
		response.fromXMLString(responseStr);
		interpretAryaResponse(response, parent, masterWindow);
	}

	@Override
	public void createComponents(Object parentObject,
			List<Object> componentsList) {
		this.parent = (Component) parentObject;
		components = new ArrayList<IAryaComponent>();
		for (Object oo : componentsList) {
			if (oo instanceof JAXBElement<?>) {
				JAXBElement<?> j = (JAXBElement<?>) oo;
				String compStr = ((JAXBElement<?>) j).getName().getLocalPart();
				if (compStr.equalsIgnoreCase("label")) {
					components.add(new ZkossLabel().create(
							(LabelType) j.getValue(), parent, aryaWindow));
				} else if (compStr.equalsIgnoreCase("textbox")) {
					components.add(new ZkossTextbox().create(
							(TextboxType) j.getValue(), parent, aryaWindow));
				} else if (compStr.equalsIgnoreCase("button")) {
					components.add(new ZkossButton().create(
							(ButtonType) j.getValue(), parent, aryaWindow));
				} else if (compStr.equalsIgnoreCase("checkbox")) {
					components.add(new ZkossCheckbox().create(
							(CheckboxType) j.getValue(), parent, aryaWindow));
				} else if (compStr.equalsIgnoreCase("datebox")) {
					components.add(new ZkossDatebox().create(
							(DateboxType) j.getValue(), parent, aryaWindow));
				} else if (compStr.equalsIgnoreCase("listbox")) {
					components.add(new ZkossListbox().create(
							(ListboxType) j.getValue(), parent, aryaWindow));
				} else if (compStr.equalsIgnoreCase("combobox")) {
					components.add(new ZkossCombobox().create(
							(ComboboxType) j.getValue(), parent, aryaWindow));
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

	public JAXBContext getJaxbContext() {
		return jaxbContext;
	}

	public void setJaxbContext(JAXBContext jaxbContext) {
		this.jaxbContext = jaxbContext;
	}

	public Unmarshaller getJaxbUnmarshaller() {
		return jaxbUnmarshaller;
	}

	public void setJaxbUnmarshaller(Unmarshaller jaxbUnmarshaller) {
		this.jaxbUnmarshaller = jaxbUnmarshaller;
	}

}