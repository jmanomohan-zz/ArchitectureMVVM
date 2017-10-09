package com.jithin.core.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import com.jithin.core.model.Sources;
import com.jithin.core.repository.Repo;

/**
 * Created by Jithin on 04/10/17.
 */

public class SourcesViewModel extends ViewModel {
    public LiveData<Sources> loadSource() {
        MediatorLiveData<Sources> mApiResponse = new MediatorLiveData<>();
        mApiResponse.addSource(
                new Repo().getSources(),
                apiResponse -> mApiResponse.setValue(apiResponse)
        );
        return mApiResponse;
    }
}
