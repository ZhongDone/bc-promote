package com.bc.promote.module.eportal.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bc.promote.common.base.annotation.Authorization;
import com.bc.promote.common.base.annotation.CurrentUser;
import com.bc.promote.common.base.model.PageReqDTO;
import com.bc.promote.common.base.model.Result;
import com.bc.promote.common.base.session.model.UserModel;
import com.bc.promote.module.eportal.dto.MenuInfoDTO;
import com.bc.promote.module.eportal.entity.EportalResource;
import com.bc.promote.module.eportal.entity.EportalRoleResource;
import com.bc.promote.module.eportal.service.IEportalResourceService;
import com.bc.promote.module.eportal.service.IEportalRoleResourceService;
import com.bc.promote.module.eportal.service.impl.EportalRoleResourceServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "菜单资源表接口")
@RestController
@RequestMapping("/eportal/eportal-resource")
public class EportalResourceController {

    @Autowired
    private IEportalResourceService eportalResourceService;

    @Autowired
    private IEportalRoleResourceService eportalRoleResourceService;


    @ApiOperation(value = "查询list", notes = "查询list")
    @PostMapping("/queryList")
    public Result<?> queryList(@RequestBody EportalResource eportalResource) {
        log.info("菜单资源表-查询List接口开始，请求参数：{}", JSONUtil.toJsonStr(eportalResource));
        try{
            List<EportalResource> list = eportalResourceService.queryList(eportalResource);
            log.info("菜单资源表-查询List接口结束，返回参数：{}", JSONUtil.toJsonStr(list));
            return Result.ok(list);
        }catch(Exception e){
            log.error("菜单资源表-查询List接口-出现异常", e);
            return Result.error("菜单资源表-查询List接口-出现异常");
        }
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping("/queryListPage")
    public Result<?> queryListPage(@RequestBody PageReqDTO<EportalResource> pageReqDTO) {
        log.info("菜单资源表-分页查询接口开始，请求参数：{}", JSONUtil.toJsonStr(pageReqDTO));
        try{
            IPage<EportalResource> page = eportalResourceService.queryListPage(pageReqDTO);
            log.info("菜单资源表-分页查询接口结束，返回参数：{}", JSONUtil.toJsonStr(page));
            return Result.ok(page);
        }catch(Exception e){
            log.error("菜单资源表-分页查询接口-出现异常", e);
            return Result.error("菜单资源表-分页查询接口-出现异常");
        }
    }

    @ApiOperation(value = "保存", notes = "保存")
    @Authorization
    @PostMapping("/save")
    public Result<?> save(@RequestBody EportalResource eportalResource, @CurrentUser UserModel userModel) {
        log.info("菜单资源表-保存接口开始，请求参数：{}", JSONUtil.toJsonStr(eportalResource));
        try{
            eportalResourceService.save(eportalResource);

            // 新增角色权限
            EportalRoleResource eportalRoleResource = new EportalRoleResource();
            eportalRoleResource.setRoleId(userModel.getRoleId());
            eportalRoleResource.setResourceId(eportalResource.getId());
            eportalRoleResourceService.save(eportalRoleResource);
            log.info("菜单资源表-保存接口结束");
            return Result.ok("处理成功");
        }catch(Exception e){
            log.error("菜单资源表-保存接口-出现异常", e);
            return Result.error("菜单资源表-保存接口-出现异常");
        }
    }


    @ApiOperation(value = "修改", notes = "修改")
    @PostMapping("/update")
    public Result<?> update(@RequestBody EportalResource eportalResource) {
        log.info("菜单资源表-修改接口开始，请求参数：{}", JSONUtil.toJsonStr(eportalResource));
        try{
            eportalResourceService.updateById(eportalResource);
            log.info("菜单资源表-修改接口结束");
            return Result.ok("处理成功");
        }catch(Exception e){
            log.error("菜单资源表-修改接口-出现异常", e);
            return Result.error("菜单资源表-修改接口-出现异常");
        }
    }

    @ApiOperation(value = "当前登录用户查询菜单树形结构", notes = "当前登录用户查询菜单树形结构")
    @Authorization
    @PostMapping("/getMenuTree")
    public Result<?> getMenuTree(@CurrentUser UserModel userModel){
        List<MenuInfoDTO> menuList = eportalResourceService.queryMenuList(userModel.getRoleId());
        return Result.ok(menuList);
    }

    @ApiOperation(value = "根据角色Id查询菜单树形结构", notes = "根据角色Id查询菜单树形结构")
    @GetMapping("/getMenuTree/{roleId}")
    public Result<?> getMenuTree(@PathVariable("roleId") Long roleId){
        List<MenuInfoDTO> menuList = eportalResourceService.queryMenuList(roleId);
        return Result.ok(menuList);
    }
}
