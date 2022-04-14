package com.ruoyi.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *平台实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlatForm {
    public static final PlatForm pornhub = new PlatForm(1,"pornhub","",1,1,0);
    public static final PlatForm xvideos = new PlatForm(2,"xvideos","",1,1,0);
    private Integer id;
    private String name;
    private String img;
    /**
     * 是否支持频道模式
     */
    private Integer hasChannel;
    /**
     * 是否支持创作者模式
     */
    private Integer hasUploader;
    /**
     * 是否可以遍历所有视频
     */
    private Integer canLoadAll;

}
