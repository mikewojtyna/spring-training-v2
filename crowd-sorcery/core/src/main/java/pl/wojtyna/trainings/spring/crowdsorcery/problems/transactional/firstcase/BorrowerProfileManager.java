package pl.wojtyna.trainings.spring.crowdsorcery.problems.transactional.firstcase;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class BorrowerProfileManager {

    private final BorrowerProfileProblemRepository borrowerProfileRepository;

    public BorrowerProfileManager(BorrowerProfileProblemRepository borrowerProfileRepository) {this.borrowerProfileRepository = borrowerProfileRepository;}

    @Transactional
    public void save(BorrowerProfile profile) {
        borrowerProfileRepository.save(profile);
    }

    @Transactional
    public void update(BorrowerProfile newProfile) {
        borrowerProfileRepository.findById(newProfile.getId())
                                 .ifPresent(oldProfile -> oldProfile.setName(newProfile.getName()));
    }

    public Optional<BorrowerProfile> find(String id) {
        return borrowerProfileRepository.findById(id);
    }
}
