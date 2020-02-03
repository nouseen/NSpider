package com.nouseen.weather;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.nouseen.weather.vo.ChinaCities;
import com.nouseen.weather.vo.NmcCity;
import com.nouseen.weather.vo.WeatherResult4nmcTemperatureForecast;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class WeatherInfoProcessor4nmcTemperatureForecast implements PageProcessor {

    Logger logger = Logger.getLogger(WeatherInfoProcessor4nmcTemperatureForecast.class);
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    public static List<WeatherResult4nmcTemperatureForecast> weatherResult4nmcTemperatureForecastList = Lists.newLinkedList();

    @Override
    public void process(Page page) {

        String rawText = page.getRawText();
        String code = page.getUrl().regex("\\d+$").toString();

        NmcCity nmcCityByCode = ChinaCities.getNmcCityByCode(code);
        String city = nmcCityByCode.getCity();
        List<WeatherResult4nmcTemperatureForecast> weatherResult4nmcTemperatureForecasts = JSON.parseArray(rawText, WeatherResult4nmcTemperatureForecast.class);
        for (WeatherResult4nmcTemperatureForecast weatherResult4nmcTemperatureForecast : weatherResult4nmcTemperatureForecasts) {
            weatherResult4nmcTemperatureForecast.setAddress(city);
        }

        weatherResult4nmcTemperatureForecastList.addAll(weatherResult4nmcTemperatureForecasts);
    }

    @Override
    public Site getSite() {
        return site;
    }
}