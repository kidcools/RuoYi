package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TBusiness;

import java.util.List;

/**
 * 商务账号管理Mapper接口
 * 
 * @author milestar
 * @date 2021-09-14
 */
public interface TBusinessMapper 
{
    /**
     * 查询商务账号管理
     * 
     * @param businessId 商务账号管理主键
     * @return 商务账号管理
     */
    public TBusiness selectTBusinessByBusinessId(Long businessId);

    /**
     * 查询商务账号管理列表
     * 
     * @param tBusiness 商务账号管理
     * @return 商务账号管理集合
     */
    public List<TBusiness> selectTBusinessList(TBusiness tBusiness);

    /**
     * 新增商务账号管理
     * 
     * @param tBusiness 商务账号管理
     * @return 结果
     */
    public int insertTBusiness(TBusiness tBusiness);

    /**
     * 修改商务账号管理
     * 
     * @param tBusiness 商务账号管理
     * @return 结果
     */
    public int updateTBusiness(TBusiness tBusiness);

    /**
     * 删除商务账号管理
     * 
     * @param businessId 商务账号管理主键
     * @return 结果
     */
    public int deleteTBusinessByBusinessId(Long businessId);

    /**
     * 批量删除商务账号管理
     * 
     * @param businessIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTBusinessByBusinessIds(String[] businessIds);
}
