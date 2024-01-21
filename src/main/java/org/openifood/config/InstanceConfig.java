package org.openifood.config;

import com.sun.tools.javac.Main;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InstanceConfig {

    private static volatile InstanceConfig instanceConfig;

    public String getMarketplaceURI() {
        return "https://marketplace.ifood.com.br/";
    }

    public String getLegacyAppKey() {
        return "54z2laLEcZ0gzfERl27dEu1N";
    }

    public static InstanceConfig config() {
        synchronized (InstanceConfig.class) {
            if (instanceConfig == null) {
                instanceConfig = new InstanceConfig();
            }
        }

        return instanceConfig;
    }

}
