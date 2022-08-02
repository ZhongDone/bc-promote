package com.bc.promote.module.eportal.controller;

import com.bc.promote.common.base.model.Result;
import com.bc.promote.module.eportal.dto.LoginReqDTO;
import com.bc.promote.module.eportal.dto.LoginResDTO;
import com.bc.promote.module.eportal.service.UserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Api(tags = "登录接口")
@RestController
@RequestMapping("/eportal")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;


    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping("/login")
    public Result<?> login(@RequestBody LoginReqDTO loginReqDTO){
        try{
            LoginResDTO loginResDTO = userLoginService.login(loginReqDTO);
            if(null == loginResDTO){
                return Result.error(Result.ERROR,"用户名密码错误");
            }
            return Result.ok(loginResDTO);
        }catch (Exception e){
            log.error("登录出现异常：", e);
            return Result.error(Result.ERROR,"系统异常，请稍后在试");
        }
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @GetMapping("/loginOut")
    public Result<?> loginOut(HttpServletRequest request){
        try{
            String token = request.getHeader("token");
            log.info("从header中得到token："+token);
            if(StringUtils.isEmpty(token)) {
                token = request.getParameter("token");
                log.info("从request中得到token："+token);
            }
            if(StringUtils.isEmpty(token)){
                return Result.ok("退出登录成功");
            }
            userLoginService.loginOut(token);
            return Result.ok("退出登录成功");
        }catch (Exception e){
            log.error("退出登录出现异常：", e);
            return Result.error(Result.ERROR,"系统异常，请稍后在试");
        }
    }

    /**
     * 校验token是否失效
     * @param request
     * @return
     */
    @GetMapping("/checkToken")
    public Result<?> checkToken(HttpServletRequest request){
        try{
            String token = request.getHeader("token");
            log.info("从header中得到token："+token);
            if(StringUtils.isEmpty(token)) {
                token = request.getParameter("token");
                log.info("从request中得到token："+token);
            }
            boolean isInvalid = userLoginService.checkToken(token);
            return Result.ok(isInvalid);
        }catch (Exception e){
            log.error("校验token是否失效出现异常：", e);
            return Result.error(Result.ERROR,"系统异常，请稍后在试");
        }
    }

}
