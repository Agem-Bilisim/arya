package tr.com.agem.arya.interpreter.zkoss;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Label;

import tr.com.agem.arya.metadata.xul.impl.LabelElementType;
import tr.com.agem.arya.metadata.xul.impl.WindowElementType;

@SuppressWarnings("serial")
public class AryaWindow extends BaseController {

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		init();
	}

	private void init() throws IOException {
		ClientResource resource = new ClientResource(
				"http://localhost:8080/someresource");

		Representation clientText = resource.get();

		String masterWindowJSON = clientText.getText();

		try {
			ObjectMapper mapper = new ObjectMapper();

			WindowElementType window = mapper.readValue(masterWindowJSON,
					WindowElementType.class);

			List<Object> comp = window.getAnyOrTextboxOrStatusbar();

			for (Object o : comp) {
				if (o instanceof LabelElementType) {
					Label label = new Label();
					label.setValue(((LabelElementType)o).getValue());
					label.setParent(getIcerik());
				}
			}

			System.out.println(window.getId());

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
