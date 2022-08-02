package com.bc.promote.module.eportal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bc.promote.common.base.model.PageReqDTO;
import com.bc.promote.module.eportal.entity.EportalRoleResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* 角色权限表 服务类
*
* @author 万爷
* @since 2022-07-27
*/
public interface IEportalRoleResourceService extends IService<EportalRoleResource> {

  /**
  * 查询list
  * @param eportalRoleResource
  * @return
  */
  List<EportalRoleResource> queryList(EportalRoleResource eportalRoleResource);

  /**
  * 分页查询
  * @param pageReqDTO
  * @return
  */
  IPage<EportalRoleResource> queryListPage(PageReqDTO<EportalRoleResource> pageReqDTO);
}
