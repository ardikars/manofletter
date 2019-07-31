package manofletter.sample.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 */
public class Slf4jSample {

    public static Logger LOGGER = LoggerFactory.getLogger(Slf4jSample.class);

    public static void main(String[] args) {
        LOGGER.info("Manofletter {} info", "password");
        LOGGER.warn("Manofletter warn");
        LOGGER.debug("Manofletter debug");
        LOGGER.error("Manofletter error");
        LOGGER.trace("Manofletter trace");
    }

}
