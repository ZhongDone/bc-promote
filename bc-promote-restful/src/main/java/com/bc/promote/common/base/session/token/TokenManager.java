package com.bc.promote.common.base.session.token;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.bc.promote.common.base.session.model.TokenModel;
import com.bc.promote.common.constant.TOKEN_COMMON_VALUE;
import com.bc.promote.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class TokenManager {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 创建 token
     * @param tokenModel
     * @return
     */
    public String createToken(TokenModel tokenModel){
        String token = null;
        boolean result = false;
        int times = 0;
        while (!result && times < 3) {
            token = getTokenGenerator();
            String key = TOKEN_COMMON_VALUE.TOKEN_NAMESPACE_ROOT + ":" + token;
            String value = JSONUtil.toJsonStr(tokenModel.getProperties());
            result = redisUtil.set(key, value, TOKEN_COMMON_VALUE.KEEP_ALIVE_TIME);
            times++;
        }
        return token;
    }


    /**
     * 创建 token
     * @param tokenModel
     * @param timeout
     * @return
     */
    public String createToken(TokenModel tokenModel, long timeout){
        String token = null;
        boolean result = false;
        int times = 0;
        while (!result && times < 3) {
            token = getTokenGenerator();
            String key = TOKEN_COMMON_VALUE.TOKEN_NAMESPACE_ROOT + ":" + token;
            String value = JSONUtil.toJsonStr(tokenModel.getProperties());
            if(timeout != 0){
                result = redisUtil.set(key, value, TOKEN_COMMON_VALUE.KEEP_ALIVE_TIME);
            }else{
                result = redisUtil.set(key, value, timeout);
            }

            times++;
        }
        return token;
    }

    /**
     * 获取 token
     * @param token
     * @return
     */
    public TokenModel getToken(String token){
        String key = TOKEN_COMMON_VALUE.TOKEN_NAMESPACE_ROOT + ":" + token;
        Object obj = redisUtil.get(key);
        if (obj != null) {
            TokenModel model = new TokenModel();
            if(obj instanceof String){
                // 将缓存里面的值转成map
                obj = (Map<String, Object>) JSON.parse((String)obj);
            }
            model.setProperties((Map<String, Object>) obj);
            model.setToken(token);
            return model;
        }
        return null;
    }

    /**
     * 更新 token
     * @param token
     * @param tokenModel
     * @return
     */
    public boolean updateToken(String token, TokenModel tokenModel){
        boolean result = false;
        int times = 0;
        while (!result && times < 3) {
            String key = TOKEN_COMMON_VALUE.TOKEN_NAMESPACE_ROOT + ":" + token;
            String value = JSONUtil.toJsonStr(tokenModel.getProperties());
            result = redisUtil.set(key, value, TOKEN_COMMON_VALUE.KEEP_ALIVE_TIME);
            times++;
        }
        return result;
    }

    /**
     * 更新 token
     * @param token
     * @param tokenModel
     * @param timeout
     * @return
     */
    public boolean updateToken(String token, TokenModel tokenModel, long timeout){
        boolean result = false;
        int times = 0;
        while (!result && times < 3) {
            String key = TOKEN_COMMON_VALUE.TOKEN_NAMESPACE_ROOT + ":" + token;
            String value = JSONUtil.toJsonStr(tokenModel.getProperties());
            result = redisUtil.set(key, value, timeout);
            times++;
        }
        return result;
    }

    /**
     * 删除 token
     * @param token
     * @return
     */
    public boolean deleteToken(String token){
        String key = TOKEN_COMMON_VALUE.TOKEN_NAMESPACE_ROOT + ":" + token;
        redisUtil.del(key);
        return true;
    }

    /**
     * 生成 token令牌
     * @return
     */
    private String getTokenGenerator() {
        return UUID.randomUUID().toString().replace("-", "");
    }


}
