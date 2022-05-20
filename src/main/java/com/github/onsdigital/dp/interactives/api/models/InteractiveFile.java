package com.github.onsdigital.dp.interactives.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InteractiveFile {
    
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String mimetype;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String size_in_bytes;

    public void setName(String x) {
        this.name = x;
    }
    public void setMimetype(String x) {
        this.mimetype = x;
    }
    public void setSize_in_bytes(String x) {
        this.size_in_bytes = x;
    }

    public String getName() {
        return this.name;
    }
    public String getMimetype() {
        return this.mimetype;
    }
    public String getSize_in_bytes() {
        return this.size_in_bytes;
    }
}
