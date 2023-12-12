package org.openifood;

import jakarta.inject.Inject;
import org.openifood.modules.authentication.Authentications;

/**
 * Accessor for ifood client methods.
 *
 * @author Leonardo Elias
 */
public interface IFoodClient {

    Authentications authentication();
}