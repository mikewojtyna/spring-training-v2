package pl.wojtyna.trainings.spring.crowdsorcery.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditLogModuleConfiguration {

    @Bean
    @Conditional(AuditLogIsSupportedCondition.class)
    public AuditLog auditLog() {
        return new InMemoryAuditLog();
    }
}
