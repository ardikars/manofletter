package manofletter.sample.log4j2;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

/**
 *
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 */
public class Log4j2Sample {

    public static Logger LOGGER = LogManager.getLogger(Log4j2Sample.class);

    public static void main(String[] args) {
        Marker marker = MarkerManager.getMarker("manofletterMarker");
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
