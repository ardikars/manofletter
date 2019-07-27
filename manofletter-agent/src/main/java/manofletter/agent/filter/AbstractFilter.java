package manofletter.agent.filter;

import com.google.re2j.Matcher;
import com.google.re2j.Pattern;
import manofletter.agent.ManofletterProperties;
import manofletter.agent.util.Properties;

/**
 *
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 */
abstract class AbstractFilter implements Filter {

    protected static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

    private static final Pattern REGEX;

    private Class type;

    public AbstractFilter(String clazz) throws ClassNotFoundException {
        if (clazz != null) {
            this.type = Class.forName(clazz);
        } else {
            this.type = Void.class;
        }
    }

    @Override
    public Class type() {
        return type;
    }

    protected Matcher matcher(String message) {
        return REGEX.matcher(message);
    }

    static {
        String regex = Properties.getProperty(ManofletterProperties.FILTER, "");
        Pattern pattern = Pattern.compile(regex);
        REGEX = pattern;
    }

}
