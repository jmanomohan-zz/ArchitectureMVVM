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
    public LiveData<ArticleRepo> loadArticles(@NonNull String source, @NonNull String sort) {
        MediatorLiveData<ArticleRepo> mApiResponse = new MediatorLiveData<>();
        mApiResponse.addSource(
                new Repo().getArticles(source, sort),
                apiResponse -> mApiResponse.postValue(apiResponse)
        );
        return mApiResponse;
    }
}
