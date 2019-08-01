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
    public void debug(CharSequence message) {
        if (message != null) {
            if (LEVEL == LEVEL.DEBUG) {
                log("[DEBUG] : " + message.toString());
            }
        }
    }

    @Override
    public void debug(CharSequence format, CharSequence argument) {
        if (format != null || argument != null) {
            if (LEVEL == LEVEL.DEBUG) {
                FormattingTuple tuple = MessageFormatter.format(format.toString(), argument);
                log("[DEBUG] : " + tuple.getMessage());
            }
        }
    }

    @Override
    public void debug(CharSequence format, CharSequence[] arguments) {
        if (format != null || (arguments != null && arguments.length > 0)) {
            if (LEVEL == LEVEL.DEBUG) {
                FormattingTuple tuple = MessageFormatter.arrayFormat(format.toString(), arguments);
                log("[DEBUG] : " + tuple.getMessage());
            }
        }
    }

    @Override
    public void info(CharSequence message) {
        if (message != null) {
            if (LEVEL == Level.INFO || LEVEL == Level.DEBUG) {
                log("[INFO] : " + message.toString());
            }
        }
    }

    @Override
    public void info(CharSequence format, CharSequence argument) {
        if (format != null || argument != null) {
            if (LEVEL == Level.INFO || LEVEL == Level.DEBUG) {
                FormattingTuple tuple = MessageFormatter.format(format.toString(), argument);
                log("[INFO] : " + tuple.getMessage());
            }
        }
    }

    @Override
    public void info(CharSequence format, CharSequence[] arguments) {
        if (format != null || (arguments != null && arguments.length > 0)) {
            if (LEVEL == Level.INFO || LEVEL == Level.DEBUG) {
                FormattingTuple tuple = MessageFormatter.arrayFormat(format.toString(), arguments);
                log("[INFO] : " + tuple.getMessage());
            }
        }
    }

    @Override
    public void warn(CharSequence message) {
        if (message != null) {
            if (LEVEL == Level.DEBUG || LEVEL == Level.INFO || LEVEL == Level.WARN) {
                log("[WARN] : " + message.toString());
            }
        }
    }

    @Override
    public void warn(CharSequence format, CharSequence argument) {
        if (format != null || argument != null) {
            if (LEVEL == Level.DEBUG || LEVEL == Level.INFO || LEVEL == Level.WARN) {
                FormattingTuple tuple = MessageFormatter.format(format.toString(), argument);
                log("[WARN] : " + tuple.getMessage());
            }
        }
    }

    @Override
    public void warn(CharSequence format, CharSequence[] arguments) {
        if (format != null || (arguments != null && arguments.length > 0)) {
            if (LEVEL == Level.DEBUG || LEVEL == Level.INFO || LEVEL == Level.WARN) {
                FormattingTuple tuple = MessageFormatter.arrayFormat(format.toString(), arguments);
                log("[WARN] : " + tuple.getMessage());
            }
        }
    }

    @Override
    public void error(CharSequence message) {
        if (message != null) {
            if (LEVEL == Level.ERROR || LEVEL == Level.DEBUG || LEVEL == Level.INFO || LEVEL == Level.WARN) {
                log("[ERROR] : " + message.toString());
            }
        }
    }

    @Override
    public void error(CharSequence format, CharSequence argument) {
        if (format != null || argument != null) {
            if (LEVEL == Level.ERROR || LEVEL == Level.DEBUG || LEVEL == Level.INFO || LEVEL == Level.WARN) {
                FormattingTuple tuple = MessageFormatter.format(format.toString(), argument);
                log("[ERROR] : " + tuple.getMessage());
            }
        }
    }

    @Override
    public void error(CharSequence format, CharSequence[] arguments) {
        if (format != null || (arguments != null && arguments.length > 0)) {
            if (LEVEL == Level.ERROR || LEVEL == Level.DEBUG || LEVEL == Level.INFO || LEVEL == Level.WARN) {
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
