package proxy;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NullProxy {

    private static MethodHandler nullPointerHandler = new MethodHandler() {
        @Override
        public Object invoke(Object o, Method method, Method method1, Object[] objects) throws Throwable {
            try {
                return method1.invoke(o, objects);
            } catch (InvocationTargetException e) {
                if (e.getTargetException() instanceof NullPointerException) {
                    return null;
                }
                throw e;
            }
        }
    };

    public static <T> T createProxy(Class<T> classTarget) {

        T target = createInstance(classTarget);

        if (target != null) ((Proxy)target).setHandler(nullPointerHandler);

        return target;
    }

    public static <T> T createProxy(T targetInstance) {
        T target = createInstance(targetInstance.getClass());

        if (target != null) ((Proxy)target).setHandler(nullPointerHandler);

        return target;
    }

    private static<T> T createInstance(Class classTarget) {
        ProxyFactory f = new ProxyFactory();

        f.setSuperclass(classTarget);

        try {
            return  (T) f.createClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
