package pl.wojtyna.trainings.spring.crowdsorcery.borrower;

import java.util.List;

public class BorrowerService {

    private final BorrowerRepository borrowerRepository;

    public BorrowerService(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    public void register(Borrower borrower) {
        borrowerRepository.save(borrower);
    }

    public List<Borrower> findAll() {
        return borrowerRepository.findAll();
    }
}
