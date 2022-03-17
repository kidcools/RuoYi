package com.ruoyi.system.service;

public interface PhSpiderService {
    String getVideoUrl(String url);
    String getVideoUrlV2(String url);

    /**
     * 测试ip有效性
     * @param url
     * @return
     */
    String getVideoUrlV3(String url);
    String download(String url);

    String getVideoUrlV4(String url,String proxy,Integer host);
}
