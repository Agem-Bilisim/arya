package tr.com.agem.core.context;

import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.http.HttpSession;

/**
 * AryaSession is a wrapper class which extends HttpSession capabilities
 * It is strictly used with AryaContext which can be accessed anywhere inside the Arya application  
 * More methods can be added here if the need arises.
 */
public class AryaSession {

	private HttpSession session;

	protected AryaSession(HttpSession session) {
		this.session = session;
	}

	public boolean containsKey(Object key) {
		if (key instanceof String == false) {
			throw new IllegalArgumentException("Only keys of type String is allowed.");
		}
		return Collections.list((Enumeration<?>) session.getAttributeNames()).contains(key);
	}

	public boolean containsValue(Object value) {
		Enumeration<?> attNames = session.getAttributeNames();
		while (attNames.hasMoreElements()) {
			Object o = session.getAttribute((String) attNames.nextElement());
			if (o != null && value != null && value.equals(o)) {
				return true;
			}
		}
		return false;
	}

	public boolean isEmpty() {
		return (session == null) ? true : session.getAttributeNames().hasMoreElements() == false;
	}

	public Object getAttribute(String attribute) {
		return (session == null) ? null : session.getAttribute(attribute);
	}

}
