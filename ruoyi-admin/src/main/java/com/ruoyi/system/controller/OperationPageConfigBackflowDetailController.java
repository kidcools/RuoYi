package com.ruoyi.system.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.system.domain.OperationPageConfigBackflow;
import com.ruoyi.system.domain.OperationPageConfigBackflowAm1;
import com.ruoyi.system.domain.OperationPageConfigBackflowDetail;
import com.ruoyi.system.mapper.OperationPageConfigBackflowAmMapper;
import com.ruoyi.system.mapper.OperationPageConfigBackflowDetailMapper;
import com.ruoyi.system.mapper.OperationPageConfigBackflowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/backflowdetail")
public class OperationPageConfigBackflowDetailController {
    @Resource
    private OperationPageConfigBackflowDetailMapper operationPageConfigBackflowDetailMapper;
    @Resource
    private OperationPageConfigBackflowMapper operationPageConfigBackflowMapper;
    @Resource
    private OperationPageConfigBackflowAmMapper operationPageConfigBackflowAmMapper;
    @GetMapping("/all")
    public OperationPageConfigBackflowDetail getAllOperationPageConfigBacflow(){
        OperationPageConfigBackflow operationPageConfigBackflow = new OperationPageConfigBackflow();
        operationPageConfigBackflow.setActivityId(12L);
        operationPageConfigBackflow.setConfigId(123L);
        OperationPageConfigBackflow result1 = operationPageConfigBackflowMapper.selectOperationPageConfigBackflowBaseList(operationPageConfigBackflow).get(0);
        OperationPageConfigBackflowDetail operationPageConfigBackflowDetail = new OperationPageConfigBackflowDetail();
        operationPageConfigBackflowDetail.setActivityId(12L);
        operationPageConfigBackflowDetail.setConfigId(123L);
        OperationPageConfigBackflowDetail result2 = operationPageConfigBackflowDetailMapper.selectOperationPageConfigBackflowDetailList(operationPageConfigBackflowDetail).get(0);
        BeanUtils.copyProperties(result1,result2);
        //获得基本信息
        //获得规则配置信息
        if(result2.getAwardMode()==1){//无条件奖励领取
            OperationPageConfigBackflowAm1 am1 = new OperationPageConfigBackflowAm1();
            am1.setActivityId(result2.getActivityId());
            am1.setConfigId(result2.getConfigId());
            List<OperationPageConfigBackflowAm1> am1s = operationPageConfigBackflowAmMapper.selectOperationPageConfigBackflowAm1List(am1);
            //封装无条件奖励模式配置信息
            result2.setOperationPageConfigBackflowAm1s(am1s);
        }else{

        }
        return result2;
    }

    @GetMapping("/enc/{value}")
    public String enc(@PathVariable("value") String value){
        return "";
    }

    @GetMapping("/csv")
    public String csv(){
        try {
            FileInputStream fileInputStream = new FileInputStream("D://test.xlsx");
            ExcelReader reader = ExcelUtil.getReader(fileInputStream);
            List<List<Object>> rows = reader.read();
            String info = (String)rows.get(0).get(0);
            if(info.equals("订单号")){
                log.info(info);
                rows.forEach(row->{
                    log.info(row.get(0).toString());
                });
            }

            return info;
        }catch(Exception e){
        }
        return "";
    }
}
