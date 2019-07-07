package com.yautumn.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;

@Aspect
@Component
public class CustomAspect {
	
	private static Logger LOG = LoggerFactory.getLogger(CustomAspect.class);
    
    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(CustomLog)")
    public void logPoinCut() {
    }
   
    //@Around：环绕通知
    @Around("logPoinCut()")
    public Object saveSysLog(ProceedingJoinPoint proceedingJoinPoint) {

        System.out.println("环绕通知开始。。。。。");
        //保存日志
        CustomInfo customInfo = new CustomInfo();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
       //获取操作
        CustomLog customLog = method.getAnnotation(CustomLog.class);
        if (customLog != null) {
            String value = customLog.message();
            customInfo.setMessage(value);//保存获取的操作
        }
        //获取请求的类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        customInfo.setMethod(className + "." + methodName);

        //请求的参数
        Object[] args = proceedingJoinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSON.toJSONString(args);
        customInfo.setParams(params);

        customInfo.setCreateDate(new Date());
        //获取用户名
        //获取用户ip地址
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        LOG.info("URL : " + request.getRequestURL().toString());
        LOG.info("HTTP_METHOD : " + request.getMethod());
        LOG.info("IP : " + request.getRemoteAddr());
        customInfo.setIp(request.getRemoteAddr());

        //开始调用时间
        // 计时并调用目标函数
        long start = System.currentTimeMillis();
        Long time = System.currentTimeMillis() - start;
        customInfo.setTotalTime(time);

        //调用service保存SysLog实体类到数据库
        //sysLogService.save(sysLog);
        try {
             Object result = proceedingJoinPoint.proceed();
            System.out.println("    "+result.toString());
            System.out.println("环绕通知结束。。。。。");
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
		return "系统异常";
    }
}
