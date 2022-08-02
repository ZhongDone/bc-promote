package com.bc.promote.module.eportal.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bc.promote.common.base.session.model.TokenModel;
import com.bc.promote.common.base.session.model.UserModel;
import com.bc.promote.common.base.session.token.TokenManager;
import com.bc.promote.common.constant.TOKEN_COMMON_VALUE;
import com.bc.promote.module.eportal.dto.LoginReqDTO;
import com.bc.promote.module.eportal.dto.LoginResDTO;
import com.bc.promote.module.eportal.entity.EportalUser;
import com.bc.promote.module.eportal.mapper.EportalUserMapper;
import com.bc.promote.module.eportal.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Resource
    private EportalUserMapper eportalUserMapper;

    @Autowired
    private TokenManager tokenManager;

    @Override
    public LoginResDTO login(LoginReqDTO loginReqDTO) {
        // 密码加密
        loginReqDTO.setPassword(DigestUtils.md5Hex(loginReqDTO.getPassword()));
        // 查询用户信息
        LambdaQueryWrapper<EportalUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(EportalUser::getUserName, loginReqDTO.getUserName())
                    .eq(EportalUser::getPassword, loginReqDTO.getPassword());
        EportalUser eportalUser = eportalUserMapper.selectOne(queryWrapper);
        if(null != eportalUser){
            // 创建返回参
            LoginResDTO loginResDTO = new LoginResDTO();
            loginResDTO.setUserId(eportalUser.getId());
            // 创建token
            String token = createToken(eportalUser);
            loginResDTO.setToken(token);
            return loginResDTO;
        }
        return null;
    }

    /**
     * 退出登录
     * @param token
     */
    @Override
    public void loginOut(String token) {
        this.tokenManager.deleteToken(token);
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    @Override
    public boolean checkToken(String token) {
        TokenModel tokenModel = this.tokenManager.getToken(token);
        if(null != tokenModel){
            return true;
        }
        return false;
    }

    /**
     * 创建token
     * @param eportalUser
     * @return
     */
    private String createToken(EportalUser eportalUser) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(eportalUser, userModel);
        if(null != eportalUser.getParentId()){
            EportalUser parentUser = eportalUserMapper.selectById(eportalUser.getParentId());
            if(null != parentUser){
                userModel.setParentUserName(parentUser.getUserName());
                userModel.setParentAccountName(parentUser.getAccountName());
            }
        }

        TokenModel tokenModel = new TokenModel();
        tokenModel.setProperty(TOKEN_COMMON_VALUE.AUTHORIZATION_USERKEY, userModel);
        return this.tokenManager.createToken(tokenModel);
    }

}
