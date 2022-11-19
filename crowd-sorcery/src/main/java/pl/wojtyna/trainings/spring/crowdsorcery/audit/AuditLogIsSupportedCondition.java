package pl.wojtyna.trainings.spring.crowdsorcery.audit;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class AuditLogIsSupportedCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        var auditLogPath = context.getEnvironment().getProperty("audit-log.path");
        return auditLogPath != null && !auditLogPath.isBlank();
    }
}
