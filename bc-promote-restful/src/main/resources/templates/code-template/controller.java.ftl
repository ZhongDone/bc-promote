package ${package.Controller};

import ${package.Entity}.${entity};
import com.eagle.third.channel.common.dto.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import ${package.Service}.${table.serviceName};
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * ${table.comment!} 前端控制器
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired
    private ${table.serviceName} ${entity?uncap_first}Service;

    /**
    * 分页列表查询
    *
    * @param ${package.ModuleName?uncap_first}
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
    @GetMapping(value = "/list")
    public Result<?> queryPageList(${entity} ${entity?uncap_first},
    @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
    @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
    HttpServletRequest req) {
    QueryWrapper<${entity}> queryWrapper = QueryGenerator.initQueryWrapper(${entity?uncap_first}, req.getParameterMap());
    Page<${entity}> page = new Page<${entity}>(pageNo, pageSize);
    IPage<${entity}> pageList = ${entity?uncap_first}Service.page(page, queryWrapper);
    return Result.ok(pageList);
    }

}
</#if>
