package org.openifood.client.impl;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import lombok.*;
import org.openifood.config.InstanceConfig;
import org.openifood.dto.authentication.response.BusinessErrorResponse;
import org.openifood.dto.authentication.response.UserSession;
import org.openifood.exception.IFoodBusinessException;
import org.openifood.exception.IFoodSerializationException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public abstract class AbstractIFoodClient {

    protected final @NonNull InstanceConfig config;

    private static final OkHttpClient httpClient = new OkHttpClient();

    public <T> T evaluate(@NonNull Request request, @NonNull Type responseBodyClass, UserSession session) {
        return evaluate(request, responseBodyClass, session, FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
    }

    public <T> T evaluate(@NonNull Request request, @NonNull Type responseBodyClass, UserSession session,
                          FieldNamingPolicy fieldNamingPolicyResponse) {
        val requestBuilder = request.newBuilder();
        val gson = new GsonBuilder()
                .setFieldNamingPolicy(fieldNamingPolicyResponse)
                .create();

        if (Objects.nonNull(session)) {
            requestBuilder.addHeader("Authorization", "Bearer " + session.getAccessToken());
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

    public <T> List<T> evaluateList(Request request, Type responseBodyClass, UserSession session) {
        return evaluate(request, getListType(responseBodyClass), session);
    }

    private Type getListType(Type clazz) {
        return TypeToken.getParameterized(List.class, clazz).getType();
    }

    protected <T> RequestBody body(T body) {
        return body(body, new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create());
    }

    protected <T> RequestBody body(T body, Gson gsonInstance) {
        return RequestBody.create(MediaType.parse("application/json"), gsonInstance.toJson(body));
    }

    @SneakyThrows
    protected String resolve(String relativePath) {
        return new URI(config.getMarketplaceURI()).resolve(relativePath).toString();
    }

    protected <T> String resolve(String relativePath, T params) {
        return resolve(relativePath) + "?" + createQueryParam(params);
    }

    protected static String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    private <T> String createQueryParam(T params) {
        Map<String, Object> fieldMap = new HashMap<>();
        for (Field field : params.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {

                val value = field.get(params);

                if (value != null) {
                    fieldMap.put(field.getName(), value);
                }

            } catch (IllegalAccessException e) {
                throw new IllegalStateException("not possible build query param from object", e);
            }
        }

        StringBuilder queryString = new StringBuilder();
        for (Map.Entry<String, Object> entry : fieldMap.entrySet()) {
            if (!queryString.isEmpty()) {
                queryString.append("&");
            }
            queryString.append(entry.getKey()).append("=").append(entry.getValue());
        }

        return queryString.toString();
    }
}
