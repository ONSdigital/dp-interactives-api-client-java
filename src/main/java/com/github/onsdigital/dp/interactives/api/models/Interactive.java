package com.github.onsdigital.dp.interactives.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Interactive {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String id;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private boolean published;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private InteractiveMetadata metadata;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private InteractiveArchive archive;

    public void setMetadata(InteractiveMetadata x) {
        this.metadata = x;
    }
    public void setArchive(InteractiveArchive x) {
        this.archive = x;
    }
    public void setId(String x) {
        this.id = x;
    }
    public void setPublished(boolean x) {
        this.published = x;
    }

    public InteractiveMetadata getMetadata() {
        return this.metadata;
    }
    public InteractiveArchive getArchive() {
        return this.archive;
    }
    public String getId() {
        return this.id;
    }
    public boolean getPublished() {
        return this.published;
    }
}
