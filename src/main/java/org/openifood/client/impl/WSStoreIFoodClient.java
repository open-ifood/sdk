package org.openifood.client.impl;


import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.openifood.config.InstanceConfig;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class WSStoreIFoodClient extends MarketplaceIFoodClient {

    public WSStoreIFoodClient(@NonNull InstanceConfig config) {
        super(config);
    }

    @Override
    protected String getApiURI() {
        return config.getWebServiceStoreURI();
    }
}
