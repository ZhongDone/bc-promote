package com.bc.promote.module.eportal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bc.promote.common.base.model.PageReqDTO;
import com.bc.promote.module.eportal.entity.EportalUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* 用户表 服务类
*
* @author 万爷
* @since 2022-08-02
*/
public interface IEportalUserService extends IService<EportalUser> {

  /**
  * 查询list
  * @param eportalUser
  * @return
  */
  List<EportalUser> queryList(EportalUser eportalUser);

  /**
  * 分页查询
  * @param pageReqDTO
  * @return
  */
  IPage<EportalUser> queryListPage(PageReqDTO<EportalUser> pageReqDTO);
}
