package pl.wojtyna.trainings.spring.crowdsorcery.newsletter;

import org.springframework.scheduling.annotation.Scheduled;

public class NewsletterScheduler {

    private final NewsletterService newsletterService;

    public NewsletterScheduler(NewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }

    @Scheduled(cron = "${crowd-sorcery.newsletter.schedule:0 0 0 * * 1}")
    public void sendNewsletters() {
        newsletterService.sendBorrowerNewsletter();
        newsletterService.sendInvestorNewsletter();
    }
}
