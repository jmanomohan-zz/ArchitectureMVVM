package com.jithin.core.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Jithin on 03/10/17.
 */

public class Connector {
    private static Connector retrofitOBJ = null;
    private Retrofit retrofit = null;
    private CallService service = null;

    public static Connector load() {
        if (retrofitOBJ == null)
            retrofitOBJ = new Connector();
        return retrofitOBJ;
    }

    public Connector() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .client(getOkHttpClient())
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        service = retrofit.create(CallService.class);
    }

    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(2, TimeUnit.MINUTES)
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public CallService call() {
        return service;
    }
}
