package com.jithin.core.repository;

import android.arch.lifecycle.LiveData;

import com.jithin.core.model.Articles;
import com.jithin.core.model.Sources;

/**
 * Created by Jithin on 04/10/17.
 */

public interface RepositoryServices {
    LiveData<Sources> getSources();
    LiveData<Articles> getArticles(String source, String sort);
}