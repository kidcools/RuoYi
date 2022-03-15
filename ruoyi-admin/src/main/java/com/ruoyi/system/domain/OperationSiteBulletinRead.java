package com.ruoyi.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationSiteBulletinRead implements Serializable {
    private static final long serialVersionUID = 4831538662409817105L;

    /** 主键id */
    private Long id;

    /** 会员id */
    private Long memberId;

    /** 系统公告id */
    private Long bulletinId;

    /** 创建人 */
    private String createdBy;

    /** 创建时间 */
    private LocalDateTime createdAt;

    /** 更新人 */
    private String updatedBy;

    /** 更新时间 */
    private LocalDateTime updatedAt;

    /** stxx */
    private Long stxx;

    /** 是否删除(1-删除,0-不删除) */
    private Integer isDelete;
}
