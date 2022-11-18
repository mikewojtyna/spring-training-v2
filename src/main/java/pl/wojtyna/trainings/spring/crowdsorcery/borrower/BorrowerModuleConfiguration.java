package pl.wojtyna.trainings.spring.crowdsorcery.borrower;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wojtyna.trainings.spring.crowdsorcery.borrower.mongo.MongoSpringBorrowerRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.borrower.mongo.MongoSpringDataBackedBorrowerRepository;

@Configuration
public class BorrowerModuleConfiguration {

    @Bean
    public InitialBorrowersPopulator initialBorrowersPopulator(BorrowerRepository borrowerRepository) {
        return new InitialBorrowersPopulator(borrowerRepository);
    }

    @Bean
    public BorrowerRepository borrowerRepository(MongoSpringBorrowerRepository repository) {
        return new MongoSpringDataBackedBorrowerRepository(repository);
    }

    @Bean
    public BorrowerService borrowerService(BorrowerRepository repository) {
        return new BorrowerService(repository);
    }
}
