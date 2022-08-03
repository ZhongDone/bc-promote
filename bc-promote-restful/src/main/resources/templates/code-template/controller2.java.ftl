package ${package.Controller};

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bc.promote.common.base.model.PageReqDTO;
import com.bc.promote.common.base.annotation.Authorization;
import com.bc.promote.common.base.annotation.CurrentUser;
import com.bc.promote.common.base.session.model.UserModel;
import com.bc.promote.common.base.model.Result;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "${table.comment!}接口")
@RestController
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
public class ${table.controllerName} {

    @Autowired
    private ${table.serviceName} ${entity?uncap_first}Service;


    @ApiOperation(value = "查询list", notes = "查询list")
    @GetMapping("/queryList")
    public Result<?> queryList(@RequestBody ${entity} ${entity?uncap_first}) {
        log.info("${table.comment!}-查询List接口开始，请求参数：{}", JSONUtil.toJsonStr(${entity?uncap_first}));
        try{
            List<${entity}> list = ${entity?uncap_first}Service.queryList(${entity?uncap_first});
            log.info("${table.comment!}-查询List接口结束，返回参数：{}", JSONUtil.toJsonStr(list));
            return Result.ok(list);
        }catch(Exception e){
            log.error("${table.comment!}-查询List接口-出现异常", e);
            return Result.error("${table.comment!}-查询List接口-出现异常");
        }
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/queryListPage")
    public Result<?> queryListPage(@RequestBody PageReqDTO<${entity}> pageReqDTO) {
        log.info("${table.comment!}-分页查询接口开始，请求参数：{}", JSONUtil.toJsonStr(pageReqDTO));
        try{
            IPage<${entity}> page = ${entity?uncap_first}Service.queryListPage(pageReqDTO);
            log.info("${table.comment!}-分页查询接口结束，返回参数：{}", JSONUtil.toJsonStr(page));
            return Result.ok(page);
        }catch(Exception e){
            log.error("${table.comment!}-分页查询接口-出现异常", e);
            return Result.error("${table.comment!}-分页查询接口-出现异常");
        }
    }

    @ApiOperation(value = "保存", notes = "保存")
    @PostMapping("/save")
    @Authorization
    public Result<?> save(@RequestBody ${entity} ${entity?uncap_first}, @CurrentUser UserModel userModel) {
        log.info("${table.comment!}-保存接口开始，请求参数：{}", JSONUtil.toJsonStr(${entity?uncap_first}));
        try{
            ${entity?uncap_first}.setCreatedUser(userModel.getUserName());
            ${entity?uncap_first}.setModifiedUser(userModel.getUserName());
            ${entity?uncap_first}Service.save(${entity?uncap_first});
            log.info("${table.comment!}-保存接口结束");
            return Result.ok("处理成功");
        }catch(Exception e){
            log.error("${table.comment!}-保存接口-出现异常", e);
            return Result.error("${table.comment!}-保存接口-出现异常");
        }
    }


    @ApiOperation(value = "修改", notes = "修改")
    @PostMapping("/update")
    @Authorization
    public Result<?> update(@RequestBody ${entity} ${entity?uncap_first}, @CurrentUser UserModel userModel) {
        log.info("${table.comment!}-修改接口开始，请求参数：{}", JSONUtil.toJsonStr(${entity?uncap_first}));
        try{
            ${entity?uncap_first}.setCreatedUser(userModel.getUserName());
            ${entity?uncap_first}.setModifiedUser(userModel.getUserName());
            ${entity?uncap_first}Service.updateById(${entity?uncap_first});
            log.info("${table.comment!}-修改接口结束");
            return Result.ok("处理成功");
        }catch(Exception e){
            log.error("${table.comment!}-修改接口-出现异常", e);
            return Result.error("${table.comment!}-修改接口-出现异常");
        }
    }
}
