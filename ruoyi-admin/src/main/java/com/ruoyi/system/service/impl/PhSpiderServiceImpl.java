package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.system.domain.VideoInfo;
import com.ruoyi.system.service.PhSpiderService;
import com.ruoyi.system.utils.DriverUtil;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhSpiderServiceImpl implements PhSpiderService {

    @Override
    public String getVideoUrl(String url) {
        WebDriver driver = DriverUtil.getDriver();
        driver.get(url);
        String pageSource = driver.getPageSource();
        Document doc = Jsoup.parse(pageSource);
        //获得VideoID
        String videoId = doc.getElementById("player").attr("data-video-id");
        String flashVar = "flashvars_"+videoId;
        JavascriptExecutor js = (JavascriptExecutor) driver;   //声明一个js执行器
        //String str = flashVar+".mediaDefinitions.length-1";
        String jsStr = "for(i=0;i<"+flashVar+".mediaDefinitions.length;i++){if("+flashVar+".mediaDefinitions[i].format==='mp4'){return "+flashVar+".mediaDefinitions[i].videoUrl}}";

        String mediaurl = (String) js.executeScript(jsStr); //调用执行器的executeScript方法执行js脚本
        driver.get(mediaurl);
        String videoHtml = driver.getPageSource();
        String videoinfo = Jsoup.parse(videoHtml).getElementsByTag("pre").get(0).html();
        List<VideoInfo> videoInfos = JSON.parseArray(videoinfo, VideoInfo.class);
         videoInfos = videoInfos.stream().filter(item -> {
            return StringUtils.isNotBlank(item.getVideoUrl());
        }).collect(Collectors.toList());
        DriverUtil.recycle(driver);
        System.out.println(videoInfos.size());
        return videoInfos.get(videoInfos.size()-1).getVideoUrl().replaceAll("&amp;","&");
    }

    @Override
    public String download(String httpUrl) {
        // 1.下载网络文件
        int byteRead;
        URL url;
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            return "success";
        }

        try {
            //2.获取链接
            URLConnection conn = url.openConnection();
            //3.输入流
            InputStream inStream = conn.getInputStream();
            //3.写入文件
            FileOutputStream fs = new FileOutputStream("F:\\test.wbem");

            byte[] buffer = new byte[1024];
            while ((byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            inStream.close();
            fs.close();
            return "success";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "fail";
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
