package com.ruoyi.system.utils;

import com.ruoyi.system.domain.Response;


public  class ResponseUtils  {

    public  static Response successRes(Object data, String msg){
        return new Response(data,msg,0);
    }
    public static Response errorRes(Object data,String msg){
        return new Response(data,msg,10001);
    }
}
