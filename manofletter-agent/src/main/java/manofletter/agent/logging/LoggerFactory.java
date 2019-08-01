package manofletter.agent.logging;

/**
 * Only for internal use.
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 */
public class LoggerFactory {

    public static Logger getLogger(Class clazz) {
        return new DefaultLogger(clazz.getName());
    }

}
