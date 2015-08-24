package tr.com.agem.arya.interpreter.components;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by volkan on 19.08.2015.
 */
public class AryaScript {

    public static String script;
    public static List<String> srcList;

    public AryaScript(XmlPullParser parser) throws IOException, XmlPullParserException {
        this.srcList=parseSrc(parser);
        this.script=parser.nextText();
    }

    private List<String> parseSrc(XmlPullParser parser) {
        srcList=null;
        if(parser.getAttributeValue(null, "import")!=null)
            srcList= Arrays.asList(parser.getAttributeValue(null, "import").toString().split(";"));
        return srcList;
    }

    public static String getScript() {
        return script;
    }

    public static void setScript(String script) {
        AryaScript.script = script;
    }

    public List<String> getSrcList() {
        return srcList;
    }

    public void setSrcList(List<String> srcList) {
        this.srcList = srcList;
    }
}
