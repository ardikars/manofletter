package manofletter.agent.filter;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 *
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 */
public class Log4j2Filter extends AbstractFilter {

    public Log4j2Filter(String clazz) throws ClassNotFoundException {
        super(clazz);
    }

    @Override
    public boolean check(Callable<?> callable, Method method, Object[] arguments) throws Exception {
        System.out.println("**Log4j2 Intercepted***");
        return false;
    }

    @Override
    public Set<String> methods() {
        return new HashSet<String>(Arrays.asList(
                "log",
                "debug",
                "info",
                "warn",
                "error",
                "fatal",
                "trace"
        ));
    }

}
