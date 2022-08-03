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
import com.bc.promote.module.eportal.entity.EportalUser;
import com.bc.promote.module.eportal.mapper.EportalUserMapper;
import com.bc.promote.module.eportal.service.IEportalUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* 用户表 服务实现类
*
* @author 万爷
* @since 2022-08-02
*/
@Service
public class EportalUserServiceImpl extends ServiceImpl<EportalUserMapper, EportalUser> implements IEportalUserService {

  /**
  * 查询list
  * @param eportalUser
  * @return
  */
  @Override
  public List<EportalUser> queryList(EportalUser eportalUser) {
    LambdaQueryWrapper<EportalUser> queryWrapper = Wrappers.lambdaQuery(eportalUser);
    return this.list(queryWrapper);
  }

  /**
  * 分页查询
  * @param pageReqDTO
  * @return
  */
  @Override
  public IPage<EportalUser> queryListPage(PageReqDTO<EportalUser> pageReqDTO) {
    if(null != pageReqDTO.getEntity()){
      EportalUser entity = pageReqDTO.getEntity();
      int total = this.count();
      Page<EportalUser> page = new Page<>(pageReqDTO.getPageNo(),pageReqDTO.getPageSize(),total);
      page.setOptimizeCountSql(false);
      LambdaQueryWrapper<EportalUser> queryWrapper = Wrappers.lambdaQuery(entity);
      return this.page(page, queryWrapper);
    }
    return null;
  }
}
