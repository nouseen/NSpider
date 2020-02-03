package com.nouseen.weather.main;

import com.nouseen.util.ExcelUtil;
import com.nouseen.util.vo.ExcelExportParamVo;
import com.nouseen.weather.WeatherInfoProcessor4nmc24hReal;
import com.nouseen.weather.WeatherInfoProcessor4nmcDetail;
import com.nouseen.weather.WeatherInfoProcessor4nmcTemperatureForecast;
import com.nouseen.weather.properties.SpiderProperties;
import com.nouseen.weather.vo.*;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Spider;

import java.util.List;

import static com.nouseen.weather.properties.SpiderProperties.*;

public class NmcDetailGetter {

    static Logger logger = Logger.getLogger(NmcDetailGetter.class);


    public static void main(String[] args) throws InterruptedException {

        /**
         * 抓数据
         */
        dataGet();


        /**
         * 导数据
         */
        exportExcel();

    }

    private static void exportExcel() {

        exportDetailForecast();

        exportMonthTemp();

        exportTemperatureForecastTemp();

        exportDetail24HReal();

        logger.info(String.format("生成excel完成"));

    }

    private static void exportDetail24HReal() {
        if (!SpiderProperties.IsEnableDetail24hRealProcess) {
            return;
        }
        ExcelExportParamVo excelExportParamVo = new ExcelExportParamVo<>(WeatherResult4nmcDetail24hReal.class)
                .setDataList(WeatherInfoProcessor4nmc24hReal.weatherResult4nmcDetail24hRealList)
                .setFileRoot(SpiderProperties.ResultPath + "24小时实况详情-").setSheetName("24小时实况详情").buildTitleMap();

        ExcelUtil.excelExport(excelExportParamVo);
    }

    private static void exportTemperatureForecastTemp() {
        if (!SpiderProperties.IsEnableTemperatureForecastProcess) {
            return;
        }
        ExcelExportParamVo excelMonthTempExportParamVo = new ExcelExportParamVo<>(WeatherResult4nmcTemperatureForecast.class)
                .setDataList(WeatherInfoProcessor4nmcTemperatureForecast.weatherResult4nmcTemperatureForecastList)
                .setFileRoot(SpiderProperties.ResultPath + "1周温度预报-").setSheetName("1周温度预报").buildTitleMap();

        ExcelUtil.excelExport(excelMonthTempExportParamVo);
    }

    private static void exportMonthTemp() {
        if (!SpiderProperties.IsEnableMothDataProcess) {
            return;
        }
        ExcelExportParamVo excelMonthTempExportParamVo = new ExcelExportParamVo<>(WeatherResult4nmcMonthTemperatureRecord.class)
                .setDataList(WeatherInfoProcessor4nmcDetail.weatherResult4NmcMonthTemperatureRecordList)
                .setFileRoot(SpiderProperties.ResultPath + "每月温度-").setSheetName("每月温度").buildTitleMap();

        ExcelUtil.excelExport(excelMonthTempExportParamVo);
    }

    private static void exportDetailForecast() {

        if (!SpiderProperties.IsEnableDetailForecastProcess) {
            return;
        }
        List<WeatherResult4nmcDetailForecast> weatherResult4nmcDetailForecastList = WeatherInfoProcessor4nmcDetail.weatherResult4nmcDetailForecastList;

        ExcelExportParamVo excelExportParamVo = new ExcelExportParamVo<>(WeatherResult4nmcDetailForecast.class)
                .setDataList(weatherResult4nmcDetailForecastList)
                .setFileRoot(SpiderProperties.ResultPath + "精细预报-").setSheetName("天气信息").buildTitleMap();

        ExcelUtil.excelExport(excelExportParamVo);
    }

    private static void dataGet() throws InterruptedException {
        List<NmcCity> chinaCities = ChinaCities.getChinaCities4nmc();
        logger.info(String.format("当前共%s个城市待抓取", chinaCities.size()));

        int i = 1;
        for (NmcCity chinaCity : chinaCities) {
            logger.info(String.format("当前抓取第%s个：%s", i++, chinaCity.getCity()));
            String url = String.format("http://www.nmc.cn%s", chinaCity.getUrl());

            Spider.create(new WeatherInfoProcessor4nmcDetail()).addUrl(url)
                    .thread(1).syncRun();

            Thread.sleep(100);

            if (IsEnableTemperatureForecastProcess) {
                // 温度高低预报 http://www.nmc.cn/f/rest/tempchart/54529 http://www.nmc.cn/f/rest/tempchart/54517
                String temperatureForecastUrl = String.format("http://www.nmc.cn/f/rest/tempchart/%s", chinaCity.getCode());
                Spider.create(new WeatherInfoProcessor4nmcTemperatureForecast()).addUrl(temperatureForecastUrl)
                        .thread(1).syncRun();
            }

            Thread.sleep(100);

            if (IsEnableDetail24hRealProcess) {
                // 24小时实况 http://www.nmc.cn/f/rest/passed/54529
                String detail24hReal = String.format("http://www.nmc.cn/f/rest/passed/%s", chinaCity.getCode());
                Spider.create(new WeatherInfoProcessor4nmc24hReal()).addUrl(detail24hReal)
                        .thread(1).syncRun();
            }

            if (IsEnableTestMode) {
                break;
            }
        }

        logger.info(String.format("抓取完成，开始生成excel，保存路径：%s", SpiderProperties.ResultPath));
    }
}
