package ru.vita.service.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Aspect
@Component
public class FirstAspect {

    @Pointcut("within(ru.sbercraft.service.service..*)")
    public void isServiceLayer() {
    }

    @Around("isServiceLayer() && target(service)")
    public Object addLoggingAround(ProceedingJoinPoint joinPoint, Object service) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        String params = IntStream.range(0, args.length)
                .mapToObj(i -> String.format("arg[%d]=%s", i, args[i] != null ? args[i] : "null"))
                .collect(Collectors.joining(", "));

        log.info("Method: {} | Parameters: {}", methodName, args.length > 0 ? params : "No parameters");

        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
            return proceed;
        } finally {
            log.info("Сервис: {}, Метод: {}, Возвращаемое значение: {}",
                    service.getClass().getSimpleName(),
                    methodName,
                    proceed);
        }
    }

    //оставил для себя
    @Before("isServiceLayer()")
    public void addLogging(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        String params = IntStream.range(0, args.length)
                .mapToObj(i -> String.format("arg[%d]=%s", i, args[i] != null ? args[i] : "null"))
                .collect(Collectors.joining(", "));

        log.info("Method: {} | Parameters: {}", methodName, args.length > 0 ? params : "No parameters");
    }

    @AfterReturning(value = "isServiceLayer() && target(service)", returning = "result")
    public void addLoggingAfterRetuning(JoinPoint joinPoint, Object result, Object service) {
        String methodName = joinPoint.getSignature().getName();

    }
}
