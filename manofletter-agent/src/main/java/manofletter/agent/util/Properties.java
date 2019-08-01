/**
 * Copyright 2017-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package manofletter.agent.util;

import manofletter.agent.ManofletterProperties;

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Property utils.
 *
 * @author <a href="mailto:contact@ardikars.com">Ardika Rommy Sanjaya</a>
 * @since 1.2.3
 * @see <a href="https://github.com/jxnet/java-common/blob/master/common-util/src/main/java/com/ardikars/common/util/Properties.java>Properties.java</a>
 */
public final class Properties {

    /**
     * Returns the value of the Java system property with the specified
     * {@code key}, while falling back to the specified default value if
     * the property access fails.
     *
     * @param key key.
     * @return returns the property value. null if there's no such property or if an access to the
     *         specified property is not allowed.
     * @since 1.2.3
     */
    public static String getProperty(final String key) {
        return getProperty(key, null);
    }

    /**
     * Returns the value of the Java system property with the specified
     * {@code key}, while falling back to the specified default value if
     * the property access fails.
     *
     * @param key key.
     * @param defaultValue default value.
     * @return the property value.
     *         {@code defaultValue} if there's no such property or if an access to the
     *         specified property is not allowed.
     * @since 1.2.3
     */
    public static String getProperty(final String key, String defaultValue) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Property key should be not null");
        }
        String value = null;
        try {
            if (System.getSecurityManager() == null) {
                value = System.getProperty(key);
            } else {
                value = AccessController.doPrivileged(new PrivilegedAction<String>() {
                    @Override
                    public String run() {
                        return System.getProperty(key);
                    }
                });
            }
        } catch (SecurityException e) {

        }
        if (value != null) {
            return value;
        }
        return defaultValue;
    }

    /**
     * Returns the value of the Java system property with the specified
     * {@code key}, while falling back to the specified default value if
     * the property access fails.
     *
     * @param key key.
     * @param defaultValue default value.
     * @return the property value.
     *         {@code defaultValue} if there's no such property or if an access to the
     *         specified property is not allowed.
     * @since 1.2.6
     */
    public static boolean getBoolean(final String key, boolean defaultValue) {
        String value = getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        value = value.trim().toLowerCase();
        if (value.isEmpty()) {
            return defaultValue;
        }

        if ("true".equals(value) || "yes".equals(value) || "1".equals(value)) {
            return true;
        }

        if ("false".equals(value) || "no".equals(value) || "0".equals(value)) {
            return false;
        }
        return defaultValue;
    }

    /**
     * Returns the value of the Java system property with the specified
     * {@code key}, while falling back to the specified default value if
     * the property access fails.
     *
     * @param key key.
     * @param defaultValue default value.
     * @return the property value.
     *         {@code defaultValue} if there's no such property or if an access to the
     *         specified property is not allowed.
     * @since 1.2.6
     */
    public static int getInt(String key, int defaultValue) {
        String value = getProperty(key);
        if (value == null) {
            return defaultValue;
        }

        value = value.trim();
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            // Ignore
        }
        return defaultValue;
    }

    /**
     * Returns the value of the Java system property with the specified
     * {@code key}, while falling back to the specified default value if
     * the property access fails.
     *
     * @param key key.
     * @param defaultValue default value.
     * @return the property value.
     *         {@code defaultValue} if there's no such property or if an access to the
     *         specified property is not allowed.
     * @since 1.2.6
     */
    public static long getLong(String key, long defaultValue) {
        String value = getProperty(key);
        if (value == null) {
            return defaultValue;
        }

        value = value.trim();
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            // Ignore
        }
        return defaultValue;
    }

}