package pl.wojtyna.trainings.spring.crowdsorcery.problems.transactional.firstcase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BorrowerProfileProblemsFirstCaseTest {

    @Autowired
    private BorrowerProfileManager profileManager;

    @Test
    void borrowerNotUpdatedTest() {
        var borrowerProfile = new BorrowerProfile();
        borrowerProfile.setId(UUID.randomUUID().toString());
        borrowerProfile.setName("George");
        profileManager.save(borrowerProfile);
        assertThat(profileManager.find(borrowerProfile.getId())).isPresent();

        var newBorrowerProfile = new BorrowerProfile();
        newBorrowerProfile.setId(borrowerProfile.getId());
        newBorrowerProfile.setName("Martin");
        profileManager.update(newBorrowerProfile);

        assertThat(profileManager.find(borrowerProfile.getId())).hasValueSatisfying(profile -> assertThat(profile.getName()).isEqualTo(
            "Martin"));
    }
}
