package com.github.onsdigital.dp.interactives.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InteractiveMetadata {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String title;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String label;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String internalId;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("collection_id")
    private String collectionId;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String slug;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("resource_id")
    private String resourceId;

    public void setTitle(String x) {
        this.title = x;
    }
    public void setLabel(String x) {
        this.label = x;
    }
    public void setInternalId(String x) {
        this.internalId = x;
    }
    public void setCollectionId(String x) {
        this.collectionId = x;
    }
    public void setSlug(String x) {
        this.slug = x;
    }
    public void setResourceId(String x) {
        this.resourceId = x;
    }

    public String getTitle() {
        return this.title;
    }
    public String getLabel() {
        return this.label;
    }
    public String getInternalId() {
        return this.internalId;
    }
    public String getCollectionId() {
        return this.collectionId;
    }
    public String getSlug() {
        return this.slug;
    }
    public String getResourceId() {
        return this.resourceId;
    }
}
