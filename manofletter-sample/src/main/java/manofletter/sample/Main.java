package manofletter.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 */
public class Main {

    public static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
//        ITimed iTimed = new TestTimed();
//        iTimed.foo();

        logger.info("ASS");
        logger.warn("ASS password");
        logger.debug("ASS");
        logger.error("ASS");
//        logger.log(LogLevel.INFO, "D");
    }
}
