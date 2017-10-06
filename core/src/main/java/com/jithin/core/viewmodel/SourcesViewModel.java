package com.jithin.core.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.jithin.core.repository.Repo;
import com.jithin.core.repository.SourceRepo;

/**
 * Created by Jithin on 04/10/17.
 */

public class SourcesViewModel extends ViewModel {
    private MediatorLiveData<SourceRepo> mApiResponse;

    // No argument constructor
    public SourcesViewModel() {
        mApiResponse = new MediatorLiveData<>();
    }

    @NonNull
    public LiveData<SourceRepo> getApiResponse() {
        return mApiResponse;
    }

    public LiveData<SourceRepo> loadSource() {
        mApiResponse.addSource(
                new Repo().getSources(),
                apiResponse -> mApiResponse.setValue(apiResponse)
        );
        return mApiResponse;
    }
}
