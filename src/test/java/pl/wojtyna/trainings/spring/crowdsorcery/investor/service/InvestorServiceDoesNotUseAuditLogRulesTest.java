package pl.wojtyna.trainings.spring.crowdsorcery.investor.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import pl.wojtyna.trainings.spring.crowdsorcery.audit.AuditLog;
import pl.wojtyna.trainings.spring.crowdsorcery.testutils.CrowdSorceryTestBase;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class InvestorServiceDoesNotUseAuditLogRulesTest extends CrowdSorceryTestBase {

    @Autowired
    private InvestorService investorService;
    @Autowired
    private ApplicationContext applicationContext;

    // @formatter:off
    @DisplayName("""
                  investor service doesn't use use audit log when audit-log.path property is not set
                  audit log is not even registered in the container
                 """)
    // @formatter:on
    @Test
    void test0() {
        var command = new RegisterInvestor("123", "George");
        investorService.register(command);
        assertThatThrownBy(() -> applicationContext.getBean(AuditLog.class)).isInstanceOf(NoSuchBeanDefinitionException.class);
    }
}
