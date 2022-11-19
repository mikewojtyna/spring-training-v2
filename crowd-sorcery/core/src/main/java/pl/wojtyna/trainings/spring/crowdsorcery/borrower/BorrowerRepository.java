package pl.wojtyna.trainings.spring.crowdsorcery.borrower;

import java.util.List;

public interface BorrowerRepository {

    void save(Borrower borrower);

    List<Borrower> findAll();
}
