package com.ruoyi.system.domain;

import lombok.Data;

@Data
public class VideoInfo {
    private boolean defaultQuality;
    private String format;
    private String videoUrl;
    private String quality;
}
