package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.system.domain.Response;
import com.ruoyi.system.domain.UrlDto;
import com.ruoyi.system.service.PhSpiderService;
import com.ruoyi.system.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;

import static com.ruoyi.common.config.datasource.DynamicDataSourceContextHolder.log;
@Slf4j
@RestController
public class PornHubController {
    @Resource
    private PhSpiderService phSpiderService;
    @GetMapping("/phvideo")
    public @ResponseBody  Response getPhVideoUrl(@RequestParam("url") String url){
        return ResponseUtils.successRes(phSpiderService.getVideoUrl(url),"查询成功");
    }
    @PostMapping("/download")
    public @ResponseBody  Response download( @RequestBody UrlDto url){
        log.info("url:{}" ,url.getUrl());
        return ResponseUtils.successRes(phSpiderService.download(url.getUrl()),"下载成功");
    }
}
