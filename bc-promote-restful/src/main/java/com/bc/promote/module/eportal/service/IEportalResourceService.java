package com.bc.promote.module.eportal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bc.promote.common.base.model.PageReqDTO;
import com.bc.promote.module.eportal.dto.MenuInfoDTO;
import com.bc.promote.module.eportal.entity.EportalResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 菜单资源表 服务类
 *
 * @author 万爷
 * @since 2022-07-27
 */
public interface IEportalResourceService extends IService<EportalResource> {

    /**
     * 查询list
     *
     * @param eportalResource
     * @return
     */
    List<EportalResource> queryList(EportalResource eportalResource);

    /**
     * 分页查询
     *
     * @param pageReqDTO
     * @return
     */
    IPage<EportalResource> queryListPage(PageReqDTO<EportalResource> pageReqDTO);

    /**
     * 根据角色id查询菜单树形结构
     *
     * @param roleId
     * @return
     */
    List<MenuInfoDTO> queryMenuList(Long roleId);
}
