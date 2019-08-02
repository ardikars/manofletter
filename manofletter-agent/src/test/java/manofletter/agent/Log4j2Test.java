package manofletter.agent;

import net.bytebuddy.agent.ByteBuddyAgent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.junit.Before;
import org.junit.Test;

public class Log4j2Test {

    private static Logger LOGGER;

    @Before
    public void premain() throws ClassNotFoundException {
        System.setProperty("manofletter.loggers", Logger.class.getName());
        System.setProperty("manofletter.methods", "debug,warn,error"); // add info if you want to enable 'password' regex filter on info method
        System.setProperty("manofletter.filter", "password");
        System.setProperty("manofletter.logging.level", "info");
        ManofletterAgent.premain(null, ByteBuddyAgent.install());
        LOGGER = LogManager.getLogger(Log4j2Test.class);
    }

    @Test
    public void log4j2Test() {
        Marker marker = MarkerManager.getMarker("manofletterMarker");
        LOGGER.info(marker, "Manofletter marker password {}", "1");
        LOGGER.info(marker, "Manofletter marker throw some exception", new IllegalArgumentException("IAE"));
        LOGGER.info("Manofletter throw some exception", new IllegalArgumentException("WTF"));
        LOGGER.info("Manofletter {} info", "password");
        LOGGER.warn("Manofletter warn");
        LOGGER.debug("Manofletter debug");
        LOGGER.error("Manofletter error");
        LOGGER.trace("Manofletter trace");
    }

}
