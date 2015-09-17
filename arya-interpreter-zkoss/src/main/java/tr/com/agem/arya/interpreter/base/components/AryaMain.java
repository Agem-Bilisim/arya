package tr.com.agem.arya.interpreter.base.components;

import org.zkoss.zul.Div;

public class AryaMain {

	AryaWindow aryaWindow;
	AryaNavBar aryaNavBar;
	Div componentContainer;

	public AryaMain() {
	}

	public AryaMain(AryaWindow aryaWindow, AryaNavBar aryaNavBar) {
		this.aryaWindow = aryaWindow;
		this.aryaNavBar = aryaNavBar;
	}

	public AryaMain(Div componentContainer) {

		this.componentContainer = componentContainer;
		this.aryaWindow = new AryaWindow(componentContainer);
		this.aryaNavBar = new AryaNavBar(componentContainer);
	}

	public AryaWindow getAryaWindow() {
		return aryaWindow;
	}

	public void setAryaWindow(AryaWindow aryaWindow) {
		this.aryaWindow = aryaWindow;
	}

	
	public Div getComponentContainer() {
		return componentContainer;
	}

	public void setComponentContainer(Div componentContainer) {
		this.componentContainer = componentContainer;
	}

	public AryaNavBar getAryaNavBar() {
		return aryaNavBar;
	}

	public void setAryaNavBar(AryaNavBar aryaNavBar) {
		this.aryaNavBar = aryaNavBar;
	}

}
