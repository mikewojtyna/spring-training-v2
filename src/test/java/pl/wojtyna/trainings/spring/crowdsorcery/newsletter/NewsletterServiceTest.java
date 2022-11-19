package pl.wojtyna.trainings.spring.crowdsorcery.newsletter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import pl.wojtyna.trainings.spring.crowdsorcery.borrower.Borrower;
import pl.wojtyna.trainings.spring.crowdsorcery.borrower.BorrowerService;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.InvestorService;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.RegisterInvestor;
import pl.wojtyna.trainings.spring.crowdsorcery.testutils.CrowdSorceryTestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Newsletter send rules")
@SpringBootTest
@MockBean(NewsletterScheduleTest.class) // disabling newsletter scheduler, so it doesn't interfere with us
class NewsletterServiceTest extends CrowdSorceryTestBase {

    @Autowired
    private InvestorService investorService;
    @Autowired
    private BorrowerService borrowerService;
    @Autowired
    private NewsletterService newsletterService;
    @Autowired
    private TestMailSender mailSender;

    @TestConfiguration
    public static class MailSenderTestConfiguration {

        @Bean
        public TestMailSender mailSender() {
            return new TestMailSender();
        }
    }

    // @formatter:off
    @DisplayName(
        """
         given one investor and one borrower,
         when send borrower newsletter,
         then mail is sent to this borrower only
        """
    )
    // @formatter:on
    @Test
    void test() {
        // given
        cleanDb();
        mailSender.reset();
        investorService.register(new RegisterInvestor(UUID.randomUUID().toString(), "George", 0));
        borrowerService.register(new Borrower(UUID.randomUUID().toString(), "Martin"));

        // when
        newsletterService.sendBorrowerNewsletter();

        // then
        assertThat(mailSender.getSentMessages()).hasSize(1)
                                                .anySatisfy(simpleMailMessage -> assertThat(simpleMailMessage.getTo()[0]).isEqualTo(
                                                    "Martin@wojtyna.pl"));
    }

    // @formatter:off
    @DisplayName(
        """
         given one investor and one borrower,
         when send investor newsletter,
         then mail is sent to this investor only
        """
    )
    // @formatter:on
    @Test
    void test2() {
        // given
        cleanDb();
        mailSender.reset();
        investorService.register(new RegisterInvestor(UUID.randomUUID().toString(), "George", 0));
        borrowerService.register(new Borrower(UUID.randomUUID().toString(), "Martin"));

        // when
        newsletterService.sendInvestorNewsletter();

        // then
        assertThat(mailSender.getSentMessages()).hasSize(1)
                                                .anySatisfy(simpleMailMessage -> assertThat(simpleMailMessage.getTo()[0]).isEqualTo(
                                                    "George@wojtyna.pl"));
    }

    private static class TestMailSender implements MailSender {

        private final List<SimpleMailMessage> sentMessages = new ArrayList<>();

        public List<SimpleMailMessage> getSentMessages() {
            return sentMessages;
        }

        public void reset() {
            sentMessages.clear();
        }

        @Override
        public void send(SimpleMailMessage simpleMessage) throws MailException {
            sentMessages.add(simpleMessage);
        }

        @Override
        public void send(SimpleMailMessage... simpleMessages) throws MailException {
            for (SimpleMailMessage simpleMessage : simpleMessages) {
                send(simpleMessage);
            }
        }
    }
}
