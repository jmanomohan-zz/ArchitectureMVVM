package com.jithin.core.repository;

import android.arch.lifecycle.LiveData;

/**
 * Created by Jithin on 04/10/17.
 */

public interface RepositoryServices {
    LiveData<SourceRepo> getSources();
    LiveData<ArticleRepo> getArticles(String source, String sort);
}