package com.ruoyi.system.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class SpiderUtil {
    /**
     * 根绝输入的pornhub的URl获得Document
     * @return
     */
    public  static Document getPhDocument(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .header("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36")
                    .header("x-forwarded-for",IPUtils2.getRandomIp())
                    .header("client-ip",IPUtils2.getRandomIp())
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
    public  static Document getXvideosDocument(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .header("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36")
                    .header("x-forwarded-for",IPUtils2.getRandomIp())
                    .header("client-ip",IPUtils2.getRandomIp())
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
    public  static Document getXvideosJson(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .header("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36")
                    .header("x-forwarded-for",IPUtils2.getRandomIp())
                    .header("client-ip",IPUtils2.getRandomIp())
                    .ignoreContentType(true)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
    public static void main(String[] args){
        Document xvideosDocument = getXvideosDocument("https://www.xvideos.com/prof-video-click/model/hunkhands/55757107/_5_11_");
        System.out.println(xvideosDocument.html());
    }
}
