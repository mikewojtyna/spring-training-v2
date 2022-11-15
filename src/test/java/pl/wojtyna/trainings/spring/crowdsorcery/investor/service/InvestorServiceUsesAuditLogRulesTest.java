package pl.wojtyna.trainings.spring.crowdsorcery.investor.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import pl.wojtyna.trainings.spring.crowdsorcery.audit.AuditLog;
import pl.wojtyna.trainings.spring.crowdsorcery.testutils.CrowdSorceryTestBase;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = "audit-log.path=path/to/audit/log")
class InvestorServiceUsesAuditLogRulesTest extends CrowdSorceryTestBase {

    @Autowired
    private InvestorService investorService;
    @Autowired
    private AuditLog auditLog;

    @DisplayName(
        """
         investor service uses audit log when audit-log.path property is not empty
        """
    )
    // @formatter:on
    @Test
    void test0() {
        var command = new RegisterInvestor("123", "George");
        investorService.register(command);
        assertThat(auditLog.entries()).hasSize(1).anySatisfy(commandEventAuditLogEntry -> {
            assertThat(commandEventAuditLogEntry.command()).isEqualTo(command);
            assertThat(commandEventAuditLogEntry.event()).isInstanceOf(InvestorRegistered.class);
        });
    }
}
