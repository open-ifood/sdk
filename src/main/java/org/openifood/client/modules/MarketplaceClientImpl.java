package org.openifood.client.modules;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Request;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.NonNull;
import org.openifood.client.AbstractIFoodClient;
import org.openifood.client.interfaces.MarketplaceClient;
import org.openifood.dto.authentication.AuthContext;
import org.openifood.dto.marketplace.request.HomeRequest;
import org.openifood.dto.marketplace.request.HomeRequestParams;
import org.openifood.dto.marketplace.response.HomeResponse;

@ApplicationScoped
public class MarketplaceClientImpl extends AbstractIFoodClient implements MarketplaceClient {

    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
            .create();

    @Override
    public @NonNull HomeResponse getHome(@NonNull AuthContext auth, @NonNull HomeRequest request,
                                         @NonNull HomeRequestParams params) {
        return evaluate(
                new Request.Builder()
                        .url(resolve("v2/home", params))
                        .post(body(request, gson))
                        .build(),
                HomeResponse.class,
                auth
        );
    }
}
