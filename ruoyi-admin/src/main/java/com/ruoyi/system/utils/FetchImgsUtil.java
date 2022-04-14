package com.ruoyi.system.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class FetchImgsUtil {
    /**
     * @param args
     */
    public static void main(String[] args) {

        String uid = "871170f2-2598-48e5-9ee8-58ed6379d8931d2ec8";
        String s = "1363239309732";
        String fileName = "ex2.mp4";
        getCheckCodePicFromXX(uid,s,fileName);
    }

    private static void getCheckCodePicFromXX(String uid, String s,String fileName) {
        String url = "https://web.telegram.org/stream/%7B%22dcId%22%3A5%2C%22location%22%3A%7B%22_%22%3A%22inputDocumentFileLocation%22%2C%22id%22%3A%226226436212387742820%22%2C%22access_hash%22%3A%228939213922908005517%22%2C%22file_reference%22%3A%5B2%2C78%2C87%2C105%2C26%2C0%2C5%2C246%2C236%2C98%2C76%2C238%2C145%2C206%2C102%2C236%2C174%2C149%2C5%2C118%2C108%2C3%2C194%2C144%2C5%2C46%2C242%2C158%2C65%5D%7D%2C%22size%22%3A546607%2C%22mimeType%22%3A%22video%2Fmp4%22%7D";
        String dirPath = "D:/";

        downloadPicture(url, dirPath, fileName);
    }

    /**
     * 从网络上下载图片
     */
    public static void downloadPicture(String url, String dirPath,
                                       String filePath) {

        DefaultHttpClient httpclient = new DefaultHttpClient();

        HttpGet httpget = new HttpGet(url);

        httpget
                .setHeader(
                        "User-Agent",
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.84 Safari/537.36");

        httpget
                .setHeader("Referer",
                        "https://web.telegram.org/k/");
        httpget
                .setHeader("Accept-Encoding",
                        "identity;q=1, *;q=0identity;q=1, *;q=0");
        httpget
                .setHeader("Range",
                        "bytes=65536-");
        httpget
                .setHeader("sec-ch-ua",
                        "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"99\", \"Google Chrome\";v=\"99\"");
        httpget
                .setHeader("sec-ch-ua-platform",
                        "Windows");

        try {
            HttpResponse resp = httpclient.execute(httpget);
            if (HttpStatus.SC_OK == resp.getStatusLine().getStatusCode()||HttpStatus.SC_PARTIAL_CONTENT == resp.getStatusLine().getStatusCode()) {
                HttpEntity entity = resp.getEntity();

                InputStream in = entity.getContent();

                savePicToDisk(in, dirPath, filePath);

                System.out.println("保存 "+filePath+" 成功....");
            }else{
                System.out.println(resp.getStatusLine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }

    /**
     * 将图片写到 硬盘指定目录下
     * @param in
     * @param dirPath
     * @param filePath
     */
    private static void savePicToDisk(InputStream in, String dirPath,
                                      String filePath) {

        try {
            File dir = new File(dirPath);
            if (dir == null || !dir.exists()) {
                dir.mkdirs();
            }

            //文件真实路径
            String realPath = dirPath.concat(filePath);
            File file = new File(realPath);
            if (file == null || !file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}