package cn.licoy.wdog.core.controller.wxlogin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.licoy.wdog.core.controller.wxlogin.*;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**

 微信PC扫码登录回调

 @description

 @author tjz

 @time 2018年9月6日 下午3:48:08
 */
public class CallbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UrlConfig urlConfig = new UrlConfig();

    public CallbackServlet() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        try {
            String result = HttpUtil.doGet(urlConfig.getOauthAccessToken(code, WeiXinConfig.wxOpenAppId, WeiXinConfig.wxOpenappSecret));
            JSONObject jsonObject = JSONObject.parseObject(result);
            Integer errcode = jsonObject.getInteger("errcode");
            if (errcode != null) {
                System.out.println("微信回调失败，授权终止！");
                return;
            }
            AccessToken accessToken = JSONObject.toJavaObject(jsonObject, AccessToken.class);
            if (accessToken == null) {
                System.out.println("微信回调处理失败！获取accessToken时无返回结果！");
                return;
            }

            JSONObject returnJson = getUserInfoByAccessToken(accessToken.getAccess_token(), accessToken.getOpenid());

            System.out.println("微信用户信息获取完毕*****************************************");
            System.out.println(returnJson.toJSONString());

            //这里获取到用户信息 就打印出来了  其他你自己实现

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**

     通过accessToken获取用户信息
     */
    public JSONObject getUserInfoByAccessToken(String accessToken, String openId) {
        System.out.println("通过accessToken获取用户信息 参数：accessToken：" + accessToken + "*******参数openId：" + openId);
        JSONObject returnJson = new JSONObject();
        returnJson.put("code", 1);
        try {
            String result = HttpUtil.doGet(urlConfig.getUserInfo(accessToken, openId));
            if (StringUtils.isBlank(result)) {
                System.out.println("通过授权accessToken获取用户信息失败！" + accessToken + "openId:" + openId);
                return null;
            }
            JSONObject jsonObject = JSONObject.parseObject(result);
            Integer errcode = jsonObject.getInteger("errcode");
            if (errcode != null) {
                if ("40001".equals(errcode)) {
                    System.out.println("授权过期，请重新授权！");
                } else {
                    System.out.println("获取用户信息失败！" + jsonObject.toJSONString());
                }
                return null;
            }
            UserEntity userEntity = JSONObject.toJavaObject(jsonObject, UserEntity.class);
            returnJson.put("data", userEntity);
            returnJson.put("code", 0);
            returnJson.put("msg", "获取微信用户信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("通过accesstoken获取用户详细信息异常" + e.getMessage());
        }
        return returnJson;
    }
}
