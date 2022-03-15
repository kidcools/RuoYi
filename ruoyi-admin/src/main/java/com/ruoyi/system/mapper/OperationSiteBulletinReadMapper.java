package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.OperationSiteBulletinRead;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper

    public interface OperationSiteBulletinReadMapper {
        /**
         * 查询【请填写功能名称】
         *
         * @param id 【请填写功能名称】主键
         * @return 【请填写功能名称】
         */

        public OperationSiteBulletinRead selectOperationSiteBulletinReadById(Long id);

        /**
         * 查询【请填写功能名称】列表
         *
         * @param operationSiteBulletinRead 【请填写功能名称】
         * @return 【请填写功能名称】集合
         */

        public List<OperationSiteBulletinRead> selectOperationSiteBulletinReadList(OperationSiteBulletinRead operationSiteBulletinRead);
        /**
         * 查询【请填写功能名称】列表
         *
         * @param ids 【目标id】
         * @return 【请填写功能名称】集合
         */

        public List<OperationSiteBulletinRead> selectIdInIds(@Param("ids") List<Long> ids, @Param("memberId") Long memberId, @Param("stxx") Long stxx);
        /**
         * 新增【请填写功能名称】
         *
         * @param operationSiteBulletinRead 【请填写功能名称】
         * @return 结果
         */
        public int insertOperationSiteBulletinRead(OperationSiteBulletinRead operationSiteBulletinRead);
        /**
         * 批量新增【请填写功能名称】
         *
         * @param operationSiteBulletinReadList
         * @return 结果
         */
        public int insertOperationSiteBulletinReadBatch(@Param("list")List<OperationSiteBulletinRead> operationSiteBulletinReadList);

        /**
         * 修改【请填写功能名称】
         *
         * @param operationSiteBulletinRead 【请填写功能名称】
         * @return 结果
         */
        public int updateOperationSiteBulletinRead(OperationSiteBulletinRead operationSiteBulletinRead);

        /**
         * 删除【请填写功能名称】
         *
         * @param id 【请填写功能名称】主键
         * @return 结果
         */
        public int deleteOperationSiteBulletinReadById(Long id);

        /**
         * 批量删除【请填写功能名称】
         *
         * @param ids 需要删除的数据主键集合
         * @return 结果
         */
        public int deleteOperationSiteBulletinReadByIds(String[] ids);

        void updateDeletedBatch(@Param("ids") List<Long> bulletinIds, @Param("memberId")Long memberId, @Param("stxx")long stxx);
    }

