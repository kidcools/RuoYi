package com.ruoyi.system.service;

import com.ruoyi.system.entity.Channel;
import com.ruoyi.system.entity.PlatForm;
import com.ruoyi.system.entity.Uploader;
import com.ruoyi.system.entity.Video;

import java.util.List;

public interface ICommonSpiderService {
    /**
     * 根绝平台和平台唯一标识 获取视频基本信息
     * @param platForm
     * @param idInPlat
     * @return
     */
    Video getVideoInfo(PlatForm platForm,String idInPlat);
    /**
     *
     */
    String getVideoDetailUrl(PlatForm platForm,String idInPlat);
    String getUperDetailUrl(PlatForm platForm,String idInPlat,Integer type);
    String getChannelDetailUrl(PlatForm platForm,String idInPlat);
    /**
     * 根据视频ID 获取视频动态播放URL
     * @param platForm
     * @param idInPlat
     * @return
     */
    String getVideoPlayUrl(PlatForm platForm,String idInPlat);

    /**
     *根绝uploaderID 获取未收录视频
     */
    List<Video> getNotInCludeVideoOfUploader(PlatForm platForm,String uploaderIdInPlat,Integer type);

    /**
     * 根绝channelId 获取未收录视频
     */
    List<Video> getNotInCludeVideoOfChannel(PlatForm platForm,String uploaderIdInPlat);
    /**
     * 根绝uploader的平台ID uploader基本信息
     */
    Uploader getUploaderInfo(PlatForm platForm,String uploaderIdInPlat,Integer type);
    /**
     *
     */
    Channel getChannelInfo(PlatForm platForm,String channelIdInPlat);



}
