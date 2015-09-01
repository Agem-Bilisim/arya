package tr.com.agem.arya.interpreter.component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import tr.com.agem.arya.interpreter.zkoss.AryaWindow;
import tr.com.agem.core.utils.AryaUtils;

public class AryaScript implements IAryaComponentProperty, Serializable {

	private static final long serialVersionUID = 9120641871916430134L;

	private String componentClassName;
	private String componentId;
	private String componentAttribute;
	private String componentValue;

	private String script;
	private List<String> srcList;

	public AryaScript(AryaWindow aryaWindow, String script, String scriptSource) {
		if (AryaUtils.isNotEmpty(scriptSource)) {
			this.srcList = Arrays.asList(scriptSource.split(";"));
		}
		this.script = script;
	}

	@Override
	public String validate() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getComponentClassName() {
		return componentClassName;
	}

	public void setComponentClassName(String componentClassName) {
		this.componentClassName = componentClassName;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getComponentAttribute() {
		return componentAttribute;
	}

	public void setComponentAttribute(String componentAttribute) {
		this.componentAttribute = componentAttribute;
	}

	public String getComponentValue() {
		return componentValue;
	}

	public void setComponentValue(String componentValue) {
		this.componentValue = componentValue;
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
