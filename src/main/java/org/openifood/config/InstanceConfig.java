package org.openifood.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InstanceConfig {

    private static volatile InstanceConfig instanceConfig;

    public String getMarketplaceURI() {
        return "https://marketplace.ifood.com.br/";
    }

    public String getLegacyAppKey() {
        return "54z2laLEcZ0gzfERl27dEu1N";
    }

    public String getRandomDeviceUUID() {
        return UUID.randomUUID().toString();
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
