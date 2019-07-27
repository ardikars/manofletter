package manofletter.agent;

import manofletter.agent.filter.Filter;
import manofletter.agent.filter.Log4j2Filter;
import manofletter.agent.filter.DefaultFilter;
import manofletter.agent.filter.Slf4jFilter;
import manofletter.agent.util.DebugHelper;
import manofletter.agent.util.Properties;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 */
public class ManofletterAgent {

    public static void premain(String arguments, Instrumentation instrumentation) throws ClassNotFoundException {
        final ManofletterInterceptor interceptor = interceptor();
        new AgentBuilder.Default()
                .type(interceptor.type())
                .transform(new AgentBuilder.Transformer() {
                               @Override
                               public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription type, ClassLoader classLoader, JavaModule module) {
                                   return builder
                                           .method(interceptor.methods())
                                           .intercept(MethodDelegation.to(ManofletterInterceptor.class));
                               }
                           }
                )
                .installOn(instrumentation);
    }

    private static ManofletterInterceptor interceptor() throws ClassNotFoundException {
        Set<Filter> filters = new HashSet<Filter>();
        ElementMatcher.Junction junction;
        String loggers = Properties.getProperty(ManofletterProperties.LOGGERS_KEY, null);
        String methods = Properties.getProperty(ManofletterProperties.METHODS_KEY, null);
        if (loggers == null || loggers.isEmpty()) {
            junction = ElementMatchers.nameContains(ManofletterProperties.LOGGER_TYPE);
            filters.add(new DefaultFilter());
        } else {
            String[] parts = loggers.split(ManofletterProperties.SEPARATOR);
            if (parts.length > 1) {
                junction = type(filters, parts[0]);
                for (int i = 1; i < parts.length; i++) {
                    junction = junction.or(type(filters, parts[i]));
                }
            } else {
                junction = type(filters, parts[0]);
            }
        }
        boolean hasMethods = methods != null && !methods.isEmpty();
        Set<String> set = new HashSet<String>();
        Iterator<Filter> iterator = filters.iterator();
        while (iterator.hasNext()) {
            Filter filter = iterator.next();
            if (!hasMethods) {
                set.addAll(filter.methods());
            }
            ManofletterInterceptor.FILTERS.add(filter);
        }
        if (hasMethods) {
            if (methods.contains(ManofletterProperties.SEPARATOR)) {
                String[] parts = methods.split(ManofletterProperties.SEPARATOR);
                for (String part : parts) {
                    set.add(part.trim());
                }
            } else {
                set.add(methods.trim());
            }
        }
        if (ManofletterProperties.DEBUG) {
            DebugHelper.log("Type      : " + junction);
            DebugHelper.log("Methods   : " + set);
            for (Filter filter : ManofletterInterceptor.FILTERS) {
                DebugHelper.log("Filter    : " + filter);
            }
        }
        return new ManofletterInterceptor(junction, methods(set));
    }

    private static ElementMatcher.Junction type(Set<Filter> filters, String clazz) throws ClassNotFoundException {
        if (clazz.equals(ManofletterProperties.SL4J_LOGGER)) {
            filters.add(new Slf4jFilter(ManofletterProperties.SL4J_LOGGER));
        } else if (clazz.equals(ManofletterProperties.LOG4J2_LOGGER)) {
            filters.add(new Log4j2Filter(ManofletterProperties.LOG4J2_LOGGER));
        } else {
            filters.add(new DefaultFilter());
        }
        return ElementMatchers.isSubTypeOf(Class.forName(clazz));
    }

    private static ElementMatcher.Junction methods(Set<String> methods) {
        Iterator<String> iterator = methods.iterator();
        ElementMatcher.Junction junction = ElementMatchers.named(iterator.next());
        while (iterator.hasNext()) {
            String method = iterator.next();
            junction = junction.or(ElementMatchers.named(method));
        }
        return junction;
    }

}
