package com.nouseen.weather;
import com.google.common.collect.Lists;
import com.nouseen.util.ExcelUtil;
import com.nouseen.util.vo.ExcelExportParamVo;
import com.nouseen.weather.vo.WeatherResult;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

import static com.nouseen.weather.vo.ChinaCities.citiesStringArray;

public class WeatherInfoProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    static List<WeatherResult> WeatherResultList = Lists.newLinkedList();

    @Override
    public void process(Page page) {
        Selectable selectable = page.getHtml().$(".tablelist tr");
        List<Selectable> nodes = selectable.nodes();

        // 所有时间的数据
        for (Selectable node : nodes) {
            // 具体值
            List<Selectable> td = node.$("td").nodes();

            WeatherResult weatherResult = new WeatherResult();

            if (td.size() < 5) {
                continue;
            }

            // 日期
            String dateValue = td.get(0).regex("title=\"(.*?日)(.*?)天气\"", 1).toString();
            String address = td.get(0).regex("title=\"(.*?日)(.*?)天气\"", 2).toString();
            if (StringUtils.isBlank(dateValue)) {
                continue;
            }

            // 最高气温
            String highT = td.get(1).regex("<td>(.*?)</td>", 1).toString();
            // 最底气温
            String lowT = td.get(2).regex("<td>(.*?)</td>", 1).toString();
            // 白天天气
            String dailyWeather = td.get(3).regex("<td>(.*?)</td>", 1).toString();
            // 夜间天气
            String nightWeather = td.get(4).regex("<td>(.*?)</td>", 1).toString();
            // 风向
            String windDirection = td.get(5).regex("<td>(.*?)</td>", 1).toString();

            weatherResult.setDateValue(dateValue);
            weatherResult.setAddress(address);
            weatherResult.setHighT(highT);
            weatherResult.setLowT(lowT);
            weatherResult.setDailyWeather(dailyWeather);
            weatherResult.setNightWeather(nightWeather);
            weatherResult.setWindDirection(windDirection);

            WeatherResultList.add(weatherResult);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        for (String[] strings : citiesStringArray) {
            // https://m.tianqi.com/lishi/wuhan/202001.html
            String url = String.format("http://www.nhvisit.com/%s1yuetianqi/?from=singlemessage&isappinstalled=0", StringUtils.lowerCase(strings[1]));
            // String url = String.format("http://www.nhvisit.com/2019beijing1yuetianqi/?from=singlemessage&isappinstalled=0", StringUtils.lowerCase(strings[1]));
            http://www.nhvisit.com//
            Spider.create(new WeatherInfoProcessor()).addUrl(url)
                    .thread(1).syncRun();
        }
        ExcelExportParamVo excelExportParamVo = new ExcelExportParamVo(WeatherResult.class).setDataList(WeatherResultList).setFileRoot("D:/weather/").setSheetName("天气信息").buildTitleMap();
        ExcelUtil.excelExport(excelExportParamVo);

    }
}