package com.jithin.core.network;

import com.jithin.core.model.Articles;
import com.jithin.core.model.Sources;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Jithin on 03/10/17.
 */

public interface CallService {
    @GET("v1/sources?language=en")
    Call<Sources> getSources();

    @GET("v1/articles?source={source}&sortBy={sort}&apiKey=6ea9d1bcb5c44e8cb82fcd1952b3286f")
    Call<Articles> getArticles(@Path("slug") String source, @Path("slug") String sort);
}
