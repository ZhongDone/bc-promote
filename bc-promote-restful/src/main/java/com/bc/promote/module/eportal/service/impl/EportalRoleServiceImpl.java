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
import com.bc.promote.module.eportal.entity.EportalRole;
import com.bc.promote.module.eportal.mapper.EportalRoleMapper;
import com.bc.promote.module.eportal.service.IEportalRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* 角色表 服务实现类
*
* @author 万爷
* @since 2022-07-27
*/
@Service
public class EportalRoleServiceImpl extends ServiceImpl<EportalRoleMapper, EportalRole> implements IEportalRoleService {

  /**
  * 查询list
  * @param eportalRole
  * @return
  */
  @Override
  public List<EportalRole> queryList(EportalRole eportalRole) {
    LambdaQueryWrapper<EportalRole> queryWrapper = Wrappers.lambdaQuery(eportalRole);
    return this.list(queryWrapper);
  }

  /**
  * 分页查询
  * @param pageReqDTO
  * @return
  */
  @Override
  public IPage<EportalRole> queryListPage(PageReqDTO<EportalRole> pageReqDTO) {
    if(null != pageReqDTO.getEntity()){
      EportalRole entity = pageReqDTO.getEntity();
      Page<EportalRole> page = new Page<>(pageReqDTO.getPageNo(),pageReqDTO.getPageSize());
      page.setOptimizeCountSql(false);
      LambdaQueryWrapper<EportalRole> queryWrapper = Wrappers.lambdaQuery(entity);
      return this.page(page, queryWrapper);
    }
    return null;
  }
}
