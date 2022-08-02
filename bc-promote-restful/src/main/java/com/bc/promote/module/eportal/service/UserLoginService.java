package com.bc.promote.module.eportal.service;


import com.bc.promote.module.eportal.dto.LoginReqDTO;
import com.bc.promote.module.eportal.dto.LoginResDTO;

/**
 * 登录-service
 */
public interface UserLoginService {

    /**
     * 登录
     * @param loginReqDTO
     * @return
     */
    LoginResDTO login(LoginReqDTO loginReqDTO);

    /**
     * 退出登录
     * @param token
     */
    void loginOut(String token);

    /**
     * 校验token
     * @param token
     * @return
     */
    boolean checkToken(String token);
}
