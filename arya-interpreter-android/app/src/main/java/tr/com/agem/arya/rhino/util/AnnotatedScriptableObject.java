package tr.com.agem.arya.rhino.util;

import org.mozilla.javascript.FunctionObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by volkan on 19.08.2015.
 */
@SuppressWarnings("serial")
public class AnnotatedScriptableObject extends ScriptableObject {

    @Target(METHOD)
    @Retention(RUNTIME)
    public @interface Expose {
    }

    public void addToScope(Scriptable scope) {

        for (Method method : this.getClass().getMethods()) {
            if (method.isAnnotationPresent(Expose.class)) {
                FunctionObject function = new FunctionObject(method.getName(), method, this);
                scope.put(function.getFunctionName(), scope, function);
            }
        }
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }
}
