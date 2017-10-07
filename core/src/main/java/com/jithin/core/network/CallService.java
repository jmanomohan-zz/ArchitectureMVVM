package com.jithin.core.network;

import com.jithin.core.model.Articles;
import com.jithin.core.model.Sources;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Jithin on 03/10/17.
 */

public interface CallService {
    @GET("v1/sources?language=en")
    Call<Sources> getSources();

    @GET("v1/articles?apiKey=6ea9d1bcb5c44e8cb82fcd1952b3286f")
    Call<Articles> getArticles(@Query("source") String source, @Query("sortBy") String sort);
}
