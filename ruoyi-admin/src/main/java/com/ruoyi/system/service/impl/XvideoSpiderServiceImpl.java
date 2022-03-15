package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.system.domain.VideoInfo;
import com.ruoyi.system.service.PhSpiderService;
import com.ruoyi.system.service.XvideoSpiderService;
import com.ruoyi.system.utils.DriverUtil;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class XvideoSpiderServiceImpl implements XvideoSpiderService {

    @Override
    public String getVideoUrl(String url) {
        Connection connect = Jsoup.connect(url);
        Document doc = null;
        try {
            doc = connect.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements scripts = doc.getElementsByTag("script");
        System.out.println("size:"+scripts.size());
        String js = "";
        for (Element item:scripts) {
            if(item.html().contains("html5player")){
                js = item.html();
                break;
            }
        }
        String patternStr = "^html5player\\.setVideoHLS.?m3u8$";
        Pattern pattern = Pattern.compile(patternStr) ;
        Matcher matcher = pattern.matcher(js) ;
        while(matcher.find()){
            int count = matcher.groupCount() ;
            for (int i = 0; i <= count; i++) {
                js = matcher.group(i);
                System.out.println("发现:"+js);
                return js;
            }
        }
        return js;
    }
}
