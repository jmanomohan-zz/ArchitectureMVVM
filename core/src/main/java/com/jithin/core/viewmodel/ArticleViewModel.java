package com.jithin.core.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.jithin.core.repository.ArticleRepo;
import com.jithin.core.repository.Repo;

/**
 * Created by Jithin on 04/10/17.
 */

public class ArticleViewModel extends ViewModel {
    private MediatorLiveData<ArticleRepo> mApiResponse;

    // No argument constructor
    public ArticleViewModel() {
        mApiResponse = new MediatorLiveData<>();
    }

    @NonNull
    public LiveData<ArticleRepo> getApiResponse() {
        return mApiResponse;
    }

    public LiveData<ArticleRepo> loadArticles(@NonNull String source, @NonNull String sort) {
        mApiResponse.addSource(
                new Repo().getArticles(source, sort),
                apiResponse -> mApiResponse.setValue(apiResponse)
        );
        return mApiResponse;
    }
}
