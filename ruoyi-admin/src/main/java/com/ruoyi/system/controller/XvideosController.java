package com.ruoyi.system.controller;

import com.ruoyi.system.domain.Response;
import com.ruoyi.system.service.PhSpiderService;
import com.ruoyi.system.service.XvideoSpiderService;
import com.ruoyi.system.utils.ResponseUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class XvideosController {
    @Resource
    private XvideoSpiderService xvideoSpiderService;
    @GetMapping("/xvideo")
    public @ResponseBody  Response getXVideoUrl(@RequestParam("url") String url){
        return ResponseUtils.successRes(xvideoSpiderService.getVideoUrl(url),"查询成功");
    }
}
