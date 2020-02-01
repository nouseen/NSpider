package com.nouseen.weather;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.LinkedList;
import java.util.List;

public class GithubRepoPageProcessor implements PageProcessor {

    int i = 0;

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    //document.querySelector("body > div.wrap > div.main.mt10 > div.main_left > div.graybox.mt10 > div")
    @Override
    public void process(Page page) {
        Selectable selectable = page.getHtml().$(".tablelist tr");
        List<Selectable> nodes = selectable.nodes();

        List<WeatherResult> WeatherResultList = Lists.newLinkedList();
        // 所有时间的数据
        for (Selectable node : nodes) {
            // 具体值
            List<Selectable> td = node.$("td").nodes();

            WeatherResult weatherResult = new WeatherResult();

            // 日期
            String dateValue = td.get(0).regex("<td>(.*?)</td>", 1).toString();

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
            weatherResult.setHighT(highT);
            weatherResult.setLowT(lowT);
            weatherResult.setDailyWeather(dailyWeather);
            weatherResult.setNightWeather(nightWeather);
            weatherResult.setWindDirection(windDirection);

            WeatherResultList.add(weatherResult);
        }

        //

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor()).addUrl("http://www.nhvisit.com/huanggang12yuetianqi/?from=singlemessage&isappinstalled=0")
                .addPipeline(new JsonFilePipeline("D:\\"))
                .thread(5).run();
    }
}