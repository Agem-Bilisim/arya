package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by volkan on 19.08.2015.
 */
public class AryaScript extends View{

    private String script;
    private List<String> srcList;

    public AryaScript(Context context, XmlPullParser parser) throws IOException, XmlPullParserException {
       super(context);
        this.srcList=parseSrc(parser);
        this.script=parser.nextText();
    }

    private List<String> parseSrc(XmlPullParser parser) {
        srcList=null;
        if(parser.getAttributeValue(null, "import")!=null)
            srcList= Arrays.asList(parser.getAttributeValue(null, "import").toString().split(";"));
        return srcList;
    }

    public String getScript() {return script;}

    public void setScript(String script) {this.script = script;}

    public List<String> getSrcList() {
        return srcList;
    }

    public void setSrcList(List<String> srcList) {
        this.srcList = srcList;
    }
}
