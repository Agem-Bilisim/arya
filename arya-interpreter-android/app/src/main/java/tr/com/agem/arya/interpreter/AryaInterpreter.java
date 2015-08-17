package tr.com.agem.arya.interpreter;

import android.content.Context;
import android.util.Log;
import android.util.Xml;
import android.widget.ImageView;
import android.widget.LinearLayout;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;

import tr.com.agem.arya.R;
import tr.com.agem.arya.interpreter.components.AryaButton;
import tr.com.agem.arya.interpreter.components.AryaCheckbox;
import tr.com.agem.arya.interpreter.components.AryaDatebox;
import tr.com.agem.arya.interpreter.components.AryaLabel;
import tr.com.agem.arya.interpreter.components.AryaTextbox;
import tr.com.agem.arya.interpreter.components.AryaWindow;

public class AryaInterpreter
{
    private static final String TAG = "AryaInterpreter";

    public static void handleViewResponse(String viewXML, final LinearLayout mainLayout, Context context){

        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(new StringReader(viewXML));

            while(parser.next() != XmlPullParser.END_DOCUMENT)
            {

                if(parser.getEventType() == XmlPullParser.START_TAG){
                    String tagName = parser.getName();
                    Log.d(TAG, ">>>>>>>>>>>>>tagname:" + tagName);
                    if(tagName.equals("window")) {
                        AryaWindow window = new AryaWindow(context, mainLayout);
                        createWindowComponents(context, window, parser);
                    }
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void createWindowComponents(Context context, LinearLayout window, XmlPullParser parser){
        try {
            while(parser.nextTag() != XmlPullParser.END_DOCUMENT)
            {
                if(parser.getEventType() == XmlPullParser.START_TAG){
                    String tagName = parser.getName();
                    Log.d(TAG, ">>>>>>>>>>>>>tagname:" + tagName);

                    if(tagName.equals("label")) {
                        new AryaLabel(context, parser, window);
                    }else if(tagName.equals("textbox")) {
                        new AryaTextbox(context, parser, window);
                    }else if(tagName.equals("checkbox")) {
                        new AryaCheckbox(context, parser, window);
                    }else if(tagName.equals("datebox")) {
                        new AryaDatebox(context, parser, window);
                    }else if(tagName.equals("button")) {
                        new AryaButton(context, parser, window);
                    }
                }else if(parser.getEventType() == XmlPullParser.END_TAG) {
                    String tagName = parser.getName();
                    if(tagName.equals("window")) {
                        ImageView image = new ImageView(context);
                        image.setImageResource(R.drawable.agem_logo);
                        image.setPaddingRelative(700, 0, 1, 0);
                        window.addView(image);
                    }
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
