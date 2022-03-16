package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.system.domain.VideoInfo;
import com.ruoyi.system.service.PhSpiderService;
import com.ruoyi.system.utils.DriverUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Slf4j
@Service
public class PhSpiderServiceImpl implements PhSpiderService {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine js = manager.getEngineByName("javascript");
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

    /**
     * 获得phVideoUrl 优化方案
     * @param url
     * @return
     */
    @Override
    public String getVideoUrlV2(String url) {
        //获得网站源码
        Document parse = null;
        Map<String, String> cookies = null;
        try {
            Connection.Response execute = Jsoup.connect(url).execute();
            cookies = execute.cookies();
            parse = execute.parse();
            //parse = Jsoup.connect(url).get();

        } catch (IOException e) {
            e.printStackTrace();
        }
        String videoId = parse.getElementById("player").attr("data-video-id");
        String flashVar = "flashvars_"+videoId;
        Elements scripts = parse.getElementsByTag("script");
        Element targetEle = null;
        for (Element ele:scripts) {
            if(ele.html().contains("flashvars_")){
                targetEle = ele;
                break;
            }
        }
        if(targetEle!=null){
            String html = targetEle.html();
            html = html.substring(0,html.lastIndexOf("playerObjList")).replace("playerObjList","");
            log.info(html);
            //获得对应的js代码
            /*WebDriver driver = DriverUtil.getDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;*/
            String jsStr = "for(i=0;i<"+flashVar+".mediaDefinitions.length;i++){if("+flashVar+".mediaDefinitions[i].format==='mp4'){return "+flashVar+".mediaDefinitions[i].videoUrl}}";
            String jsStr2 = "function say(){for(i=0;i<"+flashVar+".mediaDefinitions.length;i++){if("+flashVar+".mediaDefinitions[i].format==='mp4'){return "+flashVar+".mediaDefinitions[i].videoUrl}}}";
            html+=jsStr2;
            String mediaurl = null;
            try {
                mediaurl = (String) js.eval(html);
                Invocable js1 = (Invocable) js;
                mediaurl=(String)js1.invokeFunction("say",null);
                log.info("原生方式获取mediaUrl:{}",mediaurl);
            } catch (ScriptException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {
                Document doc  = Jsoup.connect(mediaurl).cookies(cookies).ignoreContentType(true).get();
                log.info(doc.html());
                String videoinfo  = doc.getElementsByTag("body").get(0).html();
                List<VideoInfo> videoInfos = JSON.parseArray(videoinfo, VideoInfo.class);
                videoInfos = videoInfos.stream().filter(item -> {
                    return StringUtils.isNotBlank(item.getVideoUrl());
                }).collect(Collectors.toList());
                /*DriverUtil.recycle(driver);*/
                System.out.println(videoInfos.size());
                return videoInfos.get(videoInfos.size()-1).getVideoUrl().replaceAll("&amp;","&");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
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
