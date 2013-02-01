package railway.support;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @param <T> 
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class MethodInvoker<T> {

    private Map<String, Method> mapOfMethods;
    private T object;

    /**
     * 
     * @param object
     */
    public MethodInvoker(T object) {
        this.object = object;
        Method[] methods = new Method[0];
        Class<?> cls = object.getClass();
        methods = cls.getDeclaredMethods();
        mapOfMethods = new HashMap<String, Method>();
        for (Method m : methods) {
            mapOfMethods.put(m.getName(), m);
        }
    }

    /**
     * 
     * @param methodName
     * @param parameters
     * @return
     */
    public Object invokeMethode(String methodName, Object[] parameters) {
        Method m = mapOfMethods.get(methodName);
        if (m != null) {
            try {
                return m.invoke(object, parameters);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 
     * @return
     */
    public T getObject() {
        return object;
    }
}
