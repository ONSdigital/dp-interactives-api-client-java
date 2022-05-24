package com.github.onsdigital.dp.interactives.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InteractiveFile {
    
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String mimetype;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("size_in_bytes")
    private String sizeInBytes;

    public void setName(String x) {
        this.name = x;
    }
    public void setMimetype(String x) {
        this.mimetype = x;
    }
    public void setSizeInBytes(String x) {
        this.sizeInBytes = x;
    }

    public String getName() {
        return this.name;
    }
    public String getMimetype() {
        return this.mimetype;
    }
    public String getSizeInBytes() {
        return this.sizeInBytes;
    }
}
