package tr.com.agem.arya.interpreter.zkoss;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;

@SuppressWarnings({ "serial", "rawtypes" })
public class BaseController extends GenericForwardComposer {

	private Div icerik;

	public BaseController() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}

	public void navigate(BaseNavObj navObj) {
		if (navObj != null) {
			Sessions.getCurrent().setAttribute("navObj", navObj);
			while (icerik.getFirstChild() != null)
				icerik.getFirstChild().setParent(null);

			Executions.createComponents(navObj.getUrl(), icerik, null);
		}
	}

	/**
	 * Sessiondaki navigasyon nesnesini dondurur
	 * 
	 * @return
	 */
	public BaseNavObj getNavObj() {
		BaseNavObj navObj = (BaseNavObj) Sessions.getCurrent().getAttribute("navObj");
		Sessions.getCurrent().removeAttribute("navObj");
		return navObj;
	}

	public BaseNavObj getNavObj(Class<?> navObjClass) {
		BaseNavObj navObj = null;
		Object object = Sessions.getCurrent().getAttribute("navObj");
		Sessions.getCurrent().removeAttribute("navObj");
		if (object == null || !navObjClass.isInstance(object)) {
			try {
				navObj = (BaseNavObj) navObjClass.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} else
			navObj = (BaseNavObj) object;

		return navObj;
	}

	public Div getIcerik() {
		return icerik;
	}

	public void setIcerik(Div icerik) {
		this.icerik = icerik;
	}

}
