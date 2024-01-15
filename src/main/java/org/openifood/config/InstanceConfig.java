package org.openifood.config;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InstanceConfig {

    public String getMarketplaceURI() {
        return "https://marketplace.ifood.com.br/";
    }

    public String getLegacyAppKey() {
        return "54z2laLEcZ0gzfERl27dEu1N";
    }
}
