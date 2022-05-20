package com.github.onsdigital.dp.interactives.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PatchRequest {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String attribute = "Publish";

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Interactive interactive;

    public void setAttribute(String x) {
        this.attribute = x;
    }
    public String getAttribute() {
        return this.attribute;
    }

    public void setInteractive(Interactive ix) {
        this.interactive = ix;
    }
    public Interactive getInteractive() {
        return this.interactive;
    }
}
