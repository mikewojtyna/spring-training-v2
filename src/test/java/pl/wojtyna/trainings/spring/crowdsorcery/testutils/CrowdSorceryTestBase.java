package pl.wojtyna.trainings.spring.crowdsorcery.testutils;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class CrowdSorceryTestBase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void cleanDb() {
        jdbcTemplate.batchUpdate("DELETE FROM investor_profiles WHERE is_default = FALSE",
                                 "DELETE FROM investors WHERE is_default = FALSE");
        jdbcTemplate.batchUpdate("DELETE FROM borrowers WHERE is_default = FALSE");
    }
}
