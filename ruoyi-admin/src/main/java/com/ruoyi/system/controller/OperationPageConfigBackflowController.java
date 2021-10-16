package com.ruoyi.system.controller;

import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.system.domain.OperationPageConfigBackflow;
import com.ruoyi.system.mapper.OperationPageConfigBackflowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/backflow")
public class OperationPageConfigBackflowController {
    @Resource
    private OperationPageConfigBackflowMapper operationPageConfigBackflowMapper;
    @GetMapping("/all")
    public List<OperationPageConfigBackflow> getAllOperationPageConfigBacflow(){
        OperationPageConfigBackflow operationPageConfigBackflow = new OperationPageConfigBackflow();
        operationPageConfigBackflow.setActivityId(12L);
        operationPageConfigBackflow.setConfigId(123L);
        return operationPageConfigBackflowMapper.selectOperationPageConfigBackflowBaseList(operationPageConfigBackflow);

    }
}
