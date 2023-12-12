package org.openifood.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.openifood.IFoodClient;
import org.openifood.modules.authentication.Authentications;

@ApplicationScoped
public class IFoodClientImpl implements IFoodClient {

    @Inject
    Authentications authentications;

    @Override
    public Authentications authentication() {
        return authentications;
    }
}
