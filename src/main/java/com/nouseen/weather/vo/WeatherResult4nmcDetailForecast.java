package com.nouseen.weather.vo;

import com.nouseen.util.FiledDes;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @auth nouseen
 * @since 2020/1/31
 */
public class WeatherResult4nmcDetailForecast implements Serializable, Cloneable {


    /**
     * 日期
     */
    @FiledDes(cnName = "发布时间")
    private String publishDateValue;

    /**
     * 天气时间
     */
    @FiledDes(cnName = "天气时间")
    private String dateValue;

    /**
     * 时间
     */
    @FiledDes(cnName = "时间")
    private String timeValue;

    /**
     * 地点
     */
    @FiledDes(cnName = "地点")
    private String address;

    /**
     * 天气
     */
    @FiledDes(cnName = "天气")
    private String weather;


    /**
     * 温度
     */
    @FiledDes(cnName = "温度")
    private String temperature;

    /**
     * 降水
     */
    @FiledDes(cnName = "降水")
    private String precipitation;


    /**
     * 风速
     */
    @FiledDes(cnName = "风速")
    private String windSpeed;


    /**
     * 风向
     */
    @FiledDes(cnName = "风向")
    private String windDirection;


    /**
     * 气压
     */
    @FiledDes(cnName = "气压")
    private String atmosphericPressure;


    /**
     * 相对湿度
     */
    @FiledDes(cnName = "相对湿度")
    private String RH;

    /**
     * 云量
     */
    @FiledDes(cnName = "云量")
    private String cloudAmount;

    /**
     * 能见度
     */
    @FiledDes(cnName = "能见度")
    private String visibility ;

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public String getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(String timeValue) {
        this.timeValue = timeValue;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getAtmosphericPressure() {
        return atmosphericPressure;
    }

    public void setAtmosphericPressure(String atmosphericPressure) {
        this.atmosphericPressure = atmosphericPressure;
    }

    public String getRH() {
        return RH;
    }

    public void setRH(String RH) {
        this.RH = RH;
    }

    public String getCloudAmount() {
        return cloudAmount;
    }

    public void setCloudAmount(String cloudAmount) {
        this.cloudAmount = cloudAmount;
    }

    public String getPublishDateValue() {
        return publishDateValue;
    }

    public void setPublishDateValue(String publishDateValue) {
        this.publishDateValue = publishDateValue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
