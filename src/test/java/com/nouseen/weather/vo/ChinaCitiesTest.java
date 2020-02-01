package com.nouseen.weather.vo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Map;

import static com.nouseen.weather.vo.ChinaCities.citiesStringArray;

public class ChinaCitiesTest {

    @Test
    public void testCities() {
        JSONObject json = (JSONObject)JSON.parse(ChinaCities.cities);

        for (Map.Entry<String, Object> stringObjectEntry : json.entrySet()) {
            JSONArray jsonArray = (JSONArray) stringObjectEntry.getValue();
            for (Object o : jsonArray) {
                System.out.println(o);
            }
        }
    }

    @Test
    public void testCitiesArray() {
        for (String[] strings : citiesStringArray) {
            System.out.println(String.format("城市名：%s，拼音：%s",strings[0],strings[1]));
        }
    }

    @Test
    public void testSize() {
        System.out.println(citiesStringArray.length);
    }
}