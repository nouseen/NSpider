package com.nouseen.weather;

import com.google.common.collect.Lists;
import com.nouseen.util.ExcelUtil;
import com.nouseen.util.vo.ExcelExportParamVo;
import com.nouseen.weather.vo.WeatherResult4Tianqi;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

import static com.nouseen.weather.vo.ChinaCities.citiesStringArray;

public class WeatherInfoProcessor4TianqiCom implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    static List<WeatherResult4Tianqi> WeatherResultList = Lists.newLinkedList();

    @Override
    public void process(Page page) {
        Selectable selectable = page.getHtml().$(".weatherbox a");
        List<Selectable> nodes = selectable.nodes();

        // 地点
        String address = page.getHtml().$(".weather-one").regex("<b>(.*?)2020", 1).toString();

        // 所有时间的数据
        for (Selectable node : nodes) {
            // 具体值
            List<Selectable> dd = node.$("dd").nodes();

            WeatherResult4Tianqi WeatherResult4Tianqi = new WeatherResult4Tianqi();

            // 日期
            String dateValue = node.$("a").regex("title=\".*?(2020.*?)历史", 1).toString();

            // 最低气温
            String lowT = dd.get(3).regex(">.*?(-?\\d+)~", 1).toString();

            // 最高气温
            String highT = dd.get(3).regex("<b>(.*?)</b>", 1).toString();

            // 天气
            String dailyWeather = dd.get(2).regex(">(.*?)<", 1).toString();

            WeatherResult4Tianqi.setDateValue(dateValue);
            WeatherResult4Tianqi.setAddress(address);
            WeatherResult4Tianqi.setHighT(highT);
            WeatherResult4Tianqi.setLowT(lowT);
            WeatherResult4Tianqi.setWeather(dailyWeather);

            WeatherResultList.add(WeatherResult4Tianqi);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        for (String[] strings : citiesStringArray) {
            // String url = "https://m.tianqi.com/lishi/wuhan/202001.html";
            String url = String.format("https://m.tianqi.com/lishi/%s/202001.html", StringUtils.lowerCase(strings[1]));

            Spider.create(new WeatherInfoProcessor4TianqiCom()).addUrl(url)
                    .thread(1).syncRun();
        }
        ExcelExportParamVo excelExportParamVo = new ExcelExportParamVo(WeatherResult4Tianqi.class).setDataList(WeatherResultList).setFileRoot("D:/weather/").setSheetName("天气信息").buildTitleMap();
        ExcelUtil.excelExport(excelExportParamVo);

    }
}