package manofletter.agent.logging;

/**
 * This logger class only for internal use (debugging purpose).
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 */
public interface Logger {

    boolean isDebugEnabled();

    boolean isInfoEnabled();

    boolean isWarnEnabled();

    boolean isErrorEnabled();

    void debug(CharSequence message);

    void info(CharSequence message);

    void warn(CharSequence message);

    void error(CharSequence message);

    void debug(CharSequence format, CharSequence argument);

    void info(CharSequence format, CharSequence argument);

    void warn(CharSequence format, CharSequence argument);

    void error(CharSequence format, CharSequence argument);

    void debug(CharSequence format, CharSequence[] arguments);

    void info(CharSequence format, CharSequence[] arguments);

    void warn(CharSequence format, CharSequence[] arguments);

    void error(CharSequence format, CharSequence[] arguments);

    enum Level {

        DEBUG, INFO, WARN, ERROR;

    }

}
