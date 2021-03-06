package com.nouseen.weather;

import com.google.common.collect.Lists;
import com.nouseen.util.DateUtil;
import com.nouseen.weather.properties.SpiderProperties;
import com.nouseen.weather.vo.WeatherResult4nmcDetailForecast;
import com.nouseen.weather.vo.WeatherResult4nmcMonthTemperatureRecord;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class WeatherInfoProcessor4nmcDetail implements PageProcessor {

    Logger logger = Logger.getLogger(WeatherInfoProcessor4nmcDetail.class);
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public static List<WeatherResult4nmcDetailForecast> weatherResult4nmcDetailForecastList = Lists.newLinkedList();
    public static List<WeatherResult4nmcMonthTemperatureRecord> weatherResult4NmcMonthTemperatureRecordList = Lists.newLinkedList();

    private String publishTime;
    private String cityname;
    @Override
    public void process(Page page) {

        // 初始化公用数据
        publishTime = page.getHtml().$(".btitle").regex("发布于：(.*?)<", 1).toString();
        cityname = page.getHtml().$(".cityname .cname").regex(">(.*?)<", 1).toString();

        processDetailData(page);

        processTempData12Moth(page);
    }

    /**
     * 12个月温度数据
     *
     * @auth nouseen
     * @since 2020/2/3
     */
    private void processTempData12Moth(Page page) {
        if (!SpiderProperties.IsEnableMothDataProcess) {
            return;
        }
        for (int i = 0; i < 12; i++) {
            String regex = String.format("month.push\\(%s\\); maxTemp.push\\((.*?)\\); minTemp.push\\((.*?)\\); precipitation.push\\((.*?)\\)", i + 1);
            String maxTemp = page.getHtml().regex(regex, 1).toString();
            String minTemp = page.getHtml().regex(regex, 2).toString();
            String precipitation = page.getHtml().regex(regex, 3).toString();

            WeatherResult4nmcMonthTemperatureRecord weatherResult4NmcMonthTemperatureRecord = new WeatherResult4nmcMonthTemperatureRecord();
            weatherResult4NmcMonthTemperatureRecord.setPublishDateValue(publishTime);
            weatherResult4NmcMonthTemperatureRecord.setDateValue(i + 1 + "月");
            weatherResult4NmcMonthTemperatureRecord.setAddress(cityname);
            weatherResult4NmcMonthTemperatureRecord.setPrecipitation(precipitation);
            weatherResult4NmcMonthTemperatureRecord.setLowTemperature(minTemp);
            weatherResult4NmcMonthTemperatureRecord.setHighTemperature(maxTemp);
            weatherResult4NmcMonthTemperatureRecordList.add(weatherResult4NmcMonthTemperatureRecord);
        }
    }

    private void processDetailData(Page page) {

        if (!SpiderProperties.IsEnableDetailForecastProcess) {
            return;
        }

        WeatherResult4nmcDetailForecast[][] weatherResult4nmcDetailForecastArray = new WeatherResult4nmcDetailForecast[7][8];

        // 今天
        Date nowDate = DateUtil.getNowDate();

        // 先new 7 个对象 对应7天的数据
        for (int i = 0; i < 7; i++) {

            for (int j = 0; j < 8; j++) {
                WeatherResult4nmcDetailForecast weatherResult4nmcDetailForecast = new WeatherResult4nmcDetailForecast();
                weatherResult4nmcDetailForecast.setDateValue(DateUtil.getDateFormat(nowDate));
                weatherResult4nmcDetailForecast.setPublishDateValue(publishTime);
                weatherResult4nmcDetailForecast.setAddress(cityname);
                weatherResult4nmcDetailForecastArray[i][j] = weatherResult4nmcDetailForecast;
            }
            nowDate = DateUtils.addDays(nowDate, 1);
        }

        // 先循环获取每一天
        for (int i = 0; i < 7; i++) {

            Selectable nowDayData = page.getHtml().$("#day" + i);

            // 第一行数据 - 时间
            List<Selectable> timeNodes = nowDayData.$(".row.first div").nodes();

            for (int j = 0; j < 8; j++) {

                try {
                Selectable selectable = timeNodes.get(j + 1).regex(">(.*?)<", 1);
                weatherResult4nmcDetailForecastArray[i][j].setTimeValue(selectable.toString());

                } catch (Exception e) {
                    logger.error(e);
                }
            }

            // 温度
            List<Selectable> tpNodes = nowDayData.$(".row.wd div").nodes();
            for (int j = 0; j < 8; j++) {
                try {
                    Selectable selectable = tpNodes.get(j + 1).regex(">(.*?)<", 1);
                    weatherResult4nmcDetailForecastArray[i][j].setTemperature(selectable.toString());
                } catch (Exception e) {
                    logger.error(e);
                }
            }

            // 天气
            List<Selectable> weatherNodes = nowDayData.$(".row.tqxx div").nodes();
            for (int j = 0; j < 8; j++) {
                try {
                    Selectable selectable = weatherNodes.get(j + 1).regex("(\\d+)\\.png", 1);
                    weatherResult4nmcDetailForecastArray[i][j].setWeather(selectable.toString());
                } catch (Exception e) {
                    logger.error(e);
                }
            }


            // 降水
            List<Selectable> precipitationNodes = nowDayData.$(".row.js div").nodes();
            for (int j = 0; j < 8; j++) {
                try {
                    Selectable selectable = precipitationNodes.get(j + 1).regex(">(.*?)<", 1);
                    weatherResult4nmcDetailForecastArray[i][j].setPrecipitation(selectable.toString());
                } catch (Exception e) {
                    logger.error(e);
                }
            }

            // 风速
            List<Selectable> windsNodes = nowDayData.$(".row.winds div").nodes();
            for (int j = 0; j < 8; j++) {
                try {
                    Selectable selectable = windsNodes.get(j + 1).regex(">(.*?)<", 1);
                    weatherResult4nmcDetailForecastArray[i][j].setWindSpeed(selectable.toString());
                } catch (Exception e) {
                    logger.error(e);
                }
            }

            // 风向
            List<Selectable> windDirectionNodes = nowDayData.$(".row.windd div").nodes();
            for (int j = 0; j < 8; j++) {
                try {
                    Selectable selectable = windDirectionNodes.get(j + 1).regex(">(.*?)<", 1);
                    weatherResult4nmcDetailForecastArray[i][j].setWindDirection(selectable.toString());
                } catch (Exception e) {
                    logger.error(e);
                }
            }

            // 气压
            List<Selectable> atmosphericPressureNodes = nowDayData.$(".row.qy div").nodes();
            for (int j = 0; j < 8; j++) {
                try {
                    Selectable selectable = atmosphericPressureNodes.get(j + 1).regex(">(.*?)<", 1);
                    weatherResult4nmcDetailForecastArray[i][j].setAtmosphericPressure(selectable.toString());
                } catch (Exception e) {
                    logger.error(e);
                }
            }


            // 相对湿度
            List<Selectable> rhNodes = nowDayData.$(".row.xdsd div").nodes();
            for (int j = 0; j < 8; j++) {
                try {
                    Selectable selectable = rhNodes.get(j + 1).regex(">(.*?)<", 1);
                    weatherResult4nmcDetailForecastArray[i][j].setRH(selectable.toString());
                } catch (Exception e) {
                    logger.error(e);
                }
            }

            // 云量
            List<Selectable> cloudAmountNodes = nowDayData.$(".row.yl div").nodes();
            for (int j = 0; j < 8; j++) {
                try {
                    Selectable selectable = cloudAmountNodes.get(j + 1).regex(">(.*?)<", 1);
                    weatherResult4nmcDetailForecastArray[i][j].setCloudAmount(selectable.toString());
                } catch (Exception e) {
                    logger.error(e);
                }
            }

            // 能见度
            List<Selectable> visibilityNodes = nowDayData.$(".row.njd div").nodes();
            for (int j = 0; j < 8; j++) {
                try {
                    Selectable selectable = visibilityNodes.get(j + 1).regex(">(.*?)<", 1);
                    weatherResult4nmcDetailForecastArray[i][j].setVisibility(selectable.toString());
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }

        for (WeatherResult4nmcDetailForecast[] weatherResult4nmcDetailForecasts : weatherResult4nmcDetailForecastArray) {
            weatherResult4nmcDetailForecastList.addAll(Arrays.asList(weatherResult4nmcDetailForecasts));
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}