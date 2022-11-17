package pl.wojtyna.trainings.spring.crowdsorcery.notification;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.InvestorRegistered;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.InvestorService;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.RegisterInvestor;
import pl.wojtyna.trainings.spring.crowdsorcery.testutils.CrowdSorceryTestBase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class WhenInvestorIsRegisteredThenNotificationIsSentTest extends CrowdSorceryTestBase {

    @Autowired
    private InvestorService investorService;
    @MockBean(NotificationService.class)
    private NotificationService notificationServiceMock;

    @DisplayName(
        """
         when investor is registered then notification is sent
        """
    )
    // @formatter:on
    @Test
    void test() {
        // when
        investorService.register(new RegisterInvestor("456", "George"));

        // then
        verify(notificationServiceMock).notifyAbout(any(InvestorRegistered.class));
    }
}
