package com.nouseen.weather;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.nouseen.weather.vo.NmcCity;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class WeatherInfoProcessor4nmcCities implements PageProcessor {

    Logger logger = Logger.getLogger(WeatherInfoProcessor4nmcCities.class);
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public static List<NmcCity> NmcCityList = Lists.newLinkedList();

    @Override
    public void process(Page page) {
        String nowValues = page.getRawText();
        List<NmcCity> nmcCityList = JSON.parseArray(nowValues, NmcCity.class);
        NmcCityList.addAll(nmcCityList);
    }

    @Override
    public Site getSite() {
        return site;
    }
}