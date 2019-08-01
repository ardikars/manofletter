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
public class DefaultFilter extends AbstractFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultFilter.class);

    public DefaultFilter() throws ClassNotFoundException {
        super(null);
    }

    @Override
    public boolean check(Callable<?> callable, Method method, Object[] arguments) throws Exception {
        LOGGER.info("Intercepted...");
        return false;
    }

    @Override
    public Set<String> methods() {
        return new HashSet<String>(Arrays.asList("info", "warn", "debug", "error"));
    }

}
