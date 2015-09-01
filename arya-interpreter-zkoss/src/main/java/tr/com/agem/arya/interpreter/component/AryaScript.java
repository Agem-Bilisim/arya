package tr.com.agem.arya.interpreter.component;

import java.util.Arrays;
import java.util.List;

import org.xml.sax.Attributes;
import org.zkoss.zul.Script;

import tr.com.agem.arya.interpreter.zkoss.AryaWindow;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.utils.AryaUtils;

public class AryaScript extends Script implements IAryaComponent {

	private static final long serialVersionUID = 9120641871916430134L;

	private String script;
	private List<String> srcList;

	public AryaScript(AryaWindow aryaWindow, Attributes attributes) {
		// External js files can be specified by 'src' attribute
		String sources = attributes.getValue("src");
		if (AryaUtils.isNotEmpty(sources)) {
			this.srcList = Arrays.asList(sources.split(";"));
		}
	}
	
	@Override
	public void setComponentParent(Object parent) {
	}

	@Override
	public String validate() {
		return null;
	}

	@Override
	public void setComponentId(String componentId) {
	}

	@Override
	public String getComponentId() {
		return "aryaScript";
	}

	@Override
	public void setComponentClassName(String componentClassName) {
	}

	@Override
	public String getComponentClassName() {
		return null;
	}

	@Override
	public void setComponentValue(String componentValue) {
	}

	@Override
	public String getComponentValue() {
		return null;
	}

	@Override
	public void setComponentAttribute(String componentAttribute) {
	}

	@Override
	public String getComponentAttribute() {
		return null;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public List<String> getSrcList() {
		return srcList;
	}

	public void setSrcList(List<String> srcList) {
		this.srcList = srcList;
	}

}
