package com.jithin.core.model;

import java.util.List;

/**
 * Created by Jithin on 06/10/17.
 */

public class Sources {
    private String status;
    private List<Source> sources;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }
}
