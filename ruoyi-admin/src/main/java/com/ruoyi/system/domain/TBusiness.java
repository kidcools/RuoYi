package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商务账号管理对象 t_business
 * 
 * @author milestar
 * @date 2021-09-14
 */
public class TBusiness extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long businessId;

    /** 商务账号 */
    @Excel(name = "商务账号")
    private String businessAccount;

    /** 商务姓名 */
    @Excel(name = "商务姓名")
    private String businessName;

    /** 商务密码 */
    @Excel(name = "商务密码")
    private String businessPwd;

    /** 0-未删除，1-已删除 */
    @Excel(name = "0-未删除，1-已删除")
    private Integer isDelete;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateAt;

    public void setBusinessId(Long businessId) 
    {
        this.businessId = businessId;
    }

    public Long getBusinessId() 
    {
        return businessId;
    }
    public void setBusinessAccount(String businessAccount) 
    {
        this.businessAccount = businessAccount;
    }

    public String getBusinessAccount() 
    {
        return businessAccount;
    }
    public void setBusinessName(String businessName) 
    {
        this.businessName = businessName;
    }

    public String getBusinessName() 
    {
        return businessName;
    }
    public void setBusinessPwd(String businessPwd) 
    {
        this.businessPwd = businessPwd;
    }

    public String getBusinessPwd() 
    {
        return businessPwd;
    }
    public void setIsDelete(Integer isDelete) 
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() 
    {
        return isDelete;
    }
    public void setCreateAt(Date createAt) 
    {
        this.createAt = createAt;
    }

    public Date getCreateAt() 
    {
        return createAt;
    }
    public void setUpdateAt(Date updateAt) 
    {
        this.updateAt = updateAt;
    }

    public Date getUpdateAt() 
    {
        return updateAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("businessId", getBusinessId())
            .append("businessAccount", getBusinessAccount())
            .append("businessName", getBusinessName())
            .append("businessPwd", getBusinessPwd())
            .append("isDelete", getIsDelete())
            .append("createAt", getCreateAt())
            .append("updateAt", getUpdateAt())
            .toString();
    }
}
