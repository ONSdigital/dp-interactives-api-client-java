package com.github.onsdigital.dp.interactives.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InteractiveFilter {
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private InteractiveMetadata metadata;

    public void setMetadata(InteractiveMetadata x) {
        this.metadata = x;
    }
    public InteractiveMetadata getMetadata() {
        return this.metadata;
    }
}
