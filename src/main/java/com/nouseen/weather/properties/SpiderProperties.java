package com.nouseen.weather.properties;

import com.nouseen.util.BaseProperties;

public class SpiderProperties extends BaseProperties {

    /**
     * 是否开启
     */
    public static boolean IsEnableTestMode;

    public static String ResultPath;

    public static String CitesDataName;

    /**
     * 精细预报数据
     */
    public static boolean IsEnableDetailForecastProcess;

    /**
     * 一年的每月数据
     */
    public static boolean IsEnableMothDataProcess;
    public static boolean IsEnableTemperatureForecastProcess;
    public static boolean IsEnableDetail24hRealProcess;

    private static SpiderProperties me = new SpiderProperties();

    private SpiderProperties() {
        init();
    }

    @Override
    public String getPropertiesName() {
        return "spider.properties";
    }
}
