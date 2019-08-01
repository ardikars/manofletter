package manofletter.agent;

import manofletter.agent.filter.DefaultFilter;
import manofletter.agent.filter.Filter;
import manofletter.agent.util.DebugHelper;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.implementation.bind.annotation.This;
import net.bytebuddy.matcher.ElementMatcher;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 *
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 */
public class ManofletterInterceptor {

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
        if (ManofletterProperties.DEBUG) {
            DebugHelper.log("Default        : " + ManofletterProperties.DEFAULT);
            DebugHelper.log("Default filter : " + DEFAULT_FILTER);
            DebugHelper.log("Logger         : " + logger);
            DebugHelper.log("Method         : " + method);
            DebugHelper.log("Arguments      : " + logger);
            DebugHelper.log("Callable       : " + callable);
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
        if (ManofletterProperties.DEBUG) {
            StringBuilder sb = new StringBuilder();
            for (Filter filter : FILTERS) {
                sb.append(filter.type().getName());
            }
            DebugHelper.log("Registered filter: " + sb.toString());
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
    }

}
