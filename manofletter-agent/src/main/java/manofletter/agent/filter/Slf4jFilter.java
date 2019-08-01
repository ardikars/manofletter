package manofletter.agent.filter;

import manofletter.agent.util.FormattingTuple;
import manofletter.agent.util.MessageFormatter;

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
public class Slf4jFilter extends AbstractFilter {

    private Class<?> marker = Class.forName("org.slf4j.Marker");

    public Slf4jFilter(String clazz) throws ClassNotFoundException {
        super(clazz);
    }

    @Override
    public boolean check(Callable<?> callable, Method method, Object[] arguments) throws Exception {
        FormattingTuple tuple;
        if (arguments.length > 1) {
            if (arguments[0].getClass().isAssignableFrom(marker)) {
                if (arguments[1] instanceof Throwable) {
                    tuple = MessageFormatter.arrayFormat((String) arguments[1], EMPTY_OBJECT_ARRAY, (Throwable) arguments[2]);
                } else {
                    tuple = MessageFormatter.arrayFormat((String) arguments[3], Arrays.copyOfRange(arguments, 2, arguments.length - 2));
                }
            } else {
                if (arguments[1] instanceof Throwable) {
                    tuple = MessageFormatter.arrayFormat((String) arguments[0], EMPTY_OBJECT_ARRAY, (Throwable) arguments[1]);
                } else {
                    tuple = MessageFormatter.arrayFormat((String) arguments[1], Arrays.copyOfRange(arguments, 1, arguments.length - 1));
                }
            }
        } else {
            tuple = MessageFormatter.format((String) arguments[0], EMPTY_OBJECT_ARRAY);
        }
        String message = tuple.getMessage();
        if (matcher(message).find()) {
            return true;
        }
        callable.call();
        return false;
    }

    @Override
    public Set<String> methods() {
        return new HashSet<String>(Arrays.asList(
                "trace",
                "debug",
                "info",
                "warn",
                "error"
        ));
    }

}
