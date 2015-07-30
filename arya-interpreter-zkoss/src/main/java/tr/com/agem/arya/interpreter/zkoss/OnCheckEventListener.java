package tr.com.agem.arya.interpreter.zkoss;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Messagebox;

@SuppressWarnings("rawtypes")
public class OnCheckEventListener implements EventListener {

	Component parent, component;
	String onCheck;

	public OnCheckEventListener(Component comp, Component parent, String onCheck) {
		super();
		this.parent = parent;
		this.component = comp;
		this.onCheck = onCheck;
	}

	@Override
	public void onEvent(Event event) throws Exception {
		if (((Checkbox) component).isChecked())
			Messagebox.show("Deneme", "Arya", Messagebox.OK,
					Messagebox.INFORMATION);
	}
}
