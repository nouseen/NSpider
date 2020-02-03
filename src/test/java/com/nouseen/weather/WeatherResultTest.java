package com.nouseen.weather;

import com.nouseen.weather.properties.SpiderProperties;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.lang.reflect.Field;

public class WeatherResultTest {



    @Test
    public void testSetWeatherResult() {

        Class<WeatherResult> clazz = WeatherResult.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        String className = clazz.getSimpleName();
        String beanName = StringUtils.uncapitalize(className);
        for (Field declaredField : declaredFields) {
            String fieldName = declaredField.getName();
            String result = String.format("%s.set%s(%s);", beanName, StringUtils.capitalize(fieldName), fieldName);
            System.out.println(result);
        }
    }

    @Test
    public void testSetValue() {
        WeatherResult weatherResult = new WeatherResult();
        // weatherResult.setDateValue(dateValue);
        // weatherResult.setHighT(highT);
        // weatherResult.setLowT(lowT);
        // weatherResult.setDailyWeather(dailyWeather);
        // weatherResult.setNightWeather(nightWeather);
        // weatherResult.setWindDirection(windDirection);
    }

    @Test
    public void testConfig() {
        System.out.println(SpiderProperties.IsEnableTestMode);
    }
}