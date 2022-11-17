package pl.wojtyna.trainings.spring.crowdsorcery.borrower.jpa;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import pl.wojtyna.trainings.spring.crowdsorcery.borrower.Borrower;
import pl.wojtyna.trainings.spring.crowdsorcery.borrower.BorrowerRepository;

public class InitialBorrowersPopulator implements ApplicationRunner {

    private final BorrowerRepository borrowerRepository;

    public InitialBorrowersPopulator(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        borrowerRepository.save(new Borrower("123", "George Borrower"));
        borrowerRepository.save(new Borrower("456", "Henry Borrower"));
        borrowerRepository.save(new Borrower("789", "Martin Borrower"));
    }
}
