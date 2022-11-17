package pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.mongo;

import org.springframework.beans.factory.ObjectProvider;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.InvestorRepository;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;

import java.util.stream.Stream;

public class AdvancedNameFinderRepositoryImpl implements AdvancedNameFinderRepository {

    private final ObjectProvider<InvestorRepository> repositoryProvider;

    public AdvancedNameFinderRepositoryImpl(ObjectProvider<InvestorRepository> repositoryProvider) {
        this.repositoryProvider = repositoryProvider;
    }

    @Override
    public Stream<Investor> findAllByNameLength(int length) {
        // Inefficient, but works. Please note, that custom fragment implementation doesn't need to use the same database underneath. In fact, it doesn't have to use any database at all!
        var repository = repositoryProvider.getIfUnique();
        if (repository == null) {
            throw new RuntimeException("There's no available investor repository");
        }
        return repository.findAll().stream().filter(investor -> investor.name().length() == length);
    }
}
