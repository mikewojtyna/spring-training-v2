package pl.wojtyna.trainings.spring.crowdsorcery.testutils;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class CrowdSorceryTestBase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    protected boolean shouldCleanStateBeforeEachTest() {
        return true;
    }

    @BeforeEach
    void cleanDb() {
        if (shouldCleanStateBeforeEachTest()) {
            jdbcTemplate.batchUpdate("DELETE FROM investor_profiles", "DELETE FROM investors");
        }
    }
}
