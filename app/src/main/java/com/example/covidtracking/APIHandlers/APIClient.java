package com.example.covidtracking.APIHandlers;
import com.example.covidtracking.BuildConfig;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static Retrofit retrofit;
    public static APIClient apiClient;

    public static APIClient getInstance() {
        if (apiClient == null) {
            apiClient = new APIClient();
        }
        return apiClient;
    }

    public Retrofit getRetrofitClient(String BASE_URL) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);   // development build
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);    // production build
            interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);    // production build
        }
        client.addInterceptor(interceptor);

        client.readTimeout(60, TimeUnit.SECONDS);
        client.writeTimeout(60, TimeUnit.SECONDS);
        client.connectTimeout(60, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
