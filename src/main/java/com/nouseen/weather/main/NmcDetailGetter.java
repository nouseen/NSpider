package com.nouseen.weather.main;

import com.nouseen.util.ExcelUtil;
import com.nouseen.util.vo.ExcelExportParamVo;
import com.nouseen.weather.WeatherInfoProcessor4nmcDetail;
import com.nouseen.weather.properties.SpiderProperties;
import com.nouseen.weather.vo.ChinaCities;
import com.nouseen.weather.vo.NmcCity;
import com.nouseen.weather.vo.WeatherResult4nmcDetailForecast;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Spider;

import java.util.List;

import static com.nouseen.weather.properties.SpiderProperties.IsEnableTestMode;

public class NmcDetailGetter {

    static Logger logger = Logger.getLogger(NmcDetailGetter.class);
    public static void main(String[] args) {

        List<NmcCity> chinaCities = ChinaCities.getChinaCities4nmc();
        logger.info(String.format("当前共%s个城市待抓取", chinaCities.size()));

        int i = 1;
        for (NmcCity chinaCity : chinaCities) {
            logger.info(String.format("当前抓取第%s个：%s", i++, chinaCity.getCity()));
            String url = String.format("http://www.nmc.cn%s", chinaCity.getUrl());

            Spider.create(new WeatherInfoProcessor4nmcDetail()).addUrl(url)
                    .thread(1).syncRun();
            if (IsEnableTestMode) {
                break;
            }
        }

        logger.info(String.format("抓取完成，开始生成excel，保存路径：%s", SpiderProperties.ResultPath));

        List<WeatherResult4nmcDetailForecast> weatherResult4nmcDetailForecastList = WeatherInfoProcessor4nmcDetail.weatherResult4nmcDetailForecastList;

        ExcelExportParamVo excelExportParamVo = new ExcelExportParamVo<>(WeatherResult4nmcDetailForecast.class)
                .setDataList(weatherResult4nmcDetailForecastList)
                .setFileRoot(SpiderProperties.ResultPath).setSheetName("天气信息").buildTitleMap();

        ExcelUtil.excelExport(excelExportParamVo);
        logger.info(String.format("生成excel完成"));

    }
}
