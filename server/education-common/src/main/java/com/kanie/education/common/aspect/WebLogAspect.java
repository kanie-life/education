package com.kanie.education.common.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import com.kanie.education.common.api.WebLogProperties;
import io.swagger.annotations.ApiOperation;
import net.logstash.logback.marker.Markers;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统一访问记录日志切面
 * @author ：kanie
 * @date ：Created in 2020/10/31 11:32
 */
@Aspect
@Component
//标记定义的组件加载优先级，值越小优先级越高
@Order(1)
public class WebLogAspect {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * com.kanie.education.*.*.controller.*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

    }

    @AfterReturning(value = "webLog()", returning = "obj")
    public void doAfterReturning(Object obj) throws Throwable {

    }

    //@Around注解可以用来在调用一个具体方法前和调用后来完成一些具体的任务。
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //记录请求日志
        WebLogProperties webLogProperties = new WebLogProperties();
        Object result = proceedingJoinPoint.proceed();
        MethodSignature methodSignature = (MethodSignature)proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation annotation = method.getAnnotation(ApiOperation.class);
            webLogProperties.setDescription(annotation.value());
        }
        long endTime = System.currentTimeMillis();
        String requestURL = request.getRequestURL().toString();
        webLogProperties.setBasePath(StrUtil.removeSuffix(requestURL, URLUtil.url(requestURL).getPath()));
        webLogProperties.setIp(request.getRemoteUser());
        webLogProperties.setMethod(request.getMethod());
        webLogProperties.setParameter(this.getParam(method, proceedingJoinPoint.getArgs()));
        webLogProperties.setResult(result);
        webLogProperties.setSpendTime((int)(endTime - startTime));
        webLogProperties.setStartTime(startTime);
        webLogProperties.setUri(request.getRequestURI());
        webLogProperties.setUrl(request.getRequestURL().toString());
        HashMap<String, Object> logMap = new HashMap<>();
        logMap.put("url", webLogProperties.getUrl());
        logMap.put("method", webLogProperties.getMethod());
        logMap.put("parameter", webLogProperties.getParameter());
        logMap.put("spendTime", webLogProperties.getSpendTime());
        logMap.put("description", webLogProperties.getDescription());
        LOGGER.info(Markers.appendEntries(logMap), JSONUtil.parse(webLogProperties).toString());
        return result;
    }

    /**
     * 根据方法和传入的参数获取请求参数
     */
    private Object getParam(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.size() == 0) {
            return null;
        } else if (argList.size() == 1) {
            return argList.get(0);
        } else {
            return argList;
        }
    }
}
