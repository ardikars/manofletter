package manofletter.agent.util;

import manofletter.agent.ManofletterProperties;

/**
 *
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 */
public final class DebugHelper {

    public static void log(String message) {
        if (ManofletterProperties.DEBUG) {
            System.out.println("MANOFLETTER DEBUG : " + message);
        }
    }

}
