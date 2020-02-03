package com.nouseen.weather.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class NmcCity {

    private String url;
    private String code;
    private String city;
    private String province;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
