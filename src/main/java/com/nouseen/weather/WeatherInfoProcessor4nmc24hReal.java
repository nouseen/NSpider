package com.nouseen.weather;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.nouseen.weather.vo.ChinaCities;
import com.nouseen.weather.vo.NmcCity;
import com.nouseen.weather.vo.WeatherResult4nmcDetail24hReal;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class WeatherInfoProcessor4nmc24hReal implements PageProcessor {

    Logger logger = Logger.getLogger(WeatherInfoProcessor4nmc24hReal.class);
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    public static List<WeatherResult4nmcDetail24hReal> weatherResult4nmcDetail24hRealList = Lists.newLinkedList();

    @Override
    public void process(Page page) {

        String rawText = page.getRawText();
        String code = page.getUrl().regex("\\d+$").toString();

        NmcCity nmcCityByCode = ChinaCities.getNmcCityByCode(code);
        String city = nmcCityByCode.getCity();
        List<WeatherResult4nmcDetail24hReal> WeatherResult4nmcDetail24hRealList = JSON.parseArray(rawText, WeatherResult4nmcDetail24hReal.class);
        for (WeatherResult4nmcDetail24hReal weatherResult4nmcTemperatureForecast : WeatherResult4nmcDetail24hRealList) {
            weatherResult4nmcTemperatureForecast.setAddress(city);
        }

        weatherResult4nmcDetail24hRealList.addAll(WeatherResult4nmcDetail24hRealList);
    }

    @Override
    public Site getSite() {
        return site;
    }
}