package com.bc.promote.module.eportal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bc.promote.common.base.model.PageReqDTO;
import com.bc.promote.module.eportal.entity.EportalRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* 角色表 服务类
*
* @author 万爷
* @since 2022-07-27
*/
public interface IEportalRoleService extends IService<EportalRole> {

  /**
  * 查询list
  * @param eportalRole
  * @return
  */
  List<EportalRole> queryList(EportalRole eportalRole);

  /**
  * 分页查询
  * @param pageReqDTO
  * @return
  */
  IPage<EportalRole> queryListPage(PageReqDTO<EportalRole> pageReqDTO);
}
