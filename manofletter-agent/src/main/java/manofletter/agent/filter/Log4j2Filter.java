package manofletter.agent.filter;

import manofletter.agent.logging.Logger;
import manofletter.agent.logging.LoggerFactory;

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

    private Class<?> marker = Class.forName("org.apache.logging.log4j.Marker");

    public Log4j2Filter(String clazz) throws ClassNotFoundException {
        super(clazz);
    }

    @Override
    public boolean check(Callable<?> callable, Method method, Object[] arguments) throws Exception {
        LOGGER.info("Intercepted...");
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
