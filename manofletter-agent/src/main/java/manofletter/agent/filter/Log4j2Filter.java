package manofletter.agent.filter;

import manofletter.agent.logging.Logger;
import manofletter.agent.logging.LoggerFactory;
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
public class Log4j2Filter extends AbstractFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Log4j2Filter.class);

    private final Class<?> MARKER = Class.forName("org.apache.logging.log4j.Marker");

    public Log4j2Filter(String clazz) throws ClassNotFoundException {
        super(clazz);
    }

    @Override
    public boolean check(Callable<?> callable, Method method, Object[] arguments) throws Exception {
        FormattingTuple tuple = null;
        if (arguments.length > 1) {
            if (MARKER.isInstance(arguments[0])) {
                if (arguments.length >= 3 && CharSequence.class.isInstance(arguments[1])) {
                    tuple = MessageFormatter.arrayFormat(arguments[1].toString(),
                            Arrays.copyOfRange(arguments, 2, arguments.length - 1));
                } else if (arguments.length >= 3 && CharSequence.class.isInstance(arguments[1])) {
                    if (arguments[2] != null && arguments[2] instanceof Throwable) {
                        tuple = MessageFormatter.arrayFormat(arguments[1].toString(),
                                Arrays.copyOfRange(arguments, 1, 1), (Throwable) arguments[2]);
                    }
                }
            } else {
                if (arguments.length >= 2 && CharSequence.class.isInstance(arguments[0])) {
                    tuple = MessageFormatter.arrayFormat(arguments[1].toString(),
                            Arrays.copyOfRange(arguments, 1, arguments.length - 1));
                } else if (arguments.length >= 2 && CharSequence.class.isInstance(arguments[0])) {
                    if (arguments[1] != null && arguments[1] instanceof Throwable) {
                        tuple = MessageFormatter.arrayFormat(arguments[0].toString(),
                                Arrays.copyOfRange(arguments, 0, 0), (Throwable) arguments[1]);
                    }
                }
            }
        } else {
            tuple = MessageFormatter.format((String) arguments[0], EMPTY_OBJECT_ARRAY);
        }
        if (tuple != null) {
            String message = tuple.getMessage();
            if (matcher(message).find()) {
                return true;
            }
        }
        callable.call();
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
