package pl.wojtyna.trainings.spring.crowdsorcery.notification;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import pl.wojtyna.trainings.spring.crowdsorcery.CrowdSorceryRootContextConfiguration;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.InvestorRegistered;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.InvestorService;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.RegisterInvestor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WhenInvestorIsRegisteredThenNotificationIsSentTest {

    // @formatter:off
    @DisplayName(
        """
         when investor is registered then notification is sent
        """
    )
    // @formatter:on
    @Test
    void test() {
        // given
        var notificationServiceMock = mock(NotificationService.class);
        var context = new SpringApplicationBuilder().parent(CrowdSorceryRootContextConfiguration.class)
                                                    .properties("spring.main.allow-bean-definition-overriding=true")
                                                    .child(NotificationModuleConfiguration.class)
                                                    .initializers((ApplicationContextInitializer<GenericApplicationContext>) applicationContext -> {
                                                        applicationContext.registerBean(
                                                            NotificationService.class,
                                                            () -> notificationServiceMock);
                                                    })
                                                    .run("-id=123", "-name=Henry");
        var investorService = context.getBean(InvestorService.class);

        // when
        investorService.register(new RegisterInvestor("456", "George"));

        // then
        verify(notificationServiceMock).notifyAbout(any(InvestorRegistered.class));
    }
}
