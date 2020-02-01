package com.nouseen.weather.vo;

import com.nouseen.util.FiledDes;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @auth nouseen
 * @since 2020/1/31
 */
public class WeatherResult4Tianqi implements Serializable, Cloneable {


    /**
     * 日期
     */
    @FiledDes(cnName = "日期")
    private String dateValue;

    /**
     * 地点
     */
    @FiledDes(cnName = "地点")
    private String address;


    /**
     * 最高气温
     */
    @FiledDes(cnName = "最高气温")
    private String highT;


    /**
     * 最底气温
     */
    @FiledDes(cnName = "最低气温")
    private String lowT;


    /**
     * 天气
     */
    @FiledDes(cnName = "天气")
    private String weather;

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHighT() {
        return highT;
    }

    public void setHighT(String highT) {
        this.highT = highT;
    }

    public String getLowT() {
        return lowT;
    }

    public void setLowT(String lowT) {
        this.lowT = lowT;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
