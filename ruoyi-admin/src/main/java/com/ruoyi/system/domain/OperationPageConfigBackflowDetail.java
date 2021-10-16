package com.ruoyi.system.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 回流活动-页签详细配置
 */
@Data
public class OperationPageConfigBackflowDetail extends OperationPageConfigBackflow implements Serializable {
    private static final long serialVersionUID = 1596699857412L;
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
     * 红利发放钱包，1-为中心钱包，2-为场馆钱包
     */
    private Integer bonusWallet;
    /**
     * 审核方式，0-为无需审核（场馆钱包时无需审核），1-为自动审核，2-为手动审核
     */
    private Integer auditMode;
    /**
     * '流水倍数'
     */
    private Integer flowMultiple;
    /**
     * 是否为特殊优惠，0-为否，1-为是
     */
    private Integer isSpecialDiscounts;
    /**
     * 是否开启同ip限制，0-为关闭，1-为开启
     */
    private Integer isAlikeIp;
    /**
     * 是否开启同设别限制，0-为关闭，1-为开启
     */
    private Integer isAlikeDevice;
    /**
     * 游戏场馆id，以 逗号 隔开
     */
    private String gameVenueId;
    /**
     * 是否与反水共享，0-为否，1-为是
     */
    private Integer isBackwaterShare;
    /**
     * '单日最低存款金额'
     */
    private BigDecimal minDepositDay;
    /**
     * '最大累计天数'
     */
    private Integer maxDayNum;
    /**
     * '红利上限'
     */
    private BigDecimal bonusUpperLimit;
    /** 邀请对象类型，0-为所有会员，1为白名单会员 */
    private Integer inviteObjType;

    /** 邀请白名单,名单id */
    private Long inviteWhiteListId;

    /** 邀请白名单,名单名称 */
    private String inviteWhiteListName;

    /** 邀请成功标识(标准) */
    private Integer inviteTag;

    /** 当为好友召回时，该字段有值，为APP/H5分享二维码配图 */
    private String appH5RecallQrcode;

    /** 当为连续存款奖励平均值时，该字段有值，WEB端存款记录卡片背景图 */
    private String ceaselessDeposit;

    /**
     * 奖励模式-无条件领取-奖励设置
     */
    private List<OperationPageConfigBackflowAm1> OperationPageConfigBackflowAm1s;
    /**
     * 奖励模式-阶梯奖励-奖励设置
     */
    private List<OperationPageConfigBackflowAm2> OperationPageConfigBackflowAm2s;
    /**
     * 奖励模式-分享好友召回-奖励设置
     */
    private List<OperationPageConfigBackflowAm4> OperationPageConfigBackflowAm4s;



}
