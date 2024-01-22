package org.openifood.client.impl;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Request;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.openifood.client.MarketplaceClient;
import org.openifood.config.InstanceConfig;
import org.openifood.dto.authentication.response.UserSession;
import org.openifood.dto.marketplace.request.GetMerchantCatalogParams;
import org.openifood.dto.marketplace.request.HomeRequest;
import org.openifood.dto.marketplace.request.HomeRequestParams;
import org.openifood.dto.marketplace.response.HomeResponse;
import org.openifood.dto.marketplace.response.MerchantCatalogResponse;

@NoArgsConstructor(access = AccessLevel.NONE)
public class MarketplaceClientImpl extends AbstractIFoodClient implements MarketplaceClient {

    private MarketplaceClientImpl(@NonNull InstanceConfig config) {
        super(config);
    }

    public static MarketplaceClient getInstance(@NonNull InstanceConfig config) {
        return new MarketplaceClientImpl(config);
    }

    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
            .create();

    @Override
    public @NonNull HomeResponse getHome(@NonNull UserSession session, @NonNull HomeRequest request,
                                         @NonNull HomeRequestParams params) {
        return evaluate(
                new Request.Builder()
                        .url(resolve("v2/home", params))
                        .post(body(request, gson))
                        .build(),
                HomeResponse.class,
                session,
                FieldNamingPolicy.IDENTITY
        );
    }

    @Override
    public @NonNull MerchantCatalogResponse getMerchantCatalog(@NonNull UserSession session,
                                                               @NonNull GetMerchantCatalogParams params) {
        return evaluate(
                new Request.Builder()
                        .url(resolve("v1/merchants/" + params.getMerchantId() + "/catalog", params))
                        .build(),
                MerchantCatalogResponse.class,
                session,
                FieldNamingPolicy.IDENTITY
        );
    }
}
