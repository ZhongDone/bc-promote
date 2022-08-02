package com.bc.promote.module.eportal.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bc.promote.common.base.model.PageReqDTO;
import com.bc.promote.common.utils.CommonUtils;
import com.bc.promote.module.eportal.dto.MenuInfoDTO;
import com.bc.promote.module.eportal.entity.EportalResource;
import com.bc.promote.module.eportal.mapper.EportalResourceMapper;
import com.bc.promote.module.eportal.service.IEportalResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 菜单资源表 服务实现类
 *
 * @author 万爷
 * @since 2022-07-27
 */
@Service
public class EportalResourceServiceImpl extends ServiceImpl<EportalResourceMapper, EportalResource> implements IEportalResourceService {

    @Resource
    private EportalResourceMapper eportalResourceMapper;

    /**
     * 查询list
     *
     * @param eportalResource
     * @return
     */
    @Override
    public List<EportalResource> queryList(EportalResource eportalResource) {
        LambdaQueryWrapper<EportalResource> queryWrapper = Wrappers.lambdaQuery(eportalResource);
        return this.list(queryWrapper);
    }

    /**
     * 分页查询
     *
     * @param pageReqDTO
     * @return
     */
    @Override
    public IPage<EportalResource> queryListPage(PageReqDTO<EportalResource> pageReqDTO) {
        if (null != pageReqDTO.getEntity()) {
            EportalResource entity = pageReqDTO.getEntity();
            Page<EportalResource> page = new Page<>(pageReqDTO.getPageNo(), pageReqDTO.getPageSize());
            LambdaQueryWrapper<EportalResource> queryWrapper = Wrappers.lambdaQuery(entity);
            return this.page(page, queryWrapper);
        }
        return null;
    }

    /**
     * 根据用户id查询菜单树形结构
     *
     * @param roleId
     * @return
     */
    @Override
    public List<MenuInfoDTO> queryMenuList(Long roleId) {
        return eportalResourceMapper.queryMenuList(roleId);
    }
}
