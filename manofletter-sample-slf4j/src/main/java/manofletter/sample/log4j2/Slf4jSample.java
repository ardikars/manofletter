package manofletter.sample.log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 *
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 */
public class Slf4jSample {

    public static Logger LOGGER = LoggerFactory.getLogger(Slf4jSample.class);

    public static void main(String[] args) {
        Marker marker = MarkerFactory.getMarker("manofletterMarker");
        LOGGER.info(marker, "Manofletter marker {}", "1");
        LOGGER.info(marker, "Manofletter marker throw some exception", new IllegalArgumentException("IAE"));
        LOGGER.info("Manofletter throw some exception", new IllegalArgumentException("WTF"));
        LOGGER.info("Manofletter {} info", "password");
        LOGGER.warn("Manofletter warn");
        LOGGER.debug("Manofletter debug");
        LOGGER.error("Manofletter error");
        LOGGER.trace("Manofletter trace");
    }

}
