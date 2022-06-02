package com.github.onsdigital.dp.interactives.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InteractiveHTMLFile {
    
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("uri")
    private String URI;

    public void setName(String x) {
        this.name = x;
    }
    public void setURI(String x) {
        this.URI = x;
    }

    public String getName() {
        return this.name;
    }
    public String getURI() {
        return this.URI;
    }
}
