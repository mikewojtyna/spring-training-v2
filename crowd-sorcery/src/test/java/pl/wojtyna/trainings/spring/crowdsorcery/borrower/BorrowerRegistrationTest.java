package pl.wojtyna.trainings.spring.crowdsorcery.borrower;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wojtyna.trainings.spring.crowdsorcery.testutils.CrowdSorceryTestBase;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Borrower registration examples")
@SpringBootTest
class BorrowerRegistrationTest extends CrowdSorceryTestBase {

    @Autowired
    private BorrowerService borrowerService;

    // @formatter:off
    @DisplayName("""
                  borrower can be registered
                 """)
    // @formatter:on
    @Test
    void test() {
        // given
        var id = UUID.randomUUID().toString();
        var borrowerInput = new Borrower(id, "George The Borrower");

        // when
        borrowerService.register(borrowerInput);

        // then
        var foundBorrowers = borrowerService.findAll();
        assertThat(foundBorrowers).anySatisfy(borrower -> {
            assertThat(borrower.id()).isEqualTo(id);
            assertThat(borrower.name()).isEqualTo("George The Borrower");
        });
    }
}
