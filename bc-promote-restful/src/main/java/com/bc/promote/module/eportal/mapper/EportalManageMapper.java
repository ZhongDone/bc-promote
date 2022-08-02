package com.bc.promote.module.eportal.mapper;

import com.bc.promote.module.eportal.dto.ManageInfoDTO;
import com.bc.promote.module.eportal.entity.EportalManage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理机构 Mapper 接口
 *
 * @author 万爷
 * @since 2022-07-27
 */
@Mapper
public interface EportalManageMapper extends BaseMapper<EportalManage> {

    /**
     * 查询机构树形结构
     * @return
     */
    List<ManageInfoDTO> getManageTree(@Param("parentId") Long parentId);
}
