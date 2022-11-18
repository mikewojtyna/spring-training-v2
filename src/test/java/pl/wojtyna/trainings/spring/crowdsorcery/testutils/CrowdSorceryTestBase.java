package pl.wojtyna.trainings.spring.crowdsorcery.testutils;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import pl.wojtyna.trainings.spring.crowdsorcery.borrower.Borrower;
import pl.wojtyna.trainings.spring.crowdsorcery.borrower.InitialBorrowersPopulator;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.InitialInvestorsPopulator;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;

@ActiveProfiles("test")
public class CrowdSorceryTestBase {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private InitialBorrowersPopulator initialBorrowersPopulator;
    @Autowired
    private InitialInvestorsPopulator initialInvestorsPopulator;

    protected void cleanDb() {
        jdbcTemplate.batchUpdate("DELETE FROM investor_profiles WHERE is_default = FALSE",
                                 "DELETE FROM investors WHERE is_default = FALSE");
        jdbcTemplate.batchUpdate("DELETE FROM borrowers WHERE is_default = FALSE");
        mongoTemplate.dropCollection(Investor.class);
        mongoTemplate.dropCollection(Borrower.class);
    }

    @BeforeEach
    private void cleanDbAndRepopulateWithInitialData() throws Exception {
        cleanDb();
        // recreate initial db content
        initialInvestorsPopulator.run(new DefaultApplicationArguments());
        initialBorrowersPopulator.run(new DefaultApplicationArguments());
    }
}
