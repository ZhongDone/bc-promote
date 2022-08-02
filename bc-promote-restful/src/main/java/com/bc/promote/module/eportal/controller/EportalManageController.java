package com.bc.promote.module.eportal.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bc.promote.common.base.annotation.Authorization;
import com.bc.promote.common.base.annotation.CurrentUser;
import com.bc.promote.common.base.model.PageReqDTO;
import com.bc.promote.common.base.model.Result;
import com.bc.promote.common.base.session.model.UserModel;
import com.bc.promote.module.eportal.dto.ManageInfoDTO;
import com.bc.promote.module.eportal.dto.MenuInfoDTO;
import com.bc.promote.module.eportal.entity.EportalManage;
import com.bc.promote.module.eportal.service.IEportalManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "管理机构接口")
@RestController
@RequestMapping("/eportal/eportal-manage")
public class EportalManageController {

    @Autowired
    private IEportalManageService eportalManageService;


    @ApiOperation(value = "查询list", notes = "查询list")
    @PostMapping("/queryList")
    public Result<?> queryList(@RequestBody EportalManage eportalManage) {
        log.info("管理机构-查询List接口开始，请求参数：{}", JSONUtil.toJsonStr(eportalManage));
        try{
            List<EportalManage> list = eportalManageService.queryList(eportalManage);
            log.info("管理机构-查询List接口结束，返回参数：{}", JSONUtil.toJsonStr(list));
            return Result.ok(list);
        }catch(Exception e){
            log.error("管理机构-查询List接口-出现异常", e);
            return Result.error("管理机构-查询List接口-出现异常");
        }
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping("/queryListPage")
    public Result<?> queryListPage(@RequestBody PageReqDTO<EportalManage> pageReqDTO) {
        log.info("管理机构-分页查询接口开始，请求参数：{}", JSONUtil.toJsonStr(pageReqDTO));
        try{
            IPage<EportalManage> page = eportalManageService.queryListPage(pageReqDTO);
            log.info("管理机构-分页查询接口结束，返回参数：{}", JSONUtil.toJsonStr(page));
            return Result.ok(page);
        }catch(Exception e){
            log.error("管理机构-分页查询接口-出现异常", e);
            return Result.error("管理机构-分页查询接口-出现异常");
        }
    }

    @ApiOperation(value = "保存", notes = "保存")
    @PostMapping("/save")
    public Result<?> save(@RequestBody EportalManage eportalManage) {
        log.info("管理机构-保存接口开始，请求参数：{}", JSONUtil.toJsonStr(eportalManage));
        try{
            eportalManageService.save(eportalManage);
            log.info("管理机构-保存接口结束");
            return Result.ok("处理成功");
        }catch(Exception e){
            log.error("管理机构-保存接口-出现异常", e);
            return Result.error("管理机构-保存接口-出现异常");
        }
    }


    @ApiOperation(value = "修改", notes = "修改")
    @PostMapping("/update")
    public Result<?> update(@RequestBody EportalManage eportalManage) {
        log.info("管理机构-修改接口开始，请求参数：{}", JSONUtil.toJsonStr(eportalManage));
        try{
            eportalManageService.updateById(eportalManage);
            log.info("管理机构-修改接口结束");
            return Result.ok("处理成功");
        }catch(Exception e){
            log.error("管理机构-修改接口-出现异常", e);
            return Result.error("管理机构-修改接口-出现异常");
        }
    }

    @ApiOperation(value = "查询机构树形结构", notes = "查询机构树形结构")
    @Authorization
    @PostMapping("/getManageTree")
    public Result<?> getManageTree(@CurrentUser UserModel userModel){
        List<ManageInfoDTO> menuList = eportalManageService.getManageTree();
        return Result.ok(menuList);
    }
}
