package cn.licoy.wdog.core.controller.wxlogin;

import java.net.URLEncoder;

public class ConstantManager {
    /** 返回状态失败 **/
    public static final String ERROR_STATUS ="999";
    /** 返回状态成功 **/
    public static final String SUCCESS_STATUS = "000";

    /** 返回信息失败 **/
    public static final String ERROR_MESSAGE = "FAILED";

    /** 返回状态等待 **/
    public static final String IN_PROGRESS = "666";

    /**权限不足**/
    public static final String OAUTH_STATUS = "777";

    /** 返回信息成功 **/
    public static final String SUCCESS_MESSAGE = "SUCCESS";

    public static final String RETURNMSG = "returnMsg";

    public static final String RETURNSTATUS = "returnStatus";

    public static final String RETURNDATA = "returnData";

    //用户详细信息
    public static final String OAUTH_USER_INFO="snsapi_userinfo";

    public static final String OAUTH_QQ_USER_INFO="get_user_info";


    //静默授权
    public static final String OAUTH_USER_BASE="snsapi_base";

    public static final String GRANT_TYPE="authorization_code";

    //网页授权作用域
    public static final String OAUTH_WEB_LOGIN="snsapi_login";


    public static final String PC="pc";

    public static final String WAP="wap";

/*************************************************v2******************************************************/
    /**
     * PC授权回调
     */
    public static final String WEBCALLBACKURL = URLEncoder.encode("http://www.sucaiku.xin/callback/wxCallback.do");

}
