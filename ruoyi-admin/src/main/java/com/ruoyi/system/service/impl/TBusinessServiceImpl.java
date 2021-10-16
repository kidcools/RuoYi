package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.TBusiness;
import com.ruoyi.system.mapper.TBusinessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.ITBusinessService;
import com.ruoyi.common.core.text.Convert;

/**
 * 商务账号管理Service业务层处理
 * 
 * @author milestar
 * @date 2021-09-14
 */
@Service
public class TBusinessServiceImpl implements ITBusinessService 
{
    @Autowired
    private TBusinessMapper tBusinessMapper;

    /**
     * 查询商务账号管理
     * 
     * @param businessId 商务账号管理主键
     * @return 商务账号管理
     */
    @Override
    public TBusiness selectTBusinessByBusinessId(Long businessId)
    {
        return tBusinessMapper.selectTBusinessByBusinessId(businessId);
    }

    /**
     * 查询商务账号管理列表
     * 
     * @param tBusiness 商务账号管理
     * @return 商务账号管理
     */
    @Override
    public List<TBusiness> selectTBusinessList(TBusiness tBusiness)
    {
        return tBusinessMapper.selectTBusinessList(tBusiness);
    }

    /**
     * 新增商务账号管理
     * 
     * @param tBusiness 商务账号管理
     * @return 结果
     */
    @Override
    public int insertTBusiness(TBusiness tBusiness)
    {
        return tBusinessMapper.insertTBusiness(tBusiness);
    }

    /**
     * 修改商务账号管理
     * 
     * @param tBusiness 商务账号管理
     * @return 结果
     */
    @Override
    public int updateTBusiness(TBusiness tBusiness)
    {
        return tBusinessMapper.updateTBusiness(tBusiness);
    }

    /**
     * 批量删除商务账号管理
     * 
     * @param businessIds 需要删除的商务账号管理主键
     * @return 结果
     */
    @Override
    public int deleteTBusinessByBusinessIds(String businessIds)
    {
        return tBusinessMapper.deleteTBusinessByBusinessIds(Convert.toStrArray(businessIds));
    }

    /**
     * 删除商务账号管理信息
     * 
     * @param businessId 商务账号管理主键
     * @return 结果
     */
    @Override
    public int deleteTBusinessByBusinessId(Long businessId)
    {
        return tBusinessMapper.deleteTBusinessByBusinessId(businessId);
    }
}
