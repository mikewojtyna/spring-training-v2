package pl.wojtyna.trainings.spring.crowdsorcery.borrower;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wojtyna.trainings.spring.crowdsorcery.borrower.jpa.InitialBorrowersPopulator;
import pl.wojtyna.trainings.spring.crowdsorcery.borrower.jpa.JpaBorrowerRepository;

import javax.persistence.EntityManager;

@Configuration
public class BorrowerModuleConfiguration {

    @Bean
    public InitialBorrowersPopulator initialBorrowersPopulator(BorrowerRepository borrowerRepository) {
        return new InitialBorrowersPopulator(borrowerRepository);
    }

    @Bean
    public BorrowerRepository borrowerRepository(EntityManager entityManager) {
        return new JpaBorrowerRepository(entityManager);
    }

    @Bean
    public BorrowerService borrowerService(BorrowerRepository repository) {
        return new BorrowerService(repository);
    }
}
