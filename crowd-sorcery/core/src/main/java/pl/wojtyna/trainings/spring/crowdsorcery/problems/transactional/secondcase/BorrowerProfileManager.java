package pl.wojtyna.trainings.spring.crowdsorcery.problems.transactional.secondcase;

import java.util.Optional;

public class BorrowerProfileManager {

    private final BorrowerProfileSecondCaseProblemRepository borrowerProfileRepository;

    public BorrowerProfileManager(BorrowerProfileSecondCaseProblemRepository borrowerProfileRepository) {this.borrowerProfileRepository = borrowerProfileRepository;}

    public void save(BorrowerProfile profile) {
        borrowerProfileRepository.save(profile);
    }

    public void update(BorrowerProfile newProfile) {
        borrowerProfileRepository.findById(newProfile.getId())
                                 .ifPresent(oldProfile -> oldProfile.setName(newProfile.getName()));
    }

    public Optional<BorrowerProfile> find(String id) {
        return borrowerProfileRepository.findById(id);
    }
}
