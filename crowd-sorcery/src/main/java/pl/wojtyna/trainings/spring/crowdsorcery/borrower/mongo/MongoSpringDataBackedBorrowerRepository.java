package pl.wojtyna.trainings.spring.crowdsorcery.borrower.mongo;

import pl.wojtyna.trainings.spring.crowdsorcery.borrower.Borrower;
import pl.wojtyna.trainings.spring.crowdsorcery.borrower.BorrowerRepository;

import java.util.List;

public class MongoSpringDataBackedBorrowerRepository implements BorrowerRepository {

    private final MongoSpringBorrowerRepository springRepository;

    public MongoSpringDataBackedBorrowerRepository(MongoSpringBorrowerRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public void save(Borrower borrower) {
        springRepository.save(borrower);
    }

    @Override
    public List<Borrower> findAll() {
        return springRepository.findAll();
    }
}
