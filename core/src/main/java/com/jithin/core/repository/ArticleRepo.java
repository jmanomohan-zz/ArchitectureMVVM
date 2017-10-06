package com.jithin.core.repository;

import com.jithin.core.model.Articles;

/**
 * Created by Jithin on 04/10/17.
 */

public class ArticleRepo {
    private Articles articles = null;
    private Throwable error = null;

    public Articles getArticles() {
        return articles;
    }

    public void setArticles(Articles articles) {
        this.articles = articles;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
