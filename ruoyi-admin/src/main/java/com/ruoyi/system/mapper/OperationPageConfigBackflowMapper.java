package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.OperationPageConfigBackflow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperationPageConfigBackflowMapper {
    /**
     * 查询回流活动，页签基础配置信息
     *
     * @param id 回流活动，页签基础配置信息主键
     * @return 回流活动，页签基础配置信息
     */
    public OperationPageConfigBackflow selectOperationPageConfigBackflowBaseById(Long id);

    /**
     * 查询回流活动，页签基础配置信息列表
     *
     * @param operationPageConfigBackflowBase 回流活动，页签基础配置信息
     * @return 回流活动，页签基础配置信息集合
     */
    public List<OperationPageConfigBackflow> selectOperationPageConfigBackflowBaseList(OperationPageConfigBackflow operationPageConfigBackflowBase);

    /**
     * 新增回流活动，页签基础配置信息
     *
     * @param operationPageConfigBackflowBase 回流活动，页签基础配置信息
     * @return 结果
     */
    public int insertOperationPageConfigBackflowBase(OperationPageConfigBackflow operationPageConfigBackflowBase);

    /**
     * 修改回流活动，页签基础配置信息
     *
     * @param operationPageConfigBackflowBase 回流活动，页签基础配置信息
     * @return 结果
     */
    public int updateOperationPageConfigBackflowBase(OperationPageConfigBackflow operationPageConfigBackflowBase);

    /**
     * 删除回流活动，页签基础配置信息
     *
     * @param id 回流活动，页签基础配置信息主键
     * @return 结果
     */
    public int deleteOperationPageConfigBackflowBaseById(Long id);

    /**
     * 批量删除回流活动，页签基础配置信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOperationPageConfigBackflowBaseByIds(String[] ids);
}
