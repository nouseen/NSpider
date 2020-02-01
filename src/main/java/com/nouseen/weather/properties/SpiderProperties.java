package com.nouseen.weather.properties;

import com.nouseen.util.BaseProperties;

public class SpiderProperties extends BaseProperties {

    /**
     * 是否开启
     */
    public static boolean IsEnableSpider;

    private static SpiderProperties me = new SpiderProperties();

    private SpiderProperties() {
        init();
    }

    @Override
    public String getPropertiesName() {
        return "spider.properties";
    }
}
