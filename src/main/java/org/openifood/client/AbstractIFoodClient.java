package org.openifood.client;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import jakarta.inject.Inject;
import lombok.*;
import org.openifood.config.InstanceConfig;
import org.openifood.dto.authentication.AuthContext;
import org.openifood.dto.authentication.response.BusinessErrorResponse;
import org.openifood.exception.IFoodBusinessException;
import org.openifood.exception.IFoodSerializationException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractIFoodClient {

    @Inject
    InstanceConfig config;

    private static final OkHttpClient httpClient = new OkHttpClient();

    protected static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    public <T> T evaluate(@NonNull Request request, @NonNull Type responseBodyClass, AuthContext auth) {
        val requestBuilder = request.newBuilder();

        if (Objects.nonNull(auth)) {
            requestBuilder.addHeader("Authorization", "Bearer " + auth.getAccessToken());
        }

        request = requestBuilder.build();

        val call = httpClient.newCall(request);

        try {
            val response = call.execute();

            if (response.isSuccessful()) {
                try (val body = response.body()) {
                    return gson.fromJson(body.string(), responseBodyClass);
                }
            }

            try (val body = response.body()) {
                throw new IFoodBusinessException(gson.fromJson(body.string(), BusinessErrorResponse.class));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JsonIOException | JsonSyntaxException e) {
            throw new IFoodSerializationException(e);
        }
    }

    public <T> T evaluate(@NonNull Request request, @NonNull Type responseBodyClass) {
        return evaluate(request, responseBodyClass, null);
    }

    public <T> List<T> evaluateList(Request request, Type responseBodyClass) {
        return evaluate(request, getListType(responseBodyClass));
    }

    private Type getListType(Type clazz) {
        return TypeToken.getParameterized(List.class, clazz).getType();
    }

    protected <T> RequestBody body(T body) {
        return RequestBody.create(MediaType.parse("application/json"), gson.toJson(body));
    }

    @SneakyThrows
    protected String resolve(String relativePath) {
        return new URI(config.getMarketplaceURI()).resolve(relativePath).toString();
    }

    protected static String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
