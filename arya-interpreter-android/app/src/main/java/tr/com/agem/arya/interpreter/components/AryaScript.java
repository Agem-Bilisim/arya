package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by volkan on 19.08.2015.
 */
public class AryaScript extends View {

    public static String script;
    public static   ArrayList<String> srcList;


    public AryaScript(Context context, XmlPullParser parser, LinearLayout window) throws IOException, XmlPullParserException {
        super(context);
        this.script=parser.nextText();
        //parseSrc(parser);
    }

    private void parseSrc(XmlPullParser parser) {
        String strSrc = parser.getAttributeValue(null, "src"); //null?
        Log.d("++++++++++", "parseSrc "+strSrc);
        //this.srcList= (ArrayList<String>) Arrays.asList(strSrc.split(","));
    }
}
