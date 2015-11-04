package tr.com.agem.arya.interpreter.components.base;

import org.zkoss.zul.Div;

import tr.com.agem.arya.interpreter.utils.BaseController;

public class AryaMain extends BaseController {

	private static final long serialVersionUID = 3024244510254500447L;
	private AryaWindow aryaWindow;
	private AryaNavBar aryaNavBar;
	private Div componentContainer;
	private Div menuContainer;

	public AryaMain(Div componentContainer, Div menuContainer) {
		super();
		this.menuContainer = menuContainer;
		this.componentContainer = componentContainer;
		this.aryaWindow = new AryaWindow();
		this.aryaNavBar = new AryaNavBar();
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

	public Div getMenuContainer() {
		return menuContainer;
	}

	public void setMenuContainer(Div menuContainer) {
		this.menuContainer = menuContainer;
	}
	
	
}
