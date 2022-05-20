package com.github.onsdigital.dp.interactives.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InteractiveArchive {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String size;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private InteractiveFile[] files;

    public void setName(String x) {
        this.name = x;
    }
    public void setFiles(InteractiveFile[] x) {
        this.files = x;
    }
    public void setSize(String x) {
        this.size = x;
    }

    public String getName() {
        return this.name;
    }
    public InteractiveFile[] getFiles() {
        return this.files;
    }
    public String getSize() {
        return this.size;
    }
}
