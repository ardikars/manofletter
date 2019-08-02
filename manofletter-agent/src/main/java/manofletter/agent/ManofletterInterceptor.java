package manofletter.agent;

import manofletter.agent.filter.DefaultFilter;
import manofletter.agent.filter.Filter;
import manofletter.agent.logging.Logger;
import manofletter.agent.logging.LoggerFactory;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.implementation.bind.annotation.This;
import net.bytebuddy.matcher.ElementMatcher;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 *
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 */
public class ManofletterInterceptor {

    private static final Logger LOGGER;

    static Filter DEFAULT_FILTER;
    static final Set<Filter> FILTERS;

    private ElementMatcher.Junction type;
    private ElementMatcher.Junction methods;

    ManofletterInterceptor(ElementMatcher.Junction type, ElementMatcher.Junction methods) {
        this.type = type;
        this.methods = methods;
    }

    @RuntimeType
    public static void intercept(@This Object logger,
                                 @Origin Method method,
                                 @AllArguments Object[] arguments,
                                 @SuperCall Callable<?> callable) throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Logger Type           : {}", logger.toString());
            LOGGER.debug("Method                : {}", method.toString());
            LOGGER.debug("Arguments             : {}", Arrays.asList(arguments).toString());
            LOGGER.debug("Callable              : {}", callable.toString());
        }
        Filter filter;
        if (ManofletterProperties.DEFAULT) {
            filter = DEFAULT_FILTER;
        } else {
            filter = findFilter(logger);
        }
        if (filter != null) {
            filter.check(callable, method, arguments);
        } else {
            callable.call();
        }
    }

    ElementMatcher.Junction type() {
        return type;
    }

    ElementMatcher.Junction methods() {
        return methods;
    }

    private static Filter findFilter(Object logger) {
        if (DEFAULT_FILTER != null) {
            return DEFAULT_FILTER;
        }
        Filter filter = null;
        Iterator<Filter> iterator = FILTERS.iterator();
        while (iterator.hasNext()) {
            Filter next = iterator.next();
            if (next.type().isAssignableFrom(logger.getClass())) {
                filter = next;
                break;
            } else if (next.type().isAssignableFrom(Void.class)) {
                DEFAULT_FILTER = next;
            }
        }
        if (filter == null &&  DEFAULT_FILTER != null) {
            filter = DEFAULT_FILTER;

        }
        return filter;
    }

    static {
        LOGGER = LoggerFactory.getLogger(ManofletterInterceptor.class);
        Filter defaultFilter;
        if (ManofletterProperties.DEFAULT) {
            try {
                defaultFilter = new DefaultFilter();
            } catch (ClassNotFoundException e) {
                defaultFilter = null;
            }
        } else {
            defaultFilter = null;
        }
        DEFAULT_FILTER = defaultFilter;
        FILTERS = new HashSet<Filter>();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Default Configuration : {}", String.valueOf(ManofletterProperties.DEFAULT));
        }
    }

}
