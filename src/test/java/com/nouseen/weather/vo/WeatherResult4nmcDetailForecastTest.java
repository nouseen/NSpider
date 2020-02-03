package com.nouseen.weather.vo;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.lang.reflect.Field;

public class WeatherResult4nmcDetailForecastTest {

    @Test
    public void testSetWeatherResult4nmcDetailForecast() {

        Class<WeatherResult4nmcDetailForecast> clazz = WeatherResult4nmcDetailForecast.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        String className = clazz.getSimpleName();
        String beanName = StringUtils.uncapitalize(className);
        for (Field declaredField : declaredFields) {
            String fieldName = declaredField.getName();
            String result = String.format("%s.set%s(%s);", beanName, StringUtils.capitalize(fieldName), fieldName);
            System.out.println(result);
        }
    }
}