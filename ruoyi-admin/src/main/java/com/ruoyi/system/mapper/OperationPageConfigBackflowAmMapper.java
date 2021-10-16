package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.OperationPageConfigBackflowAm1;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 回流活动-奖励模式-无条件领取-奖励设置。名中的编号是对应的奖励模式的编号Mapper接口
 *
 * @author milestar
 * @date 2021-10-14
 */
@Mapper
public interface OperationPageConfigBackflowAmMapper
{
    /**
     * 查询回流活动-奖励模式-无条件领取-奖励设置。名中的编号是对应的奖励模式的编号
     *
     * @param id 回流活动-奖励模式-无条件领取-奖励设置。名中的编号是对应的奖励模式的编号主键
     * @return 回流活动-奖励模式-无条件领取-奖励设置。名中的编号是对应的奖励模式的编号
     */
    public OperationPageConfigBackflowAm1 selectOperationPageConfigBackflowAm1ById(Long id);

    /**
     * 查询回流活动-奖励模式-无条件领取-奖励设置。名中的编号是对应的奖励模式的编号列表
     *
     * @param operationPageConfigBackflowAm1 回流活动-奖励模式-无条件领取-奖励设置。名中的编号是对应的奖励模式的编号
     * @return 回流活动-奖励模式-无条件领取-奖励设置。名中的编号是对应的奖励模式的编号集合
     */
    public List<OperationPageConfigBackflowAm1> selectOperationPageConfigBackflowAm1List(OperationPageConfigBackflowAm1 operationPageConfigBackflowAm1);

    /**
     * 新增回流活动-奖励模式-无条件领取-奖励设置。名中的编号是对应的奖励模式的编号
     *
     * @param operationPageConfigBackflowAm1 回流活动-奖励模式-无条件领取-奖励设置。名中的编号是对应的奖励模式的编号
     * @return 结果
     */
    public int insertOperationPageConfigBackflowAm1(OperationPageConfigBackflowAm1 operationPageConfigBackflowAm1);

    /**
     * 修改回流活动-奖励模式-无条件领取-奖励设置。名中的编号是对应的奖励模式的编号
     *
     * @param operationPageConfigBackflowAm1 回流活动-奖励模式-无条件领取-奖励设置。名中的编号是对应的奖励模式的编号
     * @return 结果
     */
    public int updateOperationPageConfigBackflowAm1(OperationPageConfigBackflowAm1 operationPageConfigBackflowAm1);

    /**
     * 删除回流活动-奖励模式-无条件领取-奖励设置。名中的编号是对应的奖励模式的编号
     *
     * @param id 回流活动-奖励模式-无条件领取-奖励设置。名中的编号是对应的奖励模式的编号主键
     * @return 结果
     */
    public int deleteOperationPageConfigBackflowAm1ById(Long id);

    /**
     * 批量删除回流活动-奖励模式-无条件领取-奖励设置。名中的编号是对应的奖励模式的编号
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOperationPageConfigBackflowAm1ByIds(String[] ids);
}
