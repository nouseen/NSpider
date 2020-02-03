package com.nouseen.weather.vo;

import com.nouseen.util.FiledDes;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @auth nouseen
 * @since 2020/1/31
 */
public class WeatherResult4nmcTemperatureForecast implements Serializable, Cloneable {

    /**
     * 天气时间
     */
    @FiledDes(cnName = "天气时间")
    private String realTime;

    /**
     * 地点
     */
    @FiledDes(cnName = "地点")
    private String address;

    /**
     * 日间天气
     */
    @FiledDes(cnName = "日间天气")
    private String dayImg;

    /**
     * 夜间天气
     */
    @FiledDes(cnName = "夜间天气")
    private String nightImg;


    /**
     * 低温
     */
    @FiledDes(cnName = "低温")
    private String minTemp;

    /**
     * 高温
     */
    @FiledDes(cnName = "高温")
    private String maxTemp;

    public String getRealTime() {
        return realTime;
    }

    public void setRealTime(String realTime) {
        this.realTime = realTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDayImg() {
        return dayImg;
    }

    public void setDayImg(String dayImg) {
        this.dayImg = dayImg;
    }

    public String getNightImg() {
        return nightImg;
    }

    public void setNightImg(String nightImg) {
        this.nightImg = nightImg;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
