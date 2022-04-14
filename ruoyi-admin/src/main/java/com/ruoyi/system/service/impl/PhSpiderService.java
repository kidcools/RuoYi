package com.ruoyi.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.system.domain.Customer;
import com.ruoyi.system.entity.*;
import com.ruoyi.system.service.ICommonSpiderService;
import com.ruoyi.system.utils.SpiderUtil;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.thymeleaf.model.IDocType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhSpiderService implements ICommonSpiderService {
    private final String platCode = "pornhub";
    private final String prefix = "https://cn.pornhub.com/view_video.php?viewkey=";
    private final String endfix ="";
    private final String pornStarUperPrefix = "https://cn.pornhub.com/pornstar/";
    private final String pornStarUperEndfix = "/videos/upload?page=1";
    private final String modelUperPrefix = "https://cn.pornhub.com/model/";
    private final String modelUperEndfix = "/videos?page=1";
    private final String channelPrefix = "https://cn.pornhub.com/channels/";
    private final String channelEndfix = "/videos?page=1";

    @Override
    public Video getVideoInfo(PlatForm platForm, String idInPlat) {
        checkPlatForm(platForm);
        String videoDetailUrl = getVideoDetailUrl(platForm,idInPlat);
        Document document = SpiderUtil.getPhDocument(videoDetailUrl);
        Video video = new Video();
        try {
             //设置标题
             video.setTitle(document.getElementsByTag("title").get(0).text());
             //String detail;
             video.setDetail(getDetail(document));
             //String image;
             video.setImage(getImage(document));
             //String preView;
             video.setPreView(getPreView(document));
             //Long views;
             video.setViews(getViews(document));
             //Long likes;
             video.setLikes(getLikes(document));
             //Long uperId;
             video.setUperId(getUper(document).getId());
             //Long platId;
             video.setPlatId(PlatForm.pornhub.getId());
             //String idInPlat;
             video.setIdInPlat(idInPlat);
             //String tags;
             video.setTags(getTags(document));
             //Long categoryID;
             video.setCategoryID(getCategory(document).getId());
             //String country;
             video.setCountry(getCountry(document));
        }catch (Exception e){
            e.printStackTrace();
        }

        return video;
    }
    @Override
    public Uploader getUploaderInfo(PlatForm platForm, String uploaderIdInPlat,Integer type) {
        Uploader uploader = new Uploader();
        String uperDetailUrl = getUperDetailUrl(platForm, uploaderIdInPlat, type);
        Document document = SpiderUtil.getPhDocument(uperDetailUrl);
        //String name;
        uploader.setName(getUploaderName(document));
        uploader.setIdInPlat(uploaderIdInPlat);
        //Integer videos;
        uploader.setVideos(getUploaderVideos(document));
        //String country;
        uploader.setCountry(getUploaderCountry(document));
        //String image;
        uploader.setImage(getUploaderImage(document));
        //String description
        uploader.setDescription(getUploaderDescription(document));
        return uploader;
    }
    /**
     * 获取频道基本信息
     * @param platForm
     * @param channelIdInPlat
     * @return
     */
    @Override
    public Channel getChannelInfo(PlatForm platForm, String channelIdInPlat) {
        String channelDetailUrl = getChannelDetailUrl(platForm, channelIdInPlat);
        Document document = SpiderUtil.getPhDocument(channelDetailUrl);
        Channel channel = new Channel();
        //String name;
        channel.setName(getChannelName(document));
        //String detail;
        channel.setDetail(getChannelDetail(document));
        //String image;
        channel.setImage(getChannelImage(document));
        //String idInplat;
        channel.setIdInplat(channelIdInPlat);
        //Integer platForm;
        channel.setPlatForm(PlatForm.pornhub.getId());
        return channel;
    }



    private String getChannelImage(Document document) {
        return document.getElementById("getAvatar").attr("src");
    }

    private String getChannelDetail(Document document) {
        return document.getElementsByAttributeValue("class","cdescriptions").get(0).getElementsByTag("p").get(0).text().trim();
    }

    private String getChannelName(Document document) {
        return document.getElementsByAttributeValue("class","title floatLeft").get(0).getElementsByTag("h1").get(0).text();
    }


    @Override
    public String getVideoDetailUrl(PlatForm platForm, String idInPlat) {
        checkPlatForm(platForm);
        return prefix+idInPlat+endfix;
    }

    /**
     *
     * @param platForm
     * @param idInPlat
     * @param type 1是pornstar  2是model
     * @return
     */
    @Override
    public String getUperDetailUrl(PlatForm platForm, String idInPlat,Integer type) {
        checkPlatForm(platForm);
        if(type==1){
            return pornStarUperPrefix+idInPlat+pornStarUperEndfix;
        }else if(type == 2){
            return modelUperPrefix+idInPlat+modelUperEndfix;
        }else{
            return channelPrefix+idInPlat+channelEndfix;
        }
    }

    @Override
    public String getChannelDetailUrl(PlatForm platForm, String idInPlat) {
        checkPlatForm(platForm);
        return channelPrefix+idInPlat+channelEndfix;
    }

    /**
     *
     * 获取视频播放的地址
     * @param platForm
     * @param idInPlat
     * @return
     */
    @Override
    public String getVideoPlayUrl(PlatForm platForm, String idInPlat) {
        return null;
    }

    @Override
    public List<Video> getNotInCludeVideoOfUploader(PlatForm platForm, String uploaderIdInPlat,Integer type) {
        //获取所有的页数
        Integer pagecount = getLastPageNum(PlatForm.pornhub, uploaderIdInPlat, type);
        System.out.println(pagecount);
        //根据页数倒序爬取视频条目 数据库未收录的入库 放入列表中 收录未收录的视频
        String url = "";
        List<Video> videos = new ArrayList<Video>();
        for(int i=1;i<=pagecount;i++){
            url = getUperDetailUrl(platForm, uploaderIdInPlat, type);
            url = url.substring(0, url.lastIndexOf("=") + 1)+""+i;
            System.out.println(url);
            Document document = SpiderUtil.getPhDocument(url);
            videos.addAll(getVideoInfoFromPage(document,type));
        }
        //封装数据返回  根据列表中的视频进一步完善内容
        return videos;
    }
    @Override
    public List<Video> getNotInCludeVideoOfChannel(PlatForm platForm, String channelIdInPlat) {
        //获取所有的页数
        Integer pagecount = getLastPageNum(PlatForm.pornhub, channelIdInPlat, 3);
        //根据页数倒序爬取视频条目 数据库未收录的入库 放入列表中 收录未收录的视频
        String url = "";
        List<Video> videos = new ArrayList<Video>();
        for(int i=1;i<=pagecount;i++){
            url = getUperDetailUrl(platForm, channelIdInPlat, 3);
            url = url.substring(0, url.lastIndexOf("=") + 1)+""+i;
            Document document = SpiderUtil.getPhDocument(url);
            System.out.println(url);
            videos.addAll(getVideoInfoFromPage(document,3));
        }
        //封装数据返回  根据列表中的视频进一步完善内容
        return videos;
    }
    private List<Video> getVideoInfoFromPage(Document document,Integer type) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Elements items;
        if(type==1){//pornstar
           items =  document.getElementsByAttributeValue("class","videos row-5-thumbs").get(0).getElementsByTag("li");
        }else if(type == 2){//model
           items =  document.getElementById("mostRecentVideosSection").getElementsByTag("li");
        }else{//channel
            items =  document.getElementsByAttributeValue("class","videos row-5-thumbs videosGridWrapper").get(0).getElementsByTag("li");
        }
        List<Video> videos = new ArrayList<Video>();//videos row-5-thumbs
        for (Element item:items) {
            Video video = new Video();
            //image
            String image = item.getElementsByAttributeValue("class","phimage").get(0).getElementsByTag("img").get(0).attr("data-thumb_url");
            video.setImage(image);
            //title
            String title  = item.getElementsByAttributeValue("class","phimage").get(0).getElementsByTag("img").get(0).attr("alt");
            video.setTitle(title);
            //preview
            String preview = item.getElementsByAttributeValue("class","phimage").get(0).getElementsByTag("img").get(0).attr("data-mediabook");
            video.setPreView(preview);
            //idInPlat
            String detailUrl =item.getElementsByAttributeValue("class","phimage").get(0).getElementsByTag("a").attr("src");
            String viewKey = detailUrl.substring(detailUrl.lastIndexOf("=")+1,detailUrl.length());
            video.setIdInPlat(viewKey);
            //views
            video.setViews(convertNumformStr(item.getElementsByAttributeValue("class", "videoDetailsBlock").get(0).getElementsByTag("var").get(0).text()));
            //设置市场
            video.setDuration(item.getElementsByAttributeValue("class","duration").get(0).text());
            videos.add(video);
        }
        return videos;
    }

    /**
     * 获得个人空间最后一页页数
     * @return
     */
    Integer getLastPageNum(PlatForm platForm,String uploaderIdInPlat ,Integer type){
        String uperDetailUrl = getUperDetailUrl(platForm, uploaderIdInPlat,type);
        Document document = SpiderUtil.getPhDocument(uperDetailUrl);
        if(CollectionUtil.isEmpty(document.getElementsByAttributeValue("class", "pagination3"))){
            return 1;
        }
        Elements lis = document.getElementsByAttributeValue("class", "pagination3").get(0).getElementsByTag("li");
        String lastPageNum = "1";
        String nowEndPageNum = lis.get(lis.size() - 1).getElementsByTag("img").size()>0? lis.get(lis.size() - 2).getElementsByTag("a").text().trim() : lis.get(lis.size() - 1).getElementsByTag("span").text();
        while(!lastPageNum.equals(nowEndPageNum)){
            lastPageNum =  nowEndPageNum;
            uperDetailUrl = uperDetailUrl.substring(0, uperDetailUrl.lastIndexOf("=")+1)+nowEndPageNum;
            document = SpiderUtil.getPhDocument(uperDetailUrl);
            lis = document.getElementsByAttributeValue("class", "pagination3").get(0).getElementsByTag("li");
            nowEndPageNum = lis.get(lis.size() - 1).getElementsByTag("img").size()>0? lis.get(lis.size() - 2).getElementsByTag("a").text().trim() : lis.get(lis.size() - 1).getElementsByTag("span").text();
        }
        return Integer.parseInt(lastPageNum);
    }






    private String getUploaderDescription(Document document) {
        return document.getElementsByAttributeValue("class", "aboutMeSection sectionDimensions").get(0).getElementsByTag("div").get(1).text().trim();
    }

    private String getUploaderImage(Document document) {
        Element getAvatar = document.getElementById("getAvatar");
        return  null == getAvatar?"":getAvatar.attr("src");
    }

    private String getUploaderCountry(Document document) {
        return "";
    }

    private Integer getUploaderVideos(Document document) {
        return 0;
    }

    private String getUploaderName(Document document) {
        return document.getElementsByAttributeValue("class", "nameSubscribe").get(0).getElementsByTag("h1").get(0).text();
    }


    private void checkPlatForm(PlatForm platForm){
        if(null==platForm||!platForm.getName().equals(platCode)){
            throw new ServiceException("平台错误:platInfo:"+platForm);
        }
    }

    private String getCountry(Document document) {
        return "cn";
    }

    private Category getCategory(Document document) {
        Category category = new Category(1L,"测试分类","");
        return category;
    }

    private String getTags(Document document) {
        Elements target = document.getElementsByAttributeValue("class", "tagsWrapper");
        if(CollectionUtil.isNotEmpty(target)){
            Element wrap = target.get(0);
            Elements tagEles = wrap.getElementsByAttributeValue("class", "item");
            if(CollectionUtil.isNotEmpty(tagEles)){
                String tagstr = tagEles.stream().map(item -> item.text().trim()).collect(Collectors.joining(","));
                return tagstr;
            }else{
                return "";
            }
        }else{
            throw new ServiceException("标签爬取出错");
        }

    }

    private String getidInPlat(Document document) {
        Elements target= document.getElementsByAttributeValue("property", "og:url");
        if(CollectionUtil.isNotEmpty(target)){
            String url = target.get(0).attr("content");
            url = url.substring(url.lastIndexOf("=")+1,url.length());
            return url;
        }else{
            throw new ServiceException("平台唯一标识获取出错");
        }
    }

    //TODO 获取用户的平台表示  去数据库查寻uploader信息  没有的话进行 爬取入库
    private Uploader getUper(Document document) {
        Uploader uploader  = new Uploader(1L,"miles",100,"cn","miles.img","","");
        return uploader;
    }

    private Long getViews(Document document) {
        Elements target = document.getElementsByAttributeValue("class", "count");
        if(!CollectionUtil.isEmpty(target)){
            return convertNumformStr(target.get(0).text().trim());
        }else{
            throw new ServiceException("获取总览数量出错");
        }

    }
    //TODO
    private String getPreView(Document document) {
        //下载视频
        //上传
        return "";
    }

    private String getImage(Document document) {
        Elements meta_ = document.getElementsByTag("meta ");
        List<Element> target = meta_.stream().filter(item -> {
            return item.attr("property").equals("og:image");
        }).collect(Collectors.toList());
        if(CollectionUtil.isEmpty(target)){
            throw new ServiceException("image 爬取失败");
        }else{
            return target.get(0).attr("content");
        }
    }

    private String getDetail(Document document) {
        Elements meta_ = document.getElementsByTag("meta ");
        List<Element> target = meta_.stream().filter(item -> {
            return item.attr("property").equals("og:description");
        }).collect(Collectors.toList());
        if(CollectionUtil.isEmpty(target)){
            throw new ServiceException("description 爬取失败");
        }else{
            return target.get(0).attr("content");
        }
    }

    private Long getLikes(Document document) {
        String votesUp = document.getElementsByClass("votes-fav-wrap extendedIcons").get(0).getElementsByClass("votesUp").get(0).text().trim();
        return convertNumformStr(votesUp);
    }
    //TODO 完成字符串的转换
    private Long convertNumformStr(String votesUp){
        return 1000L;
    }
    public static void main(String[] args){
        PhSpiderService phSpiderService = new PhSpiderService();
        //Video video = phSpiderService.getVideoInfo(PlatForm.pornhub, "ph6221518574baf");
        //Uploader uploader = phSpiderService.getUploaderInfo(PlatForm.pornhub, "angel");
        //Channel channel = phSpiderService.getChannelInfo(PlatForm.pornhub,"twistys");
        //System.out.println(channel);
        List<Video> videos = phSpiderService.getNotInCludeVideoOfUploader(PlatForm.pornhub, "johnny-sins", 1);
        //List<Video> videos = phSpiderService.getNotInCludeVideoOfChannel(PlatForm.pornhub, "exotic4k");
        for (Video video : videos) {
            System.out.println(video);
        }
        //System.out.println(phSpiderService.getLastPageNum(PlatForm.pornhub,"allgirlmassage",3));https://cn.pornhub.com/channels/got-mylf https://cn.pornhub.com/channels/britneyblue https://cn.pornhub.com/channels/freeuse-fantasy https://cn.pornhub.com/channels/exotic4k
    }
}
