package com.jithin.core.repository;

import com.jithin.core.model.Sources;

/**
 * Created by Jithin on 04/10/17.
 */

public class SourceRepo {
    private Sources sources = null;
    private Throwable error = null;

    public Sources getSources() {
        return sources;
    }

    public void setSources(Sources sources) {
        this.sources = sources;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
