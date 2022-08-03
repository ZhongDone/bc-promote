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
import com.bc.promote.module.eportal.entity.EportalRoleResource;
import com.bc.promote.module.eportal.mapper.EportalRoleResourceMapper;
import com.bc.promote.module.eportal.service.IEportalRoleResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* 角色权限表 服务实现类
*
* @author 万爷
* @since 2022-07-27
*/
@Service
public class EportalRoleResourceServiceImpl extends ServiceImpl<EportalRoleResourceMapper, EportalRoleResource> implements IEportalRoleResourceService {

  /**
  * 查询list
  * @param eportalRoleResource
  * @return
  */
  @Override
  public List<EportalRoleResource> queryList(EportalRoleResource eportalRoleResource) {
    LambdaQueryWrapper<EportalRoleResource> queryWrapper = Wrappers.lambdaQuery(eportalRoleResource);
    return this.list(queryWrapper);
  }

  /**
  * 分页查询
  * @param pageReqDTO
  * @return
  */
  @Override
  public IPage<EportalRoleResource> queryListPage(PageReqDTO<EportalRoleResource> pageReqDTO) {
    if(null != pageReqDTO.getEntity()){
      EportalRoleResource entity = pageReqDTO.getEntity();
      Page<EportalRoleResource> page = new Page<>(pageReqDTO.getPageNo(),pageReqDTO.getPageSize());
      page.setOptimizeCountSql(false);
      LambdaQueryWrapper<EportalRoleResource> queryWrapper = Wrappers.lambdaQuery(entity);
      return this.page(page, queryWrapper);
    }
    return null;
  }
}
