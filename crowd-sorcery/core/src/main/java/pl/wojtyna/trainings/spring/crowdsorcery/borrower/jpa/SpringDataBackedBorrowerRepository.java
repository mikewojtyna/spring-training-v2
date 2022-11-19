package pl.wojtyna.trainings.spring.crowdsorcery.borrower.jpa;

import pl.wojtyna.trainings.spring.crowdsorcery.borrower.Borrower;
import pl.wojtyna.trainings.spring.crowdsorcery.borrower.BorrowerRepository;

import java.util.List;

public class SpringDataBackedBorrowerRepository implements BorrowerRepository {

    private final SpringBorrowerEntityRepository springRepo;

    public SpringDataBackedBorrowerRepository(SpringBorrowerEntityRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public void save(Borrower borrower) {
        var borrowerEntity = new BorrowerEntity();
        borrowerEntity.setId(borrower.id());
        borrowerEntity.setName(borrower.name());
        springRepo.save(borrowerEntity);
    }

    @Override
    public List<Borrower> findAll() {
        return springRepo.findAll().stream().map(borrowerEntity -> new Borrower(
            borrowerEntity.getId(), borrowerEntity.getName())).toList();
    }
}
