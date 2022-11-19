package pl.wojtyna.trainings.spring.crowdsorcery.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@Slf4j
public class LoggingAspect {

    @Before("pl.wojtyna.trainings.spring.crowdsorcery.aspects.InputPointcuts.allInputs()")
    public void logInput(JoinPoint joinPoint) {
        var signature = joinPoint.getSignature();
        log.info("[INPUT] Calling method {}.{}({})",
                 signature.getDeclaringTypeName(),
                 signature.getName(),
                 joinPoint.getArgs());
    }

    @Before("pl.wojtyna.trainings.spring.crowdsorcery.aspects.OutputPointcuts.allOutputs()")
    public void logOutput(JoinPoint joinPoint) {
        var signature = joinPoint.getSignature();
        log.info("[OUTPUT] Calling method {}.{}({})",
                 signature.getDeclaringTypeName(),
                 signature.getName(),
                 joinPoint.getArgs());
    }
}
