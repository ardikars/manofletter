package manofletter.agent;

import manofletter.agent.util.Properties;

/**
 *
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 */
public interface ManofletterProperties {

    String SEPARATOR = ",";
    String LOGGER_TYPE = "Logger";
    String SL4J_LOGGER = "org.slf4j.Logger";
    String LOG4J2_LOGGER = "org.apache.logging.log4j.Logger";

    String LOGGERS_KEY = "manofletter.loggers";
    String METHODS_KEY = "manofletter.methods";
    String FILTER_KEY = "manofletter.filter";
    String LOGGING_KEY = "manofletter.logging.level";

    boolean DEFAULT = Properties.getProperty(LOGGERS_KEY, null) == null;

}
