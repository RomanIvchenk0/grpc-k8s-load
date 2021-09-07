package com.github.romahat.load;

import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AppConfig extends Configuration {
    @Valid
    @NotNull
    private Integer iterationCount;
    @Valid
    @NotNull
    private Integer iterationLength;
    @Valid
    @NotNull
    private String url;
    @Valid
    @NotNull
    private Integer port;

    public Integer getIterationCount() {
        return iterationCount;
    }

    public void setIterationCount(Integer iterationCount) {
        this.iterationCount = iterationCount;
    }

    public Integer getIterationLength() {
        return iterationLength;
    }

    public void setIterationLength(Integer iterationLength) {
        this.iterationLength = iterationLength;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
