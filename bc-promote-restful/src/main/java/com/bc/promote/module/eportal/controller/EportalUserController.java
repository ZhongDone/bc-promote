package com.bc.promote.module.eportal.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bc.promote.common.base.model.PageReqDTO;
import com.bc.promote.common.base.model.Result;
import com.bc.promote.module.eportal.entity.EportalUser;
import com.bc.promote.module.eportal.service.IEportalUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "用户表接口")
@RestController
@RequestMapping("/eportal/eportal-user")
public class EportalUserController {

    @Autowired
    private IEportalUserService eportalUserService;


    @ApiOperation(value = "查询list", notes = "查询list")
    @GetMapping("/queryList")
    public Result<?> queryList(@RequestBody EportalUser eportalUser) {
        log.info("用户表-查询List接口开始，请求参数：{}", JSONUtil.toJsonStr(eportalUser));
        try{
            List<EportalUser> list = eportalUserService.queryList(eportalUser);
            log.info("用户表-查询List接口结束，返回参数：{}", JSONUtil.toJsonStr(list));
            return Result.ok(list);
        }catch(Exception e){
            log.error("用户表-查询List接口-出现异常", e);
            return Result.error("用户表-查询List接口-出现异常");
        }
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/queryListPage")
    public Result<?> queryListPage(@RequestBody PageReqDTO<EportalUser> pageReqDTO) {
        log.info("用户表-分页查询接口开始，请求参数：{}", JSONUtil.toJsonStr(pageReqDTO));
        try{
            IPage<EportalUser> page = eportalUserService.queryListPage(pageReqDTO);
            log.info("用户表-分页查询接口结束，返回参数：{}", JSONUtil.toJsonStr(page));
            return Result.ok(page);
        }catch(Exception e){
            log.error("用户表-分页查询接口-出现异常", e);
            return Result.error("用户表-分页查询接口-出现异常");
        }
    }

    @ApiOperation(value = "保存", notes = "保存")
    @PostMapping("/save")
    public Result<?> save(@RequestBody EportalUser eportalUser) {
        log.info("用户表-保存接口开始，请求参数：{}", JSONUtil.toJsonStr(eportalUser));
        try{
            eportalUserService.save(eportalUser);
            log.info("用户表-保存接口结束");
            return Result.ok("处理成功");
        }catch(Exception e){
            log.error("用户表-保存接口-出现异常", e);
            return Result.error("用户表-保存接口-出现异常");
        }
    }


    @ApiOperation(value = "修改", notes = "修改")
    @PostMapping("/update")
    public Result<?> update(@RequestBody EportalUser eportalUser) {
        log.info("用户表-修改接口开始，请求参数：{}", JSONUtil.toJsonStr(eportalUser));
        try{
            eportalUserService.updateById(eportalUser);
            log.info("用户表-修改接口结束");
            return Result.ok("处理成功");
        }catch(Exception e){
            log.error("用户表-修改接口-出现异常", e);
            return Result.error("用户表-修改接口-出现异常");
        }
    }
}
