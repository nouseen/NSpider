package com.nouseen.weather;

import com.alibaba.fastjson.JSON;
import com.nouseen.util.ExcelUtil;
import com.nouseen.util.vo.ExcelExportParamVo;
import com.nouseen.weather.vo.ChinaCities;
import com.nouseen.weather.vo.NmcCity;
import com.nouseen.weather.vo.WeatherResult4nmcDetailForecast;
import org.junit.Test;
import us.codecraft.webmagic.Spider;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class WeatherInfoProcessor4nmcDetailTest {

    @Test
    public void testGetSingle() {

        String url = String.format("http://www.nmc.cn/publish/forecast/AHB/wuhan.html");

        Spider.create(new WeatherInfoProcessor4nmcDetail()).addUrl(url)
                    .thread(1).syncRun();
        ExcelExportParamVo excelExportParamVo = new ExcelExportParamVo<>(WeatherResult4nmcDetailForecast.class).setDataList(WeatherInfoProcessor4nmcDetail.weatherResult4nmcDetailForecastList).setFileRoot("D:/weather/").setSheetName("天气信息").buildTitleMap();
        ExcelUtil.excelExport(excelExportParamVo);
    }

    @Test
    public void testGetAll() {

        List<NmcCity> chinaCities = ChinaCities.getChinaCities4nmc();
        for (NmcCity chinaCity : chinaCities) {

            String url = String.format("http://www.nmc.cn%s", chinaCity.getUrl());

            Spider.create(new WeatherInfoProcessor4nmcDetail()).addUrl(url)
                    .thread(1).syncRun();
        }


        List<WeatherResult4nmcDetailForecast> weatherResult4nmcDetailForecastList = WeatherInfoProcessor4nmcDetail.weatherResult4nmcDetailForecastList;

        ExcelExportParamVo excelExportParamVo = new ExcelExportParamVo<>(WeatherResult4nmcDetailForecast.class).setDataList(weatherResult4nmcDetailForecastList).setFileRoot("D:/weather/").setSheetName("天气信息").buildTitleMap();

        ExcelUtil.excelExport(excelExportParamVo);
    }

    @Test
    public void testArray() {
        WeatherResult4nmcDetailForecast[][] weatherResult4nmcDetailForecastList = new WeatherResult4nmcDetailForecast[8][7];
        System.out.println(weatherResult4nmcDetailForecastList[0].length);
    }

    @Test
    public void testReadCities() throws IOException {
        String path = this.getClass().getResource("/").getPath();
        char[] chars = new char[1024];
        FileReader fileReader = new FileReader(path + "nmccities.txt");
        String citiesJson = "";
        int num;
        while ((num = fileReader.read(chars)) != -1) {
            citiesJson += new String(chars, 0, num);
        };

        fileReader.close();

        List<NmcCity> nmcCities = JSON.parseArray(citiesJson, NmcCity.class);
        System.out.println(nmcCities.size());

    }

    @Test
    public void testNMCCities() {
        List<NmcCity> chinaCities = ChinaCities.getChinaCities4nmc();

        System.out.println(chinaCities.size());
    }

}