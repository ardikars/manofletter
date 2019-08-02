package manofletter.agent.logging;

import manofletter.agent.ManofletterProperties;
import manofletter.agent.util.FormattingTuple;
import manofletter.agent.util.MessageFormatter;
import manofletter.agent.util.Properties;

/**
 * Only for internal use.
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 */
abstract class AbstractLogger implements Logger {

    protected static final Logger.Level LEVEL;

    protected final String name;

    AbstractLogger(String name) {
        this.name = name;
    }

    @Override
    public boolean isDebugEnabled() {
        return LEVEL == LEVEL.DEBUG;
    }

    @Override
    public boolean isInfoEnabled() {
        return LEVEL == Level.INFO || LEVEL == Level.DEBUG;
    }

    @Override
    public boolean isWarnEnabled() {
        return LEVEL == Level.DEBUG || LEVEL == Level.INFO || LEVEL == Level.WARN;
    }

    @Override
    public boolean isErrorEnabled() {
        return LEVEL == Level.ERROR || LEVEL == Level.DEBUG || LEVEL == Level.INFO || LEVEL == Level.WARN;
    }

    @Override
    public void debug(CharSequence message) {
        if (message != null) {
            if (isDebugEnabled()) {
                log("[DEBUG] : " + message.toString());
            }
        }
    }

    @Override
    public void debug(CharSequence format, CharSequence argument) {
        if (format != null || argument != null) {
            if (isDebugEnabled()) {
                FormattingTuple tuple = MessageFormatter.format(format.toString(), argument);
                log("[DEBUG] : " + tuple.getMessage());
            }
        }
    }

    @Override
    public void debug(CharSequence format, CharSequence[] arguments) {
        if (format != null || (arguments != null && arguments.length > 0)) {
            if (isDebugEnabled()) {
                FormattingTuple tuple = MessageFormatter.arrayFormat(format.toString(), arguments);
                log("[DEBUG] : " + tuple.getMessage());
            }
        }
    }

    @Override
    public void info(CharSequence message) {
        if (message != null) {
            if (isInfoEnabled()) {
                log("[INFO] : " + message.toString());
            }
        }
    }

    @Override
    public void info(CharSequence format, CharSequence argument) {
        if (format != null || argument != null) {
            if (isInfoEnabled()) {
                FormattingTuple tuple = MessageFormatter.format(format.toString(), argument);
                log("[INFO] : " + tuple.getMessage());
            }
        }
    }

    @Override
    public void info(CharSequence format, CharSequence[] arguments) {
        if (format != null || (arguments != null && arguments.length > 0)) {
            if (isInfoEnabled()) {
                FormattingTuple tuple = MessageFormatter.arrayFormat(format.toString(), arguments);
                log("[INFO] : " + tuple.getMessage());
            }
        }
    }

    @Override
    public void warn(CharSequence message) {
        if (message != null) {
            if (isWarnEnabled()) {
                log("[WARN] : " + message.toString());
            }
        }
    }

    @Override
    public void warn(CharSequence format, CharSequence argument) {
        if (format != null || argument != null) {
            if (isWarnEnabled()) {
                FormattingTuple tuple = MessageFormatter.format(format.toString(), argument);
                log("[WARN] : " + tuple.getMessage());
            }
        }
    }

    @Override
    public void warn(CharSequence format, CharSequence[] arguments) {
        if (format != null || (arguments != null && arguments.length > 0)) {
            if (isWarnEnabled()) {
                FormattingTuple tuple = MessageFormatter.arrayFormat(format.toString(), arguments);
                log("[WARN] : " + tuple.getMessage());
            }
        }
    }

    @Override
    public void error(CharSequence message) {
        if (message != null) {
            if (isErrorEnabled()) {
                log("[ERROR] : " + message.toString());
            }
        }
    }

    @Override
    public void error(CharSequence format, CharSequence argument) {
        if (format != null || argument != null) {
            if (isErrorEnabled()) {
                FormattingTuple tuple = MessageFormatter.format(format.toString(), argument);
                log("[ERROR] : " + tuple.getMessage());
            }
        }
    }

    @Override
    public void error(CharSequence format, CharSequence[] arguments) {
        if (format != null || (arguments != null && arguments.length > 0)) {
            if (isErrorEnabled()) {
                FormattingTuple tuple = MessageFormatter.arrayFormat(format.toString(), arguments);
                log("[ERROR] : " + tuple.getMessage());
            }
        }
    }

    protected abstract void log(CharSequence message);

    static {
        String level = Properties.getProperty(ManofletterProperties.LOGGING_KEY, "DEBUG");
        if (level.equalsIgnoreCase("INFO")) {
            LEVEL = Level.INFO;
        } else if (level.equalsIgnoreCase("WARN")) {
            LEVEL = Level.WARN;
        } else if (level.equalsIgnoreCase("ERROR")) {
            LEVEL = Level.ERROR;
        } else {
            LEVEL = Level.DEBUG;
        }
    }

}
