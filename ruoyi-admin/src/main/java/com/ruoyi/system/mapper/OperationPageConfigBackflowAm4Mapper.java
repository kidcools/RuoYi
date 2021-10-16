package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.OperationPageConfigBackflowAm4;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperationPageConfigBackflowAm4Mapper {

        /**
         * 查询回流活动-奖励模式-分享好友召回-奖励设置。名中的编号是对应的奖励模式的编号
         *
         * @param id 回流活动-奖励模式-分享好友召回-奖励设置。名中的编号是对应的奖励模式的编号主键
         * @return 回流活动-奖励模式-分享好友召回-奖励设置。名中的编号是对应的奖励模式的编号
         */
        public OperationPageConfigBackflowAm4 selectOperationPageConfigBackflowAm4ById(Long id);

        /**
         * 查询回流活动-奖励模式-分享好友召回-奖励设置。名中的编号是对应的奖励模式的编号列表
         *
         * @param operationPageConfigBackflowAm4 回流活动-奖励模式-分享好友召回-奖励设置。名中的编号是对应的奖励模式的编号
         * @return 回流活动-奖励模式-分享好友召回-奖励设置。名中的编号是对应的奖励模式的编号集合
         */
        public List<OperationPageConfigBackflowAm4> selectOperationPageConfigBackflowAm4List(OperationPageConfigBackflowAm4 operationPageConfigBackflowAm4);

        /**
         * 新增回流活动-奖励模式-分享好友召回-奖励设置。名中的编号是对应的奖励模式的编号
         *
         * @param operationPageConfigBackflowAm4 回流活动-奖励模式-分享好友召回-奖励设置。名中的编号是对应的奖励模式的编号
         * @return 结果
         */
        public int insertOperationPageConfigBackflowAm4(OperationPageConfigBackflowAm4 operationPageConfigBackflowAm4);

        /**
         * 修改回流活动-奖励模式-分享好友召回-奖励设置。名中的编号是对应的奖励模式的编号
         *
         * @param operationPageConfigBackflowAm4 回流活动-奖励模式-分享好友召回-奖励设置。名中的编号是对应的奖励模式的编号
         * @return 结果
         */
        public int updateOperationPageConfigBackflowAm4(OperationPageConfigBackflowAm4 operationPageConfigBackflowAm4);

        /**
         * 删除回流活动-奖励模式-分享好友召回-奖励设置。名中的编号是对应的奖励模式的编号
         *
         * @param id 回流活动-奖励模式-分享好友召回-奖励设置。名中的编号是对应的奖励模式的编号主键
         * @return 结果
         */
        public int deleteOperationPageConfigBackflowAm4ById(Long id);

        /**
         * 批量删除回流活动-奖励模式-分享好友召回-奖励设置。名中的编号是对应的奖励模式的编号
         *
         * @param ids 需要删除的数据主键集合
         * @return 结果
         */
        public int deleteOperationPageConfigBackflowAm4ByIds(String[] ids);
    }

