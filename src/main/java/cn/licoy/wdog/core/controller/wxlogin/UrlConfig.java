package cn.licoy.wdog.core.controller.wxlogin;


import org.apache.commons.lang3.StringUtils;

public class UrlConfig {
    /**
     * 微信全局调用accessToken
     *
     * @return
     */
    public String getGlobalAccessTokenUrl(String wxMpAppId,String wxMpAppSecrte) {
        return "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + wxMpAppId + "&secret="+ wxMpAppSecrte;
    }

    /**
     * 微信服务器IP列表
     *
     * @return
     */
    public String getWeiXinIpUrl(String wxMpAppId,String wxMpAppSecrte,String appId) {
        //return "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=" + weiXinUtil.getGlobalAccessToken(wxMpAppId, wxMpAppSecrte,appId);
        return null;
    }


    /**
     * 微信授权
     */
    public String getOauth(String type,String redirectUrl,String param,String wxMpAppId) {
        return "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wxMpAppId + "&redirect_uri="+ redirectUrl + "&response_type=code&scope=" + type + "&state=" + param + "#wechat_redirect";
    }



    /**
     * 网页授权
     */
    public String getOauthAccessToken(String code,String wxMpAppId,String wxMpAppSecret) {
        return "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + wxMpAppId + "&secret=" + wxMpAppSecret+ "&code=" + code + "&grant_type=" + ConstantManager.GRANT_TYPE;
    }

    /**
     * 刷新网页授权accessToken
     */
    public String refreshAccessToken(String refreshToken,String wxMpAppId) {
        return "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + wxMpAppId+ "&grant_type=refresh_token&refresh_token=" + refreshToken.trim();
    }

    /**
     * 微信用户详细信息 accessToken:网页授权accessToken openId:用户openid 通过授权获取
     */
    public String getUserInfo(String accessToken, String openId) {
        return "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken.trim() + "&openid=" + openId.trim()+ "&lang=zh_CN";
    }

    /**
     * 获取用户详细信息 通过全局accessToken获取
     */
    public String getUserInfoByGlobalToken(String globalToken, String openId) {
        return "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + globalToken + "&openid=" + openId+ "&lang=zh_CN ";
    }

    /**
     * 长短连接转换
     */
    public String getShortUrl(String accessToken) {
        return "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=" + accessToken;
    }

    /**
     * 获取模版列表
     */
    public String getModelList(String accessToken) {
        return "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=" + accessToken;
    }

    /**
     * 发送模版消息
     */
    public String sendModelMessage(String accessToken) {
        return "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
    }

    /**
     * 获取模版设置的行业信息
     */
    public String getModelIndustry(String accessToken) {
        return "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=" + accessToken;
    }

    /**
     * 获取jsapi签名
     */
    public String getJsApiConfig(String accessToken){
        return "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
    }

    /**
     * 获取关注的用户列表
     */
    public String getUserList(String accessToken,String openId){
        if(StringUtils.isBlank(openId)){
            return "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+accessToken;
        }else{
            return "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+accessToken+"&next_openid="+openId;
        }
    }

    /**
     * 创建二维码ticket
     */
    public String createQrcodeTicket(String accessToken){
        return " https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+accessToken;
    }

    /**
     * 通过ticket换取二维码
     */
    public String showQrcode(String ticket){
        return "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket;
    }

    /**
     * 微信web登录url
     */
    public String getWxWebLoginUrl(String type,String redirectUrl,String param,String wxOpenAppId){
        return "https://open.weixin.qq.com/connect/qrconnect?appid="+wxOpenAppId+"&redirect_uri="+redirectUrl+"&response_type=code&scope="+type+"&state="+param+"#wechat_redirect";
    }

    /**
     * 微信卡包ticket
     */
    public String getTicketUrl(String accessToken){
        return "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=wx_card";
    }

    /**
     * 添加客服帐号
     * @description
     *
     * @author tjz
     *
     * @param accessToken
     * @return
     *
     * @time 2018年2月2日 上午9:54:20
     */
    public String addKfAccountUrl(String accessToken){
        return "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=";
    }


    /**
     * 获取所有客服帐号
     */
    public String getKfListUrl(String accessToken){
        return "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token="+accessToken;
    }

    /**
     * 发送客服消息
     */
    public String getSendMessageUrl(String accessToken){
        return "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+accessToken;
    }

    /**
     * 获取QQ登录Url
     */
    public String getQqLoginUrl(String appId,String callbackUrl,String state,String scope){
        return "https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id="+appId+"&callbackUrl="+callbackUrl+"&state="+state+"&scope="+scope;
    }




    /**
     * 获取PC版QQ登录accessToken
     */
    public String getQqLoginAccessTokenByPcUrl(String qqAppId,String qqAppKey,String code,String redirectUri){
        return "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id="+qqAppId+"&client_secret="+qqAppKey+"&code="+code+"&redirect_uri="+redirectUri;
    }


    /**
     * 获取WAP版QQ登录accessToken
     */
    public String getQqLoginAccessTokenByWapUrl(String qqAppId,String qqAppKey,String code,String redirectUri){
        return "https://graph.z.qq.com/moc2/token?grant_type=authorization_code="+qqAppId+"&client_secret="+qqAppKey+"&code="+code+"&redirect_uri="+redirectUri;
    }


    /**
     * 刷新pc授权accessToken
     */
    public String refreshPcQqAccessTokenUrl(String qqAppId,String qqAppKey,String refreshToken){
        return "https://graph.qq.com/oauth2.0/token?grant_type=refresh_token&client_id="+qqAppId+"&client_secret="+qqAppKey+"&refresh_token="+refreshToken;
    }

    /**
     * 刷新wap授权accessToken
     */
    public String refreshWapQqAccessTokenUrl(String qqAppId,String qqAppKey,String refreshToken){
        return "https://graph.z.qq.com/moc2/token?grant_type=refresh_token&client_id="+qqAppId+"&client_secret="+qqAppKey+"&refresh_token="+refreshToken;
    }

    /**
     * 获取PC qq用户openId
     */
    public String getPcQqUserInfoByAccessTokenUrl(String accessToken){
        return "https://graph.qq.com/oauth2.0/me?token="+accessToken;
    }

    /**
     * 获取wap qq用户openId
     */
    public String getWapQqUserInfoByAccessTokenUrl(String accessToken){
        return "https://graph.z.qq.com/moc2/me?access_token="+accessToken;
    }


    /**
     * 获取QQ用户详细信息
     */
    public String getQqUserInfoByAccessTokenAndOpenId(String appId,String accessToken,String openId){
        return "https://graph.qq.com/user/get_user_info?access_token="+accessToken+"&oauth_consumer_key="+appId+"&openid="+openId;
    }

}
