package com.bc.promote.common.constant;

/**
 * token相关的值
 *
 * @author 万爷
 * @date 2022/07/23
 */
public class TOKEN_COMMON_VALUE {

    /**token存活时间 单位秒 */
    public static final long KEEP_ALIVE_TIME = 48*60*60L;

    /**token保存缓存中的路径*/
    public static final String TOKEN_NAMESPACE_ROOT = "bc:login:token";

    /**后管用户信息在 token 中的 key*/
    public static final String AUTHORIZATION_USERKEY = "eportal_user";

    /**后管用户信息在 token 中的 key*/
    public static final String AUTHORIZATION_AGENTKEY = "agent_user";

}
