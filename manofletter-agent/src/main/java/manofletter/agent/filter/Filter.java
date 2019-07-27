package manofletter.agent.filter;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 *
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 */
public interface Filter {

    /**
     * Filtering logger arguments (message).
     * @param callable call logger method if filter is passed.
     * @param arguments logging arguments.
     * @return return {@code true} if filter not passed, {@code else} otherwise.
     * @throws Exception throw {@code Exception} if there is some error in your intercepted method call.
     */
    boolean check(Callable<?> callable, Method method, Object[] arguments) throws Exception;

    /**
     * Get logger type.
     * @return returns logger type.
     */
    Class<?> type();

    /**
     * Default methods will be intercepted, it can be override by -Dmanofletter.method separated by comma (,).
     * @return returns intercepted methods.
     */
    Set<String> methods();

}
