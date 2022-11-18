package pl.wojtyna.trainings.spring.crowdsorcery.newletter;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import pl.wojtyna.trainings.spring.crowdsorcery.borrower.BorrowerRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.InvestorRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.mail.EmailAddressResolver;

public class MailingList implements NewsletterService {

    private final EmailAddressResolver emailAddressResolver;
    private final MailSender mailSender;
    private final BorrowerRepository borrowerRepository;
    private final InvestorRepository investorRepository;

    public MailingList(EmailAddressResolver emailAddressResolver, MailSender mailSender,
                       BorrowerRepository borrowerRepository,
                       InvestorRepository investorRepository) {
        this.emailAddressResolver = emailAddressResolver;
        this.mailSender = mailSender;
        this.borrowerRepository = borrowerRepository;
        this.investorRepository = investorRepository;
    }

    @Override
    public void sendBorrowerNewsletter() {
        borrowerRepository.findAll().forEach(borrower -> {
            var address = emailAddressResolver.resolveAddressOf(borrower);
            var message = new SimpleMailMessage();
            message.setSubject("Your borrower newsletter is ready");
            message.setText("Some interesting stuff for borrowers");
            message.setTo(address);
            mailSender.send(message);
        });
    }

    @Override
    public void sendInvestorNewsletter() {
        investorRepository.findAll().forEach(investor -> {
            var address = emailAddressResolver.resolveAddressOf(investor);
            var message = new SimpleMailMessage();
            message.setSubject("Your investor newsletter is ready");
            message.setText("Some interesting stuff for investors");
            message.setTo(address);
            mailSender.send(message);
        });
    }
}
