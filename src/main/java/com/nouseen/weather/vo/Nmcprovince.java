package com.nouseen.weather.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Nmcprovince implements Serializable {

    private String url;
    private String code;
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
