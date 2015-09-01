package tr.com.agem.arya.interpreter.zkoss;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;

@SuppressWarnings("rawtypes")
public class BaseController extends GenericForwardComposer {

	private static final long serialVersionUID = 8866650311533378984L;
	private Div componentContainer; // works as a parent component

	public BaseController() {
		super();
	}

	public Div getComponentContainer() {
		return componentContainer;
	}

	public void setComponentContainer(Div componentContainer) {
		this.componentContainer = componentContainer;
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public void doAfterCompose(Component comp) throws Exception {
//		super.doAfterCompose(comp);
//	}
//
//	public void navigate(BaseNavObj navObj) {
//		if (navObj != null) {
//			Sessions.getCurrent().setAttribute("navObj", navObj);
//			while (content.getFirstChild() != null)
//				content.getFirstChild().setParent(null);
//			Executions.createComponents(navObj.getUrl(), content, null);
//		}
//	}
//
//	/**
//	 * Sessiondaki navigasyon nesnesini dondurur
//	 * 
//	 * @return
//	 */
//	public BaseNavObj getNavObj() {
//		BaseNavObj navObj = (BaseNavObj) Sessions.getCurrent().getAttribute("navObj");
//		Sessions.getCurrent().removeAttribute("navObj");
//		return navObj;
//	}
//
//	public BaseNavObj getNavObj(Class<?> navObjClass) {
//		BaseNavObj navObj = null;
//		Object object = Sessions.getCurrent().getAttribute("navObj");
//		Sessions.getCurrent().removeAttribute("navObj");
//		if (object == null || !navObjClass.isInstance(object)) {
//			try {
//				navObj = (BaseNavObj) navObjClass.newInstance();
//			} catch (InstantiationException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			}
//		} else
//			navObj = (BaseNavObj) object;
//
//		return navObj;
//	}

}
