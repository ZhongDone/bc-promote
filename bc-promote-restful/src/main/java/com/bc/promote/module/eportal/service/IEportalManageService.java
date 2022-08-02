package com.bc.promote.module.eportal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bc.promote.common.base.model.PageReqDTO;
import com.bc.promote.module.eportal.dto.ManageInfoDTO;
import com.bc.promote.module.eportal.entity.EportalManage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 管理机构 服务类
 *
 * @author 万爷
 * @since 2022-07-27
 */
public interface IEportalManageService extends IService<EportalManage> {

    /**
     * 查询list
     *
     * @param eportalManage
     * @return
     */
    List<EportalManage> queryList(EportalManage eportalManage);

    /**
     * 分页查询
     *
     * @param pageReqDTO
     * @return
     */
    IPage<EportalManage> queryListPage(PageReqDTO<EportalManage> pageReqDTO);

  /**
   * 查询机构树形结构
   *
   * @return
   */
  List<ManageInfoDTO> getManageTree();
}
