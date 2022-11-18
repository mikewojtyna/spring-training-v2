package pl.wojtyna.trainings.spring.crowdsorcery.newsletter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import pl.wojtyna.trainings.spring.crowdsorcery.newletter.NewsletterService;
import pl.wojtyna.trainings.spring.crowdsorcery.testutils.CrowdSorceryTestBase;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@DisplayName("Newsletter schedule rules")
@SpringBootTest
@TestPropertySource(properties = "crowd-sorcery.newsletter.schedule=* * * * * *")
class NewsletterScheduleTest extends CrowdSorceryTestBase {

    @MockBean
    private NewsletterService newsletterService;

    // @formatter:off
    @DisplayName(
        """
         given newsletter is sent every second,
         when wait for 5 seconds,
         then newsletter is sent to borrowers and investors at least 3 times
        """
    )
    // @formatter:on
    @Test
    void test() {
        // given
        // newsletter schedule is configured at class level, so beans can be instantiated correctly at startup

        // when
        waitForFiveSeconds();

        // then
        verify(newsletterService, atLeast(3)).sendInvestorNewsletter();
        verify(newsletterService, atLeast(3)).sendBorrowerNewsletter();
    }

    private void waitForFiveSeconds() {
        try {
            Thread.sleep(5_000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
