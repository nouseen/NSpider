package com.nouseen.weather.vo;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.lang.reflect.Field;

public class WeatherResult4NmcMonthTemperatureRecordTest {

    @Test
    public void testSetweatherResult4nmcMonthTemperatureForecast() {

        Class<WeatherResult4nmcMonthTemperatureRecord> clazz = WeatherResult4nmcMonthTemperatureRecord.class;
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