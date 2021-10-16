package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.OperationPageConfigBackflowAm2;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
    /**
     * 回流活动-奖励模式-阶梯奖励-奖励设置。名中的编号是对应的奖励模式的编号Mapper接口
     *
     * @author ruoyi
     * @date 2021-10-14
     */
    @Mapper
    public interface OperationPageConfigBackflowAm2Mapper
    {
        /**
         * 查询回流活动-奖励模式-阶梯奖励-奖励设置。名中的编号是对应的奖励模式的编号
         *
         * @param id 回流活动-奖励模式-阶梯奖励-奖励设置。名中的编号是对应的奖励模式的编号主键
         * @return 回流活动-奖励模式-阶梯奖励-奖励设置。名中的编号是对应的奖励模式的编号
         */
        public OperationPageConfigBackflowAm2 selectOperationPageConfigBackflowAm2ById(Long id);

        /**
         * 查询回流活动-奖励模式-阶梯奖励-奖励设置。名中的编号是对应的奖励模式的编号列表
         *
         * @param operationPageConfigBackflowAm2 回流活动-奖励模式-阶梯奖励-奖励设置。名中的编号是对应的奖励模式的编号
         * @return 回流活动-奖励模式-阶梯奖励-奖励设置。名中的编号是对应的奖励模式的编号集合
         */
        public List<OperationPageConfigBackflowAm2> selectOperationPageConfigBackflowAm2List(OperationPageConfigBackflowAm2 operationPageConfigBackflowAm2);

        /**
         * 新增回流活动-奖励模式-阶梯奖励-奖励设置。名中的编号是对应的奖励模式的编号
         *
         * @param operationPageConfigBackflowAm2 回流活动-奖励模式-阶梯奖励-奖励设置。名中的编号是对应的奖励模式的编号
         * @return 结果
         */
        public int insertOperationPageConfigBackflowAm2(OperationPageConfigBackflowAm2 operationPageConfigBackflowAm2);

        /**
         * 修改回流活动-奖励模式-阶梯奖励-奖励设置。名中的编号是对应的奖励模式的编号
         *
         * @param operationPageConfigBackflowAm2 回流活动-奖励模式-阶梯奖励-奖励设置。名中的编号是对应的奖励模式的编号
         * @return 结果
         */
        public int updateOperationPageConfigBackflowAm2(OperationPageConfigBackflowAm2 operationPageConfigBackflowAm2);

        /**
         * 删除回流活动-奖励模式-阶梯奖励-奖励设置。名中的编号是对应的奖励模式的编号
         *
         * @param id 回流活动-奖励模式-阶梯奖励-奖励设置。名中的编号是对应的奖励模式的编号主键
         * @return 结果
         */
        public int deleteOperationPageConfigBackflowAm2ById(Long id);

        /**
         * 批量删除回流活动-奖励模式-阶梯奖励-奖励设置。名中的编号是对应的奖励模式的编号
         *
         * @param ids 需要删除的数据主键集合
         * @return 结果
         */
        public int deleteOperationPageConfigBackflowAm2ByIds(String[] ids);
    }

