package com.ruoyi.system.domain;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 回流活动-奖励模式-阶梯奖励-奖励设置表。表名中的编号是对应的奖励模式的编号
 */
public class OperationPageConfigBackflowAm2 implements Serializable {
    private static final long serialVersionUID = 1596699857412L;
    /**
     * operation_page_config_backflow_am2 表主键
     */
    private Long id;
    /**
     * operation_activity_info表id，活动id
     */
    @NotNull(message = "活动的主键必填")
    private  Long  activityId;
    /**
     * operation_page_config表id，分页配置表id
     */
    @NotNull(message = "分页主键必填")
    private  Long  configId;
    /**
     * '存款金额'
     */
    private BigDecimal deposit;
    /**
     * '最低转账金额'
     */
    private BigDecimal lastTransferAccount;
    /**
     * '红利
     */
    private BigDecimal bonus;
    /**
     * 排序
     */
    private Integer sort;

    private Integer stxx;
}
