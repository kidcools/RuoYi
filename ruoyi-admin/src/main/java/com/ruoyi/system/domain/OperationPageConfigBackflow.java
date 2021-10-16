package com.ruoyi.system.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class OperationPageConfigBackflow implements Serializable {
    private static final long serialVersionUID = 1596699857412L;
    /**
     * operation_page_config_backflow_base表主键
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
     * 奖励模式:1-无条件奖励，2-阶梯存款奖励，3-连续存款奖励平均值，4-分享好友召回奖励
     */
    private Integer awardMode;
    /**
     * 活动开始时间
     */
    private LocalDateTime activityStartTime;
    /**
     * 活动结束时间
     */
    private LocalDateTime activityEndTime;
    /**
     * vip等级要求，以 逗号 隔开
     */
    private String vipGrade;
    /**
     * 会员账号要求是否绑定手机号
     */
    private Integer isBindPhNum;
    /**
     * 会员账号要求是否绑定银行卡
     */
    private Integer isBindBankCard;
    /**
     * 活动对象类型，0-为所有会员，1为白名单会员
     */
    private Integer activityObjType;
    /**
     * 白名单,名单id
     */
    private Integer whiteListId;
    /**
     * 白名单,名单名称
     */
    String whiteListName;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private Integer stxx;
}
