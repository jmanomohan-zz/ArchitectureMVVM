package com.jithin.core.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.jithin.core.database.AppDatabase;
import com.jithin.core.model.Articles;
import com.jithin.core.model.Sources;
import com.jithin.core.network.Connector;
import com.jithin.core.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jithin on 04/10/17.
 */

public class Repo implements RepositoryServices {

    String TAG = Repo.class.getSimpleName();

    @Override
    public LiveData<Sources> getSources() {
        final MutableLiveData<Sources> liveData = new MutableLiveData<>();
        Sources saved = AppDatabase.getDatabase().sourceDao().get(1);
        long updated_at = (saved != null ? saved.getUpdated_at() : 0);
        long current_time = System.currentTimeMillis();
        if (saved == null || (current_time - updated_at > Constants.CACHE_EXPIRY)) {

            Call<Sources> call = Connector.load().call().getSources();
            call.enqueue(new Callback<Sources>() {
                @Override
                public void onResponse(Call<Sources> call, Response<Sources> response) {
                    Sources sources = response.body();
                    if (sources != null) {
                        sources.setId(1);
                        sources.setUpdated_at(System.currentTimeMillis());
                        AppDatabase.getDatabase().sourceDao().add(sources);
                    }
                    liveData.setValue(sources);
                }

                @Override
                public void onFailure(Call<Sources> call, Throwable t) {
                    liveData.setValue(saved);
                }
            });
        } else {
            liveData.setValue(saved);
        }
        return liveData;
    }

    @Override
    public LiveData<Articles> getArticles(String source, String sort) {
        final MutableLiveData<Articles> liveData = new MutableLiveData<>();
        Articles saved = AppDatabase.getDatabase().articleDao().get(source);
        long updated_at = (saved != null ? saved.getUpdated_at() : 0);
        long current_time = System.currentTimeMillis();
        if (saved == null || current_time - updated_at > Constants.CACHE_EXPIRY) {
            Call<Articles> call = Connector.load().call().getArticles(source, sort);
            call.enqueue(new Callback<Articles>() {
                @Override
                public void onResponse(Call<Articles> call, Response<Articles> response) {
                    Articles articles = response.body();
                    if (articles != null) {
                        articles.setSource(source);
                        articles.setUpdated_at(System.currentTimeMillis());
                        AppDatabase.getDatabase().articleDao().add(articles);
                    }
                    liveData.setValue(articles);
                }

                @Override
                public void onFailure(Call<Articles> call, Throwable t) {
                    liveData.setValue(saved);
                }
            });
        } else {
            liveData.setValue(saved);
        }
        return liveData;
    }
}
