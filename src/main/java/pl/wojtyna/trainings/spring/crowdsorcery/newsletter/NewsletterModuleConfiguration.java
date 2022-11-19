package pl.wojtyna.trainings.spring.crowdsorcery.newsletter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.wojtyna.trainings.spring.crowdsorcery.borrower.BorrowerRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.InvestorRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.mail.EmailAddressResolver;

@Configuration
@EnableScheduling
public class NewsletterModuleConfiguration {

    @Bean
    public NewsletterScheduler newsletterScheduler(NewsletterService newsletterService) {
        return new NewsletterScheduler(newsletterService);
    }

    @Bean
    public NewsletterService newsletterService(EmailAddressResolver emailAddressResolver, MailSender mailSender,
                                               BorrowerRepository borrowerRepository,
                                               InvestorRepository investorRepository) {
        return new MailingList(emailAddressResolver, mailSender, borrowerRepository, investorRepository);
    }
}
