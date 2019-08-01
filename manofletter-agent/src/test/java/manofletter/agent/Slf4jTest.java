package manofletter.agent;

import net.bytebuddy.agent.ByteBuddyAgent;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class Slf4jTest {

    private static Logger LOGGER;

    @Before
    public void premain() throws ClassNotFoundException {
        System.setProperty("manofletter.loggers", Logger.class.getName());
        System.setProperty("manofletter.methods", "debug,warn"); // add info if you want to filter 'password' regex on info method
        System.setProperty("manofletter.filter", "password");
        System.setProperty("manofletter.logging.level", "INFO");
        ManofletterAgent.premain(null, ByteBuddyAgent.install());
        LOGGER = LoggerFactory.getLogger(Slf4jTest.class);
    }

    @Test
    public void slf4jTest() {
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
