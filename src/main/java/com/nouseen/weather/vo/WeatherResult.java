package com.nouseen.weather.vo;

import com.nouseen.util.FiledDes;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @auth nouseen
 * @since 2020/1/31
 */
public class WeatherResult implements Serializable, Cloneable {


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
     * 白天天气
     */
    @FiledDes(cnName = "白天天气")
    private String dailyWeather;


    /**
     * 夜间天气
     */
    @FiledDes(cnName = "夜间天气")
    private String nightWeather;


    /**
     * 风向
     */
    @FiledDes(cnName = "风向")
    private String windDirection;

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
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

    public String getDailyWeather() {
        return dailyWeather;
    }

    public void setDailyWeather(String dailyWeather) {
        this.dailyWeather = dailyWeather;
    }

    public String getNightWeather() {
        return nightWeather;
    }

    public void setNightWeather(String nightWeather) {
        this.nightWeather = nightWeather;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
