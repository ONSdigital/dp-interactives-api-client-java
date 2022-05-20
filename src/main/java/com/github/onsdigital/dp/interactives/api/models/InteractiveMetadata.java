package com.github.onsdigital.dp.interactives.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InteractiveMetadata {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String title;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String label;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String internal_id;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String collection_id;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String slug;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String resource_id;

    public void setTitle(String x) {
        this.title = x;
    }
    public void setLabel(String x) {
        this.label = x;
    }
    public void setInternal_id(String x) {
        this.internal_id = x;
    }
    public void setCollection_id(String x) {
        this.collection_id = x;
    }
    public void setSlug(String x) {
        this.slug = x;
    }
    public void setResource_id(String x) {
        this.resource_id = x;
    }

    public String getTitle() {
        return this.title;
    }
    public String getLabel() {
        return this.label;
    }
    public String getInternal_id() {
        return this.internal_id;
    }
    public String getCollection_id() {
        return this.collection_id;
    }
    public String getSlug() {
        return this.slug;
    }
    public String getResource_id() {
        return this.resource_id;
    }
}
