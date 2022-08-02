package com.bc.promote.module.eportal.mapper;

import com.bc.promote.module.eportal.dto.MenuInfoDTO;
import com.bc.promote.module.eportal.entity.EportalResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单资源表 Mapper 接口
 *
 * @author 万爷
 * @since 2022-07-27
 */
@Mapper
public interface EportalResourceMapper extends BaseMapper<EportalResource> {

    /**
     * 查询菜单树形结构
     * @param roleId
     * @return
     */
    List<MenuInfoDTO> queryMenuList(@Param("roleId")Long roleId);

    /**
     * 根据父节点ID查询
     * @param id
     * @return
     */
    List<MenuInfoDTO> queryMenuListByparentId(@Param("parentId") Long id, @Param("roleId")Long roleId);
}
