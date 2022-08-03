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
import com.bc.promote.module.eportal.dto.ManageInfoDTO;
import com.bc.promote.module.eportal.entity.EportalManage;
import com.bc.promote.module.eportal.mapper.EportalManageMapper;
import com.bc.promote.module.eportal.service.IEportalManageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* 管理机构 服务实现类
*
* @author 万爷
* @since 2022-07-27
*/
@Service
public class EportalManageServiceImpl extends ServiceImpl<EportalManageMapper, EportalManage> implements IEportalManageService {

  @Resource
  private EportalManageMapper eportalManageMapper;

  /**
  * 查询list
  * @param eportalManage
  * @return
  */
  @Override
  public List<EportalManage> queryList(EportalManage eportalManage) {
    LambdaQueryWrapper<EportalManage> queryWrapper = Wrappers.lambdaQuery(eportalManage);
    return this.list(queryWrapper);
  }

  /**
  * 分页查询
  * @param pageReqDTO
  * @return
  */
  @Override
  public IPage<EportalManage> queryListPage(PageReqDTO<EportalManage> pageReqDTO) {
    if(null != pageReqDTO.getEntity()){
      EportalManage entity = pageReqDTO.getEntity();
      Page<EportalManage> page = new Page<>(pageReqDTO.getPageNo(),pageReqDTO.getPageSize());
      page.setOptimizeCountSql(false);
      LambdaQueryWrapper<EportalManage> queryWrapper = Wrappers.lambdaQuery(entity);
      return this.page(page, queryWrapper);
    }
    return null;
  }


  @Override
  public List<ManageInfoDTO> getManageTree() {
    return eportalManageMapper.getManageTree(0L);
  }
}
