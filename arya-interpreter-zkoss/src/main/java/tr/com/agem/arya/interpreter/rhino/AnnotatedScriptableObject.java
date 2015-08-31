package tr.com.agem.arya.interpreter.rhino;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import org.mozilla.javascript.FunctionObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class AnnotatedScriptableObject extends ScriptableObject {

	private static final long serialVersionUID = 2807853563062589252L;

	@Target(METHOD)
    @Retention(RUNTIME)
    public @interface AryaJsFunction {
    }

    public void addToScope(Scriptable scope) {

        for (Method method : this.getClass().getMethods()) {
            if (method.isAnnotationPresent(AryaJsFunction.class)) {
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