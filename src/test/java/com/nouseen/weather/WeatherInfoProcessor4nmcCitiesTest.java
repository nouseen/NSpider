package com.nouseen.weather;

import com.alibaba.fastjson.JSON;
import com.nouseen.weather.vo.ChinaCities;
import com.nouseen.weather.vo.Nmcprovince;
import org.junit.Test;
import us.codecraft.webmagic.Spider;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WeatherInfoProcessor4nmcCitiesTest {

    @Test
    public void testGetCities() throws IOException {

        String url = String.format("http://www.nmc.cn/f/rest/province/ALN");

        Spider.create(new WeatherInfoProcessor4nmcCities()).addUrl(url)
                .thread(1).syncRun();

        String result = JSON.toJSONString(WeatherInfoProcessor4nmcCities.NmcCityList);
        FileWriter fileWriter = new FileWriter("D:\\weather\\nmccities.txt");
        fileWriter.write(result);
        fileWriter.flush();
        fileWriter.close();

    }
    @Test
    public void testGetAllCities() throws IOException {

        List<Nmcprovince> nmcprovinces = JSON.parseArray(ChinaCities.province4nmc, Nmcprovince.class);
        for (Nmcprovince nmcprovince : nmcprovinces) {
            String url = String.format("http://www.nmc.cn/f/rest/province/%s",nmcprovince.getCode());

            Spider.create(new WeatherInfoProcessor4nmcCities()).addUrl(url)
                    .thread(1).syncRun();
        }


        String result = JSON.toJSONString(WeatherInfoProcessor4nmcCities.NmcCityList);
        FileWriter fileWriter = new FileWriter("D:\\weather\\nmccities.txt");
        fileWriter.write(result);
        fileWriter.flush();
        fileWriter.close();

    }



}