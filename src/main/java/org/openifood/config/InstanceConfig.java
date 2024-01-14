package org.openifood.config;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InstanceConfig {

    public String getMarketplaceURI() {
        return "https://marketplace.ifood.com.br/";
    }
}
