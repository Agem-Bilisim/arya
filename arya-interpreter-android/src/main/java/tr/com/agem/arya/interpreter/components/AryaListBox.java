package tr.com.agem.arya.interpreter.components;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SpinnerAdapter;

import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaListBox extends ListView implements IAryaComponent {

	private static final String TAG = "AryaListBox";

	private String componentClassName;
	private String componentId;
	private String componentAttribute;
	private String componentValue;

	public AryaListBox(Context context, XmlPullParser parser, LinearLayout window) {

		super(context);

		this.componentId = parser.getAttributeValue(null, "id");
		this.componentClassName = parser.getAttributeValue(null, "class");
		this.componentAttribute = parser.getAttributeValue(null, "attribute");
		this.componentValue = parser.getAttributeValue(null, "value");

		final String onChange = parser.getAttributeValue(null, "onChange");

		/*
		 * this.setAdapter((ListAdapter) getAdapter(context, parser, window));
		 * 
		 * this.setOnItemSelectedListener(new OnItemSelectedListener() {
		 * 
		 * @Override public void onItemSelected(AdapterView<?> parent, View
		 * view, int position, long id) {
		 * 
		 * }
		 * 
		 * @Override public void onNothingSelected(AdapterView<?> parent) {
		 * 
		 * } });
		 */

		window.addView(this);

	}

	/*private SpinnerAdapter getAdapter(Context context, XmlPullParser parser, LinearLayout window) {

		List<AryaComboItem> list = new ArrayList<AryaComboItem>();

		try {
			while (parser.nextTag() != XmlPullParser.END_DOCUMENT) {
				if (parser.getEventType() == XmlPullParser.START_TAG) {
					String tagName = parser.getName();

					if (tagName.equals("listitem")) {
						Log.d("", parser.getAttributeValue(null, "id") + "");
						AryaComboItem item = new AryaComboItem(context, attribute);
						window.addView(item);

						list.add(item);
					}
				}
			}
		} catch (XmlPullParserException e) {
			Log.e(TAG , "XmlPullParserException: unexpected type", e);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ArrayAdapter<AryaComboItem> dataAdapter = new ArrayAdapter<AryaComboItem>(context, android.R.layout.simple_spinner_item,
				list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		return dataAdapter;
	}*/

	@Override
	public String getComponentClassName() {
		return componentClassName;
	}

	@Override
	public void setComponentClassName(String componentClassName) {
		this.componentClassName = componentClassName;
	}

	@Override
	public String getComponentId() {
		return componentId;
	}

	@Override
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	@Override
	public String getComponentAttribute() {
		return componentAttribute;
	}

	@Override
	public String validate() {
		return null;
	}

	@Override
	public void setComponentParent(Object o) {

	}

	@Override
	public void setComponentAttribute(String componentAttribute) {
		this.componentAttribute = componentAttribute;
	}

	@Override
	public String getComponentValue() {
		return componentValue;
	}

	@Override
	public void setComponentValue(String componentValue) {
		this.componentValue = componentValue;
	}
}
