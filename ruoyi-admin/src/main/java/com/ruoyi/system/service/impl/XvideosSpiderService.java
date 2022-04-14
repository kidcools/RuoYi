package com.ruoyi.system.service.impl;

import cn.hutool.json.JSONSupport;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.system.entity.*;
import com.ruoyi.system.service.ICommonSpiderService;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.utils.SpiderUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class XvideosSpiderService implements ICommonSpiderService {
    /**
     *      *   1.profiles
     *      * 	2.pornstar-channels
     *      * 	3.amateur-channels
     *      * 	4.model-channels
     */
    private final String platCode = "xvideos";
    private final String prefix = "https://www.xvideos.com/video";
    private final String endfix ="";
    private final String profilesUperPrefix = "https://www.xvideos.com/profiles/";
    private final String profilesUperEndfix = "/videos/best/0";
    private final String pornstarUperPrefix = "https://www.xvideos.com/pornstar-channels/";
    private final String pornstarUperEndfix = "/videos/best/0";
    private final String amateurUperPrefix = "https://www.xvideos.com/amateur-channels/";
    private final String amateurUperEndfix = "/videos/best/0";
    private final String modelUperPrefix = "ttps://www.xvideos.com/model-channels/";
    private final String modelUperEndfix = "/videos/best/0";
    private final String channelPrefix = "https://www.xvideos.com/channels/";
    private final String channelEndfix = "/videos/best/0";
    /**
     * 播放页获取视频信息
     * @param platForm
     * @param idInPlat
     * @return
     */
    @Override
    public Video getVideoInfo(PlatForm platForm, String idInPlat) {
        String videoDetailUrl = getVideoDetailUrl(platForm, idInPlat);
        Document xvideosDocument = SpiderUtil.getXvideosDocument(videoDetailUrl);
        Video video = new Video();
        //获得标签
        String tags = getTags(xvideosDocument);
        video.setTags(tags);
        String country = getCountry(xvideosDocument);
        video.setCountry(country);
        //创建类型
        String categoryIdInPlat = "";
        Category category = getCategory(categoryIdInPlat);
        video.setCategoryID(category.getId());
        //创建上传者
        String upperIdInPlat = xvideosDocument.getElementsByAttributeValue("class", "video-metadata video-tags-list ordered-label-list cropped").get(0).getElementsByTag("li").get(0).getElementsByTag("a").get(0).attr("href");
        Uploader upper = getUpper(upperIdInPlat);
        video.setUperId(upper.getId());
        return video;
    }
    /*
     *TODO
     *获取上传者信息
     */
    private Uploader getUpper(String upperIdInPlat) {
        //根绝upperIdInPlat SQL获得upper
        //根绝upperIdInPlat 网站爬去
        //返回上传者信息
        Uploader uploader = new Uploader();
        uploader.setId(1L);
        return uploader;
    }

    /**
     * 获取类型信息
     * @param categoryIdInPlat
     * @return
     */
    private Category getCategory(String categoryIdInPlat) {
        //根据categoryIdInPlat SQL获得upper
        //根绝categoryIdInPlatt 网站爬去
        //返回Category信息
        Category category = new Category();
        category.setId(1l);
        return category;
    }

    /**
     * 获得国家
     * @param xvideosDocument
     * @return
     */
    private String getCountry(Document xvideosDocument) {
        return "未知";
    }

    private String getTags(Document xvideosDocument) {
        return xvideosDocument.getElementsByAttributeValue("class", "video-metadata video-tags-list ordered-label-list cropped").get(0).getElementsByTag("li").stream().filter(item -> item.getElementsByTag("a").get(0).attr("href").startsWith("/tag")).collect(Collectors.toList()).stream().map(item -> item.text()).collect(Collectors.joining(","));
    }

    /**
     * 根绝用户平台ID 获取视频播放页URL
     * @param platForm
     * @param idInPlat
     * @return
     */
    @Override
    public String getVideoDetailUrl(PlatForm platForm, String idInPlat) {
        return "https://www.xvideos.com/video"+idInPlat;
    }


    /**
     * 根绝用户平台ID 获取详情页URL
     *  1.profiles
     * 	2.pornstar-channels
     * 	3.amateur-channels
     * 	4.model-channels
     * @param platForm
     * @param idInPlat
     * @param type
     * @return
     */
    @Override
    public String getUperDetailUrl(PlatForm platForm, String idInPlat, Integer type) {
        String url = "";
        if(type == 1){ //profile
            url = profilesUperPrefix+idInPlat;
        }else if(type == 2){//pornstar-channels
            url = pornstarUperPrefix+idInPlat;
        }else if(type == 3) {//amateur模式
            url = amateurUperPrefix+idInPlat;
        }else if(type ==4){ //channel-model 模式
            url = modelUperPrefix+idInPlat;
        }
        return url;
    }
    /**
     * 根据CHANNEL平台ID 获取详情页URL
     * @param platForm
     * @param idInPlat
     * @return
     */
    @Override
    public String getChannelDetailUrl(PlatForm platForm, String idInPlat) {
        return channelPrefix+idInPlat;
    }

    /**
     * 获得视频播放的URL
     * @param platForm
     * @param idInPlat
     * @return
     */
    @Override
    public String getVideoPlayUrl(PlatForm platForm, String idInPlat) {
        Document document = SpiderUtil.getXvideosDocument(getVideoDetailUrl(platForm, idInPlat));
        Elements scripts = document.getElementsByTag("script");
        String script = "";
        for(int i=0;i<scripts.size();i++){
            if(scripts.get(i).data().trim().contains("m3u8")){
                script = scripts.get(i).data().trim();
                break;
            }
        }
        //System.out.println(script);
        //script="html5player.setVideoHLS('https://hls-hw.xvideos-cdn.com/videos_new/hls/4c/82/4f/4c824f5070eff8cb16d01bdbc7a84eb5-1/hls.m3u8?e=1649918419&l=0&h=f65c4539682f78dbfaab7527ff71dc2a')";
        String regEx = "setVideoHLS\\('.+\\)";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(script);
        String playUrl = "";
        while(matcher.find()){
            playUrl = matcher.group();
            playUrl = playUrl.substring(playUrl.lastIndexOf("(")+2,playUrl.lastIndexOf("'"));
            break;
        }
        return playUrl;
    }

    /**
     * 获取所有视频信息
     * @param platForm
     * @param uploaderIdInPlat
     * @param type
     * @return
     */
    @Override
    public List<Video> getNotInCludeVideoOfUploader(PlatForm platForm, String uploaderIdInPlat, Integer type) {
        String uperDetailUrl="";
        //生成目标url链接
        if(type == 1){ //profile
            uperDetailUrl = profilesUperPrefix+uploaderIdInPlat+profilesUperEndfix;
        }else if(type == 2){//pornstar-channels
            uperDetailUrl = pornstarUperPrefix+uploaderIdInPlat+pornstarUperEndfix;
        }else if(type == 3) {//amateur模式
            uperDetailUrl = amateurUperPrefix+uploaderIdInPlat+amateurUperEndfix;
        }else if(type ==4){ //channel-model 模式
            uperDetailUrl = modelUperPrefix+uploaderIdInPlat+modelUperEndfix;
        }
        String result = SpiderUtil.getXvideosJson(uperDetailUrl).text();
        //计算页数
        XvideosPageInfo xvideosPageInfo = JSON.parseObject(result, XvideosPageInfo.class);
        if(xvideosPageInfo.getNb_per_page()>0){
            xvideosPageInfo.setTotalPage(xvideosPageInfo.getNb_videos()/ xvideosPageInfo.getNb_per_page());
        }else{
            xvideosPageInfo.setTotalPage(0);
        }
        List<Video> videos  = new ArrayList<Video>();
        //爬去每页数据封装
        for(int i =0;i<= xvideosPageInfo.getTotalPage();i++){
            uperDetailUrl= uperDetailUrl.substring(0, uperDetailUrl.lastIndexOf("/") + 1).concat(i+"");
            String res = SpiderUtil.getXvideosJson(uperDetailUrl).text();
            List<XvideosItem> videoItems = JSON.parseObject(res, XvideosPageInfo.class).getVideos();
            videoItems.forEach(item->{
                Video video = new Video();
                video.setTitle(item.getTf());
                video.setImage(item.getI());
                video.setDuration(item.getD());
                video.setPreView(getPreviewUrl(video.getImage()));
                //TODO 根绝文字 转换数字 3.2M
                video.setLikes(1L);
                video.setPlatId(2);
                video.setUpperType(getUpperType(item.getPu()));
                videos.add(video);
            });
        }
        //返回数据
        return videos;
    }
    private String getPreviewUrl(String imageUrl){
        imageUrl = imageUrl.replace("thumbs169","videopreview");
        return imageUrl.substring(0, imageUrl.lastIndexOf("/")).concat("_169.mp4");
    }

    /**         0.channel
     *       *  1.profiles
     *      * 	2.pornstar-channels
     *      * 	3.amateur-channels
     *      * 	4.model-channels
     * 根据url识别类型
     * @param url
     * @return
     */
    private Integer getUpperType(String url){
        if(url.startsWith("/model-channels")){
            return 4;
        }else if(url.startsWith("/amateur-channels")){
            return 3;
        }else if(url.startsWith("/pornstar-channels")){
            return 2;
        }else if(url.startsWith("/profiles")){
            return 1;
        }else if(url.startsWith("/channel")){
            return 0;
        }else{
            throw new ServiceException("获取UpperType异常");
        }
    }
    @Override
    public List<Video> getNotInCludeVideoOfChannel(PlatForm platForm, String uploaderIdInPlat) {
        return null;
    }

    @Override
    public Uploader getUploaderInfo(PlatForm platForm, String uploaderIdInPlat, Integer type) {
        String uperDetailUrl = getUperDetailUrl(platForm, uploaderIdInPlat, type);
        Document doc = SpiderUtil.getXvideosDocument(uperDetailUrl);
        Element pnode = doc.getElementsByAttributeValue("class", "profile-title").get(0);
        Uploader uploader = new Uploader();
        uploader.setCountry("");
        uploader.setName(pnode.getElementsByAttributeValue("class","text-danger").text().trim());
        uploader.setDescription(pnode.getElementById("header-about-me").text().trim());
        uploader.setImage(pnode.getElementsByTag("img").get(0).attr("src"));
        uploader.setIdInPlat(uploaderIdInPlat);
        return uploader;
    }

    @Override
    public Channel getChannelInfo(PlatForm platForm, String channelIdInPlat) {
        return null;
    }
    public static void main(String[] args) throws IOException {
        XvideosSpiderService xvideosSpiderService = new XvideosSpiderService();
        /*Document document = Jsoup.connect("https://www.xvideos.com/profiles/hardtied/videos/best/0").ignoreContentType(true).get();
        XvideosPageInfo xvideosPageInfo = JSON.parseObject(document.text(), XvideosPageInfo.class);
        xvideosPageInfo.setTotalPage(xvideosPageInfo.getNb_videos()/ xvideosPageInfo.getNb_per_page());*/
        //Video videoInfo = xvideosSpiderService.getVideoInfo(PlatForm.xvideos, "51230955/bound_naked_babe_restrained_and_dildoed");
        //System.out.println(xvideosSpiderService.getVideoPlayUrl(PlatForm.xvideos,"68287749/_"));
        List<Video> videos = xvideosSpiderService.getNotInCludeVideoOfUploader(PlatForm.xvideos, "txrwmm", 3);
        videos.forEach(item->System.out.println(item));

    }
}
