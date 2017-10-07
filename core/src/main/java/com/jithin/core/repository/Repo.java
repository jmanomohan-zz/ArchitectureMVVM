package com.jithin.core.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.jithin.core.model.Articles;
import com.jithin.core.model.Sources;
import com.jithin.core.network.Connector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jithin on 04/10/17.
 */

public class Repo implements RepositoryServices {

    String TAG = Repo.class.getSimpleName();

    @Override
    public LiveData<SourceRepo> getSources() {
        final MutableLiveData<SourceRepo> liveData = new MutableLiveData<>();
        Call<Sources> call = Connector.load().call().getSources();
        call.enqueue(new Callback<Sources>() {
            @Override
            public void onResponse(Call<Sources> call, Response<Sources> response) {
                SourceRepo responses = new SourceRepo();
                responses.setSources(response.body());
                liveData.setValue(responses);
            }

            @Override
            public void onFailure(Call<Sources> call, Throwable t) {
                SourceRepo responses = new SourceRepo();
                responses.setError(t);
                liveData.setValue(responses);
            }
        });
        return liveData;
    }

    @Override
    public LiveData<ArticleRepo> getArticles(String source, String sort) {
        final MutableLiveData<ArticleRepo> liveData = new MutableLiveData<>();
        Call<Articles> call = Connector.load().call().getArticles(source, sort);
        call.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                Log.d(TAG, "onResponse: " + call.request().url());
                ArticleRepo responses = new ArticleRepo();
                responses.setArticles(response.body());
                liveData.setValue(responses);
                Log.d(TAG, "onResponse: " + (response.body() == null || response.body().getArticles() == null ? 0 : response.body().getArticles().size()));
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                Log.d(TAG, "onFailure: " + call.request().url());
                ArticleRepo responses = new ArticleRepo();
                responses.setError(t);
                liveData.setValue(responses);
            }
        });
        return liveData;
    }
}
