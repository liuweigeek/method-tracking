package com.scott.spring.methodtracking.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: scott
 * @Email: <a href="liuweigeek@outlook.com">scott</a>
 * @Date: 2020/6/6 17:27
 * @Description:
 */
@Slf4j
@Aspect
@Component
@ConditionalOnProperty(prefix = "method-tracker", name = "enable", havingValue = "true")
public class MethodTrackerAop {

    private final Integer logLineLength;

    public MethodTrackerAop(@Value("${method-tracker.log-line-length}") Integer logLineLength) {
        this.logLineLength = logLineLength;
    }

    private final ThreadLocal<Integer> calledLevel = ThreadLocal.withInitial(() -> 0);
    private final ThreadLocal<List<String>> logList = ThreadLocal.withInitial(ArrayList::new);

    //@Around("@within(com.scott.spring.methodtracking.annotation.TimeTrack) || @annotation(com.scott.spring.methodtracking.annotation.TimeTrack)")
    @Around("execution(* com.scott.spring.methodtracking..*.*(..))")
    public Object timeTracker(ProceedingJoinPoint point) throws Throwable {

        long startTime = System.currentTimeMillis();
        calledLevel.set(calledLevel.get() + 1);
        int index = logList.get().size();
        logList.get().add(null);
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        Object[] parameters = point.getArgs();
        try {
            return point.proceed();
        } finally {
            calledLevel.set(calledLevel.get() - 1);
            logList.get().set(index, generateLog(method, parameters, startTime, System.currentTimeMillis(), calledLevel.get()));
            if (calledLevel.get() == 0) {
                printLog();
            }
        }
    }

    private String generateLog(Method method, Object[] parameters, long startTime, long endTime, int callLevel) {
        String methodStr = callLevel == 0 ?
                String.format("| %s", method.getDeclaringClass().getName() + "." + method.getName() + "(" + StringUtils.join(parameters, ", ") + ");")
                :
                String.format("| %s\\- %s", StringUtils.repeat("  ", callLevel),
                        method.getDeclaringClass().getName() + "." + method.getName() + "(" + StringUtils.join(parameters, ", ") + ");");

        return methodStr + StringUtils.leftPad("[" + (endTime - startTime) + "ms] ", logLineLength - 1 - methodStr.length()) + "|";
    }

    private void printLog() {
        System.out.println();
        log.info(StringUtils.repeat("=", logLineLength));
        logList.get().forEach(log::info);
        log.info(StringUtils.repeat("=", logLineLength) + "\n");
        logList.get().clear();
    }
}
