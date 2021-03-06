package cn.licoy.wdog.common.aspect;

import cn.licoy.wdog.common.annotation.SysLogs;
import cn.licoy.wdog.common.util.HttpTools;
import cn.licoy.wdog.core.config.jwt.JwtToken;
import cn.licoy.wdog.core.dto.SignInDTO;
import cn.licoy.wdog.core.dto.system.user.ResetPasswordDTO;
import cn.licoy.wdog.core.dto.system.user.UserAddDTO;
import cn.licoy.wdog.core.entity.system.SysLog;
import cn.licoy.wdog.core.service.system.SysLogService;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author Licoy
 * @version 2018/4/27/17:19
 */
@Aspect
@Component
public class SysLogAspect {

    private final SysLogService sysLogService;

    @Autowired
    public SysLogAspect(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    @Pointcut("@annotation(cn.licoy.wdog.common.annotation.SysLogs)")
    public void log(){}

    @AfterReturning("log()")
    public void after(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        PrincipalCollection spc = null;
        Subject subject = SecurityUtils.getSubject();
        if(subject.getPrincipals()!=null){
            spc = subject.getPrincipals();
        }
        SysLog sysLog = new SysLog();
        //????????????Action??????
        sysLog.setActionName(getMethodSysLogsAnnotationValue(joinPoint));
        //??????IP
        sysLog.setIp(HttpTools.getClientIp(request));
        sysLog.setAjax(HttpTools.ajax(request) ? 1 : 0);
        sysLog.setUri(request.getRequestURI());
        String s = this.paramFilter(joinPoint.getArgs());
        //???????????????????????????
        sysLog.setParams(s.length()>500 ? "??????????????????????????????" : s);
        sysLog.setHttpMethod(request.getMethod());
        sysLog.setClassMethod(joinPoint.getSignature().getDeclaringTypeName()
                + "." + joinPoint.getSignature().getName()+"()");
        //????????????????????????
        if(spc!=null){
            JwtToken jwtToken = new JwtToken();
            BeanUtils.copyProperties(spc.getPrimaryPrincipal(),jwtToken);
            sysLog.setUsername(jwtToken.getUsername());
            sysLog.setUid(jwtToken.getUid());
        }else{
            sysLog.setUsername("??????");
            sysLog.setUid("0");
        }
        sysLog.setCreateDate(new Date());
        sysLogService.insert(sysLog);
    }

    private String getMethodSysLogsAnnotationValue(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method method = methodSignature.getMethod();
        if(method.isAnnotationPresent(SysLogs.class)){
            //???????????????????????????????????????
            SysLogs sysLogs = method.getAnnotation(SysLogs.class);
            return sysLogs.value();
        }
        return "??????";
    }

    private String paramFilter(Object[] params){
        //????????????????????????????????????
        final String filterString = "******";
        if(params.length>0){
            for (int i = 0; i < params.length; i++) {
                if(params[i] instanceof SignInDTO){
                    SignInDTO sign = (SignInDTO) params[i];
                    sign.setPassword(filterString);
                    params[i] = sign;
                }
                if(params[i] instanceof UserAddDTO){
                    UserAddDTO userAddDTO = (UserAddDTO) params[i];
                    userAddDTO.setPassword(filterString);
                    params[i] = userAddDTO;
                }
                if(params[i] instanceof ResetPasswordDTO){
                    ResetPasswordDTO resetPasswordDTO = (ResetPasswordDTO) params[i];
                    resetPasswordDTO.setPassword(filterString);
                    params[i] = resetPasswordDTO;
                }
            }
        }
        return JSON.toJSONString(params);
    }


}
