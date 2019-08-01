package manofletter.agent.logging;

import java.io.PrintStream;

/**
 * Only for internal use.
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 */
class DefaultLogger extends AbstractLogger {

    private static final PrintStream LOGGER = System.out;

    DefaultLogger(String name) {
        super(name);
    }

    @Override
    protected void log(CharSequence message) {
        LOGGER.println(name + " [" + LEVEL + "] " + message);
    }

}
