package com.nouseen.weather.vo;

import com.nouseen.util.FiledDes;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @auth nouseen
 * @since 2020/1/31
 */
public class WeatherResult4nmcDetail24hReal implements Serializable, Cloneable {


    /**
     * 天气时间
     */
    @FiledDes(cnName = "天气时间")
    private String time;

    /**
     * 地点
     */
    @FiledDes(cnName = "地点")
    private String address;

    /**
     * 温度
     */
    @FiledDes(cnName = "温度")
    private String temperature;

    /**
     * 降水
     */
    @FiledDes(cnName = "降水")
    private String rain1h;


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
    private String pressure;


    /**
     * 相对湿度
     */
    @FiledDes(cnName = "相对湿度")
    private String humidity;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getRain1h() {
        return rain1h;
    }

    public void setRain1h(String rain1h) {
        this.rain1h = rain1h;
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

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
