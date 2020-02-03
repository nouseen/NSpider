package com.nouseen.weather.vo;

import com.nouseen.util.FiledDes;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @auth nouseen
 * @since 2020/1/31
 */
public class WeatherResult4nmcMonthTemperatureRecord implements Serializable, Cloneable {


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
     * 地点
     */
    @FiledDes(cnName = "地点")
    private String address;

    /**
     * 降水量
     */
    @FiledDes(cnName = "降水量")
    private String precipitation;


    /**
     * 低温
     */
    @FiledDes(cnName = "低温")
    private String lowTemperature;

    /**
     * 高温
     */
    @FiledDes(cnName = "高温")
    private String highTemperature;

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getPublishDateValue() {
        return publishDateValue;
    }

    public void setPublishDateValue(String publishDateValue) {
        this.publishDateValue = publishDateValue;
    }

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


    public String getLowTemperature() {
        return lowTemperature;
    }

    public void setLowTemperature(String lowTemperature) {
        this.lowTemperature = lowTemperature;
    }

    public String getHighTemperature() {
        return highTemperature;
    }

    public void setHighTemperature(String highTemperature) {
        this.highTemperature = highTemperature;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
