package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.OperationPageConfigBackflowDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 回流活动-页签详细配置Mapper接口
 *
 * @author milestar
 * @date 2021-10-14
 */
@Mapper
public interface OperationPageConfigBackflowDetailMapper
{
    /**
     * 查询回流活动-页签详细配置
     *
     * @param id 回流活动-页签详细配置主键
     * @return 回流活动-页签详细配置
     */
    public OperationPageConfigBackflowDetail selectOperationPageConfigBackflowDetailById(Long id);

    /**
     * 查询回流活动-页签详细配置列表
     *
     * @param operationPageConfigBackflowDetail 回流活动-页签详细配置
     * @return 回流活动-页签详细配置集合
     */
    public List<OperationPageConfigBackflowDetail> selectOperationPageConfigBackflowDetailList(OperationPageConfigBackflowDetail operationPageConfigBackflowDetail);

    /**
     * 新增回流活动-页签详细配置
     *
     * @param operationPageConfigBackflowDetail 回流活动-页签详细配置
     * @return 结果
     */
    public int insertOperationPageConfigBackflowDetail(OperationPageConfigBackflowDetail operationPageConfigBackflowDetail);

    /**
     * 修改回流活动-页签详细配置
     *
     * @param operationPageConfigBackflowDetail 回流活动-页签详细配置
     * @return 结果
     */
    public int updateOperationPageConfigBackflowDetail(OperationPageConfigBackflowDetail operationPageConfigBackflowDetail);

    /**
     * 删除回流活动-页签详细配置
     *
     * @param id 回流活动-页签详细配置主键
     * @return 结果
     */
    public int deleteOperationPageConfigBackflowDetailById(Long id);

    /**
     * 批量删除回流活动-页签详细配置
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOperationPageConfigBackflowDetailByIds(String[] ids);
}
