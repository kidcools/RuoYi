package com.ruoyi.system.service;

public interface PhSpiderService {
    String getVideoUrl(String url);
    String getVideoUrlV2(String url);

    String download(String url);
}
