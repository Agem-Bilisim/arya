package tr.com.agem.arya.interpreter.components;

import android.content.Context;
import android.view.View;

import org.xml.sax.Attributes;
import java.util.Arrays;
import java.util.List;

import tr.com.agem.core.interpreter.IAryaComponent;

public class AryaScript extends View implements IAryaComponent{

    private String script;
    private List<String> srcList;

    public AryaScript(Context context, Attributes attributes, AryaWindow aryaWindow) {
       super(context);
        this.srcList=parseSrc(attributes.getValue("src"));
    }

    private List<String> parseSrc(String src) {
        srcList=null;
        srcList= Arrays.asList(src.split(";"));
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

    @Override
    public void setComponentId(String s) {

    }

    @Override
    public String getComponentId() {
        return null;
    }

    @Override
    public void setComponentClassName(String s) {

    }

    @Override
    public String getComponentClassName() {
        return null;
    }

    @Override
    public void setComponentValue(String s) {

    }

    @Override
    public String getComponentValue() {
        return null;
    }

    @Override
    public void setComponentAttribute(String s) {

    }

    @Override
    public String getComponentAttribute() {
        return null;
    }

    @Override
    public String validate() {
        return null;
    }

    @Override
    public void setComponentParent(Object o) {

    }
}
