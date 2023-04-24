package pl.wojtyna.trainings.spring.crowdsorcery.problems.transactional.secondcase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BorrowerProfileProblemsSecondCaseTest {

    @Autowired
    private BorrowerProfileManager profileManager;

    @Test
    @Transactional
    void borrowerSavedTest() {
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
        // TODO: now, try to run `BorrowerProfileProblemsSecondCaseRestApiTest`. Did everything go as intended?
    }
}
