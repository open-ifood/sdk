package org.openifood.client;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import jakarta.inject.Inject;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.openifood.config.InstanceConfig;
import org.openifood.dto.authentication.response.BusinessErrorResponse;
import org.openifood.exception.IFoodBusinessException;
import org.openifood.exception.IFoodSerializationException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractIFoodClient {

    @Inject
    InstanceConfig config;

    private static final OkHttpClient httpClient = new OkHttpClient();

    protected static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    public <T> T evaluate(Request request, Type responseBodyClass) {

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
}
