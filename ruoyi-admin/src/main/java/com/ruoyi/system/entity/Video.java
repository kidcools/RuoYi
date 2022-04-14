package com.ruoyi.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 视频实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {
    private Long id;
    private String title;
    private String originTitle;
    private String detail;
    private String originDetail;
    private String image;
    private String preView;
    private Long views;
    private Long likes;
    private Long uperId;
    private Integer platId;
    private String idInPlat;
    private String videoUrlStr;
    private String tags;
    private Long categoryID;
    private String country;
    /**
     * 视频时长
     */
    private String duration;
    /**
     * 1是pornstar  2是model
     */
    private Integer upperType;
}
